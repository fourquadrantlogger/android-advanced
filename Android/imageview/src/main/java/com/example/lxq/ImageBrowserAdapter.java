package com.example.lxq;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.example.lxq.photoview.PhotoView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

public class ImageBrowserAdapter extends PagerAdapter {

	private Context mContext;
	private List<String> mList;

	public ImageBrowserAdapter(Context context, List<String> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList == null?0:mList.size()>1?Integer.MAX_VALUE:mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		PhotoView photoView = new PhotoView(container.getContext());
		container.addView(photoView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		
//		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
//				mList.get(position % mList.size()));
		
		getImageView(mContext, photoView, mList.get(position % mList.size()));
		
		return photoView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	private void getImageView(Context context, final PhotoView photoView, String url) {
		BitmapUtils bitmapUtils = new BitmapUtils(context);
		
		bitmapUtils.display(photoView, url,
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView imageView, String s,
							Bitmap bitmap,
							BitmapDisplayConfig bitmapDisplayConfig,
							BitmapLoadFrom bitmapLoadFrom) {
						photoView.setImageBitmap(bitmap);
					}

					@Override
					public void onLoadFailed(ImageView imageView, String s,
							Drawable drawable) {
					}

					@Override
					public void onLoading(ImageView container, String uri,
							BitmapDisplayConfig config, long total, long current) {
						super.onLoading(container, uri, config, total, current);
						
						System.out.println("==========="+current);
					}

					@Override
					public void onLoadStarted(ImageView container, String uri,
							BitmapDisplayConfig config) {
						super.onLoadStarted(container, uri, config);
					}

					public void onPreLoad(ImageView container, String uri,
							BitmapDisplayConfig config) {
					};

				});
	}

}
