package com.takeout.client.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.takeout.client.R;
import com.takeout.client.model.MerchantInformation;
import com.takeout.client.utils.AnimateFirstDisplayListener;
import com.takeout.client.utils.DensityUtil;
import com.takeout.client.utils.ImageLoaderConfigFactory;
import com.takeout.client.utils.StringUtils;
import com.takeout.client.view.RoundCornerImageView;

/**
 * Created by 鹏 on 2015/12/18.
 * 店鋪資訊
 */
public class ShopInfoActivity extends Activity implements View.OnClickListener{

    private ImageView shop_back_img;
    private RelativeLayout merchant_title_layout;
    private ImageView merchant_img_iv;
    private RoundCornerImageView merchant_logo_iv;
    private TextView merchant_name_tv;
    private TextView settings_id_tv; //店鋪id
    private TextView settings_addr_tv; //地址
    private TextView settings_link_tv; //聯繫電話
    private TextView settings_ontime_tv; //營業時間
    private TextView settings_web_tv; // 網站
    private TextView settings_remark_tv; //備註
    private TextView settings_card_tv; // 名片信息

    private DisplayMetrics displayMetrics;
    private WindowManager windowManager;

    private RelativeLayout.LayoutParams rlParams;
    private LinearLayout.LayoutParams llParams;

    private ImageLoader imageLoader;
    private ImageLoaderConfigFactory configFactory;

    private MerchantInformation information = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopinfo);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        imageLoader = ImageLoader.getInstance();
        configFactory = ImageLoaderConfigFactory.getInstance();
        initView();
    }

    private void initView(){
        shop_back_img = (ImageView) findViewById(R.id.shop_back_img);
        merchant_title_layout = (RelativeLayout) findViewById(R.id.merchant_title_layout);
        merchant_img_iv = (ImageView) findViewById(R.id.merchant_img_iv);
        merchant_logo_iv = (RoundCornerImageView) findViewById(R.id.merchant_logo_iv);
        merchant_name_tv = (TextView) findViewById(R.id.merchant_name_tv);
        settings_id_tv = (TextView) findViewById(R.id.settings_id_tv);
        settings_addr_tv = (TextView) findViewById(R.id.settings_addr_tv);
        settings_link_tv = (TextView) findViewById(R.id.settings_link_tv);
        settings_ontime_tv = (TextView) findViewById(R.id.settings_ontime_tv);
        settings_web_tv = (TextView) findViewById(R.id.settings_web_tv);
        settings_remark_tv = (TextView) findViewById(R.id.settings_remark_tv);
        settings_card_tv = (TextView) findViewById(R.id.settings_card_tv);
        shop_back_img.setOnClickListener(this);

        rlParams = new RelativeLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels * 9 / 16);
        llParams = new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels * 9 / 16 + DensityUtil.dip2px(this, 40));
        merchant_img_iv.setLayoutParams(rlParams);
        merchant_title_layout.setLayoutParams(llParams);
        initDatas();
    }

    private void initDatas(){
        parseMerchantInfo();
        imageLoader.displayImage(information.getMerchant_bg(), merchant_img_iv, configFactory.getCollectImg(), new AnimateFirstDisplayListener());
        merchant_name_tv.setText(StringUtils.getMerchantNameStr(information.getMerchant_name() + "  -" + information.getMerchant_name_vice(), information.getMerchant_name().length(), this, 25, 13));
        settings_id_tv.setText(information.getMerchant_id());
        settings_addr_tv.setText(information.getMerchant_addr());
        settings_link_tv.setText(information.getMerchant_link());
        settings_ontime_tv.setText(information.getMerchant_showTime());
        settings_web_tv.setText(information.getMerchant_web());
        settings_remark_tv.setText(information.getMerchant_remark());
        settings_card_tv.setText(information.getMerchant_card());
    }

    public MerchantInformation parseMerchantInfo() {
        information = new MerchantInformation();
        information.setMerchant_id("114-283");
        information.setMerchant_bg("http://image1.huangye88.com/2012/09/10/15c368d4f374110b.jpg");
        information.setMerchant_name("五十嵐");
        information.setMerchant_name_vice("敦化店");
        information.setMerchant_addr("台北市大安區忠孝東路27號-3");
        information.setMerchant_card("法式卡布奇諾 熱賣中~ 滿百即可外送");
        information.setMerchant_remark("歡迎攜帶寵物");
        information.setMerchant_link("02-8825252");
        information.setMerchant_showTime("am09~pm10 每週一公休");
        information.setMerchant_remark("歡迎攜帶寵物");information.setMerchant_web("http://www.baidu.com");
        return information;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_back_img:
                finish();
                break;
        }
    }
}
