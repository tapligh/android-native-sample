package com.tapligh.android.sample_native.view.displayTapligh;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tapligh.android.sample_native.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ehsan Abbasi for public-android-native-sample at 6/2/19
 */
class DefaultListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<String> items;
  private LIST_TYPE listType;

  public enum LIST_TYPE {
    LIST_1,
    LIST_3,
    LIST_6,
    LIST_9,
    LIST_13,
    GRID_1,
    GRID_2,
    GRID_3
  }

  DefaultListAdapter(List<String> data, LIST_TYPE listType) {
    items = new ArrayList<>(data);
    this.listType = listType;
  }


  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    if (listType == LIST_TYPE.LIST_1) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_1, parent, false);
      return new List1ViewHolder(rootView);

    } else if (listType == LIST_TYPE.LIST_3) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_3, parent, false);
      return new List3ViewHolder(rootView);

    } else if (listType == LIST_TYPE.LIST_6) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_6, parent, false);
      return new List6ViewHolder(rootView);

    } else if (listType == LIST_TYPE.LIST_9) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_9, parent, false);
      return new List9ViewHolder(rootView);

    } else if (listType == LIST_TYPE.LIST_13) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_13, parent, false);
      return new List13ViewHolder(rootView);

    } else if (listType == LIST_TYPE.GRID_1) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_1, parent, false);
      return new Grid1ViewHolder(rootView);

    } else if (listType == LIST_TYPE.GRID_2) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_2, parent, false);
      return new Grid2ViewHolder(rootView);

    } else if (listType == LIST_TYPE.GRID_3) {

      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_3, parent, false);
      return new Grid3ViewHolder(rootView);

    } else
      return null;

  }

  @Override
  public long getItemId(int position) {
    return -System.identityHashCode(items.get(position));
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  class List1ViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView icon;
    private ImageView banner;

    List1ViewHolder(View itemView) {
      super(itemView);

      title = itemView.findViewById(R.id.list_title);
      icon = itemView.findViewById(R.id.list_icon);
      banner = itemView.findViewById(R.id.list_banner);
    }

    public void bind(String data) {
      title.setText(data);
      icon.setImageResource(R.color.colorPrimaryDark);
      banner.setImageResource(R.color.colorPrimaryDark);
    }

  }

  class List3ViewHolder extends RecyclerView.ViewHolder {

    private TextView text;
    private ImageView icon;

    List3ViewHolder(View itemView) {
      super(itemView);

      text = itemView.findViewById(R.id.list_title);
      icon = itemView.findViewById(R.id.list_icon);
    }

    public void bind(String data) {
      text.setText(data);
      icon.setImageResource(R.color.colorPrimaryDark);
    }
  }

  class List6ViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView icon;
    private Button btAction;

    List6ViewHolder(View itemView) {
      super(itemView);

      title = itemView.findViewById(R.id.list_title);
      icon = itemView.findViewById(R.id.list_icon);
      btAction = itemView.findViewById(R.id.list_action_button);

    }

    public void bind(String data) {
      title.setText(data);
      icon.setImageResource(R.color.colorPrimaryDark);
    }
  }

  class List9ViewHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvDesc;
    private ImageView imBanner;
    private Button btAction;

    List9ViewHolder(View itemView) {
      super(itemView);

      tvTitle = itemView.findViewById(R.id.list_title);
      tvDesc = itemView.findViewById(R.id.list_desc);
      imBanner = itemView.findViewById(R.id.list_banner);
      btAction = itemView.findViewById(R.id.list_action_button);
    }

    public void bind(String data) {

      tvTitle.setText(data);
      tvDesc.setText("Description for" + data);
      imBanner.setImageResource(R.color.colorPrimaryDark);

    }
  }

  class List13ViewHolder extends RecyclerView.ViewHolder {

    private TextView text;
    private ImageView icon;

    List13ViewHolder(View itemView) {
      super(itemView);

      text = itemView.findViewById(R.id.list_title);
      icon = itemView.findViewById(R.id.list_icon);
    }

    void bind(String data) {
      text.setText(data);
      icon.setImageResource(R.color.colorPrimaryDark);
    }
  }

  class Grid1ViewHolder extends RecyclerView.ViewHolder {

    private TextView text;

    Grid1ViewHolder(@NonNull View itemView) {
      super(itemView);

      text = itemView.findViewById(R.id.list_title);

    }

    void bind(String data) {

      text.setText(data);

    }
  }

  class Grid2ViewHolder extends RecyclerView.ViewHolder {

    Grid2ViewHolder(@NonNull View itemView) {
      super(itemView);
    }

    void bind(String data) {


    }
  }

  class Grid3ViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView banner;

    Grid3ViewHolder(@NonNull View itemView) {
      super(itemView);

      title = itemView.findViewById(R.id.list_title);
      banner = itemView.findViewById(R.id.list_banner);
    }


    void bind(String data) {

      title.setText(data);
      banner.setImageResource(R.color.colorPrimaryDark);

    }

  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

    String data = items.get(position);

    if (listType == LIST_TYPE.LIST_1)
      ((List1ViewHolder) holder).bind(data);

    else if (listType == LIST_TYPE.LIST_3)
      ((List3ViewHolder) holder).bind(data);

    else if (listType == LIST_TYPE.LIST_6)
      ((List6ViewHolder) holder).bind(data);

    else if (listType == LIST_TYPE.LIST_9)
      ((List9ViewHolder) holder).bind(data);

    else if (listType == LIST_TYPE.LIST_13)
      ((List13ViewHolder) holder).bind(data);

    else if (listType == LIST_TYPE.GRID_1)
      ((Grid1ViewHolder) holder).bind(data);


    else if (listType == LIST_TYPE.GRID_2)
      ((Grid2ViewHolder) holder).bind(data);


    else if (listType == LIST_TYPE.GRID_3)
      ((Grid3ViewHolder) holder).bind(data);

  }

  void addNewItems(final ArrayList<String> newItems, final ArrayList<String> totalItems) {

    int pos = this.items.size();

    DiffUtil.DiffResult diffUtil = DiffUtil.calculateDiff(new DiffUtil.Callback() {
      @Override
      public int getOldListSize() {
        return items.size();
      }

      @Override
      public int getNewListSize() {
        return totalItems.size();
      }

      @Override
      public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return items.get(oldItemPosition).equals(totalItems.get(newItemPosition));
      }

      @Override
      public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return items.get(oldItemPosition).equals(totalItems.get(newItemPosition));
      }
    });

    diffUtil.dispatchUpdatesTo(this);
    this.items.addAll(newItems);

  }

  public List<String> getItems() {
    return items;
  }
}
