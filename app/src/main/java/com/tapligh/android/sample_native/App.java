package com.tapligh.android.sample_native;

import android.app.Application;
import android.content.Context;

import com.tapligh.android.sample_native.util.LocaleHelper;
import com.tapligh.sdk.nativead.Tapligh;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/27 15:07
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        new Tapligh.Builder(this, "9df6a91a-fd3e-482f-8e04-8bb58d8573c4").build();


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "fa"));

    }
}
