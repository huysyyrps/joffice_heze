package com.smartbus.heze.main.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/3/31.
 */

public class WillDoCheckTask implements Serializable {

    /**
     * success : true
     * assigned : true
     */

    private String success;
    private String assigned;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
}
