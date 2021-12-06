package com.zhong.struggle_mvvm.view.fragment;

import androidx.activity.OnBackPressedCallback;

import com.struggle.base.base.mvvm.BaseVMFragment;
import com.struggle.base.launcher.TxToast;
import com.zhong.struggle_mvvm.databinding.FragmentTestBinding;
import com.zhong.struggle_mvvm.logic.mvvm.model.MyModel;
import com.zhong.struggle_mvvm.view.dialog.MyDialog;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:15
 * @Description TODO
 */
public class MyFragment extends BaseVMFragment<FragmentTestBinding, MyModel> {

    private final Boolean noAllowBack = true;

    @Override
    public void initEvent() {
        bind.btTest.setOnClickListener(v -> viewModel.requestGanHuo());

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(noAllowBack) {
            @Override
            public void handleOnBackPressed() {
                setEnabled(false);
                TxToast.showToast("fragment监听到返回");
            }
        });
    }

    @Override
    public void observer() {
        super.observer();

        viewModel.ganHuoLiveData.observe(this, testBeans -> {
            if (testBeans != null && testBeans.size() > 0) {
                MyDialog dialog = new MyDialog();
                dialog.show(getChildFragmentManager());
            }
        });
    }
}
