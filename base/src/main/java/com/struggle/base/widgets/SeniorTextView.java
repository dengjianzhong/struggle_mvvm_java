package com.struggle.base.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import com.struggle.base.R;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/9 15:51
 * @Description 具有圆角背景的TextView
 */
public class SeniorTextView extends androidx.appcompat.widget.AppCompatTextView {
    private float leftTopRadius = 0; //左侧顶部圆角半径
    private float rightTopRadius = 0; //右侧顶部圆角半径
    private float rightBottomRadius = 0; //右侧底部圆角半径
    private float leftBottomRadius = 0; //左侧底部圆角半径

    private int solidColor = 0;//默认填充透明色
    private int strokeWidth = 0;//边框宽
    private int strokeColor = 0;//默认边框透明色

    public SeniorTextView(Context context) {
        super(context);

        init(context, null);
    }

    public SeniorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public SeniorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    /**
     * 初始化TextView Shape
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SeniorTextView);
            float radius = attributes.getDimension(R.styleable.SeniorTextView_radius, 0);
            if (radius != 0) {
                leftTopRadius = radius;
                rightTopRadius = radius;
                rightBottomRadius = radius;
                leftBottomRadius = radius;
            } else {
                leftTopRadius = attributes.getDimension(R.styleable.SeniorTextView_leftTopRadius, leftTopRadius);
                rightTopRadius = attributes.getDimension(R.styleable.SeniorTextView_rightTopRadius, rightTopRadius);
                rightBottomRadius = attributes.getDimension(R.styleable.SeniorTextView_rightBottomRadius, rightBottomRadius);
                leftBottomRadius = attributes.getDimension(R.styleable.SeniorTextView_leftBottomRadius, leftBottomRadius);
            }
            solidColor = attributes.getColor(R.styleable.SeniorTextView_solidColor, solidColor);
            strokeWidth = attributes.getDimensionPixelSize(R.styleable.SeniorTextView_stroke_width, 0);
            strokeColor = attributes.getColor(R.styleable.SeniorTextView_stroke_color, strokeColor);
            attributes.recycle();
        }

        /**默认效果*/
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(solidColor);
        normalDrawable.setStroke(strokeWidth, strokeColor);
        normalDrawable.setCornerRadii(new float[]{
                leftTopRadius, leftTopRadius,
                rightTopRadius, rightTopRadius,
                rightBottomRadius, rightBottomRadius,
                leftBottomRadius, leftBottomRadius
        });
        /**按压后的效果*/
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setColor(solidColor != 0 ? getBrighterColor(solidColor) : solidColor);
        pressedDrawable.setStroke(strokeWidth, strokeColor != 0 ? getBrighterColor(strokeColor) : strokeColor);
        pressedDrawable.setCornerRadii(new float[]{
                leftTopRadius, leftTopRadius,
                rightTopRadius, rightTopRadius,
                rightBottomRadius, rightBottomRadius,
                leftBottomRadius, leftBottomRadius
        });

        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        drawable.addState(new int[]{}, normalDrawable);
        setBackground(drawable);

        initTextColorSelector();
    }

    /**
     * 初始化字体颜色按压效果
     */
    private void initTextColorSelector() {
        int textColor = getCurrentTextColor();
        ColorStateList drawable = new ColorStateList(new int[][]{{android.R.attr.state_pressed}, {}},
                new int[]{getBrighterColor(textColor), textColor});
        setTextColor(drawable);
    }

    /**
     * 提取当前颜色的浅色
     *
     * @param color
     */
    private int getBrighterColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        hsv[1] = hsv[1] - 0.15f;
        hsv[2] = hsv[2] + 0.15f;

        return Color.HSVToColor(hsv);
    }
}
