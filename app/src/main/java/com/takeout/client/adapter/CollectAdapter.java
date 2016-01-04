package com.takeout.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyViewHolder> {

    private List<ShopInfo> list;
    private Context context;
    private ImageLoader imageLoader;
    private ImageLoaderConfigFactory configFactory;

    public CollectAdapter(Context context, List<ShopInfo> list) {
        this.context = context;
        this.list = list;
        imageLoader = ImageLoader.getInstance();
        configFactory = ImageLoaderConfigFactory.getInstance();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ShopInfo info = list.get(position);
        imageLoader.displayImage(info.getImg(), holder.bg, configFactory.getCollectImg(), new AnimateFirstDisplayListener());
        if(position == 0){
            imageLoader.displayImage(info.getIcon(), holder.icon, configFactory.getShopIcon(), new AnimateFirstDisplayListener());
        }
//        holder.shop_name.setText(info.getName());
//        holder.shop_address.setText(info.getAddress());
//        holder.shop_desc.setText(info.getDesc());
        holder.shop_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ShopInfoActivity.class);
                context.startActivity(intent);
            }
        });
        holder.shop_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MenuActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RoundCornerImageView bg;
        public RoundCornerImageView icon;
        public TextView shop_name;
        public TextView shop_address;
        public TextView shop_desc;
        public TextView shop_information;
        public Button shop_menu;

        public MyViewHolder(View itemView) {
            super(itemView);
            bg = (RoundCornerImageView) itemView.findViewById(R.id.collect_item_bg);
            icon = (RoundCornerImageView) itemView.findViewById(R.id.collect_item_icon);
            shop_name = (TextView) itemView.findViewById(R.id.collect_item_shop_name);
            shop_address = (TextView) itemView.findViewById(R.id.collect_item_shop_address);
            shop_desc = (TextView) itemView.findViewById(R.id.collect_item_shop_desc);
            shop_information = (TextView) itemView.findViewById(R.id.collect_item_shop_information);
            shop_menu = (Button) itemView.findViewById(R.id.collect_item_shop_menu);
        }

    }

}
