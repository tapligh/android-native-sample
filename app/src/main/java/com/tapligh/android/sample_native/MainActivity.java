package com.tapligh.android.sample_native;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tapligh.android.sample_native.databinding.ActivityMainBinding;
import com.tapligh.android.sample_native.util.LocaleHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navigator = new AppNavigator(getSupportFragmentManager(), binding.mainContainer.getId());

        navigator.gotoChooserFragment(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else
            super.onBackPressed();
    }
}
