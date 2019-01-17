package com.hjl.library.utils.slideback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hjl.library.AppDroid;
import com.hjl.library.utils.slideback.widget.SlideBackLayout;


/**
 * [description about this class]
 *
 * @author hujiao
 * @version [SlideBack-master, 2018/04/08 10:58]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class SlideBackAcitivty extends AppCompatActivity {

    public SlideBackLayout mSlideBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSlideBackLayout = SlideBackHelper.attach(
                // 当前Activity
                SlideBackAcitivty.this, AppDroid.getActivityHelper(),
                // 参数的配置
                new SlideConfig.Builder()
                        // 屏幕是否旋转
                        .rotateScreen(true)
                        // 是否侧滑
                        .edgeOnly(false)
                        // 是否禁止侧滑
                        .lock(false)
                        // 侧滑的响应阈值，0~1，对应屏幕宽度*percent
                        .edgePercent(0.1f)
                        // 关闭页面的阈值，0~1，对应屏幕宽度*percent
                        .slideOutPercent(0.5f).create(),
                // 滑动的监听
                null);
    }
}
