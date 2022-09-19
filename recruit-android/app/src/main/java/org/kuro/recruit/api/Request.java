package org.kuro.recruit.api;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class Request<T, R> {
    private String cacheKey;
    protected String mUrl;
    protected HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, Object> params = new HashMap<>();

    // 只访问缓存
    public static final int CACHE_ONLY = 1;
    // 先访问缓存，再访问网络
    public static final int CACHE_FIRST = 2;
    // 只访问网络
    public static final int NET_ONLY = 3;
    // 从网络接口请求到数据后，缓存到本地
    public static final int NET_CACHE = 4;

    @IntDef({CACHE_ONLY, CACHE_FIRST, NET_ONLY, NET_CACHE})
    public @interface CacheStrategy {

    }

    public Request(String url) {
        mUrl = url;
    }

    public R addHeader(String key, String value) {
        headers.put(key, value);
        return (R) this;
    }

    public R addParam(String key, Object value) {
        try {
            Field field = value.getClass().getField("TYPE");
            Class claz = (Class) field.get(null);
            if (claz.isPrimitive()) {
                params.put(key, value);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return (R) this;
    }

    public R cacheKey(String key) {
        this.cacheKey = key;
        return (R) this;
    }

    public void execute(JsonCallback<T> callback) {
        getCall().enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ApiResponse<T> response = new ApiResponse<>();
                response.message = e.getMessage();
                callback.onError(response);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ApiResponse<T> apiResponse = parseResponse(response, callback);
                if (apiResponse != null && apiResponse.status) {
                    callback.onSuccess(apiResponse);
                } else {
                    callback.onError(apiResponse);
                }
            }
        });
    }


    public void execute() {

    }

    private Call getCall() {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        addHeaders(builder);
        okhttp3.Request request = generateRequest(builder);

        return ApiService.okHttpClient.newCall(request);
    }

    protected abstract okhttp3.Request generateRequest(okhttp3.Request.Builder builder);

    private void addHeaders(okhttp3.Request.Builder builder) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    private ApiResponse<T> parseResponse(Response response, JsonCallback<T> callback) {
        String message = null;
        boolean status = response.isSuccessful();
        int code = response.code();
        ApiResponse<T> result = new ApiResponse<>();

        try {
            if (status) {
                String content = response.body().string();
                if (callback != null) {
                    ParameterizedType type = (ParameterizedType) callback.getClass().getGenericSuperclass();
                    Type argument = type.getActualTypeArguments()[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
