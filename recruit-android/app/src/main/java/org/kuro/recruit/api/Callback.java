package org.kuro.recruit.api;

public interface Callback {

    void onSuccess(String res);

    void onFailure(Exception e);
}
