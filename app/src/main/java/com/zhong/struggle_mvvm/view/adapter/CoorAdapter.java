package com.zhong.struggle_mvvm.view.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhong.struggle_mvvm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/11/23 10:15
 * @Description TODO
 */
public class CoorAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public CoorAdapter() {
        super(R.layout.item_city);

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("");
        }
        setNewInstance(list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Object o) {

    }
}
