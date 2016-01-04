package com.takeout.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.model.ShopInfo;
import com.takeout.client.view.RoundCornerImageView;

/**
 * 搜尋dialog
 */
public class SearchDialog extends Activity implements View.OnClickListener {

    private RoundCornerImageView search_dialog_icon;
    private TextView search_dialog_shop_name;
    private TextView search_dialog_shop_address;
    private TextView search_dialog_shop_desc;
    private ShopInfo shopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialog);
        getIntentData();
        init();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        shopInfo = (ShopInfo) bundle.getSerializable("shop_info");
    }

    private void init() {
        search_dialog_icon = (RoundCornerImageView) findViewById(R.id.search_dialog_icon);
        search_dialog_shop_name = (TextView) findViewById(R.id.search_dialog_shop_name);
        search_dialog_shop_address = (TextView) findViewById(R.id.search_dialog_shop_address);
        search_dialog_shop_desc = (TextView) findViewById(R.id.search_dialog_shop_desc);

        findViewById(R.id.search_dialog_layout).setOnClickListener(this);
        findViewById(R.id.search_dialog_shop_information).setOnClickListener(this);
        findViewById(R.id.search_dialog_collect).setOnClickListener(this);
        findViewById(R.id.search_dialog_shop_menu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_dialog_layout:
                finish();
                break;
            case R.id.search_dialog_shop_information:
                break;
            case R.id.search_dialog_collect:
                break;
            case R.id.search_dialog_shop_menu:
                break;
        }
    }
}
