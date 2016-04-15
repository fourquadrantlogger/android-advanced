package xyz.moechat.mytextviewdemo;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Vibrator;
import android.text.ClipboardManager;
import android.text.Layout;
import android.text.Selection;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;

import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;

import android.widget.EditText;
import android.widget.Toast;





import java.util.Date;

/**
 * Created by timeloveboy on 16/3/29.
 */
public class MyTextView extends EditText{
    private int off; // 字符串的偏移值

    public MyTextView (Context context) {
        super(context);
        setGravity(Gravity.TOP);
        setBackgroundColor(Color.WHITE);
    }

    public MyTextView (Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.TOP);
        setBackgroundColor(Color.WHITE);
    }

    public MyTextView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setGravity(Gravity.TOP);
        setBackgroundColor(Color.WHITE);
    }
    @Override
    public boolean getDefaultEditable() {
        return false;
    }
    String text;
    /**
     * 判断是否有长按动作发生
     * @param lastX 按下时X坐标
     * @param lastY 按下时Y坐标
     * @param thisX 移动时X坐标
     * @param thisY 移动时Y坐标
     * @param lastDownTime 按下时间
     * @param thisEventTime 移动时间
     * @param longPressTime 判断长按时间的阀值
     */
    long longPressTime=500;
    boolean mIsLongPressedOK=false;
    float lastX,lastY;
    long lastDownTime;
    private boolean isLongPressed(float lastX,float lastY, float thisX,float thisY ,long thisEventTime){
        float offsetX = Math.abs(thisX - lastX);
        float offsetY = Math.abs(thisY - lastY);
        long intervalTime = thisEventTime - lastDownTime;
        if(offsetX <=10 && offsetY<=10 && intervalTime >= longPressTime){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int  action = event.getAction();
        this.requestFocus();

        Layout layout = getLayout();
        int  line = 0;
        switch (action)
        {
            case  MotionEvent.ACTION_DOWN:
                //
                SpannableStringBuilder BASEstyle=new SpannableStringBuilder(this.getText());
                BASEstyle.setSpan(new BackgroundColorSpan(Color.WHITE),0,getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                this.setText(BASEstyle);
                ///

                lastX = event.getX();
                lastY = event.getY();
                lastDownTime = new Date().getTime();
                if(mIsLongPressedOK) {
                    getParent().requestDisallowInterceptTouchEvent(true);

                    line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                    off = layout.getOffsetForHorizontal(line, (int) event.getX());
//                Selection.setSelection(getEditableText(), off);
                }
                break;
            case  MotionEvent.ACTION_MOVE:
                if(mIsLongPressedOK) {
                    try{
                        line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                        int curOff = layout.getOffsetForHorizontal(line, (int) event.getX());
                        if(curOff<off){
                            int temp=off;
                            off=curOff;
                            curOff=temp;
                        }

                        Selection.setSelection(getEditableText(), off, curOff);

                        SpannableStringBuilder style=new SpannableStringBuilder(this.getText());
                        style.setSpan(new BackgroundColorSpan(Color.rgb(225, 225, 225)), off, curOff, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        this.setText(style);

                        text = getText().subSequence(off, curOff).toString();
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
                break ;
            case  MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                Log.v("moe", mIsLongPressedOK + "");
                if( mIsLongPressedOK ){
                    //长按模式所做的事

                    showDialog();
                    mIsLongPressedOK=false;
                }else{
                    //移动模式所做的事

                }
                if(mIsLongPressedOK==false){
                    mIsLongPressedOK = isLongPressed(lastX, lastY,event.getX(),event.getY(),new Date().getTime());
                    if(mIsLongPressedOK==true) {
                        setEditableFactory(android.text.Editable.Factory.getInstance());
                        Vibrator vibrator = (Vibrator) getContext().getSystemService(getContext().VIBRATOR_SERVICE);
                        vibrator.vibrate(50);
                        Toast.makeText(getContext(), "滑动以选取文本", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Selection.setSelection(getEditableText(), off);
                    }
                }else {
                    Selection.setSelection(getEditableText(), off);
                }
                break ;
        }
        return   true ;
    }
    public void showDialog(){
        AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("所复制内容");
        dialogBuilder.setMessage(text);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("好", new DialogInterface.OnClickListener() {
            //确认按钮的点击事件
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ClipboardManager cmb = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(text);
                Log.v("moe",  "复制文本:"+text);
                Toast.makeText(getContext(),"复制成功",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton("不", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog=dialogBuilder.create();
        dialog.show();

    }
}

