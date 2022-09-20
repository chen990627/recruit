package org.kuro.recruit.api;

/*
 * 自定义异常类,返回code,msg到业务层
 */
public class OkHttpException extends Exception {

    private static final long serialVersionUID = 1L;

    private int eCode;

    private Object eMsg;

    public OkHttpException(int eCode, Object eMsg) {
        this.eCode = eCode;
        this.eMsg = eMsg;
    }

    public int getECode() {
        return eCode;
    }

    public Object getEMsg() {
        return eMsg;
    }
}