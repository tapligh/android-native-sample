package com.tapligh.android.sample_native.view.displayTapligh;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.tapligh.android.sample_native.AppNavigator;
import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.data.Model;
import com.tapligh.android.sample_native.view.TAPLIGH_TYPE;
import com.tapligh.sdk.nativead.AdViewBindingOption;
import com.tapligh.sdk.nativead.AspectRatio;
import com.tapligh.sdk.nativead.BadgePosition;
import com.tapligh.sdk.nativead.LoadErrorStatus;
import com.tapligh.sdk.nativead.NativeAdLoader;
import com.tapligh.sdk.nativead.NativeAdLoadingListener;
import com.tapligh.sdk.nativead.TaplighNativeAd;
import com.tapligh.sdk.nativead.TaplighNativeAdView;
import com.tapligh.sdk.nativead.list.AdPlacementStrategy;
import com.tapligh.sdk.nativead.list.TaplighNativeRenderer;
import com.tapligh.sdk.nativead.list.TaplighRecyclerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.tapligh.android.sample_native.util.Constants.UNIT_CODE;

/**
 * Created by Ehsan Abbasi for public-android-native-sample at 5/28/19
 */
public class DisplayTaplighFragment extends Fragment {
  private static final String TAG = "DisplayTaplighFragment";
  private AppNavigator navigator;
  private static String EXTRA_KEY_LAYOUT = "extra_key_layout";
  private static String EXTRA_KEY_TAPLIGH_TYPE = "extra_key_tapligh_type";
  private ViewGroup view;
  private int fragment_layout;
  private TAPLIGH_TYPE taplighType;

  private boolean isLoading = false;

  private DefaultListAdapter adapter;
  private TaplighRecyclerAdapter taplighAdapter;

  private TaplighNativeAdView nativeAdView;

  private ArrayList<String> items;

  private int DATA_SIZE = 50;
  private final int AD_INTERVAL = 6;
  private int page = 1;
  private final int SPAN_SIZE = 6; // 6


  public static DisplayTaplighFragment instance(int childLayout, TAPLIGH_TYPE taplighType) {

    Bundle arg = new Bundle();
    arg.putInt(EXTRA_KEY_LAYOUT, childLayout);
    arg.putSerializable(EXTRA_KEY_TAPLIGH_TYPE, taplighType);

    DisplayTaplighFragment fragment = new DisplayTaplighFragment();
    fragment.setArguments(arg);

    return fragment;
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {

      fragment_layout = getArguments().getInt(EXTRA_KEY_LAYOUT);
      taplighType = (TAPLIGH_TYPE) getArguments().getSerializable(EXTRA_KEY_TAPLIGH_TYPE);

    } else {
      throw new IllegalArgumentException("There must be some arguments here !!!");
    }

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = (ViewGroup) inflater.inflate(fragment_layout, container, false);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    displayAd();

  }

  public void getBitmapFromURL(final TaplighNativeAd ad, final Model model) {

    new Thread(new Runnable() {
      @Override
      public void run() {

        try {

          URL url = new URL(ad.getBanners().get(AspectRatio.AR16x9));
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setDoInput(true);
          connection.connect();
          InputStream input = connection.getInputStream();
          Bitmap myBitmap = BitmapFactory.decodeStream(input);

          new Palette.Builder(myBitmap)
            .generate(new Palette.PaletteAsyncListener() {
              @Override
              public void onGenerated(@Nullable Palette palette) {

                palette.getVibrantSwatch();
                palette.getLightVibrantSwatch();
                palette.getDarkVibrantSwatch();
                palette.getMutedSwatch();
                palette.getLightMutedSwatch();
                palette.getDarkMutedSwatch();


                nativeAdView = (TaplighNativeAdView) view.findViewById(R.id.native_ad_root);
                nativeAdView.setAdViewBinder(model.getAdBinding());
                nativeAdView.setAdPlacement(BadgePosition.top_left);

                AdViewBindingOption option = new AdViewBindingOption();
                option.setBannerAR(AspectRatio.AR16x9);
                model.getAdBinding().setBindingOption(option);

                nativeAdView.showAd(ad);
                nativeAdView.setVisibility(View.VISIBLE);

                Button btAction = view.findViewById(R.id.native_ad_action_button);
                btAction.setBackgroundColor(palette.getDarkVibrantColor(Color.YELLOW));
                btAction.setTextColor(palette.getLightMutedColor(Color.RED));

              }
            });


        } catch (IOException e) {
          // Log exception
        }

      }
    }).start();
  }


  /**
   * Create {@link Model} for selected Tapligh type
   * <p>
   * then try to show Tapligh base on it's type
   * Types are:
   * Dialog / List / Grid / Single
   */
  private void displayAd() {

    final Model model = Model.prepareModel(view, fragment_layout);

    if (model.isShowInDialog()) {

      showTaplighInDialog();

    } else if (model.isShowInList() || model.isShowInGrid()) {

      items = new ArrayList<>();

      RecyclerView reDefault = view.findViewById(R.id.rc_list);

      prepareTaplighList(reDefault, model);
      setupScrollListener(reDefault);

    } else {

      prepareTaplighSingle(model);

    }

  }

