package com.cjf.popupdialog;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity{

    FrameLayout fl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        fl_content = findViewById(R.id.fl_content);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,new MainFragment()).commit();
    }
}
