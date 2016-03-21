package com.example.timeloveboy.handlermessage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Activity_main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    ImageView imageView;
    void init(){
        imageView=(ImageView)findViewById(R.id.imageView);

    }
    private android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    qiandao_anmiation();
                    break;

                default:

                    break;
            }
        }
    };

    public void qiandao_anmiation() {
        imageView.setImageResource(R.drawable.qiandaochenggong);
        Log.v("reading", "tv_readbook_qiandao");
        AnimationSet set = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.qiandao);
        set.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(set);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Message message=new Message();
        message.what=0;
        handler.sendMessage(message);
    }
}
