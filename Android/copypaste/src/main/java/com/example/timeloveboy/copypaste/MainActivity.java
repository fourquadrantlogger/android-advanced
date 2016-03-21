package com.example.timeloveboy.copypaste;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnLongClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    ClipboardManager clipboard;
    TextView textView;
    EditText editText,editText2;
    void initView(){
        textView=(TextView)findViewById(R.id.textView);
        textView.setOnLongClickListener(this);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId()){
            case R.id.textView:
                clipboard= (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("moe",textView.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(MainActivity.this,"复制成功",Toast.LENGTH_SHORT).show();
                return true;

        }
        return false;
    }
}
