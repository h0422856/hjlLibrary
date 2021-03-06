/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hjl.library.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjl.library.R;
import com.hjl.library.net.LogicHelper;
import com.hjl.library.net.logic.Callback;
import com.hjl.library.net.logic.EventLogic;
import com.hjl.library.net.logic.LogicCallback;
import com.hjl.library.utils.FastClickFilter;
import com.hjl.library.utils.dialog.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Presenter base class for Fragment
 * Presenter层的实现基类
 *
 * @author hiphonezhu@gmail.com
 * @version [AndroidLibrary, 2018-3-6]
 */
public abstract class FragmentPresenter extends Fragment implements LogicCallback, View.OnClickListener {
    protected EventBus eventBus;
    Callback callback;
    private ProgressDialog loadingDialog;
    private Unbinder unbinder;

    public <T> void setCallback(Callback<T> callback) {
        this.callback = callback;
    }

    public <T> void doCall(T data) {
        if (callback != null) {
            callback.call(data);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutId(), container, false);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isDestroyed = false;
    }

    @Override
    public void onClick(View v) {
        if (FastClickFilter.isFastClickAndNet(getActivity())) {
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
        loadingDialog = null;
        isDestroyed = true;
        logicHelper.unregisterAll();
    }

    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected ProgressDialog getInteractingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(getContext(), null, true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        return loadingDialog;
    }


    protected void showProgressDialog(final String message) {
        ProgressDialog dialog = getInteractingDialog();
        if (dialog != null) {
            dialog.showMessage(message);
            dialog.show();
        }
    }

    protected void showProgressDialog() {
        ProgressDialog dialog = getInteractingDialog();
        if (dialog != null) {
            dialog.showMessage(getResources().getString(R.string.loading_progress_text));
            dialog.show();
        }
    }

    /**
     * Dismiss interacting progress dialog
     */
    public void dismissProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    LogicHelper logicHelper = new LogicHelper();
    boolean isDestroyed;

    /**
     * 注册BaseLogic, Activity销毁时是自动取消解绑
     *
     * @param logic
     * @param <T>
     * @return
     */
    protected <T extends EventLogic> T findLogic(EventLogic logic) {
        return logicHelper.findLogic(logic);
    }


    /**
     * EventBus订阅者事件通知的函数, UI线程
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        if (!isDestroyed && !isDetached()) {
            onResponse(msg);
        }
    }

    /**
     * 业务层回调
     *
     * @param msg
     */
    @Override
    public void call(Message msg) {
        if (!isDestroyed && !isDetached()) {
            onResponse(msg);
        }
    }

    protected void onResponse(Message msg) {

    }
}
