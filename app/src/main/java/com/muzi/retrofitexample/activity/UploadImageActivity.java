package com.muzi.retrofitexample.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;

import com.muzi.retrofitexample.R;
import com.muzi.retrofitexample.http.RxHttp;
import com.muzi.retrofitexample.http.RxUtils;
import com.muzi.retrofitexample.http.api.ImageApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UploadImageActivity extends AppCompatActivity {

    private File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/teng.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        findViewById(R.id.uploadImage1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base64();
            }
        });
        findViewById(R.id.uploadImage2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multipart();
            }
        });
        findViewById(R.id.uploadImage3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image();
            }
        });
        findViewById(R.id.uploadImage4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageText();
            }
        });
    }

    private void base64() {
        InputStream is;
        byte[] data;
        String result = null;
        try {
            is = new FileInputStream(file);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RxHttp.getApi(ImageApi.class)
                .uploadBase64(result)
                .compose(RxUtils.<ResponseBody>scheduling())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void multipart() {
        RequestBody requestFile = MultipartBody.create(MultipartBody.FORM, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
        RxHttp.getApi(ImageApi.class)
                .uploadMultipart(part)
                .compose(RxUtils.<ResponseBody>scheduling())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void image() {
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
        RxHttp.getApi(ImageApi.class)
                .uploadImage(body)
                .compose(RxUtils.<ResponseBody>scheduling())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void imageText() {
        RequestBody requestFile =
                RequestBody.create(MultipartBody.FORM, file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        String token = "lkjkj456jknklkljkm3212316545gygjhghfgdfgd2313456456jkhkjhkjhkj";
        RxHttp.getApi(ImageApi.class)
                .uploadImageText(body, token)
                .compose(RxUtils.<ResponseBody>scheduling())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
