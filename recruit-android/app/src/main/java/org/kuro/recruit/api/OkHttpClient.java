package org.kuro.recruit.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
public class OkHttpClient {

    private static final int TIME_OUT = 30;
    private static okhttp3.OkHttpClient mOkHttpClient;


    static {
        okhttp3.OkHttpClient.Builder okHttpClientBuilder = new okhttp3.OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier((hostname, session) -> true);

        // 添加公共请求头
        okHttpClientBuilder.addInterceptor(chain -> {
            Request request = chain
                    .request().newBuilder()
                    .addHeader("token", "token")
                    .build();
            return chain.proceed(request);
        });
        // okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);

        okHttpClientBuilder.sslSocketFactory(
                HttpsUtils.initSSLSocketFactory(),
                HttpsUtils.initTrustManager()
        );
        mOkHttpClient = okHttpClientBuilder.build();
    }


    public static okhttp3.OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


    /**
     * 通过构造好的Request,Callback去发送请求
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new JsonCallback(handle));
        return call;
    }


    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new JsonCallback(handle));
        return call;
    }


    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new FileCallback(handle));
        return call;
    }
}
