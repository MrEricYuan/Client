package com.takeout.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.adapter.MenuDetailAdapter;
import com.takeout.client.adapter.MenuNoeedAdapter;
import com.takeout.client.model.MenuDetail;
import com.takeout.client.model.MenuInfo;
import com.takeout.client.utils.ListViewUtil;
import com.takeout.client.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单详情
 */
public class MenuDetailActivity extends Activity implements View.OnClickListener {

    private TextView menu_detail_title;
    private ExpandableListView menu_detail_lv;
    private TextView menu_detail_num;
    private MenuDetailAdapter adapter;
    private MenuInfo menuInfo;
    private List<MenuDetail> list;
    private boolean[][] states;
    private int num = 1;
    private ListView menu_noneed_lv;
    private MenuNoeedAdapter noeedAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        getIntentData();
        init();
        initData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        menuInfo = (MenuInfo) bundle.getSerializable("menu");
    }

    private void init() {
        menu_detail_title = (TextView) findViewById(R.id.menu_detail_title);
        menu_detail_lv = (ExpandableListView) findViewById(R.id.menu_detail_lv);
        menu_detail_num = (TextView) findViewById(R.id.menu_detail_num);
        menu_noneed_lv = (ListView) findViewById(R.id.menu_noneed_lv);

        menu_detail_title.setText(menuInfo.getName());

        findViewById(R.id.menu_detail_back).setOnClickListener(this);
        findViewById(R.id.menu_detail_sub).setOnClickListener(this);
        findViewById(R.id.menu_detail_add).setOnClickListener(this);
        findViewById(R.id.menu_detail_join_shopcar).setOnClickListener(this);
        menu_detail_lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    private void initData() {
        list = new ArrayList<MenuDetail>();
        List<String> s1 = new ArrayList<String>();
        s1.add("大杯 30");
        s1.add("中杯 20");
        list.add(new MenuDetail("款式",s1));
        List<String> s2 = new ArrayList<String>();
        s2.add("正常");
        s2.add("少冰");
        s2.add("去冰");
        list.add(new MenuDetail("冰塊",s2));
        List<String> s3 = new ArrayList<String>();
        s3.add("正常");
        s3.add("少糖");
        s3.add("半糖");
        s3.add("微糖");
        s3.add("無糖");
        list.add(new MenuDetail("甜度",s3));
        states = new boolean[list.size()][];
        for(int i=0; i<list.size(); i ++) {
            boolean[] s = new boolean[list.get(i).getChild().size()];
            states[i] = s;
        }
        adapter = new MenuDetailAdapter(list, states);
        menu_detail_lv.setAdapter(adapter);
        for(int i=0; i<list.size() ;i++) {
            menu_detail_lv.expandGroup(i);
        }
        ListViewUtil.setListViewHeight(menu_detail_lv);
        List<String> s4 = new ArrayList<String>();
        s4.add("加厚");
        s4.add("珍珠 +10");
        s4.add("粉条 +15");
        s4.add("布丁 +10");
        s4.add("芋圆 +15");
        noeedAdapter = new MenuNoeedAdapter(s4);
        menu_noneed_lv.setAdapter(noeedAdapter);
        ListViewUtil.setListViewHeight(menu_noneed_lv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_detail_back:
                finish();
                break;
            case R.id.menu_detail_sub:
                if(num<=1) {
                    Util.showToast(this, getString(R.string.menu_detail_num_hint));
                    return;
                }
                num--;
                menu_detail_num.setText(num+"");
                break;
            case R.id.menu_detail_add:
                num++;
                menu_detail_num.setText(num+"");
                break;
            case R.id.menu_detail_join_shopcar:
                Util.showToast(this, getString(R.string.menu_detail_join_hint));
                finish();
                break;
        }
    }
}
