package com.example.timeloveboy.copypaste;
/**
 * Created by timeloveboy on 16/4/14.
 */

import android.app.AlertDialog;
import  android.content.Context;
import android.content.DialogInterface;
import  android.graphics.Color;
import android.os.Message;
import android.os.Vibrator;
import android.text.ClipboardManager;
import android.text.Editable;
import  android.text.Layout;
import  android.text.Selection;
import android.util.AttributeSet;
import android.util.Log;
import  android.view.ContextMenu;
import  android.view.Gravity;
import  android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import  android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

/**
 *
 @author chroya
 */
public class TextPage  extends  EditText {
    private  int  off;  //字符串的偏移值
    public TextPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initialize();
    }

    public TextPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initialize();
    }
    public  TextPage(Context context) {
        super (context);
        initialize();
    }
    private   void  initialize(){
        setGravity(Gravity.TOP);
        setBackgroundColor(Color.WHITE);
    }
    @Override
    protected   void  onCreateContextMenu(ContextMenu menu) {
        //不做任何处理，为了阻止长按的时候弹出上下文菜单
        super.onCreateContextMenu(menu);
    }

    @Override
    public boolean  getDefaultEditable() {
        return  false ;
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
    private boolean isLongPressed(float lastX,float lastY, float thisX,float thisY, long lastDownTime,long thisEventTime){
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
                    lastX = event.getX();
                    lastY = event.getY();
                    lastDownTime = new Date().getTime();
                if(mIsLongPressedOK) {
                    line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                    off = layout.getOffsetForHorizontal(line, (int) event.getX());
//                Selection.setSelection(getEditableText(), off);
                }
                break;
            case  MotionEvent.ACTION_MOVE:
                if(mIsLongPressedOK) {
                    line = layout.getLineForVertical(getScrollY() + (int) event.getY());
                    int curOff = layout.getOffsetForHorizontal(line, (int) event.getX());
                    Selection.setSelection(getEditableText(), off, curOff);
                    try {
                        text = getText().subSequence(getSelectionStart(), getSelectionEnd()).toString();
                    } catch (Exception e) {
                        text = "";
                    }
                }
                break ;
            case  MotionEvent.ACTION_UP:
                Log.v("moe",mIsLongPressedOK+"");
                if( mIsLongPressedOK ){
                    //长按模式所做的事
                    showDialog();
                    mIsLongPressedOK=false;
                }else{
                    //移动模式所做的事
                }
                if(mIsLongPressedOK==false){
                    mIsLongPressedOK = isLongPressed(lastX, lastY,event.getX(),event.getY(), lastDownTime,new Date().getTime());
                    if(mIsLongPressedOK==true) {
                        setEditableFactory(android.text.Editable.Factory.getInstance());
                        Vibrator vibrator = (Vibrator) getContext().getSystemService(getContext().VIBRATOR_SERVICE);
                        vibrator.vibrate(50);
                        Toast.makeText(getContext(),"滑动以选取文本",Toast.LENGTH_LONG).show();
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
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("复制内容");
        dialogBuilder.setMessage(text);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("好", new DialogInterface.OnClickListener() {
            //确认按钮的点击事件
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.v("moe", text);
                ClipboardManager cmb = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(text);
            }
        });
        dialogBuilder.setNegativeButton("不", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}