package xb.com.refreshdemo.net.config;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hjl.library.config.BaseNotification;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import xb.com.refreshdemo.MyApplication;
import xb.com.refreshdemo.bean.User;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 17:58]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class AccountManager {

    private static AccountManager instance;

    public static final Object object = new Object();

    private User currentUser;

    private User anonymousUser;

    private static final String USER_CACHE_NAME = "wsx_user";

    public static AccountManager getInstance() {
        if (instance == null) {
            synchronized (object) {
                if (instance == null) {
                    instance = new AccountManager();
                }
            }
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        if (user == null) {
//            clearAccountInfo();
        }
        boolean isChangedUser;
        if (getCurrentUser() != null && user != null) {
            if (getCurrentUser().getuId() != user.getuId()) {
                isChangedUser = true;
            } else {
                isChangedUser = false;
            }
        } else {
            isChangedUser = true;
        }

        if (user != null && this.currentUser != null && !isChangedUser) {
            if (this.currentUser.getPassword() != null) {
                user.setPassword(this.currentUser.getPassword());
            }
            if (this.currentUser.getServiceType() != null) {//更新当前用户的身份 是否是客服
                user.setServiceType(this.currentUser.getServiceType());
                user.setAfterSale(this.currentUser.isAfterSale());
            }
            if (this.currentUser.getThirdAuthToken() != null) {
                user.setThirdAuthToken(this.currentUser.getThirdAuthToken());
                user.setThirdAuthId(this.currentUser.getThirdAuthId());
                user.setThirdAuthType(this.currentUser.getThirdAuthType());
                if (!TextUtils.isEmpty(this.currentUser.getUnionid())) {//微信unionid 微信登录头请求需要传递
                    user.setUnionid(this.currentUser.getUnionid());
                }
            }
        }
        this.currentUser = user;
        writeToCache(user, USER_CACHE_NAME);
        if (isChangedUser) {
            EventBus.getDefault().post(new BaseNotification(BaseNotification.NOTIFY_LOGIN_STATE_CHANGED));
        }
//        EventBus.getDefault().post(new BaseNotification(BaseNotification.NOTIFY_USER_DATA_CHANGED));
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            currentUser = getSerializeObj(USER_CACHE_NAME);
        }
        return currentUser;
    }

    protected void writeToCache(User user, String cacheKey) {
        try {
            SharedPreferences.Editor sharePrefEditor = MyApplication.getInstance().getSharedPreferences().edit();
            String result = serializeObj(user);
            sharePrefEditor.putString(cacheKey, result);
            sharePrefEditor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String serializeObj(User user) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    private User getSerializeObj(String cacheKey) {
        User cacheUser = null;
        try {
            SharedPreferences sp = MyApplication.getInstance().getSharedPreferences();
            if (sp == null || !sp.contains(cacheKey)) {
                return null;
            }
            String str = sp.getString(cacheKey, null);
            String redStr = java.net.URLDecoder.decode(str, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    byteArrayInputStream);
            cacheUser = (User) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheUser;
    }
}
