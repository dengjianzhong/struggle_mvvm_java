package com.struggle.base.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.struggle.base.R;

/**
 * @Author 邓建忠
 * @CreateTime 2021/10/20 10:18
 * @Description 三角形标签
 */
public class LabelView extends View {
    private float density;

    {
        density = Resources.getSystem().getDisplayMetrics().density;
    }

    private final TextPaint textPaint;//文本画笔

    {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    private final Paint shapePaint;//形状画笔

    {
        shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shapePaint.setStyle(Paint.Style.FILL);
    }

    private float rightBottomRadius = 0;//右下角圆角大小

    private String label = "label";//默认文本
    private int textSize = dip2px(11);//文本默认大小
    private int textColor = 0xFFFFFFFF;//默认文本颜色
    private int shapeColor = 0xFF53E69D;//三角形颜色

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        density = Resources.getSystem().getDisplayMetrics().density;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int index = array.getIndex(i);
            if (index == R.styleable.LabelView_label) {
                label = array.getString(index);
            } else if (index == R.styleable.LabelView_label_size) {
                textSize = array.getDimensionPixelSize(index, textSize);
            } else if (index == R.styleable.LabelView_label_color) {
                textColor = array.getColor(index, textColor);
            } else if (index == R.styleable.LabelView_shape_color) {
                shapeColor = array.getColor(index, shapeColor);
            } else if (index == R.styleable.LabelView_right_bottom_radius) {
                rightBottomRadius = array.getDimension(index, rightBottomRadius);
            }
        }
        array.recycle();
    }


    /**
     * 设置标签文本
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;

        invalidate();
    }

    /**
     * 设置标签文本字体大小
     *
     * @param textSize
     */
    public void setLabelSize(int textSize) {
        this.textSize = textSize;

        invalidate();
    }

    /**
     * 设置文本颜色
     *
     * @param color
     */
    public void setLabelColor(int color) {
        this.textColor = color;

        invalidate();
    }

    /**
     * 设置文本颜色
     *
     * @param color
     */
    public void setShapeColor(int color) {
        this.shapeColor = color;

        invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        /**绘制三角标签背景*/
        {
            shapePaint.setColor(shapeColor);

            Path path = new Path();
            path.moveTo(width, 0);
            path.lineTo(width, height);
            path.lineTo(0, height);
            path.close();

            RectF rectF = new RectF(0, 0, width, height);
            float[] radius = {
                    0, 0,
                    0, 0,
                    rightBottomRadius, rightBottomRadius,
                    0, 0
            };
            Path clipPath = new Path();
            clipPath.addRoundRect(rectF, radius, Path.Direction.CCW);

            canvas.save();
            canvas.clipPath(clipPath);
            canvas.drawPath(path, shapePaint);
            canvas.restore();
        }

        /**绘制三角标签文本*/
        {
            int spacing = (int) (width * 0.1);//间距
            int translateX = width / 2;
            int textWith = width - translateX - spacing;

            textPaint.setTextSize(textSize);
            textPaint.setColor(textColor);

            StaticLayout staticLayout;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                staticLayout = StaticLayout.Builder
                        .obtain(label, 0, label.length(), textPaint, textWith)
                        .setLineSpacing(0, 1f)
                        .setAlignment(Layout.Alignment.ALIGN_CENTER)
                        .build();
            } else {
                staticLayout = new StaticLayout(
                        label,
                        textPaint,
                        textWith,
                        Layout.Alignment.ALIGN_CENTER,
                        1f,
                        0f,
                        false);
            }
            canvas.save();
            canvas.translate(translateX, (float) (height * 0.6));
            staticLayout.draw(canvas);
            canvas.restore();
        }
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    private int dip2px(float dp) {
        return (int) (dp * density + 0.5);
    }
}