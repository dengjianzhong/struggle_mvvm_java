package com.zhong.struggle_mvvm;

import androidx.fragment.app.FragmentTransaction;

import com.struggle.base.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        {
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.flContainer, new MyFragment());
            transaction.commit();
        }
    }
}