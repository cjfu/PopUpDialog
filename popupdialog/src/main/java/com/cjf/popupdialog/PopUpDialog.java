package com.cjf.popupdialog;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

/**
 * Created by cjf.
 * 用于在任意位置弹出任意样式的弹框
 */

public class PopUpDialog {

    /**
     * 在依赖View的下方弹出
     */
    public final static int DIALOG_DOWN = 0;
    /**
     * 在依赖View的上方弹出
     */
    public final static int DIALOG_UP = 1;
    /**
     * 在依赖View的左边弹出
     */
    public final static int DIALOG_LEFT = 2;
    /**
     * 在依赖View的右边弹出
     */
    public final static int DIALOG_RIGHT = 3;
    /**
     * 在屏幕下方弹出
     */
    public final static int DIALOG_PARENT_DOWN = 4;
    /**
     * 在屏幕上方弹出
     */
    public final static int DIALOG_PARENT_UP = 5;
    /**
     * 在屏幕左侧弹出
     */
    public final static int DIALOG_PARENT_LEFT = 6;
    /**
     * 在屏幕右侧弹出
     */
    public final static int DIALOG_PARENT_RIGHT = 7;
    /**
     * 在屏幕中间弹出
     */
    public final static int DIALOG_PARENT_CENTER = 8;

    private PopupWindow popupWindow;
    private View rootView;              //父布局View
    private View clickView;             //依赖的View
    private View popView;               //弹出框的View
    private int mode;                   //弹出模式
    private Activity context;

    /**
     * 用于构造一个弹框（点击控件后显示在该视图正上方）
     *
     * @param dialogLayoutId 弹框布局文件（R.layout.xxx）
     * @param clickView      与弹框关联的控件（弹框将显示在该控件的上方）,若不为上弹框(DIALOG_UP)，则可传入null
     * @param mode           弹出模式，用于决定打开方式。
     */
    public PopUpDialog(int dialogLayoutId, @NonNull final View clickView, int mode) {
        context = (Activity) clickView.getContext();
        rootView = context.findViewById(android.R.id.content);
        this.clickView = clickView;
        this.mode = mode;
        popView = context.getLayoutInflater().inflate(dialogLayoutId, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                clickView.setSelected(false);
            }
        });
    }

    /**
     * 仅当mode为DIALOG_PARENT_DOWN、DIALOG_PARENT_UP、DIALOG_PARENT_LEFT、DIALOG_PARENT_RIGHT是，可使用该方法构造。
     *
     * @param dialogLayoutId 弹框布局文件（R.layout.xxx）
     * @param context        Activity实例
     * @param mode           弹出模式，用于决定打开方式。
     */
    public PopUpDialog(int dialogLayoutId, final Activity context, int mode) {
        this(dialogLayoutId, new View(context), mode);
        if (mode == DIALOG_RIGHT || mode == DIALOG_LEFT || mode == DIALOG_UP || mode == DIALOG_DOWN) {
            Log.e("PopUpDialog", "PopUpDialog may not be in the correct location. Please make sure the mode is correct or use another constructor");
        }
    }

    /**
     * @param view 被定位的View
     * @return location[0]为x轴坐标，location[1]为y轴坐标
     */
    private int[] getViewLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    /**
     * @param popupWindow 被定位的View
     * @return location[0]为宽度，location[1]为高度
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
     *
     * @return 屏幕宽
     */
    private int getScreenWidth() {
        if (context != null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.widthPixels;
        } else {
            return 0;
        }
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高
     */
    private int getScreenHeight() {
        if (context != null) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm.heightPixels;
        } else {
            return 0;
        }
    }

    /**
     * 显示弹框
     */
    public void showPopUpWindow() {
        if (popupWindow == null) {
            return;
        }
        switch (mode) {
            case DIALOG_LEFT:
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupLeft);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, getViewLocation(clickView)[0] - getPopUpWindowViewSize(popupWindow)[0],
                        (getViewLocation(clickView)[1] + clickView.getHeight() / 2) - getPopUpWindowViewSize(popupWindow)[1] / 2);
                break;
            case DIALOG_RIGHT:
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupRight);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, getViewLocation(clickView)[0] + clickView.getWidth(),
                        getViewLocation(clickView)[1] + clickView.getHeight() / 2 - getPopUpWindowViewSize(popupWindow)[1] / 2);
                break;
            case DIALOG_DOWN:
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupDown);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, (getViewLocation(clickView)[0]
                                + clickView.getWidth() / 2) - getPopUpWindowViewSize(popupWindow)[0] / 2,
                        getViewLocation(clickView)[1] + clickView.getHeight());
                break;
            case DIALOG_UP:
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupUp);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, (getViewLocation(clickView)[0]
                                + clickView.getWidth() / 2) - getPopUpWindowViewSize(popupWindow)[0] / 2,
                        getViewLocation(clickView)[1] - getPopUpWindowViewSize(popupWindow)[1]);
                break;
            case DIALOG_PARENT_LEFT:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupLeft);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, 0,
                        (getScreenHeight() - getPopUpWindowViewSize(popupWindow)[1]) / 2);
                break;
            case DIALOG_PARENT_RIGHT:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupRight);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, getScreenWidth() - getPopUpWindowViewSize(popupWindow)[0],
                        (getScreenHeight() - getPopUpWindowViewSize(popupWindow)[1]) / 2);
                break;
            case DIALOG_PARENT_DOWN:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupUp);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, (getScreenWidth() - getPopUpWindowViewSize(popupWindow)[0]) / 2,
                        getScreenHeight() - getPopUpWindowViewSize(popupWindow)[1]);
                break;
            case DIALOG_PARENT_UP:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupDown);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, (getScreenWidth() - getPopUpWindowViewSize(popupWindow)[0]) / 2,
                        0);
                break;
            case DIALOG_PARENT_CENTER:
                popupWindow.setFocusable(false);
                popupWindow.setAnimationStyle(R.style.PopupCenter);
                popupWindow.showAtLocation(rootView, Gravity.NO_GRAVITY, (getScreenWidth() - getPopUpWindowViewSize(popupWindow)[0]) / 2,
                        (getScreenHeight() - getPopUpWindowViewSize(popupWindow)[1]) / 2);
                break;
            default:
                break;
        }
        if (clickView != null) {
            clickView.setSelected(true);
        }
    }

    /**
     * 关闭弹框
     */
    public void dismiss() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    /**
     * 判断弹框是否显示
     * @return
     */
    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    /**
     * 设置控件点击事件
     * @param id 控件的id
     * @param onClickListener 事件监听
     */
    public void setOnClickListener(int id, View.OnClickListener onClickListener) {
        if (popView != null) {
            View view = popView.findViewById(id);
            view.setOnClickListener(onClickListener);
        }
    }

    /**
     * 批量设置控件点击事件
     * @param ids 多个控件的id
     * @param onClickListener 事件监听
     */
    public void setOnClickListener(int[] ids, View.OnClickListener onClickListener) {
        if (popView != null) {
            for (int id : ids) {
                View view = popView.findViewById(id);
                view.setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * 获取弹框布局中的控件
     * @param id 控件id（R.id.XX）
     * @return 弹框布局中的控件
     */
    public View findViewById(int id) {
        return popView.findViewById(id);
    }
}
