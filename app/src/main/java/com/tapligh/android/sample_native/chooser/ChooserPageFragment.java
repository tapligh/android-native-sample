package com.tapligh.android.sample_native.chooser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.AppNavigator;
import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.databinding.FragmentChooserBinding;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/27 15:48
 */
public class ChooserPageFragment extends Fragment {

    private static final int TYPE_SIMPLE = 1;


    private FragmentChooserBinding binding;
    private AppNavigator navigator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chooser, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.simpleNative.setOnClickListener(new AdButtonClickListener(TYPE_SIMPLE));
    }

    public void setNavigator(AppNavigator navigator) {
        this.navigator = navigator;
    }


    private class AdButtonClickListener implements View.OnClickListener {

        private int type;

        public AdButtonClickListener(int type) {
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            switch (type) {
                case TYPE_SIMPLE:
                    navigator.gotoSimpleAdFragment(getContext());
                    break;

            }
        }
    }

}
