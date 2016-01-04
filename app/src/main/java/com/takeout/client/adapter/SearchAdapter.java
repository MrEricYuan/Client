package com.takeout.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.takeout.client.R;
import com.takeout.client.model.ShopInfo;
import com.takeout.client.ui.MenuActivity;
import com.takeout.client.ui.ShopInfoActivity;
import com.takeout.client.utils.AnimateFirstDisplayListener;
import com.takeout.client.utils.ImageLoaderConfigFactory;
import com.takeout.client.view.RoundCornerImageView;

import java.util.List;

/**
 * 收藏適配器
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<ShopInfo> list;
    private ImageLoader imageLoader;
    private ImageLoaderConfigFactory configFactory;
    private ItemClickListener itemClickListener;

    public SearchAdapter(List<ShopInfo> list, ItemClickListener itemClickListener) {
        this.list = list;
        this.itemClickListener = itemClickListener;
        imageLoader = ImageLoader.getInstance();
        configFactory = ImageLoaderConfigFactory.getInstance();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ShopInfo info = list.get(position);
        imageLoader.displayImage(info.getIcon(), holder.icon, configFactory.getShopIcon(), new AnimateFirstDisplayListener());
//        holder.shop_name.setText(info.getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout layout;
        public RoundCornerImageView icon;
        public TextView shop_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.search_item_layout);
            icon = (RoundCornerImageView) itemView.findViewById(R.id.search_item_icon);
            shop_name = (TextView) itemView.findViewById(R.id.search_item_shop_name);
        }

    }

    public interface ItemClickListener{
        public void click(int position);
    }

}
