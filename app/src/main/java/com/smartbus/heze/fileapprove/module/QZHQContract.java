package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.QZHQ;
import com.smartbus.heze.http.base.BaseDEightView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface QZHQContract {
    interface View extends BaseDEightView<presenter> {
        //通用借款修改状态
        void setQZHQ(QZHQ s);
        void setQZHQMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getQZHQ(String runId);
    }
}
