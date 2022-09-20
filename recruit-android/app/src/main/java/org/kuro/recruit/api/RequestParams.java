package org.kuro.recruit.api;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestParams {

    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<>();


    public RequestParams() {
        this((Map<String, String>) null);
    }


    public RequestParams(Map<String, String> source) {
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }


    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }


    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }


    public void put(String key, Object object) throws FileNotFoundException {
        if (key != null) {
            fileParams.put(key, object);
        }
    }


    public boolean hasParams() {
        return urlParams.size() > 0 || fileParams.size() > 0;
    }
}
