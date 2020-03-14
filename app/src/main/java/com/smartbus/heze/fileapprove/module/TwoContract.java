package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.TwoPerson;
import com.smartbus.heze.http.base.BaseDSecondView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface TwoContract {
    interface View extends BaseDSecondView<presenter> {
        void setTwoPerson(TwoPerson s);
        void setTwoPersonMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getTwoPerson(String defId, String activityName);
    }
}
