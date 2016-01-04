package com.takeout.client.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takeout.client.R;
import com.takeout.client.adapter.MenuAdapter;
import com.takeout.client.model.MenuInfo;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment implements OnRefreshListener{

	private static final String ARG_POSITION = "position";
	private int position;
	private RecyclerView menu_rv;
	private View viewFlayout = null;
	private LinearLayoutManager mLayoutManager;
	private MenuAdapter adapter;
	private List<MenuInfo> list;

	public static MenuFragment newInstance(int position) {
		MenuFragment f = new MenuFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		viewFlayout = inflater.inflate(R.layout.frag_menu, container, false);
		initView();
		return viewFlayout;
	}

	private void initView(){
		menu_rv = (RecyclerView) viewFlayout.findViewById(R.id.menu_rv);
		mLayoutManager = new LinearLayoutManager(getActivity());
		menu_rv.setLayoutManager(mLayoutManager);
		menu_rv.setItemAnimator(new DefaultItemAnimator());

		list = new ArrayList<MenuInfo>();
		list.add(new MenuInfo("豆漿"));
		list.add(new MenuInfo("紅茶"));
		list.add(new MenuInfo("烏龍茶"));
		list.add(new MenuInfo("多多綠茶"));
		list.add(new MenuInfo("原味奶茶"));
		list.add(new MenuInfo("豆漿紅茶"));
		list.add(new MenuInfo("鴛鴦奶茶"));
		list.add(new MenuInfo("珍珠奶茶"));
		list.add(new MenuInfo("紅茶拿鐵"));
		list.add(new MenuInfo("仙草奶昔"));
		list.add(new MenuInfo("紅茶瑪奇朵"));
		adapter = new MenuAdapter(getActivity(), list);
		menu_rv.setAdapter(adapter);
	}

	@Override
	public void onRefresh() {

	}
}