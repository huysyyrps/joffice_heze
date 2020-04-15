package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.http.base.BaseDSixView;
import com.smartbus.heze.http.base.BasePresenter;
import com.smartbus.heze.oaflow.bean.CheckType;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface DepartBudgetWillCheckTypeContract {
    interface View extends BaseDSixView<presenter> {
        //事故借款修改状态
        void setDepartBudgetWillCheckType(CheckType s);
        void setDepartBudgetWillCheckTypeMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getDepartBudgetWillCheckType(String runId, String vocationId, String destName, String mycomments);
    }
}
