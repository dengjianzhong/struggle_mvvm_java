package com.zhong.struggle_mvvm.view;

import androidx.fragment.app.FragmentTransaction;

import com.struggle.base.base.vm.BaseVMActivity;
import com.struggle.base.launcher.TxToast;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityMainBinding;
import com.zhong.struggle_mvvm.model.MyModel;

public class MainActivity extends BaseVMActivity<ActivityMainBinding, MyModel> {
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

    @Override
    public void initData() {
        bind.tvView.setText("你好世界");
    }

    @Override
    public void initEvent() {
        bind.tvView.setOnClickListener(v -> {
            viewModel.requestGanHuo();
        });
    }

    @Override
    public void observer() {
        super.observer();

        viewModel.ganHuoLiveData.observe(this, testBeans -> {
            if (testBeans != null && testBeans.size() > 0) {
                TxToast.showToast("数据请求成功");
            }
        });
    }
}