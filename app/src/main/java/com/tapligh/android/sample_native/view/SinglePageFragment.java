package com.tapligh.android.sample_native.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.data.Model;
import com.tapligh.android.sample_native.util.Constants;
import com.tapligh.sdk.nativead.AdBadgeView;
import com.tapligh.sdk.nativead.BadgePosition;
import com.tapligh.sdk.nativead.LoadErrorStatus;
import com.tapligh.sdk.nativead.NativeAdLoader;
import com.tapligh.sdk.nativead.NativeAdLoadingListener;
import com.tapligh.sdk.nativead.TaplighNativeAd;
import com.tapligh.sdk.nativead.TaplighNativeAdView;

import java.util.List;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/May/22 12:40
 */
public class SinglePageFragment extends Fragment implements NativeAdLoadingListener {

    private static final String TAG = SinglePageFragment.class.getSimpleName();

    private Model model;
    private TaplighNativeAdView nativeAdView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(model.getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nativeAdView = view.findViewById(model.getAdRootResId());
        nativeAdView.setAdViewBinder(model.getAdBinding());
        nativeAdView.setAdPlacement(BadgePosition.top_left);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadTapligh();
    }

    private void loadTapligh() {
        nativeAdView.setVisibility(View.GONE);
        NativeAdLoader loader = new NativeAdLoader.Builder(getContext(), Constants.UNIT_CODE)
                .setRequestNumber(1)
                .setListener(this)
                .build();
        loader.loadAd();
    }

    @Override
    public void onAdLoaded(List<TaplighNativeAd> list) {
        if(list != null && list.size() > 0){
            nativeAdView.setVisibility(View.VISIBLE);
            nativeAdView.showAd(list.get(0));
        } else {
            Log.e(TAG, "Empty response!!!");
        }
    }

    @Override
    public void onLoadError(LoadErrorStatus loadErrorStatus) {
        switch (loadErrorStatus){
            case adUnitDisabled:
                Log.d(TAG, "Ad Unit is disabled");
                return;
            case adUnitNotFound:
                Log.d(TAG, "Ad Unit not found");
                return;
            case appNotFound:
                Log.d(TAG, "App packageName and token not the same with the tapligh panel");
                return;
            case noAdReady:
                Log.d(TAG, "No Ad is READY");
                return;
            case inProcess:
                Log.d(TAG, "Loading is in process. Waiting...");
                return;
            case noInternetAccess:
                Log.d(TAG, "No internet access");
                return;
            case internalError:
                Log.d(TAG, "<Unknown error>");
                return;
        }
    }
}
