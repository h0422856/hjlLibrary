package com.hjl.library.net.logic;



import com.blankj.utilcode.util.GsonUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

import static android.R.attr.src;

/**
 * Post请求格式
 * Created by bodong on 2017/1/20.
 */
public class ApiPost {
    /**
     * 目标对象到JSON数据格式转换Post数据参数
     * Post请求用
     *
     * @param src 目标对象
     */
    public static RequestBody toPost(Object src) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), GsonUtils.toJson(src));
        return body;
    }
}
