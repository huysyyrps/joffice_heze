package com.smartbus.heze.exam.module;

import com.smartbus.heze.exam.bean.Maintain;
import com.smartbus.heze.http.base.BaseDView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface MaintainContract {
    interface View extends BaseDView<presenter> {
        //预算单
        void setMaintain(Maintain s);
        void setMaintainMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getMaintain(String startTime, String endTime);
    }
}
