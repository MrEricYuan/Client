package com.takeout.client.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.api.Api;
import com.takeout.client.model.UserInfo;
import com.takeout.client.utils.DatePickerUtil;
import com.takeout.client.utils.SharedpreferenceUtil;
import com.takeout.client.utils.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注册
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText register_phone;
    private EditText register_pwd;
    private EditText register_repwd;
    private EditText register_family_name;
    private EditText register_name;
    private TextView register_birthday_text;
    private TextView register_sex_text;
    private String[] sexSelect;
    private int position;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_register);
        init();
    }

    private void init(){
        register_phone = (EditText) findViewById(R.id.register_phone);
        register_pwd = (EditText) findViewById(R.id.register_pwd);
        register_repwd = (EditText) findViewById(R.id.register_repwd);
        register_family_name = (EditText) findViewById(R.id.register_family_name);
        register_name = (EditText) findViewById(R.id.register_name);
        register_birthday_text = (TextView) findViewById(R.id.register_birthday_text);
        register_sex_text = (TextView) findViewById(R.id.register_sex_text);

        findViewById(R.id.register_birthday_layout).setOnClickListener(this);
        findViewById(R.id.register_sex_layout).setOnClickListener(this);
        findViewById(R.id.register_close).setOnClickListener(this);
        findViewById(R.id.register_btn).setOnClickListener(this);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        register_birthday_text.setText(format.format(new Date()));
        sexSelect = getResources().getStringArray(R.array.sex);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sexSelect);
        register_sex_text.setText("男");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_birthday_layout:
                DatePickerUtil util = new DatePickerUtil(this, datePickerListener);
                util.show();
                break;
            case R.id.register_sex_layout:
                showDialogSex();
                break;
            case R.id.register_close:
                Intent intent = new Intent();
                intent.putExtra("state", 0);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.register_btn:
                register();
                break;
        }
    }

    private void register(){
        String phone = register_phone.getText().toString();
        String pwd = register_pwd.getText().toString();
        String repwd = register_repwd.getText().toString();
        String familyName = register_family_name.getText().toString();
        String name = register_name.getText().toString();
        String birthday = register_birthday_text.getText().toString();
        String sex = sexSelect[position];
        if(TextUtils.isEmpty(phone)) {
            Util.showToast(this, getString(R.string.phone_is_null));
            return;
        }
        if(!Util.checkTel(phone)) {
            Util.showToast(this, getString(R.string.phone_is_illegal));
            return;
        }
        if(TextUtils.isEmpty(pwd)) {
            Util.showToast(this, getString(R.string.pwd_is_null));
            return;
        }
        if(pwd.length()<6) {
            Util.showToast(this, getString(R.string.pwd_is_illegal));
            return;
        }
        if(TextUtils.isEmpty(repwd)) {
            Util.showToast(this, getString(R.string.repwd_is_null));
            return;
        }
        if(!pwd.equals(repwd)) {
            Util.showToast(this, getString(R.string.pwd_is_inconsistent));
            return;
        }
        if(TextUtils.isEmpty(familyName)) {
            Util.showToast(this, getString(R.string.family_name_is_null));
            return;
        }
        if(TextUtils.isEmpty(name)) {
            Util.showToast(this, getString(R.string.name_is_null));
            return;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUid("1");
        userInfo.setFamilyName(familyName);
        userInfo.setName(name);
        userInfo.setPhone(phone);
        userInfo.setPwd(pwd);
        userInfo.setBirthday(birthday);
        userInfo.setSex(sex);
        Api api = new Api(this, handler);
        api.register(userInfo);
        SharedpreferenceUtil.setUser(this, userInfo);
        Intent intent = new Intent();
        intent.putExtra("state", 1);
        setResult(RESULT_OK, intent);
        RegisterActivity.this.finish();
    }

    private void showDialogSex() {
        final String[] strs = new String[]{"女", "男"};
        Dialog dialog = new AlertDialog.Builder(this).setTitle("選擇性別").setItems(strs,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        register_sex_text.setText(which == 1 ? "男" : "女");
                    }
                }).create();
        dialog.show();
    }


    private DatePickerUtil.DatePickerListener datePickerListener = new DatePickerUtil.DatePickerListener() {
        @Override
        public void finish(int year, int month, int day) {
            register_birthday_text.setText(year+"/"+month+"/"+day);
        }
    };

}
