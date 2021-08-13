package com.zhong.struggle_mvvm.view;

import com.struggle.base.base.mvvm.BaseVMFragment;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.FragmentTestBinding;
import com.zhong.struggle_mvvm.model.MyModel;

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
        bind.btTest.setOnClickListener(v -> {
            /*MyPopupWindow window = new MyPopupWindow(getContext());
            window.showAsDropDown(v);*/

            viewModel.requestGanHuo();
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
