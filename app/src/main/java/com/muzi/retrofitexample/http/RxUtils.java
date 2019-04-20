package com.muzi.retrofitexample.http;

/**
 * 作者: lipeng
 * 时间: 2019/1/20
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class RxUtils {

    /**
     * 通用线程调度
     * 子线程->主线程
     *
     * @param <T>
     * @return
     */
    public static <T> SchedulerTransformer<T> scheduling() {
        return new SchedulerTransformer();
    }


}
