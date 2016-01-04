package com.takeout.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.model.OrderInfo;
import com.takeout.client.utils.Util;

import java.util.List;

/**
 * Created by Eric on 2015/12/20.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyOrderViewHolder>{

    private List<OrderInfo> orderList;
    private Context context;

    public OrderAdapter(Context context,List<OrderInfo> orderList){
        this.context = context;
        this.orderList = orderList;
    }
    @Override
    public OrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        MyOrderViewHolder vh = new MyOrderViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.MyOrderViewHolder holder, int position) {
        if(orderList.get(position).getOrderSubList() != null){
            Log.i("Tag", "OrderSubList()=" + orderList.get(position).getOrderSubList().size());
            OrderSubAdapter adapter = new OrderSubAdapter(orderList.get(position).getOrderSubList());
            holder.order_menu_lv.setAdapter(adapter);
            Util.setListViewHeight(holder.order_menu_lv);
            if(position == 1){
                holder.order_state_tv.setBackgroundResource(R.drawable.order_bg);
                holder.order_state_tv.setText("待取中");
                holder.order_state_tv.setTextColor(context.getResources().getColor(R.color.white));
                holder.order_time_tv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyOrderViewHolder extends RecyclerView.ViewHolder {
        private ListView order_menu_lv;
        private TextView order_state_tv;
        private TextView order_time_tv;
        public MyOrderViewHolder(View itemView) {
            super(itemView);
            order_menu_lv = (ListView) itemView.findViewById(R.id.order_menu_lv);
            order_state_tv = (TextView) itemView.findViewById(R.id.order_state_tv);
            order_time_tv = (TextView) itemView.findViewById(R.id.order_time_tv);
        }
    }
}
