package com.tapligh.android.sample_native.grid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.databinding.FragmentGridBinding;
import com.tapligh.android.sample_native.list.SimpleListAdapter;
import com.tapligh.sdk.nativead.AdViewBinding;
import com.tapligh.sdk.nativead.AdViewBindingOption;
import com.tapligh.sdk.nativead.AspectRatio;
import com.tapligh.sdk.nativead.BadgePosition;
import com.tapligh.sdk.nativead.list.AdPlacementStrategy;
import com.tapligh.sdk.nativead.list.TaplighNativeRenderer;
import com.tapligh.sdk.nativead.list.TaplighRecyclerAdapter;

import java.util.ArrayList;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/30 09:54
 */
public class EndlessGridFragment extends Fragment {

    private static final String UNIT_CODE = "e388c999-af88-4ea9-aa9c-ae8527a8403b";
    private final int DATA_SIZE = 60;
    private final int AD_INTERVAL = 7;
    private int startPosition = 0;

    private int type;

    private FragmentGridBinding binding;
    private SimpleGridAdapter originalAdapter;
    private TaplighRecyclerAdapter taplighAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            type = bundle.getInt("step");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_grid, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        originalAdapter = new SimpleGridAdapter();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.gridView.setLayoutManager(layoutManager);

        createAdAssets();
        binding.gridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager == null) return;

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        fillList();
                    }

                }
            }
        });
        fillList();
    }

    private void createAdAssets() {
        AdPlacementStrategy strategy = new AdPlacementStrategy();
        strategy.enableRepeatingMode(AD_INTERVAL);

        AdViewBindingOption option = new AdViewBindingOption();

        taplighAdapter = new TaplighRecyclerAdapter(getActivity(), originalAdapter, strategy);
        TaplighNativeRenderer renderer ;

        if(type == 1){  // simple
            AdViewBinding adBinding = new AdViewBinding
                    .Builder()
                    .setIconRes(R.id.native_ad_icon)
                    .setTitleRes(R.id.native_ad_title)
                    .setBannerRes(R.id.native_ad_banner)
                    .build();
            option.setBannerAR(AspectRatio.AR1x1);
            adBinding.setBindingOption(option);

            renderer = new TaplighNativeRenderer(R.layout.native_view_template3, R.id.native_ad_root, adBinding);

        } else { // banner
            AdViewBinding adBinding = new AdViewBinding
                    .Builder()
                    .setTitleRes(R.id.native_ad_title)
                    .setBannerRes(R.id.native_ad_banner)
                    .setDescriptionRes(R.id.native_ad_desc)
                    .build();
            option.setBannerAR(AspectRatio.AR16x9);
            adBinding.setBindingOption(option);

            renderer = new TaplighNativeRenderer(R.layout.native_view_template4, R.id.native_ad_root, adBinding);
            taplighAdapter.showAdAsBanner(true);
        }

        renderer.setBadgePosition(BadgePosition.top_left);
        taplighAdapter.setAdRenderer(renderer);

        binding.gridView.setAdapter(taplighAdapter);

    }

    private void fillList() {
        updateDataList();
        taplighAdapter.loadAd(UNIT_CODE);
    }

    private void updateDataList() {
        ArrayList<String> tmp = new ArrayList<>();
        for (int i = startPosition; i < DATA_SIZE + startPosition; i++) {
            tmp.add("آیتم جایگاه شماره " + (i + 1));
        }
        originalAdapter.addItems(tmp);
        startPosition += DATA_SIZE;
    }
}
