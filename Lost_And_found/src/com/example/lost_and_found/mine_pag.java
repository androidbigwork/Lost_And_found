package com.example.lost_and_found;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class mine_pag extends Activity{

	ListView lv;
	String string;
	String[] strs = new String[] {
		    "������Ϣ", "��ʧ��¼", "�һؼ�¼", "ʰ����¼", "�黹��¼","����"
		    };//����һ��String����������ʾListView������
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mine);
	        
	        lv=(ListView) findViewById(R.id.main_listView);
	        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs));
	 lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			string=lv.getItemAtPosition(position).toString();
			
		}
	});
	 
	 
	 }
}
