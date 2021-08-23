package com.struggle.base.base.mvp.impl;

public interface IView {
    void showLoad();

    void showLoad(boolean mCancelable);

    void showLoad(boolean mCancelable, String text);

    void hideLoad();

    void onMessage(String msg);
}
