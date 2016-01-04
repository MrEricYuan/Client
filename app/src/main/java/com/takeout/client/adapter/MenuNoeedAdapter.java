package com.takeout.client.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.takeout.client.R;

import java.util.List;

/**
 * Created by EricYuan on 2015/12/31.
 */
public class MenuNoeedAdapter extends BaseAdapter {
    private List<String> list;

    public MenuNoeedAdapter(List<String> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_detail_child_item, null);
            holder.nameBox = (CheckBox) convertView.findViewById(R.id.menu_detail_child);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameBox.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        private CheckBox nameBox;
    }
}
