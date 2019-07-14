package com.tapligh.android.sample_native.view.displayTapligh;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.util.Constants;
import com.tapligh.sdk.nativead.AdViewBinding;
import com.tapligh.sdk.nativead.AdViewBindingOption;
import com.tapligh.sdk.nativead.AspectRatio;
import com.tapligh.sdk.nativead.BadgePosition;
import com.tapligh.sdk.nativead.LoadErrorStatus;
import com.tapligh.sdk.nativead.NativeAdLoader;
import com.tapligh.sdk.nativead.NativeAdLoadingListener;
import com.tapligh.sdk.nativead.TaplighNativeAd;
import com.tapligh.sdk.nativead.TaplighNativeAdView;

import java.util.List;

/**
 * Created by Ehsan Abbasi for public-android-native-sample at 6/2/19
 */
public class TaplighDialogFragment extends DialogFragment {

  private static final String TAG = "TaplighDialogFragment";
  private final static String EXTRA_KEY_TAPLIGH_NATIVE = "extra_key_tapligh_native";

  private View rootView;
  private AdViewBinding adBinding;
  private TaplighNativeAdView nativeAdView;

  public static TaplighDialogFragment newInstance() {
    Bundle args = new Bundle();

    TaplighDialogFragment f = new TaplighDialogFragment();
    f.setArguments(args);
    return f;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();

    Dialog dialog = getDialog();
    if (dialog != null) {
      dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.dialog_tapligh, container, false);

    return rootView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);


    NativeAdLoader loader = new NativeAdLoader
      .Builder(getContext(), Constants.UNIT_CODE)
      .setRequestNumber(1)
      .setListener(new NativeAdLoadingListener() {
        @Override
        public void onAdLoaded(List<TaplighNativeAd> ads) {
          Log.e(TAG, "onAdLoaded() called with: ads = [" + ads.size() + "]");

          showAd(ads.get(0));
        }

        @Override
        public void onLoadError(LoadErrorStatus status) {
          Log.e(TAG, "onLoadError() called with: status = [" + status + "]");
        }
      }).build();
    loader.loadAd();


  }

  private void showAd(TaplighNativeAd ad){

    adBinding = new AdViewBinding.Builder()
      .setTitleRes(R.id.native_ad_title)
      .setDescriptionRes(R.id.native_ad_desc)
      .setActionTextRes(R.id.native_ad_action_button)
      .setBannerRes(R.id.native_ad_banner)
      .build();

    nativeAdView = (TaplighNativeAdView) rootView.findViewById(R.id.native_ad_root);
    nativeAdView.setAdViewBinder(adBinding);
    nativeAdView.setAdPlacement(BadgePosition.top_left);

    AdViewBindingOption option = new AdViewBindingOption();
    option.setBannerAR(AspectRatio.AR16x9);
    adBinding.setBindingOption(option);
    nativeAdView.showAd(ad);
    nativeAdView.setVisibility(View.VISIBLE);
  }

}
