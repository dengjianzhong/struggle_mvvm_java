package com.struggle.base.widgets.loadding.renderer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.struggle.base.launcher.UIPix;
import com.struggle.base.utils.UIUtils;

/**
 * @Author 邓建忠
 * @CreateTime 2021/3/5 13:57
 * @Description 加载弹窗布局View
 */
public class LoadingView extends FrameLayout {

    private LinearLayout.LayoutParams layoutParams;
    public int LOADING_ID = 0x100;//加载View ID
    public int CONTENT_ID = 0x101;//加载提示内容
    private LayoutParams params;

    public LoadingView(Context context) {
        super(context);

        initView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }


    @SuppressLint("ResourceType")
    private void initView() {
        Context context = getContext();
        int dp1 = UIPix.dip2px(1);

        /**初始化根布局*/
        params = new LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        params.gravity = Gravity.CENTER;
        setLayoutParams(params);
        CardView cardView = new CardView(context);
        cardView.setCardBackgroundColor(0X78000000);
        cardView.setRadius(dp1 * 7);
        cardView.setCardElevation(0);

        params = new LayoutParams(dp1 * 110, dp1 * 110);
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(params);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dp1 * 10, dp1 * 10, dp1 * 10, dp1 * 10);

        this.layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.layoutParams.setMargins(dp1 * 12, dp1 * 12, dp1 * 12, dp1 * 12);
        DaisyView loadingView = new DaisyView(context);
        loadingView.setId(LOADING_ID);
        loadingView.setLayoutParams(this.layoutParams);

        this.layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(context);
        textView.setId(CONTENT_ID);
        textView.setTextColor(0XFFFFFFFF);
        textView.setTextSize(13);
        textView.setSingleLine();
        textView.setGravity(Gravity.CENTER);

        /**添加到布局*/
        addView(cardView);
        cardView.addView(layout);
        layout.addView(loadingView);
        layout.addView(textView);
    }
}
