package com.hjl.library.ui;

import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;

import com.hjl.library.net.retrofit.bean.InfoResult;
import com.hjl.library.ui.base.NetworkError;
import com.hjl.library.ui.base.Presenter;
import com.hjl.library.ui.base.PublicInit;
import com.hjl.library.utils.slideback.SlideBackAcitivty;

import butterknife.ButterKnife;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [HjlLibrary, 2019/01/16 10:12]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public abstract class BaseActivity extends ActivityPresenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onResponse(Message msg) {
        if (msg.obj instanceof InfoResult) {
            InfoResult infoResult = (InfoResult) msg.obj;
            if (infoResult.isSuccess()) {
                onSuccess(msg.what, infoResult.getData(), infoResult.getCode());
            } else {
                onFailure(msg.what, infoResult.getData(), infoResult.getCode(), infoResult.getErrmsg());
            }
        } else {
            onFailure(msg.what, msg.obj, null, NetworkError.errorMsg(getApplicationContext(), msg.obj));
        }
    }

    /**
     * 成功响应
     *
     * @param requestId    请求Id
     * @param response     响应结果
     * @param responseCode 响应码
     */
    protected void onSuccess(int requestId, Object response, String responseCode) {
    }

    /**
     * 失败响应
     *
     * @param requestId    请求Id
     * @param response     响应结果
     * @param responseCode 响应码
     * @param errmsg       错误信息
     */
    protected void onFailure(int requestId, Object response, String responseCode, String errmsg) {
    }

    public abstract void initView();


    public abstract void initData();

    protected void onCreate() {

    }
}
