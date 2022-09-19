package org.kuro.recruit.api;

import java.util.Map;

import okhttp3.FormBody;

public class PostRequest<T> extends Request<T, PostRequest> {

    public PostRequest(String url) {
        super(url);
    }

    @Override
    protected okhttp3.Request generateRequest(okhttp3.Request.Builder builder) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            bodyBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        return builder.url(mUrl).post(bodyBuilder.build()).build();
    }
}
