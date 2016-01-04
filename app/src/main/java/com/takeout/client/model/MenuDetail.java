package com.takeout.client.model;

import java.util.List;

/**
 * 菜单详情
 */
public class MenuDetail {

    private String group;
    private List<String> child;

    public MenuDetail(String group, List<String> child) {
        this.group = group;
        this.child = child;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getChild() {
        return child;
    }

    public void setChild(List<String> child) {
        this.child = child;
    }
}
