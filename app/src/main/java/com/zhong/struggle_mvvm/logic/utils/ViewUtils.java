package com.zhong.struggle_mvvm.logic.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

/**
 * Created by tian on 2018/7/17.
 */

public class ViewUtils {

    /**
     * 两个listView同步滑动
     *
     * @param recyclerView1
     * @param recyclerView2
     */
    public static void setListViewOnTouchAndScrollListener(final RecyclerView recyclerView1, final RecyclerView recyclerView2) {
        LinkedList<RecyclerView.OnScrollListener> listeners = new LinkedList<>();
        RecyclerView.OnScrollListener scrollListener1 = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                recyclerView2.clearOnScrollListeners();
                recyclerView2.scrollBy(dx, dy);
                recyclerView2.addOnScrollListener(listeners.get(1));
            }
        };

        RecyclerView.OnScrollListener scrollListener2 = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                recyclerView1.clearOnScrollListeners();
                recyclerView1.scrollBy(dx, dy);
                recyclerView1.addOnScrollListener(listeners.get(0));
            }
        };
        listeners.add(scrollListener1);
        listeners.add(scrollListener2);

        recyclerView1.addOnScrollListener(listeners.get(0));
        recyclerView2.addOnScrollListener(listeners.get(1));
    }
}
