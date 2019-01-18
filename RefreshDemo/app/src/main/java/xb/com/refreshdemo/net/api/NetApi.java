package xb.com.refreshdemo.net.api;

import com.hjl.library.net.retrofit.bean.InfoResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import xb.com.refreshdemo.bean.User;
import xb.com.refreshdemo.bean.banner.BannerInfo;
import xb.com.refreshdemo.bean.message.MessageBase;
import xb.com.refreshdemo.bean.tag.ChampionBaseTag;
import xb.com.refreshdemo.roundedimage.bean.FileData;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 17:00]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public interface NetApi {

    @FormUrlEncoded
    @POST("public/signin")
    Observable<InfoResult<User>> getLogin(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("user/{uId}/updatePassWord")
    Observable<InfoResult<User>> updatePassWord(@Path("uId") Object userId, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @GET("classifyTag/tag/getList")
    Observable<InfoResult<List<ChampionBaseTag>>> getList(@FieldMap Map<String, Object> params);

    @GET("message/getCommonMessage/{uId}")
    Observable<InfoResult<MessageBase>> getCommonMessage(@Path("uId") Object userId, @QueryMap Map<String, Object> params);

    @GET("banner/bannerList")
    Observable<InfoResult<BannerInfo>> bannerList(@QueryMap Map<String, Object> params);

    @DELETE("message/{uId}/unread-num")
    Observable<InfoResult<String>> deleteUnreadNum(@Path("uId") Object userId);

    @PATCH("user/updateAuth/{uId}")
    Observable<InfoResult<User>> patchUpdateAuth(@Path("uId") Object userId, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Observable<InfoResult<List<FileData>>> uploadImgs(@Url String url, @PartMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("user/addPersonalShow")
    Observable<InfoResult<String>> addPersonalShow(@FieldMap Map<String, Object> params);
}
