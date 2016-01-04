package com.takeout.client.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.adapter.SearchAdapter;
import com.takeout.client.api.RequestConstant;
import com.takeout.client.model.ShopInfo;
import com.takeout.client.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class SearchFrag extends Fragment {

    private View parentView;
    private EditText search_et;
    private RecyclerView search_rv;
    private LinearLayoutManager mLayoutManager;
    private SearchAdapter adapter;
    private List<ShopInfo> list;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RequestConstant.REQUEST_1:
                    list = new ArrayList<ShopInfo>();
                    list.add(new ShopInfo());
                    list.add(new ShopInfo());
                    adapter = new SearchAdapter(list, itemClickListener);
                    search_rv.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.frag_search, container, false);
        init();
        return parentView;
    }

    private void init(){
        search_et = (EditText) parentView.findViewById(R.id.search_et);
        search_rv = (RecyclerView) parentView.findViewById(R.id.search_rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        search_rv.setLayoutManager(mLayoutManager);
        search_rv.setItemAnimator(new DefaultItemAnimator());
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    colseKey();
                    String keyword = search_et.getText().toString();
                    if (TextUtils.isEmpty(keyword)) {
                        Util.showToast(getActivity(), getString(R.string.search_is_null));
                        return false;
                    }
                    handler.sendEmptyMessageDelayed(RequestConstant.REQUEST_1, 1000);
                    return true;
                }
                return false;
            }
        });
    }

    // 关闭键盘输入框
    private void colseKey() {
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(search_et.getWindowToken(), 0);
    }

    private SearchAdapter.ItemClickListener itemClickListener = new SearchAdapter.ItemClickListener() {
        @Override
        public void click(int position) {
            Intent intent = new Intent(getActivity(),SearchDialog.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("shop_info", list.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}