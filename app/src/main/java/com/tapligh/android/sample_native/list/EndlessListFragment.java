package com.tapligh.android.sample_native.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.databinding.FragmentListBinding;
import com.tapligh.sdk.nativead.AdViewBinding;
import com.tapligh.sdk.nativead.AdViewBindingOption;
import com.tapligh.sdk.nativead.AspectRatio;
import com.tapligh.sdk.nativead.BadgePosition;
import com.tapligh.sdk.nativead.list.AdPlacementStrategy;
import com.tapligh.sdk.nativead.list.TaplighNativeRenderer;
import com.tapligh.sdk.nativead.list.TaplighRecyclerAdapter;

import java.util.ArrayList;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/Jan/29 09:59
 */
public class EndlessListFragment extends Fragment {

    private static final String UNIT_CODE = "e388c999-af88-4ea9-aa9c-ae8527a8403b";
    private final int DATA_SIZE = 60;
    private final int AD_INTERVAL = 7;
    private int startPosition = 0;

    private FragmentListBinding binding;
    private SimpleListAdapter originalAdapter;
    private TaplighRecyclerAdapter taplighAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        originalAdapter = new SimpleListAdapter();

        createAdAssets();
        binding.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (linearLayoutManager == null) return;

                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        fillList();
                    }

                }
            }
        });

        fillList();
    }

    private void updateDataList() {
        ArrayList<String> tmp = new ArrayList<>();
        for (int i = startPosition; i < DATA_SIZE + startPosition; i++) {
            tmp.add("آیتم در جایگاه شماره " + (i + 1));
        }
        originalAdapter.addItems(tmp);
        startPosition += DATA_SIZE;
    }

    private void createAdAssets() {
        AdPlacementStrategy strategy = new AdPlacementStrategy();
        strategy.enableRepeatingMode(AD_INTERVAL);

        AdViewBinding adBinding = new AdViewBinding
                .Builder()
                .setIconRes(R.id.native_ad_icon)
                .setTitleRes(R.id.native_ad_title)
                .setBannerRes(R.id.native_ad_banner)
                .build();
        AdViewBindingOption option = new AdViewBindingOption();
        option.setBannerAR(AspectRatio.AR16x9);
        adBinding.setBindingOption(option);

        taplighAdapter = new TaplighRecyclerAdapter(getActivity(), originalAdapter, strategy);
        TaplighNativeRenderer renderer = new TaplighNativeRenderer(R.layout.native_view_template2, R.id.native_ad_root, adBinding);
        renderer.setBadgePosition(BadgePosition.top_left);
        taplighAdapter.setAdRenderer(renderer);

        binding.listView.setAdapter(taplighAdapter);

    }

    private void fillList() {
        updateDataList();
        taplighAdapter.loadAd(UNIT_CODE);
    }
}
