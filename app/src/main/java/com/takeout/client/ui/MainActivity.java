package com.takeout.client.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.takeout.client.R;
import com.takeout.client.api.Api;
import com.takeout.client.model.UserInfo;
import com.takeout.client.utils.SharedpreferenceUtil;
import com.takeout.client.utils.Util;
import com.takeout.client.view.SignInDialog;
import com.takeout.client.view.SignOutDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

    private RadioGroup main_tabs;
    private RadioButton main_tab_collect;
    private RadioButton main_tab_order;
    private RadioButton main_tab_search;
    private RadioButton main_tab_user;
    private UserInfo userInfo;
    private List<Fragment> fragments;
    private int currentIndex = 0;
    private boolean isHand = false;
    private Api api;

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initTabData();
    }

    private void init() {
        main_tabs = (RadioGroup) findViewById(R.id.main_tabs);
        main_tab_collect = (RadioButton) findViewById(R.id.main_tab_collect);
        main_tab_order = (RadioButton) findViewById(R.id.main_tab_order);
        main_tab_search = (RadioButton) findViewById(R.id.main_tab_search);
        main_tab_user = (RadioButton) findViewById(R.id.main_tab_user);

        api = new Api(this, handler);
        main_tabs.setOnCheckedChangeListener(this);
    }

    private void initTabData() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new CollectFrag());
        fragments.add(new OrderFrag());
        fragments.add(new SearchFrag());

        userInfo = SharedpreferenceUtil.getUser(this);
        if (TextUtils.isEmpty(userInfo.getUid())) {
            main_tab_user.setChecked(true);
        } else {
            main_tab_collect.setChecked(true);
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.main_content, fragments.get(currentIndex));
//            ft.commit();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_tab_collect:
                setTab(0);
                break;
            case R.id.main_tab_order:
                setTab(1);
                break;
            case R.id.main_tab_search:
                if (isHand) {
                    isHand = false;
                    setSelect(currentIndex);
//                    main_logo.setVisibility(View.GONE);
//                    main_search_layout.setVisibility(View.VISIBLE);
                } else {
                    setTab(2);
                }
                break;
            case R.id.main_tab_user:
                userInfo = SharedpreferenceUtil.getUser(this);
                if (TextUtils.isEmpty(userInfo.getUid())) {
                    showSignInDialog();
                } else {
                    showSignOutDialog();
                }
                break;
        }
    }

    private void setSelect(int select) {
        switch (select) {
            case 0:
                main_tab_collect.setChecked(true);
                break;
            case 1:
                main_tab_order.setChecked(true);
                break;
            case 2:
                main_tab_search.setChecked(true);
                break;
        }
    }

    private void setTab(int selectIndex) {
        fragments.get(currentIndex).onPause(); // 暂停当前tab
        if (currentIndex != selectIndex) {
            FragmentTransaction ft = obtainFragmentTransaction(selectIndex);
            Fragment fragment = fragments.get(selectIndex);
            if (fragment.isAdded()) {
                fragment.onResume(); // 启动目标tab的onResume()
            } else {
                ft.add(R.id.main_content, fragment);
            }
            showTab(selectIndex); // 显示目标tab
            ft.commit();
        } else {
            if(fragments.get(currentIndex).isAdded()) {
                fragments.get(currentIndex).onResume();
            } else {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.main_content, fragments.get(currentIndex));
                ft.commit();
            }

        }
    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentIndex = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if (index > currentIndex) {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && data != null) {
            int state = data.getIntExtra("state", 0);
            if (state == 0) {
                MainActivity.this.finish();
            } else {
                setSelect(currentIndex);
            }
        }
    }

    private void showSignInDialog() {
        final SignInDialog dialog = new SignInDialog(this);
        dialog.setCancelable(false);
        dialog.setClickListener(new SignInDialog.SignInListener() {
            @Override
            public void close() {
                dialog.dismiss();
                MainActivity.this.finish();
            }

            @Override
            public void register() {
                dialog.dismiss();
                showRegisterDialog();
            }

            @Override
            public void signIn(String phone, String pwd) {
                if (TextUtils.isEmpty(phone)) {
                    Util.showToast(MainActivity.this, getString(R.string.phone_is_null));
                    return;
                }
                if (!Util.checkTel(phone)) {
                    Util.showToast(MainActivity.this, getString(R.string.phone_is_illegal));
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Util.showToast(MainActivity.this, getString(R.string.pwd_is_null));
                    return;
                }
                if (pwd.length() < 6) {
                    Util.showToast(MainActivity.this, getString(R.string.pwd_is_illegal));
                    return;
                }
                api.login(phone, pwd);
                UserInfo info = new UserInfo();
                info.setUid("1");
                info.setPhone(phone);
                info.setFamilyName("李");
                info.setName("明");
                info.setSex("男");
                SharedpreferenceUtil.setUser(MainActivity.this, info);
                dialog.dismiss();
                setSelect(currentIndex);
            }
        });
        dialog.show();
    }

    private void showSignOutDialog() {
        final SignOutDialog dialog = new SignOutDialog(this);
        dialog.setCancelable(false);
        dialog.setClickListener(new SignOutDialog.SignOutListener() {
            @Override
            public void close() {
                dialog.dismiss();
                setSelect(currentIndex);
            }

            @Override
            public void signOut() {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        dialog.show();
    }

    private void showRegisterDialog() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 100);
    }

}
