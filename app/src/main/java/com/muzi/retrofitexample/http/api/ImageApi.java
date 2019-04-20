package com.muzi.retrofitexample.http.api;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 作者: lipeng
 * 时间: 2019/4/20
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public interface ImageApi {

    @POST("image")
    @FormUrlEncoded
    Observable<ResponseBody> uploadBase64(@Field("image") String image);

    @Multipart
    @POST("image")
    Observable<ResponseBody> uploadMultipart(@Part MultipartBody.Part parts);

    @POST("image")
    Observable<ResponseBody> uploadImage(@Body RequestBody body);

    @Multipart
    @POST("image")
    Observable<ResponseBody> uploadImageText(@Part MultipartBody.Part file2,
                                             @Query("token") String token);

}
