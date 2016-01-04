package com.takeout.client.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.takeout.client.R;
import com.takeout.client.view.CategoryTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2015/12/19.
 */
public class MenuActivity extends FragmentActivity implements View.OnClickListener{

    private ImageView menu_back_img;
    private CategoryTabStrip category_strip;
    private ViewPager menu_viewPager;
    private MyPagerAdapter adapter;
    private ImageView menu_car_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu);
        initView();
    }

    private void initView(){
        category_strip = (CategoryTabStrip) findViewById(R.id.category_strip);
        menu_viewPager = (ViewPager) findViewById(R.id.menu_viewPager);
        menu_back_img = (ImageView) findViewById(R.id.menu_back_img);
        menu_car_img = (ImageView) findViewById(R.id.menu_car_img);
        menu_back_img.setOnClickListener(this);
        menu_car_img.setOnClickListener(this);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        menu_viewPager.setAdapter(adapter);
        category_strip.setViewPager(menu_viewPager);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final List<String> catalogs = new ArrayList<String>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            catalogs.add(getString(R.string.category_all));
            catalogs.add(getString(R.string.category_all_my));
            catalogs.add(getString(R.string.category_society));
            catalogs.add(getString(R.string.category_subscribe));
            catalogs.add(getString(R.string.category_video));
            catalogs.add(getString(R.string.category_health));
            catalogs.add(getString(R.string.category_sports));
            catalogs.add(getString(R.string.category_tech));
            catalogs.add(getString(R.string.category_car));
            catalogs.add(getString(R.string.category_entertainment));
            catalogs.add(getString(R.string.category_finance));
            catalogs.add(getString(R.string.category_military));
            catalogs.add(getString(R.string.category_world));
            catalogs.add(getString(R.string.category_hot));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catalogs.get(position);
        }

        @Override
        public int getCount() {
            return catalogs.size();
        }

        @Override
        public Fragment getItem(int position) {
            return MenuFragment.newInstance(position);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_back_img:
                finish();
                break;
            case R.id.menu_car_img:
                Intent intent = new Intent(MenuActivity.this,ConfirmOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
