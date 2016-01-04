package com.takeout.client.model;

import java.io.Serializable;

/**
 * 菜单信息
 */
public class MenuInfo implements Serializable {

    private String id;
    private String name;
    private String desc;

    public MenuInfo(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
