package com.smartbus.heze.checkup.module;

import com.smartbus.heze.checkup.bean.RCJCNewList;
import com.smartbus.heze.http.base.BaseDFiveView;
import com.smartbus.heze.http.base.BasePresenter;

/**
 * Created by Administrator on 2020/4/26.
 */

public interface RCJCNewListContract {
    interface View extends BaseDFiveView<presenter> {
        void setRCJCNewList(RCJCNewList rcjcNewList);
        void setRCJCNewListMessage(String s);

    }

    interface presenter extends BasePresenter {
        //banner回调
        void getRCJCNewList(int start,int limit);
    }
}
