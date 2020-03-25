package com.smartbus.heze.main.module;

import com.smartbus.heze.http.base.BaseDThirdView;
import com.smartbus.heze.http.base.BasePresenter;
import com.smartbus.heze.main.bean.WillDoNum;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface WillDoNumContract {
    interface View extends BaseDThirdView<presenter> {
        void setWillDoNum(WillDoNum willDoNum);
        void setWillDoNumMessage(String s);
    }

    interface presenter extends BasePresenter {
        //banner回调
        void getWillDoNum(String userId);
    }
}
