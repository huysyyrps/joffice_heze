package com.smartbus.heze.checkup.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/4/22.
 */

public class RCJCNewListItem implements Serializable {
    public String carId;
    public String checkPerson;
    public String mome;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome;
    }
}
