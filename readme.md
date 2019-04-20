1. Base64格式
```java
   @POST("image")
   @FormUrlEncoded
   Observable<ResponseBody> upload(@Field("image") String image);
```

2. ```
   @Multipart
   @POST("image")
   Observable<ResponseBody> upload(@Part MultipartBody.Part parts);
   ```

   构建```MultipartBody.Part```

   ```
   RequestBody requestFile = MultipartBody.create(MultipartBody.FORM, file);
   MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
   ```

   这里的```files```问服务端的同学要，这是他们获取参数的```key```

3. ```
   @POST("image")
   Observable<ResponseBody> upload(@Body RequestBody body);
   ```

   构建```RequestBody```

   ```
   RequestBody requestFile = MultipartBody.create(MultipartBody.FORM, file);
   ```

