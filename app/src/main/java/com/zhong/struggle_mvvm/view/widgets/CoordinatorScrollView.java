package com.zhong.struggle_mvvm.view.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.widget.NestedScrollView;

import com.zhong.struggle_mvvm.R;

/**
 * @Author 邓建忠
 * @CreateTime 2022/1/13 14:30
 * @Description 实现协调RecyclerView协调滑动的ScrollView
 */
public class CoordinatorScrollView extends NestedScrollView implements NestedScrollingParent2 {
    private int coordinatorViewId;//需要与ScrollView协调的View
    private int adsorptionId;//需要吸附在协调的View顶部的View
    private int scrollTargetId;//ScrollView参照该View的高度为最大滑动距离

    private int maxScrollHeight;
    private final int coordinatorViewMaxShowHeight = -1;

    public CoordinatorScrollView(@NonNull Context context) {
        super(context);
    }

    public CoordinatorScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public CoordinatorScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorScrollView);
        coordinatorViewId = attributes.getResourceId(R.styleable.CoordinatorScrollView_coordinatorViewId, -1);
        adsorptionId = attributes.getResourceId(R.styleable.CoordinatorScrollView_adsorptionId, -1);
        scrollTargetId = attributes.getResourceId(R.styleable.CoordinatorScrollView_scrollTargetId, -1);
        //coordinatorViewMaxShowHeight = attributes.getDimensionPixelSize(R.styleable.CoordinatorScrollView_coordinatorViewMaxShowHeight, -1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View view = findViewById(coordinatorViewId);
        if (view != null) {
            switch (coordinatorViewMaxShowHeight) {
                case -1:
                    bindCoordinatorView(view);
                    break;
                default:
                    bindCoordinatorView(view, coordinatorViewMaxShowHeight);
                    break;
            }
        }

        View targetView = findViewById(scrollTargetId);
        if (targetView != null) {
            post(() -> {
                setMaxScrollHeight(targetView.getHeight());
            });
        }
    }

    /**
     * 设置CoordinatorScrollView可以滑动的最大高度，然后把滑动事件分发给需要协调的View
     *
     * @param maxScrollHeight
     */
    private void setMaxScrollHeight(int maxScrollHeight) {
        this.maxScrollHeight = maxScrollHeight;

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
        View childAtView = getChildAt(0);
        childAtView.measure(width, height);

        post(() -> {
            int scrollDistance = childAtView.getMeasuredHeight() - getHeight();//可滑动距离
            if (scrollDistance < this.maxScrollHeight) {//如果可滑动距离超出了最大滑动高度，则以最大滑动距离为准
                this.maxScrollHeight = scrollDistance;
            }
        });
    }

    /**
     * 绑定需要协调的View
     *
     * @param view
     */
    private void bindCoordinatorView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);

        post(() -> {
            int adsorptionHeight = 0;
            View adsorptionView = findViewById(adsorptionId);
            if (adsorptionView != null) {
                adsorptionHeight = adsorptionView.getHeight();
            }
            params.height = getHeight() - adsorptionHeight;
            view.setLayoutParams(params);
        });
    }

    /**
     * 绑定需要协调的View
     *
     * @param view
     * @param showHeight 需要协调View的显示高度
     */
    private void bindCoordinatorView(View view, int showHeight) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);

        post(() -> {
            params.height = showHeight;
            view.setLayoutParams(params);
        });
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (dy > 0 && getScrollY() < maxScrollHeight) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }
}
