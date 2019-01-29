package com.tapligh.android.sample_native;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.tapligh.sdk.nativead.Tapligh;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/27 15:07
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        new Tapligh.Builder(this, "9df6a91a-fd3e-482f-8e04-8bb58d8573c4").build();


    }
}
