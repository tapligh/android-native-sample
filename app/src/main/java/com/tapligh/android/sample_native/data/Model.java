package com.tapligh.android.sample_native.data;

import com.tapligh.sdk.nativead.AdViewBinding;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/May/22 12:39
 */
public class Model {

    private int layoutResId;
    private int adLayoutResId;
    private int adRootResId;
    private AdViewBinding adBinding;

    public Model(int layoutResId, int adLayoutResId, int adRootResId, AdViewBinding adBinding) {
        this.layoutResId = layoutResId;
        this.adLayoutResId = adLayoutResId;
        this.adRootResId = adRootResId;
        this.adBinding = adBinding;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    public int getAdLayoutResId() {
        return adLayoutResId;
    }

    public void setAdLayoutResId(int adLayoutResId) {
        this.adLayoutResId = adLayoutResId;
    }

    public AdViewBinding getAdBinding() {
        return adBinding;
    }

    public void setAdBinding(AdViewBinding adBinding) {
        this.adBinding = adBinding;
    }

    public int getAdRootResId() {
        return adRootResId;
    }

    public void setAdRootResId(int adRootResId) {
        this.adRootResId = adRootResId;
    }
}
