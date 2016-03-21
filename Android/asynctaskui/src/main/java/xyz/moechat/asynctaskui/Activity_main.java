package xyz.moechat.asynctaskui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.SeekBar;


public class Activity_main extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    Button button_startdownloadtask;
    SeekBar seekBar;
    void init(){
        button_startdownloadtask=(Button)findViewById(R.id.button_startdownloadtask);
        button_startdownloadtask.setOnClickListener(this);
        seekBar=(SeekBar)findViewById(R.id.seekBar);seekBar.setMax(100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_startdownloadtask:

                break;
        }
    }

    class DownloadTask extends AsyncTask<Void,Integer,Boolean> {
        @Override
        protected void onPreExecute() {
            seekBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            seekBar.setMax(values[0]);
        }

        //子线程的方法
        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }
    }
}
