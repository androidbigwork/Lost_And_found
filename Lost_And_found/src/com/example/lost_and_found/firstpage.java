package com.example.lost_and_found;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class firstpage extends FragmentActivity implements OnClickListener {

	private LinearLayout mtabFind;
	private LinearLayout mtabLost;
	private LinearLayout mtabMine;
	private LinearLayout mtabGet;

	private ImageButton ImgFind;
	private ImageButton ImgLost;
	private ImageButton ImgMine;
	private ImageButton ImgGet;

	private Fragment findtab;
	private Fragment losttab;
	private Fragment getbacktab;
	private Fragment minetab;

	private ViewPager viewpager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.centerpage);
		initView();
		ininevents();
		setSelect(0);

	}

	private void setSelect(int i) {
		// 设置图片为亮色

	
		setTab(i);
		viewpager.setCurrentItem(i);
	}

	private void setTab(int i){
		switch (i) {
		case 0:
			ImgFind.setImageResource(R.drawable.find);
			break;

		case 1:

			ImgLost.setImageResource(R.drawable.lost);
			break;
		case 2:

			ImgGet.setImageResource(R.drawable.getback);
			break;
		case 3:

			ImgMine.setImageResource(R.drawable.myself);
			break;
		default:
			break;
		}
	}
	private void ininevents() {
		// TODO Auto-generated method stub
		mtabFind.setOnClickListener(this);
		mtabLost.setOnClickListener(this);
		mtabGet.setOnClickListener(this);
		mtabMine.setOnClickListener(this);

	}

	private void initView() {
		mtabFind = (LinearLayout) findViewById(R.id.find_layout);
		mtabLost = (LinearLayout) findViewById(R.id.lost_layout);
		mtabGet = (LinearLayout) findViewById(R.id.getback_layout);
		mtabMine = (LinearLayout) findViewById(R.id.mine_layout);

		ImgFind = (ImageButton) findViewById(R.id.find_imbtn);
		ImgLost = (ImageButton) findViewById(R.id.lost_imbtn);
		ImgGet = (ImageButton) findViewById(R.id.return_imbtn);
		ImgMine = (ImageButton) findViewById(R.id.minr_imbtn);

		mFragments = new ArrayList<Fragment>();
		viewpager = (ViewPager) findViewById(R.id.id_viewpager);
		findtab = new Find_FrameActivity();
		losttab = new Lost_FrameActivity();
		getbacktab = new Back_FrameActivity();
		minetab = new mine_pag();

		mFragments.add(findtab);
		mFragments.add(losttab);
		mFragments.add(getbacktab);
		mFragments.add(minetab);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mFragments.get(arg0);
			}
		};
		viewpager.setAdapter(mAdapter);
		
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				int currentItem=viewpager.getCurrentItem();
				setTab(currentItem);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/*
	 * 切换图片至暗色
	 */
	private void resetImag() {
		// TODO Auto-generated method stub
		ImgFind.setImageResource(R.drawable.find);
		ImgGet.setImageResource(R.drawable.getback);
		ImgLost.setImageResource(R.drawable.lost);
		ImgMine.setImageResource(R.drawable.myself);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_layout:
			setSelect(0);
			break;

		case R.id.lost_layout:
			setSelect(1);
			break;
		case R.id.getback_layout:
			setSelect(2);
			break;
		case R.id.mine_layout:
			setSelect(3);
			break;
		default:
			break;
		}
	}

}
