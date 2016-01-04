package com.takeout.client.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 14:23
 * 描述：$TODO
 */
public class StringUtils {

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if ("null".equals(str)) {
            return true;
        }
        if (str == null) {
            return true;
        }
        return false;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static CharSequence getMerchantNameStr(String source, int splitIndex, Context context, int firstSize, int secondSize) {
        if (isEmpty(source))
            return "";
        Spannable wordtoSpan = new SpannableString(source);
        wordtoSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(context, firstSize), false), 0, splitIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(context, secondSize), false), splitIndex, source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return wordtoSpan;
    }
}
