package com.cjf.popupdialog.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.cjf.popupdialog.popupdialog.R;

/**
 * Created by cjf on 2017/8/14.
 * 用于在任意位置弹出任意样式的弹框
 */

public class PopUpDialog {

    public final static int DIALOG_UP = 1;          //在依赖View的上方弹出
    public final static int DIALOG_LEFT = 2;        //在屏幕中间左侧弹出
    public final static int DIALOG_RIGHT = 3;       //在屏幕中间右侧弹出
    public final static int DIALOG_VIEW_UP = 4;          //在依赖View的上方弹出


    private PopupWindow popupWindow;
    private View rootView;              //父布局View
    private View clickView;             //依赖的View
    private View popView;               //弹出框的View
    private int mode;                   //弹出模式
    private Context context;


    /**
     * 用于构造一个弹框（点击控件后显示在该视图正上方）
     *
     * @param rootViewId    activity的id，即activity跟节点的id。需要在xml中定义（R.id.xxx）
     * @param dialogViewId  弹框布局文件id（R.layout.xxx）
     * @param clickView     与弹框关联的控件（弹框将显示在该控件的上方）,若不为上弹框(DIALOG_UP)，则可传入null
     * @param mode          弹出模式，用于决定打开方式。
     * @param context       上下文
     */
    public PopUpDialog(int rootViewId, int dialogViewId, final View clickView, int mode, int Width, int Height, Activity context) {
        if (context==null){
            return;
        }
        rootView = context.findViewById(rootViewId);
        this.context=context;
        this.clickView = clickView;
        this.mode = mode;
        popView = context.getLayoutInflater().inflate(dialogViewId, null);
        popupWindow = new PopupWindow(popView, Width, Height, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                clickView.setSelected(false);
            }
        });
    }

    /**
     * @param view  被定位的View
     * @return  location[0]为x轴坐标，location[1]为y轴坐标
     */
    private int[] getViewLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    /**
     *
     * @param popupWindow   被定位的View
     * @return  location[0]为宽度，location[1]为高度
     */
    private int[] getPopUpWindowViewSize(PopupWindow popupWindow) {
        int[] size = new int[2];
        if (popupWindow.getContentView() != null) {
            popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int popWidth = popupWindow.getContentView().getMeasuredWidth();
            int popHeight = popupWindow.getContentView().getMeasuredHeight();
            size[0] = popWidth;
            size[1] = popHeight;
        }
        return size;
    }

    /**
     * 获取屏幕宽度
     * @return  屏幕宽
     */
    private int getScreenWidth(){
        if (context != null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.widthPixels;
        }else{
            return 0;
        }
    }

    /**
     * 获取屏幕高度
     * @return  屏幕高
     */
    private int getScreenHeight(){
        if (context != null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.heightPixels;
        }else{
            return 0;
        }
    }

    public void changeClickView(View view){
        clickView=view;
    }

    public void showPopUpWindow() {
        if (popupWindow == null) {
            return;
        }
        switch (mode) {
            case DIALOG_UP:
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupUp);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, (getViewLocation(clickView)[0]
                                + clickView.getWidth() / 2) - getPopUpWindowViewSize(popupWindow)[0] / 2,
                        getViewLocation(clickView)[1] - getPopUpWindowViewSize(popupWindow)[1]);
                break;
            case DIALOG_LEFT:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupLeft);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, 0,
                        (getScreenHeight()-getPopUpWindowViewSize(popupWindow)[1])/2);
                break;
            case DIALOG_RIGHT:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupRight);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, getScreenWidth()-getPopUpWindowViewSize(popupWindow)[0],
                        (getScreenHeight()-getPopUpWindowViewSize(popupWindow)[1])/2);
                break;
            default:
                break;
        }
        clickView.setSelected(true);
    }

    public void dismiss(){
        if(popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public boolean isShowing(){
        return popupWindow.isShowing();

    }

    public void setOnClickListener(int id, View.OnClickListener onClickListener){
        if(popView!=null){
            View view = popView.findViewById(id);
            view.setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(int[] ids, View.OnClickListener onClickListener){
        if(popView!=null){
            for (int id : ids) {
                View view = popView.findViewById(id);
                view.setOnClickListener(onClickListener);
            }
        }
    }

    public View getPopView(){
        return popView;
    }
}
