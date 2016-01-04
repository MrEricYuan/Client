package com.takeout.client.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.takeout.client.R;
import com.takeout.client.model.MenuDetail;

import java.util.List;

/**
 * 菜单详情适配器.
 */
public class MenuDetailAdapter extends BaseExpandableListAdapter {

    private List<MenuDetail> list;
    private boolean[][] states;

    public MenuDetailAdapter(List<MenuDetail> list, boolean[][] states) {
        this.list = list;
        this.states = states;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChild().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChild().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //行是否具有唯一id
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_detail_group_item, null);
        TextView name = (TextView) convertView.findViewById(R.id.menu_detail_group);
        name.setText(list.get(groupPosition).getGroup());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition,final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_detail_child_item, null);
        CheckBox name = (CheckBox) convertView.findViewById(R.id.menu_detail_child);
        name.setText(list.get(groupPosition).getChild().get(childPosition));
        if(states[groupPosition][childPosition]) {
            name.setChecked(true);
        } else {
            name.setChecked(false);
        }
        name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reset(groupPosition);
                    states[groupPosition][childPosition] = true;
                    notifyDataSetChanged();
                } else {
                    states[groupPosition][childPosition] = false;
                }
            }
        });
        return convertView;
    }

    //行是否可选
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private void reset(int group) {
        for(int i=0; i<states[group].length; i++){
            states[group][i] = false;
        }
    }
}
