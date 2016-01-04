package com.takeout.client.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * 日期选择对话框
 */
public class DatePickerUtil {

    private DatePickerListener listener;
    private Context context;

    public DatePickerUtil(Context context, DatePickerListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void show(){
        Calendar cal = Calendar.getInstance();
        final DatePickerDialog mDialog = new DatePickerDialog(context, null,
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        // 手动设置按钮
        final int t_year = cal.get(Calendar.YEAR);
        final int t_monthOfYear = cal.get(Calendar.MONTH) + 1;
        final int t_dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "完成",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 通过mDialog.getDatePicker()获得dialog上的DatePicker组件，然后可以获取日期信息
                        DatePicker datePicker = mDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth() + 1;
                        int day = datePicker.getDayOfMonth();
                        if (year > t_year) {
                            Util.showToast(context, "日期不能超过当前日期");
                            return;
                        }
                        if (year == t_year && month > t_monthOfYear) {
                            Util.showToast(context, "日期不能超过当前日期");
                            return;
                        }
                        if (year == t_year && month == t_monthOfYear
                                && day > t_dayOfMonth) {
                            Util.showToast(context, "日期不能超过当前日期");
                            return;
                        }
                        if(listener != null) {
                            listener.finish(year, month, day);
                        }
                    }
                });
        // 取消按钮，如果不需要直接不设置即可
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        mDialog.show();
    }

    public interface DatePickerListener{
        public void finish(int year, int month, int day);
    }
}
