package com.zhong.struggle_mvvm.view.activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.struggle.base.base.basics.BaseActivity;
import com.zhong.struggle_mvvm.databinding.ActivityFramework9Binding;
import com.zhong.struggle_mvvm.logic.utils.ViewUtils;
import com.zhong.struggle_mvvm.view.adapter.ContentAdapter;
import com.zhong.struggle_mvvm.view.adapter.CoorAdapter;

import java.util.ArrayList;
import java.util.List;

public class FrameworkActivity9 extends BaseActivity<ActivityFramework9Binding> {

    @Override
    protected void initData() {
        CoorAdapter coorAdapter1 = new CoorAdapter();
        ContentAdapter contentAdapter = new ContentAdapter();

        bind.titleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bind.titleRecyclerView.setAdapter(coorAdapter1);

        bind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bind.recyclerView.setAdapter(contentAdapter);

        bind.scrollView.post(() -> {
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add("");
            }
            coorAdapter1.setNewInstance(list);
            contentAdapter.setNewInstance(list);
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        ViewUtils.setListViewOnTouchAndScrollListener(bind.titleRecyclerView, bind.recyclerView);
    }
}