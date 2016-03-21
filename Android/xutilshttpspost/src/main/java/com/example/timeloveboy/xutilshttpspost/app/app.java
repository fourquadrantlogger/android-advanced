package com.example.timeloveboy.xutilshttpspost.app;

import android.app.Application;
import org.xutils.x;
/**
 * Created by timeloveboy on 16/3/21.
 */
public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
    }
}
