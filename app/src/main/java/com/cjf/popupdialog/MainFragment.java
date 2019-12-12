package com.cjf.popupdialog;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {

    private PopUpDialog popUpDialog1;
    private PopUpDialog popUpDialog2;
    private PopUpDialog popUpDialog3;
    private PopUpDialog popUpDialog4;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        initView(view);
        initListener();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        popUpDialog1 = new PopUpDialog(R.layout.dialog_layout, getActivity(), PopUpDialog.DIALOG_PARENT_CENTER);
        popUpDialog2 = new PopUpDialog(R.layout.dialog_layout, getActivity(), PopUpDialog.DIALOG_PARENT_UP);
        popUpDialog3 = new PopUpDialog(R.layout.dialog_layout, button3, PopUpDialog.DIALOG_LEFT);
        popUpDialog4 = new PopUpDialog(R.layout.dialog_layout, button4, PopUpDialog.DIALOG_RIGHT);
    }

    /**
     * 绑定事件
     */
    private void initListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        popUpDialog1.setOnClickListener(R.id.dialog_button, this);//弹框中若只有一个控件需要绑定事件，可使用该方法绑定
        int[] ids = {R.id.dialog_button};
        popUpDialog2.setOnClickListener(ids, this);//弹框中若有多个控件需绑定事件，可使用该方法绑定
        popUpDialog3.setOnClickListener(R.id.dialog_button, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.button4:
                if (popUpDialog4.isShowing()) {
                    popUpDialog4.dismiss();
                } else {
                    popUpDialog4.showPopUpWindow();
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
