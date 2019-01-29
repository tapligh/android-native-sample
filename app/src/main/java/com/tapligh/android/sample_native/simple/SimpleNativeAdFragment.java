package com.tapligh.android.sample_native.simple;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.databinding.FragmentSimpleNativeAdBinding;
import com.tapligh.sdk.nativead.AdViewBinding;
import com.tapligh.sdk.nativead.BadgePosition;
import com.tapligh.sdk.nativead.LoadErrorStatus;
import com.tapligh.sdk.nativead.NativeAdLoader;
import com.tapligh.sdk.nativead.NativeAdLoadingListener;
import com.tapligh.sdk.nativead.TaplighNativeAd;
import com.tapligh.sdk.nativead.TaplighNativeAdView;

import java.util.List;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/27 11:46
 */
public class SimpleNativeAdFragment extends Fragment {

    private FragmentSimpleNativeAdBinding binding;
    private TaplighNativeAdView nativeAdView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_simple_native_ad,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createAssets();
        loadAd();
    }

    private void createAssets() {
        nativeAdView = binding.getRoot().findViewById(R.id.native_ad_root);
        AdViewBinding adViewBinding = new AdViewBinding.Builder()
                .setTitleRes(R.id.native_ad_title)
                .setIconRes(R.id.native_ad_icon)
                .setDescriptionRes(R.id.native_ad_desc)
                .build();
        nativeAdView.setAdViewBinder(adViewBinding);
        nativeAdView.setAdPlacement(BadgePosition.top_left);
    }

    private void loadAd() {
        NativeAdLoader loader = new NativeAdLoader.Builder(getContext(), "78e33858-fe3c-4c36-ab4c-179f828052d4")
                .setRequestNumber(1)
                .setListener(new NativeAdLoadingListener() {
                    @Override
                    public void onAdLoaded(List<TaplighNativeAd> list) {
                        nativeAdView.showAd(list.get(0));
                    }

                    @Override
                    public void onLoadError(LoadErrorStatus loadErrorStatus) {
                        nativeAdView.setVisibility(View.GONE);
                        binding.errorText.setVisibility(View.VISIBLE);
                        switch (loadErrorStatus) {
                            case noAdReady:
                                binding.errorText.setText("متاسفانه تبلیغی برای نمایش وجود ندارد");
                                return;
                            case appNotFound:
                                binding.errorText.setText("برنامه وجود ندارد. مقادیر token و packageName برنامه خود را با پنل چک کنید");
                                return;
                            case adUnitNotFound:
                                binding.errorText.setText("واحد تبلیغ وجود ندارد. کد واحد تبلیغ را با پنل چک کنید");
                                return;
                            case adUnitDisabled:
                                binding.errorText.setText("واحد تبلیغ غیرفعال است. برای فعالسازی از طریق پنل اقدام نمایید");
                                return;
                            case noInternetAccess:
                                binding.errorText.setText("عدم اتصال به اینترنت");
                                return;
                            case internalError:
                            default:
                                binding.errorText.setText("<بروز خطای داخلی>");
                        }
                    }
                }).build();

        loader.loadAd();
    }
}
