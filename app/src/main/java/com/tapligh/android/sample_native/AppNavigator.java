package com.tapligh.android.sample_native;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tapligh.android.sample_native.chooser.ChooserPageFragment;
import com.tapligh.android.sample_native.grid.EndlessGridFragment;
import com.tapligh.android.sample_native.list.EndlessListFragment;
import com.tapligh.android.sample_native.simple.SimpleNativeAdFragment;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/27 11:43
 */
public class AppNavigator {

    private FragmentManager manager;
    private int rootView;

    public AppNavigator(FragmentManager manager, int rootView) {
        this.manager = manager;
        this.rootView = rootView;
    }

    private void changeFragment(Fragment fragment, String backStackTag) {
        manager.beginTransaction()
                .add(rootView, fragment)
                .addToBackStack(backStackTag)
                .commitAllowingStateLoss();
    }

    public void gotoChooserFragment(Context context) {
        ChooserPageFragment fragment = (ChooserPageFragment) ChooserPageFragment
                .instantiate(context, ChooserPageFragment.class.getName());
        fragment.setNavigator(this);
        changeFragment(fragment, ChooserPageFragment.class.getName());
    }

    public void gotoSimpleAdFragment(Context context) {
        SimpleNativeAdFragment fragment = (SimpleNativeAdFragment) SimpleNativeAdFragment
                .instantiate(context, SimpleNativeAdFragment.class.getName());
        changeFragment(fragment, SimpleNativeAdFragment.class.getName());
    }

    public void gotoEndlessListFragment(Context context) {
        EndlessListFragment fragment = (EndlessListFragment) EndlessListFragment
                .instantiate(context, EndlessListFragment.class.getName());
        changeFragment(fragment, EndlessListFragment.class.getName());
    }

    public void gotoSimpleEndlessGridFragment(Context context) {
        Bundle bundle = new Bundle();
        bundle.putInt("step" , 1);

        EndlessGridFragment fragment = (EndlessGridFragment) EndlessGridFragment
                .instantiate(context, EndlessGridFragment.class.getName(), bundle);
        changeFragment(fragment, EndlessGridFragment.class.getName());
    }


    public void gotoBannerEndlessGridFragment(Context context){
        Bundle bundle = new Bundle();
        bundle.putInt("step" , 2);

        EndlessGridFragment fragment = (EndlessGridFragment) EndlessGridFragment
                .instantiate(context, EndlessGridFragment.class.getName(), bundle);
        changeFragment(fragment, EndlessGridFragment.class.getName());
    }
}
