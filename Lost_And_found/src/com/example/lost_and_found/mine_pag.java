package com.example.lost_and_found;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class mine_pag extends Fragment {
	private View view;
	ListView minelv;
	String strr[] = { "个人信息", "丢失记录", "找回记录", "拾到记录", "归还记录", "设置" };// 定义一个String数组用来显示ListView的内容

	int pict[] = { R.drawable.people, R.drawable.dispear, R.drawable.backup,
			R.drawable.book, R.drawable.backnote, R.drawable.setup };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.mine, container, false);
		minelv = (ListView) view.findViewById(R.id.mine_listView);
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/* 在数组中存放数据 */
		SimpleAdapter simple = new SimpleAdapter(getActivity(), listItem,
				R.layout.minedetail, new String[] { "image", "text" },
				new int[] { R.id.imageView, R.id.textView });
		for (int i = 0; i < pict.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", pict[i]);// 加入图片
			map.put("text", strr[i]);
			listItem.add(map);
		}
		minelv.setAdapter(simple);
		minelv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 String str = minelv.getItemAtPosition(position).toString();
				if (str.equals("个人信息")) {

					Intent intent = new Intent();
					intent.setClass(getActivity(), privateInformation.class);
					startActivity(intent);
					
				}
			}
		});

		return view;

	}


}
