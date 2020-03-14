package com.smartbus.heze.main.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/3/9.
 */

public class WillDoFormDefId implements Serializable {
    public String success;
    public String data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
