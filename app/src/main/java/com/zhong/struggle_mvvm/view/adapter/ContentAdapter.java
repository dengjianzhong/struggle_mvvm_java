package com.zhong.struggle_mvvm.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhong.struggle_mvvm.R;

/**
 * @Author 邓建忠
 * @CreateTime 2022/1/14 11:03
 * @Description TODO
 */
public class ContentAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public ContentAdapter() {
        super(R.layout.item_content);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Object o) {
        holder.setText(R.id.tv_retail_amount, "销售" + holder.getAdapterPosition());
        Log.i("===>", String.format("内容预加载位置%d....", holder.getAdapterPosition()));
    }
}
