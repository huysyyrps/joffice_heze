package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.QZLR;
import com.smartbus.heze.http.base.BaseDSixView;
import com.smartbus.heze.http.base.BasePresenter;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface QZLRContract {
    interface View extends BaseDSixView<presenter> {
        //通用借款修改状态
        void setQZLR(QZLR s);
        void setQZLRMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getQZLR(Map<String, String> map);
    }
}
