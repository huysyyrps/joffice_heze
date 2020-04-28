package com.smartbus.heze.checkup.module;

import com.smartbus.heze.fileapprove.bean.BackData;
import com.smartbus.heze.http.base.BaseDThirdView;
import com.smartbus.heze.http.base.BasePresenter;

import java.util.Map;

/**
 * Created by Administrator on 2020/4/26.
 */

public interface RCJCNewContract {
    interface View extends BaseDThirdView<presenter> {
        //预算单
        void setUPYSD(BackData s);
        void setUPYSDMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getRCJCNew(Map<String, String> map);
    }
}
