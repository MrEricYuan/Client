package com.takeout.client.model;

import java.util.List;

/**
 * Created by Eric on 2015/12/20.
 */
public class OrderInfo {
    private String shopImg;
    private String shopName;
    private String shopAddress;
    private String orderNum;
    private String shopNum;
    private String userName;
    private String orderTime;
    private String orderPrice;
    private String orderState;
    private List<OrderSubInfo> orderSubList;

    public List<OrderSubInfo> getOrderSubList() {
        return orderSubList;
    }

    public void setOrderSubList(List<OrderSubInfo> orderSubList) {
        this.orderSubList = orderSubList;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
