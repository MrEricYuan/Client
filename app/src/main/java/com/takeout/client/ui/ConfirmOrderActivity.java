package com.takeout.client.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.takeout.client.R;
import com.takeout.client.adapter.OrderSubAdapter;
import com.takeout.client.model.OrderSubInfo;
import com.takeout.client.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2015/12/23.
 */
public class ConfirmOrderActivity extends Activity {
    private ListView confirm_order_lv;
    private OrderSubAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        initView();
    }
    private void initView(){
        confirm_order_lv = (ListView) findViewById(R.id.confirm_order_lv);
        initData();
    }
    private void initData(){
        List<OrderSubInfo> subList = new ArrayList<>();
        OrderSubInfo subInfo = new OrderSubInfo();
        subInfo.setoSubName("黑糖刨冰");
        subInfo.setoSubNorm("大碗");
        subInfo.setoSubNum("3");
        subInfo.setoSubAdd("少冰" +" 少糖" + " 花生" + " 牛奶" + " 土豆"+"\n"+"仙草"+" 杏仁豆腐");
        subList.add(subInfo);
        OrderSubInfo subInfo1 = new OrderSubInfo();
        subInfo1.setoSubName("珍珠奶茶");
        subInfo1.setoSubNorm("大碗");
        subInfo1.setoSubNum("2");
        subInfo1.setoSubAdd("紅豆" + " 白糖" + " 花生" + " 仙草");
        subList.add(subInfo1);
        OrderSubInfo subInfo2 = new OrderSubInfo();
        subInfo2.setoSubName("多多綠茶");
        subInfo2.setoSubNorm("大杯");
        subInfo2.setoSubNum("4");
        subInfo2.setoSubAdd("少冰" + " 少糖" + " +珍珠");
        subList.add(subInfo2);
        adapter = new OrderSubAdapter(subList);
        confirm_order_lv.setAdapter(adapter);
        Util.setListViewHeight(confirm_order_lv);
    }
}
