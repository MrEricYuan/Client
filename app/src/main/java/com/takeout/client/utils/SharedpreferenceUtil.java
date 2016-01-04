package com.takeout.client.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.takeout.client.model.UserInfo;

/**
 * Created by louise on 2015/12/16.
 */
public class SharedpreferenceUtil {

    public static void setUser(Context context, UserInfo info) {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putString("id", info.getUid());
        et.putString("phone", info.getPhone());
        et.putString("familyName", info.getFamilyName());
        et.putString("name", info.getName());
        et.putString("sex", info.getSex());
        et.putString("birthday", info.getBirthday());
        et.commit();
    }

    public static UserInfo getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        UserInfo info = new UserInfo();
        info.setUid(sp.getString("id", ""));
        info.setPhone(sp.getString("phone", ""));
        info.setFamilyName(sp.getString("familyName", ""));
        info.setName(sp.getString("name", ""));
        info.setSex(sp.getString("sex", ""));
        info.setBirthday(sp.getString("birthday", ""));
        return info;
    }
}
