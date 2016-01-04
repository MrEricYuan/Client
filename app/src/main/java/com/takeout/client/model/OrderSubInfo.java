package com.takeout.client.model;

/**
 * Created by Eric on 2015/12/20.
 */
public class OrderSubInfo {
    private String oSubName;  //子訂單名稱
    private String oSubNorm; // 規格
    private String oSubNum; // 規格
    private String oSubAdd; // 自訂單的添加物

    public String getoSubName() {
        return oSubName;
    }

    public void setoSubName(String oSubName) {
        this.oSubName = oSubName;
    }

    public String getoSubNorm() {
        return oSubNorm;
    }

    public void setoSubNorm(String oSubNorm) {
        this.oSubNorm = oSubNorm;
    }

    public String getoSubNum() {
        return oSubNum;
    }

    public void setoSubNum(String oSubNum) {
        this.oSubNum = oSubNum;
    }

    public String getoSubAdd() {
        return oSubAdd;
    }

    public void setoSubAdd(String oSubAdd) {
        this.oSubAdd = oSubAdd;
    }
}
