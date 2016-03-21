package com.example.timeloveboy.xutilshttpspost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.timeloveboy.xutilshttpspost.utils.Mlog;


import org.xutils.image.ImageOptions;
import org.xutils.x;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();
        preparetoshow();
    }

    ImageView imageView_downpic;
    void findview(){
        imageView_downpic=(ImageView)findViewById(R.id.imageView_downpic);
    }

    void preparetoshow(){
        x.image().bind(imageView_downpic, "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg", ImageOptions.DEFAULT);
        Mlog.v("moe"," x.image().bind");
    }
}