  private void prepareTaplighSingle(final Model model) {


    NativeAdLoader loader = new NativeAdLoader
      .Builder(getContext(), UNIT_CODE)
      .setRequestNumber(1)
      .setListener(new NativeAdLoadingListener() {
        @Override
        public void onAdLoaded(List<TaplighNativeAd> ads) {
          Log.e(TAG, "onAdLoaded() called with: ads = [" + ads.size() + "]");

          if (fragment_layout == R.layout.fragment_custom_2) {

            getBitmapFromURL(ads.get(0), model);

            return;
          }

          nativeAdView = (TaplighNativeAdView) view.findViewById(R.id.native_ad_root);
          nativeAdView.setAdViewBinder(model.getAdBinding());
          nativeAdView.setAdPlacement(BadgePosition.top_left);

          AdViewBindingOption option = new AdViewBindingOption();
          option.setBannerAR(AspectRatio.AR16x9);
          model.getAdBinding().setBindingOption(option);

          nativeAdView.showAd(ads.get(0));
          nativeAdView.setVisibility(View.VISIBLE);


          /**
           * Set {@link R.anim.heart_beat} animation for
           * {@link R.layout.fragment_custom_1} layout
           */
          if (fragment_layout == R.layout.fragment_custom_1) {

            Button actionBt = view.findViewById(R.id.native_ad_action_button);
            Animation pulse = AnimationUtils.loadAnimation(getContext(), R.anim.heart_beat);
            actionBt.startAnimation(pulse);

          }

          if (fragment_layout == R.layout.fragment_custom_3) {

            view.findViewById(R.id.root_custom_3).setVisibility(View.VISIBLE);

            view.findViewById(R.id.user_close).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                Toast.makeText(getContext(), "Do any thing!!!!", Toast.LENGTH_SHORT).show();

              }
            });
          }
        }

        @Override
        public void onLoadError(LoadErrorStatus status) {
          Log.e(TAG, "onLoadError() called with: status = [" + status + "]");
        }
      }).build();
    loader.loadAd();
  }

  private void setupScrollListener(RecyclerView reDefault) {

    reDefault.addOnScrollListener(new OnScrollListener() {

      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        if (dy > 0) {

          LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
          int visibleItemCount = linearLayoutManager.getChildCount();
          int totalItemCount = linearLayoutManager.getItemCount();
          int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

          if (isLoading) {
            return;
          }

          isLoading = true;

          page = adapter.getItemCount();

          if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {

            ArrayList<String> localItems = new ArrayList<>();

            for (int i = 0; i < DATA_SIZE; i++) {
              localItems.add("items inside at " + (i + 1 + page));
              items.add("items inside at " + (i + 1 + page));
            }

            adapter.addNewItems(localItems, items);
            taplighAdapter.loadAd(UNIT_CODE);

          }

          isLoading = false;

        }
      }
    });
  }


  /**
   * Generate sample data inorder to use in the list
   *
   * @param dataSize
   * @return
   */
  private ArrayList<String> getSampleList(int dataSize) {

    for (int i = 0; i < dataSize; i++) {
      items.add("items inside at " + (i * page + 1));
    }

    return items;
  }


  private void prepareTaplighList(RecyclerView reDefault, Model model) {

    if (fragment_layout == R.layout.fragment_list_1) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.LIST_1);

    } else if (fragment_layout == R.layout.fragment_list_3) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.LIST_3);


    } else if (fragment_layout == R.layout.fragment_list_6) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.LIST_6);

    } else if (fragment_layout == R.layout.fragment_list_9) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.LIST_9);

    } else if (fragment_layout == R.layout.fragment_list_13) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.LIST_13);

    } else if (fragment_layout == R.layout.fragment_grid_1) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.GRID_1);

    } else if (fragment_layout == R.layout.fragment_grid_2) {

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.GRID_2);

    } else if (fragment_layout == R.layout.fragment_grid_3) {

      GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_SIZE);
      layoutManager.setSpanSizeLookup(new MySpanSize());
      reDefault.setLayoutManager(layoutManager);

      adapter = new DefaultListAdapter(getSampleList(DATA_SIZE), DefaultListAdapter.LIST_TYPE.GRID_3);

    }


    AdPlacementStrategy strategy = new AdPlacementStrategy();
    strategy.enableRepeatingMode(AD_INTERVAL);

    taplighAdapter = new TaplighRecyclerAdapter(getActivity(), adapter, strategy);
    taplighAdapter.setAdRenderer(new TaplighNativeRenderer(model.getAdLayoutResId(), model.getAdRootResId(), model.getAdBinding())
      .setBadgePosition(BadgePosition.top_left));

    if (fragment_layout == R.layout.fragment_grid_2 || fragment_layout == R.layout.fragment_grid_3)
      taplighAdapter.showAdAsBanner(true);

    taplighAdapter.loadAd(UNIT_CODE);
    reDefault.setAdapter(taplighAdapter);
  }

  private class MySpanSize extends GridLayoutManager.SpanSizeLookup {

    @Override
    public int getSpanSize(int position) {

      switch (position % 5) {
        // first two items span 3 columns each
        case 0:
        case 1:
          return 3; // 6/3=2 Cells for these items
        // next 3 items span 2 columns each
        case 2:
        case 3:
        case 4:
          return 2; // 6/2=3 Cells for these items
      }
      return 1;
    }

  }

  private void showTaplighInDialog() {

    // DialogFragment.show() will take care of adding the fragment
    // in a transaction.  We also want to remove any currently showing
    // dialog, so make our own transaction and take care of that here.
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
    if (prev != null) {
      ft.remove(prev);
    }
    ft.addToBackStack(null);

    // Create and show the dialog.
    DialogFragment newFragment = TaplighDialogFragment.newInstance();
    newFragment.show(ft, "dialog");

  }

  public void setNavigator(AppNavigator navigator) {
    this.navigator = navigator;
  }

}
