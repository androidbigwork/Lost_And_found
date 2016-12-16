package com.example.lost_and_found;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;
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
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

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

	//高德地图
	private LocationManagerProxy aMapManager;
	private ImageButton locImageButton;
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.centerpage);
		initView();
		ininevents();
		setSelect(0);
		//采用高德地图定位
		locImageButton=(ImageButton)findViewById(R.id.locat_imgbtn);
		textView=(TextView)findViewById(R.id.position_text);
		locImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startAmap();
			}
		});


	}
	//开始定位
	private void startAmap() {
		aMapManager = LocationManagerProxy.getInstance(this);
		/*
		 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
		 * API定位采用GPS和网络混合定位方式
		 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
		 */
		aMapManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 2000, 10, mAMapLocationListener);
	}

	private void stopAmap() {
		if (aMapManager != null) {
			aMapManager.removeUpdates(mAMapLocationListener);
			aMapManager.destory();
		}
		aMapManager = null;
	}
	//定位监听者
    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			
		}
		
		@Override
		public void onLocationChanged(AMapLocation location) {
			if (location != null) {
				String desc = "";
				Bundle locBundle = location.getExtras();
				if (locBundle != null) {
					desc = locBundle.getString("desc");
				}

				textView.setText(desc);
			}
		}
	};

	private void setSelect(int i) {
		// 设置图片为亮色

		setTab(i);
		viewpager.setCurrentItem(i);
	}

	private void setTab(int i) {
		View discover_STUB = findViewById(R.id.stb_viewstub);
		switch (i) {
		case 0:
			discover_STUB.setVisibility(View.VISIBLE);
			ImgFind.setImageResource(R.drawable.find);

			break;

		case 1:

			discover_STUB.setVisibility(View.GONE);
			ImgLost.setImageResource(R.drawable.lost);
			break;
		case 2:
			discover_STUB.setVisibility(View.GONE);
			ImgGet.setImageResource(R.drawable.getback);
			break;
		case 3:
			discover_STUB.setVisibility(View.GONE);
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
				int currentItem = viewpager.getCurrentItem();
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
		View inflated, inflated1, inflated2, inflated3;
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
