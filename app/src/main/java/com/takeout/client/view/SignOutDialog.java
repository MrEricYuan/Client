package com.takeout.client.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.model.UserInfo;
import com.takeout.client.utils.SharedpreferenceUtil;

/**
 * 自定义登入对话框
 *
 * @author louise
 */
public class SignOutDialog extends Dialog {

    private SignOutListener clickListener;

    private TextView sign_out_phone;

    private TextView sign_out_name;

    private ImageView sign_out_close;

    private Button sign_out_btn;

    private UserInfo userInfo;

    public SignOutDialog(Context context) {
        super(context,R.style.DialogStyle);
        userInfo = SharedpreferenceUtil.getUser(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sign_out);
        sign_out_phone = (TextView) findViewById(R.id.sign_out_phone);
        sign_out_name = (TextView) findViewById(R.id.sign_out_name);
        sign_out_close = (ImageView) findViewById(R.id.sign_out_close);
        sign_out_btn = (Button) findViewById(R.id.sign_out_btn);

        sign_out_phone.setText(userInfo.getPhone());
        StringBuffer sb = new StringBuffer();
        sb.append(userInfo.getFamilyName());
        if("男".equals(userInfo.getSex())) {
            sb.append(" 先生");
        } else {
            sb.append(" 女士");
        }
        sign_out_name.setText(sb.toString());
        sign_out_close.setOnClickListener(listener);
        sign_out_btn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                switch (v.getId()) {
                    case R.id.sign_out_close:
                        clickListener.close();
                        break;
                    case R.id.sign_out_btn:
                        clickListener.signOut();
                        break;
                }
            }
        }
    };

    public void setClickListener(SignOutListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface SignOutListener {
        public void close();

        public void signOut();
    }
}
