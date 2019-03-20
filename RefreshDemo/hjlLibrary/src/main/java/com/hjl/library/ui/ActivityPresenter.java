package com.hjl.library.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.hjl.library.R;
import com.hjl.library.net.LogicHelper;
import com.hjl.library.net.logic.EventLogic;
import com.hjl.library.net.logic.LogicCallback;
import com.hjl.library.ui.base.Presenter;
import com.hjl.library.utils.FastClickFilter;
import com.hjl.library.utils.dialog.ProgressDialog;
import com.hjl.library.utils.slideback.SlideBackAcitivty;
import com.hjl.library.utils.toolbar.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 14:08]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public abstract class ActivityPresenter extends SlideBackAcitivty implements LogicCallback, View.OnClickListener {
    public boolean isDestroyed;
    public LogicHelper logicHelper = new LogicHelper();
    public EventBus eventBus;
    protected ImmersionBar mImmersionBar;
    // ProgressDialog， 如 "加载中..."
    private ProgressDialog loadingDialog;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus = EventBus.getDefault();
        isDestroyed = false;
        if (isImmersionBarEnabled())
            initImmersionBar();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true).keyboardEnable(true).init();
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
        loadingDialog = null;
        isDestroyed = true;
        logicHelper.unregisterAll();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    /**
     * EventBus订阅者事件通知的函数, UI线程
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        if (!isDestroyed && !isFinishing()) {
            onResponse(msg);
        }
    }

    protected void onResponse(Message msg) {

    }

    @Override
    public void call(Message msg) {
        if (!isDestroyed && !isFinishing()) {
            onResponse(msg);
        }
    }

    protected void initTitleBar(int titleBarId, int leftBtn, int rightBtn, int title) {
        titleBar = (TitleBar) findViewById(titleBarId);
        titleBar.setTitle(title);
        if (leftBtn == 0) {
            titleBar.left.setVisibility(View.INVISIBLE);
        } else {
            titleBar.left.setImageResource(leftBtn);
            titleBar.left.setVisibility(View.VISIBLE);
            titleBar.left.setOnClickListener(this);
        }
        if (rightBtn == 0) {
            titleBar.right.setVisibility(View.INVISIBLE);
        } else {
            titleBar.right.setImageResource(rightBtn);
            titleBar.right.setVisibility(View.VISIBLE);
            titleBar.right.setOnClickListener(this);
        }
        ImmersionBar.setTitleBar(ActivityPresenter.this, titleBar.action_bar_rl);
    }

    protected void initTitleBar(int titleBarId, int leftBtn, String rightBtn, int title) {
        titleBar = (TitleBar) findViewById(titleBarId);
        titleBar.setTitle(title);
        if (leftBtn == 0) {
            titleBar.left.setVisibility(View.INVISIBLE);
        } else {
            titleBar.left.setImageResource(leftBtn);
            titleBar.left.setVisibility(View.VISIBLE);
            titleBar.left.setOnClickListener(this);
        }
        if (TextUtils.isEmpty(rightBtn)) {
            titleBar.right.setVisibility(View.INVISIBLE);
        } else {
            titleBar.rightTitle.setText(rightBtn);
            titleBar.rightTitle.setVisibility(View.VISIBLE);
            titleBar.rightTitle.setOnClickListener(this);
        }
        ImmersionBar.setTitleBar(ActivityPresenter.this, titleBar.action_bar_rl);
    }

    @Override
    public void onClick(View v) {
        if (FastClickFilter.isFastClickAndNet(ActivityPresenter.this)) {
            return;
        }
        if (v.getId() == R.id.tb_left) {
            finishActivity();
        }
    }

    protected void finishActivity() {
        finishActivity(null);
    }

    protected void finishActivity(Intent intent) {
        if (intent == null) {
            finish();
        } else {
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    protected ProgressDialog getInteractingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this, null, true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        return loadingDialog;
    }


    protected void showProgressDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressDialog dialog = getInteractingDialog();
                if (dialog != null) {
                    dialog.showMessage(message);
                    dialog.show();
                }
            }
        });
    }

    protected void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressDialog dialog = getInteractingDialog();
                if (dialog != null) {
                    dialog.showMessage(getResources().getString(R.string.loading_progress_text));
                    dialog.show();
                }
            }
        });
    }

    /**
     * Dismiss interacting progress dialog
     */
    public void dismissProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
