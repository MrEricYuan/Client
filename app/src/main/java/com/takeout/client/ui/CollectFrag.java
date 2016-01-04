package com.takeout.client.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takeout.client.R;
import com.takeout.client.adapter.CollectAdapter;
import com.takeout.client.model.ShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏
 */
public class CollectFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View parentView;
    private SwipeRefreshLayout collect_refresh_widget;
    private RecyclerView collect_rv;
    private LinearLayoutManager mLayoutManager;
    private CollectAdapter adapter;
    private List<ShopInfo> list;
    private int lastVisibleItem;
    private boolean isAdd;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            collect_refresh_widget.setRefreshing(false);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.frag_collect, container, false);
        init();
        return parentView;
    }

    private void init(){
        collect_refresh_widget = (SwipeRefreshLayout) parentView.findViewById(R.id.collect_refresh_widget);
        collect_rv = (RecyclerView) parentView.findViewById(R.id.collect_rv);

        collect_refresh_widget.setColorSchemeColors(R.color.progress_color1, R.color.progress_color2, R.color.progress_color3, R.color.progress_color4);
        collect_refresh_widget.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mLayoutManager = new LinearLayoutManager(getActivity());
//        collect_rv.setHasFixedSize(true);
        collect_rv.setLayoutManager(mLayoutManager);
        collect_rv.setItemAnimator(new DefaultItemAnimator());
        collect_refresh_widget.setOnRefreshListener(this);
        /*collect_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
//                    collect_refresh_widget.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    handler.sendEmptyMessageDelayed(0, 3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });*/
        list = new ArrayList<ShopInfo>();
        list.add(new ShopInfo());
        list.add(new ShopInfo());
        adapter = new CollectAdapter(getActivity(), list);
        collect_rv.setAdapter(adapter);
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}