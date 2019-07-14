package com.tapligh.android.sample_native.view.Chooser;

import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tapligh.android.sample_native.R;
import com.tapligh.android.sample_native.databinding.ItemChildCategoryBinding;
import com.tapligh.android.sample_native.view.TAPLIGH_TYPE;

/**
 * Created by Ehsan Abbasi for public-android-native-sample at 5/28/19
 */
public class ChildCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private TypedArray mainList;
  private OnChildClickListener listener;
  private TAPLIGH_TYPE taplighType;

  ChildCategoryAdapter(TypedArray mainList, OnChildClickListener listener, TAPLIGH_TYPE taplighType) {
    this.mainList = mainList;
    this.listener = listener;
    this.taplighType = taplighType;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    ItemChildCategoryBinding binding = DataBindingUtil.inflate(
      LayoutInflater.from(
        viewGroup.getContext()
      ),
      R.layout.item_child_category,
      viewGroup,
      false
    );
    return new CategoryViewHolder(binding);
  }

  class CategoryViewHolder extends RecyclerView.ViewHolder {

    ItemChildCategoryBinding binding;

    CategoryViewHolder(@NonNull ItemChildCategoryBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    void onBind(int layoutID) {
      binding.tvCategoryChildTitle.setText(layoutID);
      itemView.setOnClickListener(new OnChildClickListener(layoutID));
    }

    class OnChildClickListener implements View.OnClickListener {

      int childLayout;

      OnChildClickListener(int childLayout) {
        this.childLayout = childLayout;
      }

      @Override
      public void onClick(View view) {

        listener.onChildClick(childLayout, taplighType);
      }
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    ((CategoryViewHolder) viewHolder).onBind(mainList.getResourceId(position, R.layout.fragment_single_1));
  }

  @Override
  public int getItemCount() {
    return mainList.length();
  }

}
