package com.smartbus.heze.fileapprove.module;

import com.smartbus.heze.fileapprove.bean.OnePerson;
import com.smartbus.heze.http.base.BaseDView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface OneContract {
    interface View extends BaseDView<presenter> {
        //预算单
        void setOnePerson(OnePerson s);
        void setOnePersonMessage(String s);
    }

    interface presenter extends BasePresenter {
        void getOnePerson(String defId);
    }
}
