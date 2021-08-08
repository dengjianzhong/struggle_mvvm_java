package com.zhong.struggle_mvvm;

import android.view.View;

import com.struggle.base.base.BaseVMFragment;
import com.struggle.base.base.basics.BaseFragment;
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
        bind.btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getChildFragmentManager());

                /*MyPopupWindow window = new MyPopupWindow(getContext());
                window.showAsDropDown(v);*/

                viewModel.postNet();
            }
        });
    }
}
