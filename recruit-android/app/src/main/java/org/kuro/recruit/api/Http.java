package org.kuro.recruit.api;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.kuro.recruit.config.ApiConfig;
import org.kuro.recruit.ui.LoginActivity;
import org.kuro.recruit.utils.LogUtil;
import org.kuro.recruit.utils.SpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http {

    private static OkHttpClient client;
    private static String mUrl;
    private static HashMap<String, Object> mParams;
    public static Http http = new Http();

    public Http() {
    }

    public static Http config(String url, HashMap<String, Object> params) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(8, TimeUnit.SECONDS);
        builder.readTimeout(8, TimeUnit.SECONDS);
        builder.writeTimeout(8, TimeUnit.SECONDS);
        builder.followRedirects(true);

        builder.sslSocketFactory(
                HttpsUtils.initSSLSocketFactory(),
                HttpsUtils.initTrustManager()
        );

        client = builder.build();

        mUrl = ApiConfig.BASE_URL + url;
        mParams = params;
        return http;
    }


    public void post(Context context, final Callback callback) {
        String token = SpUtil.getInstance().getString("token", "");
        String paramsStr = new JSONObject(mParams).toString();

        RequestBody requestBody = RequestBody.create(
                paramsStr,
                MediaType.parse("application/json;charset=utf-8")
        );
        Request request = new Request.Builder()
                .url(mUrl)
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    String r = Objects.requireNonNull(response.body()).string();
                    tokenTimeOut(r, context);
                    callback.onSuccess(r);
                }
            }
        });
    }


    public void get(Context context, final Callback callback) {
        String token = SpUtil.getInstance().getString("token", "");
        String url = getAppendUrl(mUrl, mParams);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    String r = Objects.requireNonNull(response.body()).string();
                    tokenTimeOut(r, context);
                    callback.onSuccess(r);
                }
            }
        });
    }


    /**
     * 构建get请求参数
     *
     * @param url 初始请求地址
     * @param map 请求参数
     * @return 处理后的请求地址
     */
    private String getAppendUrl(String url, Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            StringBuilder buffer = new StringBuilder(url).append("?");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return url;
    }


    /**
     * 登录超时处理
     *
     * @param res     http请求返回结果
     * @param context context
     */
    private void tokenTimeOut(String res, Context context) {
        try {
            JSONObject jsonObject = new JSONObject(res);
            String code = jsonObject.getString("code");
            if ("577".equals(code)) {
                // todo 清理用户缓存
                // UserManage.logout(context);
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        } catch (JSONException e) {
            LogUtil.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
