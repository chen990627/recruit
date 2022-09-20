package org.kuro.recruit.api;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 处理json类型的响应
 */
public class JsonCallback implements Callback {

    // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final String RESULT_CODE = "eCode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "eMsg";
    protected final String EMPTY_MSG = "";

    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    // 将其它线程的数据转发到UI线程
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;


    public JsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void onFailure(@NonNull final Call call, @NonNull final IOException ioexception) {
        // 此时还在非UI线程，因此要转发
        mDeliveryHandler.post(() -> mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception)));
    }


    @Override
    public void onResponse(@NonNull final Call call, final Response response) throws IOException {
        final String result = Objects.requireNonNull(response.body()).string();
        mDeliveryHandler.post(() -> handleResponse(result));
    }


    private void handleResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            // 协议确定后看这里如何修改
            JSONObject result = new JSONObject(responseObj.toString());
            if (mClass == null) {
                mListener.onSuccess(result);
            } else {
                Object obj = new Gson().fromJson(responseObj.toString(), mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}
