package com.zhong.struggle_mvvm.view;

import android.view.View;

import androidx.lifecycle.Observer;

import com.struggle.base.base.vm.BaseVMFragment;
import com.struggle.base.launcher.TxToast;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.bean.TestBean;
import com.zhong.struggle_mvvm.databinding.FragmentTestBinding;
import com.zhong.struggle_mvvm.model.MyModel;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:15
 * @Description TODO
 */
public class MyFragment extends BaseVMFragment<FragmentTestBinding, MyModel> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initEvent() {
        bind.btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getChildFragmentManager());

                /*MyPopupWindow window = new MyPopupWindow(getContext());
                window.showAsDropDown(v);*/

                viewModel.requestGanHuo();
            }
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
