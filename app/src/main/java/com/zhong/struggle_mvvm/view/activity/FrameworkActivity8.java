package com.zhong.struggle_mvvm.view.activity;

import android.media.MediaPlayer;

import com.struggle.base.base.basics.BaseActivity;
import com.zhong.struggle_mvvm.databinding.ActivityFramework8Binding;

public class FrameworkActivity8 extends BaseActivity<ActivityFramework8Binding> {
    @Override
    protected void initView() {
        /*bind.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bind.recyclerView.setAdapter(new CoorAdapter());*/

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
    }
}