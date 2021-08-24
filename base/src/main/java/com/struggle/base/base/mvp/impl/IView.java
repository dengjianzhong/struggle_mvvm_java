package com.struggle.base.base.mvp.impl;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.app.bean.LoadingBean;

public interface IView {
    void showLoad(LoadingBean b);

    void hideLoad();

    void onMessage(DataResponse msg);
}
