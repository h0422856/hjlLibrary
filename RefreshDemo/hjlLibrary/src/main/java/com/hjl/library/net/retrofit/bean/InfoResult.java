package com.hjl.library.net.retrofit.bean;

import java.io.Serializable;


/**
 * @author zhuhf
 * @version [AndroidLibrary, 2018-03-07]
 */
public class InfoResult<T>  implements Serializable {
    static final String SUCCESS_CODE = "1000";
    private AbsResponseHeader header;
    private T data;

    public AbsResponseHeader getHeader() {
        return header;
    }

    public void setHeader(AbsResponseHeader header) {
        this.header = header;
    }

    public boolean isSuccess() {
        if (getHeader() != null) {
            return SUCCESS_CODE.equals(getHeader().getCode());
        } else {
            return false;
        }
    }

    public String getCode() {
        return header.getCode();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrmsg() {
        return header.getDescription();
    }

    @Override
    public String toString() {
        return "InfoResult{" +
                "header=" + header +
                ", data=" + data +
                '}';
    }

}


