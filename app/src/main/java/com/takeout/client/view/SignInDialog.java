package com.takeout.client.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.takeout.client.R;

/**
 * 自定义登入对话框
 *
 * @author louise
 */
public class SignInDialog extends Dialog {

    private SignInListener clickListener;

    private EditText sign_in_phone;

    private EditText sign_in_pwd;

    private ImageView sign_in_close;

    private Button sign_in_register;

    private Button sign_in_btn;

    public SignInDialog(Context context) {
        super(context,R.style.DialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sign_in);
        sign_in_phone = (EditText) findViewById(R.id.sign_in_phone);
        sign_in_pwd = (EditText) findViewById(R.id.sign_in_pwd);
        sign_in_close = (ImageView) findViewById(R.id.sign_in_close);
        sign_in_register = (Button) findViewById(R.id.sign_in_register);
        sign_in_btn = (Button) findViewById(R.id.sign_in_btn);

        sign_in_close.setOnClickListener(listener);
        sign_in_register.setOnClickListener(listener);
        sign_in_btn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                switch (v.getId()) {
                    case R.id.sign_in_close:
                        clickListener.close();
                        break;
                    case R.id.sign_in_register:
                        clickListener.register();
                        break;
                    case R.id.sign_in_btn:
                        String phone = sign_in_phone.getText().toString();
                        String pwd = sign_in_pwd.getText().toString();
                        clickListener.signIn(phone, pwd);
                        break;
                }
            }
        }
    };

    public void setClickListener(SignInListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface SignInListener {
        public void close();

        public void register();

        public void signIn(String phone, String pwd);
    }
}
