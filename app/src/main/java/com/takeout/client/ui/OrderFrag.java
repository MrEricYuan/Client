package com.takeout.client.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takeout.client.R;
import com.takeout.client.adapter.OrderAdapter;
import com.takeout.client.model.OrderInfo;
import com.takeout.client.model.OrderSubInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 */
public class OrderFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View parentView;

    private SwipeRefreshLayout collect_refresh_widget;

    private RecyclerView collect_rv;

    private LinearLayoutManager mLayoutManager;

    private List<OrderInfo> orderList = null;

    private OrderAdapter orderAdapter = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            collect_refresh_widget.setRefreshing(false);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.frag_collect, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        collect_refresh_widget = (SwipeRefreshLayout) parentView.findViewById(R.id.collect_refresh_widget);
        collect_rv = (RecyclerView) parentView.findViewById(R.id.collect_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        collect_rv.setLayoutManager(mLayoutManager);
        collect_rv.setItemAnimator(new DefaultItemAnimator());
        collect_refresh_widget.setOnRefreshListener(this);
        initDatas();
    }

    private void initDatas() {
        orderList = new ArrayList<>();
        List<OrderSubInfo> subList = new ArrayList<>();
        OrderSubInfo subInfo = new OrderSubInfo();
        subInfo.setoSubName("黑糖刨冰");
        subInfo.setoSubNorm("大碗");
        subInfo.setoSubNum("3");
        subInfo.setoSubAdd("珍珠" + " 少糖" + " 花生" + " 牛奶" + " 土豆"+"\n"+"仙草"+" 杏仁豆腐");
        subList.add(subInfo);
        OrderSubInfo subInfo1 = new OrderSubInfo();
        subInfo1.setoSubName("珍珠奶茶");
        subInfo1.setoSubNorm("大碗");
        subInfo1.setoSubNum("2");
        subInfo1.setoSubAdd("紅豆" + " 白糖" + " 花生" + " 仙草");
        subList.add(subInfo1);

        OrderInfo orderInfo = new OrderInfo();
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo.setOrderSubList(subList);
        orderInfo1.setOrderSubList(subList);
        orderList.add(orderInfo);
        orderList.add(orderInfo1);
        orderAdapter = new OrderAdapter(getActivity(), orderList);
        collect_rv.setAdapter(orderAdapter);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}