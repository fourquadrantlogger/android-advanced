package com.example.lxq.photoview;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SDK16 {
	public static void postOnAnimation(View view, Runnable r) {
		view.postOnAnimation(r);
	}
}
