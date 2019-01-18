package com.hjl.library.ui.base;

/**
 * 页面展示逻辑基类
 */
public interface Presenter {

    //销去持有外部的mContext;
    void onDestroy();
}
