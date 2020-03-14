package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.CapitalApprovalCheckType;
import com.smartbus.heze.http.base.BaseDFourView;
import com.smartbus.heze.http.base.BasePresenter;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface CapitalApprovalLRContract {
    interface View extends BaseDFourView<presenter> {
        //资金审批录入
        void setCapitalApprovalLR(CapitalApprovalCheckType s);
        void setCapitalApprovalLRMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getCapitalApprovalLR(Map<String, String> map);
    }
}
