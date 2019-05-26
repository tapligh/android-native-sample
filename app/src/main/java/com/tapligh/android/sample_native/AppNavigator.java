package com.tapligh.android.sample_native;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tapligh.android.sample_native.view.ChooserPageFragment;

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

}
