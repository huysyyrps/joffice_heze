package com.smartbus.heze.main.module;

import com.smartbus.heze.http.base.BaseDView;
import com.smartbus.heze.http.base.BasePresenter;
import com.smartbus.heze.main.bean.WillDoListType;

/**
 * Created by Administrator on 2019/4/11.
 */

public interface WillDoListTypeContract {
    interface View extends BaseDView<presenter> {
        void setWillDoListType(WillDoListType willDoListType);
        void setWillDoListTypeMessage(String s);
    }

    interface presenter extends BasePresenter {
        //banner回调
        void getWillDoListType(String userId, String catKey);
    }
}
