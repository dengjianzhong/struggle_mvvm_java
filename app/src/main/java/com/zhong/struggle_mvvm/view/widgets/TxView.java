package com.zhong.struggle_mvvm.view.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        String content = "今天(9月9日)，四川省文物考古研究院召开“考古中国”重要项目——三星堆遗址考古发掘阶段性成果新闻通气会，通报三星堆遗址祭祀区三号坑、四号坑等阶段性重大考古成果。\n" +
                "\n" +
                "　　三星堆又有哪些新发现呢?一起来看吧\n" +
                "\n" +
                "　　神树纹玉琮\n" +
                "\n" +
                "　　3号坑发现的神树纹玉琮，整件器物由整块灰白色玉料加工而成，对应的两侧线刻神树纹样，刻痕甚浅。带有神树纹的玉琮前所未见，为今人研究古蜀社会中神树的意义、象征等问题提供了重要依据。";
        /*StaticLayout layout = new StaticLayout(
                content,
                0,
                content.length(),
                textPaint,
                (int) (width * 0.5),
                Layout.Alignment.ALIGN_CENTER,
                1.5f,
                0f,
                true);*/
        StaticLayout layout = new StaticLayout(content,
                textPaint,
                width - margin * 2,
                Layout.Alignment.ALIGN_NORMAL,
                1.5f,
                0f,
                true);

        canvas.save();
        //canvas.translate(margin, Math.abs(height / 2 - layout.getHeight() / 2));
        canvas.translate(margin, height / 2);
        layout.draw(canvas);
        canvas.restore();
    }
}
