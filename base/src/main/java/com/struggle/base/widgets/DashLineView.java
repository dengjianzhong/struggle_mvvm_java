package com.struggle.base.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.struggle.base.R;

/**
 * @Author 邓建忠
 * @CreateTime 2021/10/9 15:30
 * @Description 绘制虚线
 */
public class DashLineView extends View {

    private final Paint mPaint;

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.color_53E69D));
        mPaint.setStrokeWidth(3);
        mPaint.setPathEffect(new DashPathEffect(new float[]{2, 8}, 0));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        canvas.drawLine(0, centerY, getWidth(), centerY, mPaint);
    }
}