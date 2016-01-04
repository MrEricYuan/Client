package com.takeout.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.model.MenuInfo;
import com.takeout.client.ui.MenuDetailActivity;

import java.util.List;

/**
 * 菜单適配器
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<MenuInfo> list;
    private Context context;

    public MenuAdapter(Context context, List<MenuInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MenuInfo info = list.get(position);
        holder.name.setText(info.getName());
//        holder.desc.setText(info.getDesc());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MenuDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("menu",info);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public FrameLayout layout;
        public TextView name;
        public TextView desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = (FrameLayout) itemView.findViewById(R.id.menu_item_layout);
            name = (TextView) itemView.findViewById(R.id.menu_item_name);
            desc = (TextView) itemView.findViewById(R.id.menu_item_desc);
        }

    }

}
