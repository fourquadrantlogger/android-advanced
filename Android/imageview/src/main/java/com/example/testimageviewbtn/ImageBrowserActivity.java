package com.example.testimageviewbtn;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.lxq.ImageBrowserAdapter;
import com.example.lxq.ScrollViewPager;
//用于浏览（多个）图片的Activity
public class ImageBrowserActivity extends Activity implements OnPageChangeListener,OnClickListener{
	
	public static List<String> mList;
	public static int position;
	
	
	private int count;

	private ScrollViewPager slideViewPager;

	private ImageBrowserAdapter mAdapter;
	private TextView textView_ImageBrowser,transfer_left,transfer_right;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_imagebrowser);

		slideViewPager = (ScrollViewPager) findViewById(R.id.imagebrowser_svp_pager);
		textView_ImageBrowser = (TextView) findViewById(R.id.textView_ImageBrowser);
		transfer_left=(TextView)findViewById(R.id.textView_left_transfer);
		transfer_right=(TextView)findViewById(R.id.textView_right_transfer);
		transfer_left.setOnClickListener(this);
		transfer_right.setOnClickListener(this);
		slideViewPager.setOnPageChangeListener(this);
		init();
	}

	private void init() {

		if (mList == null || mList.size() == 0) {
			return;
		}
		count = mList.size();
		if (count > 1) {

			textView_ImageBrowser.setText((position % count) + 1 + "/" + count);
			mAdapter = new ImageBrowserAdapter(this, mList);
			slideViewPager.setAdapter(mAdapter);
			slideViewPager.setCurrentItem(position, false);
		} else if(count==1) {
			position = 0;
			textView_ImageBrowser.setText((position % count) + 1 + "/" + count);
			mAdapter = new ImageBrowserAdapter(this,mList);
			slideViewPager.setAdapter(mAdapter);
		}
		else {
			textView_ImageBrowser.setText("0");
			Log.v("reading","没有图片可以显示");
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
		position = arg0;
		textView_ImageBrowser.setText((position % count) + 1 + "/" + count);
	}

	@Override
	public void onClick(View v) {
		String url=mList.get(slideViewPager.getCurrentItem());
		switch (v.getId()){
			case R.id.textView_left_transfer:
				Log.v("moe",url );

				break;
			case R.id.textView_right_transfer:
				Log.v("moe",url );
				break;
		}
	}
}
