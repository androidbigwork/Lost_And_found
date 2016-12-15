package com.example.lost_and_found;



import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

public class mine_pag extends Fragment {

	ListView minelv;
	String string;
	String[] strs = new String[] { "个人信息", "丢失记录", "找回记录", "拾到记录", "归还记录", "设置" };// 定义一个String数组用来显示ListView的内容

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.mine, container, false);
	}
}
