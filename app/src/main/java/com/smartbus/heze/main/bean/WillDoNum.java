package com.smartbus.heze.main.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/3/24.
 */

public class WillDoNum implements Serializable {

    /**
     * success : true
     */

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
