package com.hjl.library.net.retrofit.single;

import com.hjl.library.net.retrofit.bean.InfoResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [HjlLibrary, 2019/01/15 11:17]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public interface UrlAPI {
    @POST("bugs/")
    Observable<InfoResult<Object>> postSendCrashLogSever(@Body RequestBody json);

//    @POST("bugs/")
//    Observable<InfoResult<Object>> postSendCrashLogSever(@Body RequestBody params);
}
