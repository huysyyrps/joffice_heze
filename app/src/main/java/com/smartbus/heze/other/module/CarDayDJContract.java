package com.smartbus.heze.other.module;

import com.smartbus.heze.http.base.BaseDView;
import com.smartbus.heze.http.base.BasePresenter;
import com.smartbus.heze.oaflow.bean.UserdLeaveWill;
import com.smartbus.heze.other.bean.CarDayDj;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface CarDayDJContract {
    interface View extends BaseDView<presenter> {
        //预算单
        void setCarDayDJ(CarDayDj s);
        void setCarDayDJMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getCarDayDJ(Map<String, String> map);
    }
}
