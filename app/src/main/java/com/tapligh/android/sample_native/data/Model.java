package com.tapligh.android.sample_native.data;

import android.view.View;

import com.tapligh.android.sample_native.R;
import com.tapligh.sdk.nativead.AdViewBinding;
import com.tapligh.sdk.nativead.AdViewBindingOption;
import com.tapligh.sdk.nativead.AspectRatio;

import java.io.Serializable;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/May/22 12:39
 */
public class Model implements Serializable {

  private int layoutResId;
  private int adLayoutResId;
  private int adRootResId;
  private AdViewBinding adBinding;
  private boolean showInDialog;
  private boolean showInList;
  private boolean showInGrid;


  public int getLayoutResId() {
    return layoutResId;
  }

  private void setLayoutResId(int layoutResId) {
    this.layoutResId = layoutResId;
  }

  public int getAdLayoutResId() {
    return adLayoutResId;
  }

  private void setAdLayoutResId(int adLayoutResId) {
    this.adLayoutResId = adLayoutResId;
  }

  public AdViewBinding getAdBinding() {
    return adBinding;
  }

  private void setAdBinding(AdViewBinding adBinding) {
    this.adBinding = adBinding;
  }

  public int getAdRootResId() {
    return adRootResId;
  }

  private void setAdRootResId(int adRootResId) {
    this.adRootResId = adRootResId;
  }

  public boolean isShowInDialog() {
    return showInDialog;
  }

  private void setShowInDialog(boolean showInDialog) {
    this.showInDialog = showInDialog;
  }

  public boolean isShowInList() {
    return showInList;
  }

  private void setShowInList(boolean showInList) {
    this.showInList = showInList;
  }

  public boolean isShowInGrid() {
    return showInGrid;
  }

  public void setShowInGrid(boolean showInGrid) {
    this.showInGrid = showInGrid;
  }

  public static Model prepareModel(View view, int fragmentLayout) {

    Model model = new Model();

    AdViewBinding adBinding = null;

    AdViewBindingOption option = new AdViewBindingOption();

    switch (fragmentLayout) {

      case R.layout.fragment_single_1:
        adBinding =
          new AdViewBinding.Builder()
            .setIconRes(R.id.native_ad_icon)
            .setTitleRes(R.id.native_ad_title)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_2:
        adBinding =
          new AdViewBinding.Builder()
            .setIconRes(R.id.native_ad_icon)
            .setTitleRes(R.id.native_ad_title)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_3:
        adBinding =
          new AdViewBinding.Builder()
            .setIconRes(R.id.native_ad_icon)
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_4:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_6:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setIconRes(R.id.native_ad_icon)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_7:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_9:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setActionTextRes(R.id.native_ad_action_button)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_11:
        adBinding =
          new AdViewBinding.Builder()
            .setIconRes(R.id.native_ad_icon)
            .setTitleRes(R.id.native_ad_title)
            .setActionTextRes(R.id.native_ad_action_button)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_single_12:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setActionTextRes(R.id.native_ad_action_button)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);


      /*  view.findViewById(R.id.native_ad_action_button)
          .setBackgroundColor(palette.getMutedColor(Color.YELLOW));*/

        break;

      case R.layout.fragment_single_13:
        model.setShowInDialog(true);
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setActionTextRes(R.id.native_ad_action_button)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_list_1:

        adBinding = new AdViewBinding
          .Builder()
          .setIconRes(R.id.native_ad_icon)
          .setTitleRes(R.id.native_ad_title)
          .setBannerRes(R.id.native_ad_banner)
          .build();

        option.setBannerAR(AspectRatio.AR16x9);


        model.setShowInList(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_1);
        model.setLayoutResId(R.layout.fragment_list_1);
        break;

      case R.layout.fragment_list_3:

        adBinding = new AdViewBinding
          .Builder()
          .setIconRes(R.id.native_ad_icon)
          .setTitleRes(R.id.native_ad_title)
          .setDescriptionRes(R.id.native_ad_desc)
          .build();

        option.setBannerAR(AspectRatio.AR16x9);

        model.setShowInList(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_3);
        model.setLayoutResId(R.layout.fragment_list_1);
        break;

      case R.layout.fragment_list_6:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setIconRes(R.id.native_ad_icon)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);


        model.setShowInList(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_6_custom);
        model.setLayoutResId(R.layout.fragment_list_6);
        break;

      case R.layout.fragment_list_9:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setIconRes(R.id.native_ad_icon)
            .setBannerRes(R.id.native_ad_banner)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR9x16);

        model.setShowInList(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_9);
        model.setLayoutResId(R.layout.fragment_list_9);
        break;

      case R.layout.fragment_list_13:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setIconRes(R.id.native_ad_icon)
            .setBannerRes(R.id.native_ad_banner)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        model.setShowInList(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_13_wrap);
        model.setLayoutResId(R.layout.fragment_list_13);
        break;

      case R.layout.fragment_grid_1:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR1x1);

        model.setShowInGrid(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_4);
        model.setLayoutResId(R.layout.fragment_grid_1);

        break;

      case R.layout.fragment_grid_2:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        model.setShowInGrid(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_4);
        model.setLayoutResId(R.layout.fragment_grid_2);

        break;

      case R.layout.fragment_grid_3:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        model.setShowInGrid(true);
        model.setAdRootResId(R.id.native_ad_root);
        model.setAdLayoutResId(R.layout.native_view_template_4);
        model.setLayoutResId(R.layout.fragment_grid_3);

        break;

      case R.layout.fragment_custom_1:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setBannerRes(R.id.native_ad_banner)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_custom_2:
        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setBannerRes(R.id.native_ad_banner)
            .setActionTextRes(R.id.native_ad_action_button)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

        break;

      case R.layout.fragment_custom_3:

        adBinding =
          new AdViewBinding.Builder()
            .setTitleRes(R.id.native_ad_title)
            .setDescriptionRes(R.id.native_ad_desc)
            .setActionTextRes(R.id.native_ad_action_button)
            .setBannerRes(R.id.native_ad_banner)
            .build();

        option.setBannerAR(AspectRatio.AR16x9);

    }


    if (adBinding != null)
      adBinding.setBindingOption(option);

    model.setAdBinding(adBinding);

    return model;
  }

}
