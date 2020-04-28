package com.smartbus.heze.checkup.presenter;

import android.content.Context;

import com.smartbus.heze.checkup.bean.RCJCNewList;
import com.smartbus.heze.checkup.module.RCJCNewListContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @date: 2018/7/25
 * @description:
 */

public class RCJCNewListPresenter implements RCJCNewListContract.presenter {

    private Context context;
    private RCJCNewListContract.View view;

    public RCJCNewListPresenter(Context context, RCJCNewListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getRCJCNewList(int start, int limit) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getRCJCNewList(start,limit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<RCJCNewList>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(RCJCNewList t) throws Exception {
                        view.setRCJCNewList(t);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setRCJCNewListMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
