package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.CapitalApprovalCheckType;
import com.smartbus.heze.http.base.BaseDFourView;
import com.smartbus.heze.http.base.BasePresenter;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface DepartBudgeLRContract {
    interface View extends BaseDFourView<presenter> {
        //部门预算录入
        void setDepartBudgeLR(CapitalApprovalCheckType s);
        void setDepartBudgeLRMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getDepartBudgeLR(Map<String, String> map);
    }
}
