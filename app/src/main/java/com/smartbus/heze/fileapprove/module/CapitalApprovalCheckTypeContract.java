package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.http.base.BaseDSevenView;
import com.smartbus.heze.http.base.BasePresenter;
import com.smartbus.heze.oaflow.bean.CheckType;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface CapitalApprovalCheckTypeContract {
    interface View extends BaseDSevenView<presenter> {
        //通用借款修改状态
        void setCapitalApprovalCheckType(CheckType s);
        void setCapitalApprovalCheckTypeMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getCapitalApprovalCheckType(String runId, String vocationId);
    }
}
