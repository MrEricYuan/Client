package com.takeout.client.model;

import java.io.Serializable;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 13:51
 * 描述：商铺信息数据元
 */
public class MerchantInformation implements Serializable {
    private String merchant_bg;
    private String merchant_id;
    private String merchant_addr;
    private String merchant_link;
    private String merchant_showTime;
    private String merchant_web;
    private String merchant_remark;
    private String merchant_card;
    private String merchant_name;
    private String merchant_name_vice;

    public String getMerchant_bg() {
        return merchant_bg;
    }

    public void setMerchant_bg(String merchant_bg) {
        this.merchant_bg = merchant_bg;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getMerchant_addr() {
        return merchant_addr;
    }

    public void setMerchant_addr(String merchant_addr) {
        this.merchant_addr = merchant_addr;
    }

    public String getMerchant_link() {
        return merchant_link;
    }

    public void setMerchant_link(String merchant_link) {
        this.merchant_link = merchant_link;
    }

    public String getMerchant_showTime() {
        return merchant_showTime;
    }

    public void setMerchant_showTime(String merchant_showTime) {
        this.merchant_showTime = merchant_showTime;
    }

    public String getMerchant_web() {
        return merchant_web;
    }

    public void setMerchant_web(String merchant_web) {
        this.merchant_web = merchant_web;
    }

    public String getMerchant_remark() {
        return merchant_remark;
    }

    public void setMerchant_remark(String merchant_remark) {
        this.merchant_remark = merchant_remark;
    }

    public String getMerchant_card() {
        return merchant_card;
    }

    public void setMerchant_card(String merchant_card) {
        this.merchant_card = merchant_card;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getMerchant_name_vice() {
        return merchant_name_vice;
    }

    public void setMerchant_name_vice(String merchant_name_vice) {
        this.merchant_name_vice = merchant_name_vice;
    }
}
