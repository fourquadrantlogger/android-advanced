package com.example.testimageviewbtn;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxq.ImageBrowserAdapter;
import com.example.lxq.ScrollViewPager;

public class ImageBrowserActivity extends Activity implements OnPageChangeListener{
	
	public static List<String> mList;
	public static int mPosition;
	
	
	private int mTotal;

	private ScrollViewPager mScrollViewPager;
	private ImageBrowserAdapter mAdapter;
	private TextView textView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_imagebrowser);


		mScrollViewPager = (ScrollViewPager) findViewById(R.id.imagebrowser_svp_pager);
		textView = (TextView) findViewById(R.id.imagebrowser_tv_page);

		mScrollViewPager.setOnPageChangeListener(this);


		init();
	}

	private void init() {
		
		if (mList==null||mList.size()==0) {
			return;
		}
		
		mTotal = mList.size();

		if (mTotal > 1) {
			mPosition += 1000 * mTotal;
			textView.setText((mPosition % mTotal) + 1 + "/" + mTotal);
			mAdapter = new ImageBrowserAdapter(this,mList);
			mScrollViewPager.setAdapter(mAdapter);
			mScrollViewPager.setCurrentItem(mPosition, false);
		}else {
			mPosition = 0;
			textView.setText((mPosition % mTotal) + 1 + "/" + mTotal);
			mAdapter = new ImageBrowserAdapter(this,mList);
			mScrollViewPager.setAdapter(mAdapter);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		mPosition = arg0;
		textView.setText((mPosition % mTotal) + 1 + "/" + mTotal);
	}

}
