package com.zhong.struggle_mvvm;

import android.view.View;

import com.struggle.base.base.BaseVMDialog;
import com.struggle.base.base.basics.BaseDialog;
import com.zhong.struggle_mvvm.databinding.DialogTestBinding;
import com.zhong.struggle_mvvm.model.MyModel;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description TODO
 */
public class MyDialog extends BaseVMDialog<DialogTestBinding, MyModel> {
    @Override
    public int getLayoutId() {
        return R.layout.dialog_test;
    }

    @Override
    public void initEvent() {
        bind.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.postNet();
            }
        });
    }
}
