package com.takeout.client.api;

import android.content.Context;
import android.os.Handler;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.takeout.client.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by louise on 2015/12/16.
 */
public class Api {
    protected RequestManager requestManager;
    protected Context context;
    protected String url;
    protected Handler mHandler;

    public Api(Context context, Handler mHandler) {
        this.context = context;
        this.mHandler = mHandler;
        requestManager = RequestManager.getInstance(context);
    }

    public void login(final String phone, final String pwd) {
//        url = context.getString(R.string.ip).concat(context.getString(R.string.url_action_list));
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                    }

                }, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                return map;
            }
        };
        requestManager.addToRequestQueue(request);
    }

    public void register(final UserInfo userInfo) {
//        url = context.getString(R.string.ip).concat(context.getString(R.string.url_action_list));
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                    }

                }, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                return map;
            }
        };
        requestManager.addToRequestQueue(request);
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            mHandler.sendEmptyMessage(RequestConstant.REQUEST_ERROR);
        }

    };
}
