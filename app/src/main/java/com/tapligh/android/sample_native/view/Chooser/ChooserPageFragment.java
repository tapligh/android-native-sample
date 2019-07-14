package com.tapligh.android.sample_native.view.Chooser;

import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.tapligh.android.sample_native.AppNavigator;
import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.databinding.FragmentChooseBinding;
import com.tapligh.android.sample_native.view.TAPLIGH_TYPE;


/**
 * Created by Ehsan Abbasi for public-android-native-sample at 5/28/19
 */
public class ChooserPageFragment extends Fragment implements OnChildClickListener {

  private FragmentChooseBinding binding;
  private AppNavigator navigator;
  private LayoutInflater inflater;
  private ChildCategoryAdapter[] adapter;

  private static final int ROTATE_DURATION = 250;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose, container, false);
    this.inflater = inflater;
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setupCategories();
  }

  private void setupCategories() {

    String[] categories = getResources().getStringArray(R.array.categoryType);
    TypedArray singleChild = getResources().obtainTypedArray(R.array.singleType);
    TypedArray listChild = getResources().obtainTypedArray(R.array.listType);
    TypedArray gridChild = getResources().obtainTypedArray(R.array.gridType);
    TypedArray customChild = getResources().obtainTypedArray(R.array.customType);

    adapter = new ChildCategoryAdapter[categories.length];

    for (int i = 0; i < categories.length; i++) {

      if (categories[i].equals(getString(R.string.category_single_tapligh))) {

        adapter[i] = new ChildCategoryAdapter(singleChild, this, TAPLIGH_TYPE.SINGLE_TAPLIGH);

      } else if (categories[i].equals(getString(R.string.category_list_tapligh))) {

        adapter[i] = new ChildCategoryAdapter(listChild, this, TAPLIGH_TYPE.LIST_TAPLIGH);

      } else if (categories[i].equals(getString(R.string.category_list_grid_tapligh))) {

        adapter[i] = new ChildCategoryAdapter(gridChild, this, TAPLIGH_TYPE.GRID_TAPLIGH);

      }else  if (categories[i].equals(getString(R.string.category_custome_tapligh))){

        adapter[i] = new ChildCategoryAdapter(customChild , this , TAPLIGH_TYPE.SINGLE_TAPLIGH);

      }

      CardView row = (CardView) inflater.inflate(R.layout.item_category, binding.lyContainer, false);
      TextView tvTitle = row.findViewById(R.id.tv_category_title);
      ImageView imExpand = row.findViewById(R.id.iv_category_expand);
      RecyclerView reChild = row.findViewById(R.id.re_category);

      reChild.setAdapter(adapter[i]);
      row.setOnClickListener(new OnCategoryClickListener(categories[i], imExpand, reChild));
      tvTitle.setText(categories[i]);

      binding.lyContainer.addView(row);
    }

  }

  @Override
  public void onChildClick(int childLayout, TAPLIGH_TYPE taplighType) {

    navigator.gotoDisplayTaplighFragment(getContext(), childLayout, taplighType);
  }

  class OnCategoryClickListener implements View.OnClickListener {

    private String category;
    private ImageView imExpand;
    private RecyclerView recyclerView;

    public OnCategoryClickListener(String category, ImageView imExpand, RecyclerView recyclerView) {
      this.category = category;
      this.imExpand = imExpand;
      this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(View view) {

      boolean visible = recyclerView.getVisibility() == View.VISIBLE;

      recyclerView.setVisibility(visible ? View.GONE : View.VISIBLE);

      RotateAnimation animation = new RotateAnimation(
        visible ? 180 : 0,
        visible ? 360 : 180,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f);

      animation.setDuration(ROTATE_DURATION);
      animation.setFillAfter(true);

      imExpand.setAnimation(animation);
      imExpand.animate();

    }
  }

  public void setNavigator(AppNavigator navigator) {
    this.navigator = navigator;
  }

}
