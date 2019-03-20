package com.hjl.library.utils;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjl.library.R;

/**
 * Created by peter on 2016/7/13.
 * 防止1秒内连续点击多次
 */
public class FastClickFilter {
    private static long lastClickTime;

    // 点击时顺便检测网络是否连接
    public static boolean isFastClickAndNet(Context context) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        if (!NetworkUtils.isConnected()) {  //顺便判断网络 如果没有网络连接 返回true 提示用户没有网络连接 无法操作
            ToastUtils.showShort(context.getResources().getString(R.string.no_internet_connection_tip));
            return true;
        } else {
            //如果没网 返回true
            return false;
        }
    }

    // 点击时不检测网络是否连接
    public static boolean isFastClick1(Context context) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        //如果没网 返回true
        return false;
    }


}
