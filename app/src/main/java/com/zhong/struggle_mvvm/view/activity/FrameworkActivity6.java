package com.zhong.struggle_mvvm.view.activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.struggle.base.BaseApp;
import com.struggle.base.base.basics.BaseActivity;
import com.zhong.struggle_mvvm.databinding.ActivityFramework6Binding;
import com.zhong.struggle_mvvm.logic.bean.FirstNode;
import com.zhong.struggle_mvvm.view.adapter.CityAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 二级列表无嵌套演示(3)
 */
public class FrameworkActivity6 extends BaseActivity<ActivityFramework6Binding> {
    @Override
    protected void initView() {
        bind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bind.recyclerView.setAdapter(new CityAdapter(this, parseJson()));
    }

    /**
     * 解析城市列表
     *
     * @return
     */
    private List<FirstNode> parseJson() {
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            inputStream = BaseApp.getContext().getAssets().open("province.json");
            int len;
            byte[] bytes = new byte[5 * 1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();

            Type type = new TypeToken<List<FirstNode>>() {
            }.getType();
            return new Gson().fromJson(new String(outputStream.toByteArray()), type);
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
}