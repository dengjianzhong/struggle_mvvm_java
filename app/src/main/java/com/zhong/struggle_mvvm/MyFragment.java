package com.zhong.struggle_mvvm;

import android.view.View;

import com.struggle.base.base.BaseFragment;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:15
 * @Description TODO
 */
public class MyFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initEvent() {
        getView().findViewById(R.id.btTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getChildFragmentManager(),"");
            }
        });
    }
}
