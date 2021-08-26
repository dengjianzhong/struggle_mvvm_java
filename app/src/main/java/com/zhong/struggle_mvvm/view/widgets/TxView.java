package com.zhong.struggle_mvvm.view.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author 邓建忠
 * @CreateTime 2021/7/26 15:29
 * @Description TODO
 */
public class TxView extends View {

    private TextPaint textPaint;
    private TextPaint linePaint;

    public TxView(Context context) {
        super(context);
        init();
    }

    public TxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        //textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50f);

        linePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        canvas.drawLine(width / 2, 0, width / 2, height, linePaint);
        canvas.drawLine(0, height / 2, width, height / 2, linePaint);

        int margin = 100;
        String content = "残奥游泳-男女混合50米自由泳接力中国夺金 打破世界纪录";
        /*StaticLayout layout = new StaticLayout(content,
                0,
                content.length(),
                paint,
                (int) (width * 0.6),
                Layout.Alignment.ALIGN_NORMAL,
                1.5f,
                0f,
                true);*/
        StaticLayout layout = new StaticLayout(content,
                textPaint,
                width - margin * 2,
                Layout.Alignment.ALIGN_CENTER,
                1.5f,
                0f,
                true);

        canvas.save();
        canvas.translate(margin, Math.abs(height / 2 - layout.getHeight() / 2));
        layout.draw(canvas);
        canvas.restore();
    }
}
