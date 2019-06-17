package com.cjf.popupdialog.popupdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cjf.popupdialog.utils.PopUpDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    PopUpDialog popUpDialog1;
    PopUpDialog popUpDialog2;
    PopUpDialog popUpDialog3;

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        //popupdialog初始化，决定了弹框的特性
        popUpDialog1 = new PopUpDialog(R.id.main_layout,
                R.layout.dialog_layout,
                button1,
                PopUpDialog.DIALOG_LEFT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                this);
        popUpDialog2 = new PopUpDialog(R.id.main_layout,
                R.layout.dialog_layout,
                button2,
                PopUpDialog.DIALOG_RIGHT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                this);
        popUpDialog3 = new PopUpDialog(R.id.main_layout,
                R.layout.dialog_layout,
                button3,
                PopUpDialog.DIALOG_UP,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                this);
    }

    /**
     * 绑定事件
     */
    private void initListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        popUpDialog1.setOnClickListener(R.id.dialog_button, this);//弹框中若只有一个控件需要绑定事件，可使用该方法绑定
        int[] ids = {R.id.dialog_button};
        popUpDialog2.setOnClickListener(ids, this);//弹框中若有多个控件需绑定事件，可使用该方法绑定
        popUpDialog3.setOnClickListener(R.id.dialog_button, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (popUpDialog1.isShowing()) {
                    popUpDialog1.dismiss();
                } else {
                    popUpDialog1.showPopUpWindow();
                }
                break;
            case R.id.button2:
                if (popUpDialog2.isShowing()) {
                    popUpDialog2.dismiss();
                } else {
                    popUpDialog2.showPopUpWindow();
                }
                break;
            case R.id.button3:
                if (popUpDialog3.isShowing()) {
                    popUpDialog3.dismiss();
                } else {
                    popUpDialog3.showPopUpWindow();
                }
                break;
            case R.id.dialog_button:
                if (popUpDialog1.isShowing()) {
                    popUpDialog1.dismiss();
                }
                if (popUpDialog2.isShowing()) {
                    popUpDialog2.dismiss();
                }
                if (popUpDialog3.isShowing()) {
                    popUpDialog3.dismiss();
                }
                break;
        }
    }
}
