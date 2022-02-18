package com.zhong.struggle_mvvm.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.gson.Gson;
import com.struggle.base.app.bean.CallLogBean;
import com.struggle.base.base.mvvm.BaseVMActivity;
import com.struggle.base.base.plugins.PermissionPlugin;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.other.NotificationHelper;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityFramework2Binding;
import com.zhong.struggle_mvvm.logic.bean.print.DNBFoodMessageBean;
import com.zhong.struggle_mvvm.logic.mvvm.model.MyModel;
import com.zhong.struggle_mvvm.other.ContentProviderHelper;
import com.zhong.struggle_mvvm.view.dialog.MyDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 架构功能演示(2)
 */
public class FrameworkActivity2 extends BaseVMActivity<ActivityFramework2Binding, MyModel> implements PermissionPlugin {

    private long startTime = 1639701650000L;//查询 短信/电话 开始时间

    @Override
    public void initView() {
        setTransparentStatusBar();
    }

    public synchronized void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                viewModel.requestArticleDetail("5e777432b8ea09cade05263f");
                break;
            case R.id.button3:
                NotificationHelper.init(getApplication());
                NotificationHelper.sendNotification();
                break;
            case R.id.button4:
                startTime = System.currentTimeMillis();
                break;
            case R.id.button5:
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.reset();
                ActivityResultLauncher<Intent> launcher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                });
                launcher1.launch(new Intent());

                ActivityResultLauncher<String> launcher2 = registerForActivityResult(new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                    }
                });
                launcher2.launch("");
                break;
            case R.id.button6:
                openActivity(CameraActivity.class);
                break;
            default:
                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager());
                break;
        }
    }

    @Override
    public void observer() {
        viewModel.articleDetail.observe(this, articleDetailBean -> {
            TxToast.showToast("数据请求成功");
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 解析城市列表
     *
     * @return
     */
    private DNBFoodMessageBean parseJson() {
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            inputStream = getResources().getAssets().open("print.json");
            int len;
            byte[] bytes = new byte[5 * 1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            return new Gson().fromJson(new String(outputStream.toByteArray()), DNBFoodMessageBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) outputStream.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        requestPermission(new String[]{Manifest.permission.READ_CALL_LOG}, b -> {
            if (b) {
                List<CallLogBean> beans = ContentProviderHelper.queryPhone(this, startTime, System.currentTimeMillis());
                if (beans.size() > 0) {
                    Log.i("====>", String.valueOf(beans.size()));
                }
            }
        });

        /*requestPermission(new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, b -> {
            if (b) {
                List<SmsLogBean> beans = ContentProviderHelper.querySms(this, startTime, System.currentTimeMillis());
                if (beans.size() > 0) {
                    Log.i("====>", String.valueOf(beans.size()));
                }
            }
        });*/
    }
}