package com.zhong.struggle_mvvm.view.widgets.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.zhong.struggle_mvvm.view.widgets.camera.luban.onSimpleCompressListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import top.zibin.luban.Luban;

/**
 * @Author 邓建忠
 * @CreateTime 2022/2/17 10:12
 * @Description TODO
 */
public class YxCameraView extends SurfaceView implements SurfaceHolder.Callback {
    private Camera camera;
    private int cameraType = 0; //当前选用的摄像头，1前置 0后置
    private Bitmap photoBitmap;
    private onSimpleCompressListener listener;//拍照完成保存图片时的回调

    public YxCameraView(Context context) {
        super(context);
        init();
    }

    public YxCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public YxCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
    }

    /**
     * 设置保存图片监听
     *
     * @param listener
     */
    public void setListener(onSimpleCompressListener listener) {
        this.listener = listener;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        camera = Camera.open(cameraType);
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size size = autoAdjustTheScreen(getWidth(), getHeight());
        if (size != null) {
            parameters.setPreviewSize(size.width, size.height);//设置预览画面尺寸
            parameters.setPictureSize(size.width, size.height);//设置图片的尺寸
        }

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = size.width;
        setLayoutParams(layoutParams);

        initCamera();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (photoBitmap != null) {
            photoBitmap.recycle();
            photoBitmap = null;
        }
        releaseCamera();
        getHolder().removeCallback(this);
    }

    /**
     * 初始化相机
     */
    public void initCamera() {
        camera = camera == null ? Camera.open(cameraType) : camera;
        try {
            setCameraOrientation();
            Camera.Parameters params = camera.getParameters();
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动对焦
            params.setPictureFormat(ImageFormat.JPEG);//照片格式

            camera.setPreviewDisplay(getHolder());//相机预览流附载到SurfaceView
            camera.startPreview();//开启预览
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换摄像头
     */
    public void switchCamera() {
        if (cameraType == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            cameraType = Camera.CameraInfo.CAMERA_FACING_BACK;
        } else {
            cameraType = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }

        releaseCamera();
        initCamera();
    }

    /**
     * 设置相机显示方向（横屏/竖屏）
     */
    public void setCameraOrientation() {
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            camera.setDisplayOrientation(0);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            camera.setDisplayOrientation(90);
        }
    }

    /**
     * 自动调整预览界面
     *
     * @param width
     * @param height
     * @return
     */
    private Camera.Size autoAdjustTheScreen(int width, int height) {
        Camera.Size cameraSize = null;
        float min_diff = 2F;
        float canvasRatio = (float) Math.max(width, height) / (float) Math.min(width, height);
        for (Camera.Size size : camera.getParameters().getSupportedPreviewSizes()) {
            float previewRatio = (float) Math.max(size.height, size.width) / (float) Math.min(size.height, size.width);
            float current_diff = canvasRatio - previewRatio;
            if (width == size.height && current_diff > 0 && current_diff < min_diff) {
                min_diff = current_diff;
                cameraSize = size;
            }
        }
        return cameraSize;
    }

    /**
     * 拍照
     */
    public void photograph() {
        if (camera != null) {
            camera.takePicture(null, null, (data, camera) -> {
                photoBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            });
        }
    }

    /**
     * 保存图片
     */
    public void savePhoto() {
        if (photoBitmap != null) {
            File sourceFile = new File(FileUtils.getCacheDirPath(getContext()), String.format("%d.jpg", System.currentTimeMillis()));
            try {
                Matrix matrix = new Matrix();
                matrix.setRotate(90);//由于Camera拍出来的原图为倒置，需要旋转90度来得到竖直方向的照片

                FileOutputStream outputStream = new FileOutputStream(sourceFile);
                photoBitmap = Bitmap.createBitmap(photoBitmap, 0, 0, photoBitmap.getWidth(), photoBitmap.getHeight(), matrix, true);
                photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

                Luban.with(getContext())
                        .load(sourceFile)
                        .ignoreBy(100)
                        .setTargetDir(FileUtils.getCacheDirPath(getContext()))
                        .filter(path -> !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif")))
                        .setCompressListener((onSimpleCompressListener) file -> {
                            if (listener != null) {
                                listener.onSuccess(file);
                            }
                            sourceFile.delete();
                        })
                        .launch();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止预览
     */
    public void stopPreviewCamera() {
        if (camera != null) {
            camera.stopPreview();
        }
    }

    /**
     * 停止预览并释放相机
     */
    public void releaseCamera() {
        stopPreviewCamera();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
