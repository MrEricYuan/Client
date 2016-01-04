package com.takeout.client.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.model.OrderSubInfo;

import java.util.List;

/**
 * Created by Eric on 2015/12/20.
 */
public class OrderSubAdapter extends BaseAdapter{
    private List<OrderSubInfo> orderSubList;
    public OrderSubAdapter(List<OrderSubInfo> orderSubList){
        this.orderSubList = orderSubList;
    }
    @Override
    public int getCount() {
        return orderSubList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderSubList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordersub_item,null);
            holder.ordersub_name = (TextView) convertView.findViewById(R.id.ordersub_name);
            holder.ordersub_norm = (TextView) convertView.findViewById(R.id.ordersub_norm);
            holder.ordersub_num = (TextView) convertView.findViewById(R.id.ordersub_num);
            holder.ordersub_add_item = (TextView) convertView.findViewById(R.id.ordersub_add_item);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderSubInfo orderSubInfo = orderSubList.get(position);
        holder.ordersub_name.setText(orderSubInfo.getoSubName());
        holder.ordersub_norm.setText(orderSubInfo.getoSubNorm());
        holder.ordersub_num.setText("X"+orderSubInfo.getoSubNum());
        holder.ordersub_add_item.setText(orderSubInfo.getoSubAdd());
        return convertView;
    }

    class ViewHolder{
        private TextView ordersub_name;
        private TextView ordersub_norm;
        private TextView ordersub_num;
        private TextView ordersub_add_item;
    }
}
