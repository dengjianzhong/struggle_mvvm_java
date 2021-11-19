package com.zhong.struggle_mvvm.view.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.struggle.base.launcher.IOHandler;
import com.zhong.struggle_mvvm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/10/21 16:38
 * @Description TODO
 */
public class LuckDrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder;
    private boolean isDrawing = false;

    private Paint paint;
    private Float value = 0F;
    private List<String> prizeList;
    private Paint textPaint;
    private RectF centerRect;

    public LuckDrawSurfaceView(Context context) {
        super(context);

        init();
    }

    public LuckDrawSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LuckDrawSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);

        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);

        prizeList = new ArrayList<>();
        prizeList.add("手机");
        prizeList.add("电脑");
        prizeList.add("冰箱");
        prizeList.add("微波炉");
        prizeList.add("盘子");
        prizeList.add("电饭锅");

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(0xFFFFFFFF);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        isDrawing = true;
        IOHandler.postWork(this);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        isDrawing = true;
        IOHandler.postWork(this);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        IOHandler.remove(this);
    }

    @Override
    public void run() {
        Canvas canvas = holder.lockCanvas();

        handlePainting(canvas);
    }

    private void handlePainting(Canvas canvas) {
        while (true) {
            if (!isDrawing) break;

            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            int centerX, centerY;
            centerX = centerY = Math.min(width, height);
            RectF rectF = new RectF(0, 0, centerX, centerY);
            int radius = Math.min(width, height) / 2;

            canvas.save();
            canvas.rotate(value, centerX / 2, centerY / 2);

            int whirlAngle = 360 / prizeList.size();
            float radiusSpace = (float) (radius * 0.6);
            for (int i = 0; i < prizeList.size(); i++) {
                paint.setColor(i % 2 == 0 ? 0xFF53E69D : 0xFF6F6CFF);
                Path path = new Path();
                path.addArc(rectF, i * whirlAngle, whirlAngle);
                canvas.drawArc(rectF, i * whirlAngle, whirlAngle, true, paint);
                canvas.drawTextOnPath(prizeList.get(i), path, 10, 40, textPaint);

                Matrix matrix = new Matrix();
                matrix.postScale(0.2f, 0.2f);
                matrix.postRotate(-i * whirlAngle);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_luckdrawstart);
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();

                float offsetAngle = (float) Math.toRadians((i + 1) * whirlAngle - whirlAngle / 2);
                float pointX = (float) (radius + Math.cos(offsetAngle) * radiusSpace);
                float pointY = (float) (radius + Math.sin(offsetAngle) * radiusSpace);

                RectF newRectF = new RectF(pointX - 50, pointY - 50, pointX + 50, pointY + 50);
                canvas.drawBitmap(newBitmap, null, newRectF, paint);
            }
            canvas.restore();

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_luckdrawstart);
            float spacing = (float) (radius * 0.4);
            centerRect = new RectF(
                    radius - spacing,
                    radius - spacing,
                    radius + spacing,
                    radius + spacing);
            canvas.drawBitmap(bitmap, null, centerRect, paint);

            isDrawing = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() > centerRect.left
                        && event.getX() < centerRect.right
                        && event.getY() > centerRect.top
                        && event.getY() < centerRect.bottom) {
                    start();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 275 * 6);
        valueAnimator.addUpdateListener(animation -> {
            isDrawing = true;
            value = (Float) animation.getAnimatedValue();
        });
        valueAnimator.setDuration(1000 * 6);
        valueAnimator.start();
    }
}
