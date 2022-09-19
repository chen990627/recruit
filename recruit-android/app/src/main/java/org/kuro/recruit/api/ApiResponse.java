package org.kuro.recruit.api;

public class ApiResponse<T> {
    public boolean status;
    public int code;
    public String message;
    public T body;
}
