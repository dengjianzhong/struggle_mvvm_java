package com.struggle.base.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.struggle.base.R;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/9 15:51
 * @Description 公共标题bar
 */
public class TitleBarView extends FrameLayout {
    private int defaultColor = 0xff2a2a2a;
    private ImageView ivBack;

    public TitleBarView(@NonNull Context context) {
        super(context);
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    /**
     * 初始化标题View
     *
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.layout_titlebar_view, this);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        ivBack = view.findViewById(R.id.iv_back);
        TypedArray attributeSet = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        ColorStateList colorStateList =
                attributeSet.getColorStateList(R.styleable.TitleBarView_back_icon_color);

        /**为titleBar设置属性值**/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && colorStateList != null) {
            ivBack.setImageTintList(colorStateList);
        }
        tvTitle.setText(attributeSet.getString(R.styleable.TitleBarView_center_title));
        tvTitle.setTextColor(attributeSet.getColor(R.styleable.TitleBarView_center_title_color, defaultColor));
        tvTitle.setTextSize(attributeSet.getFloat(R.styleable.TitleBarView_center_title_size, 16f));

        attributeSet.recycle();

        initEvent();
    }

    private void initEvent() {
        ivBack.setOnClickListener(v -> {
            ((Activity) getContext()).finish();
        });
    }
}
