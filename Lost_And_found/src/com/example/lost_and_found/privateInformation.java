package com.example.lost_and_found;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class privateInformation extends Activity {

	ListView infolv;
	String string;
	String[] strs = new String[] {

	"学号", "院系", "专业", "姓名", "昵称", "性别", "电话号码" };// 定义一个String数组用来显示ListView的内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.privateinformation_page);
		infolv = (ListView) findViewById(R.id.info_listView);
		infolv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs));
		infolv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});
	}

}
