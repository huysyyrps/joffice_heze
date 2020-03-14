package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.CapitalApprovalWill;
import com.smartbus.heze.http.base.BaseDView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface CapitalApprovalWillContract {
    interface View extends BaseDView<presenter> {
        //资金审批单待办
        void setCapitalApprovalWill(CapitalApprovalWill s);
        void setCapitalApprovalWillMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getCapitalApprovalWill(String activityName, String taskId, String defId);
    }
}
