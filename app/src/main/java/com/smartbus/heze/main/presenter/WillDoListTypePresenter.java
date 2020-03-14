package com.smartbus.heze.main.presenter;

import android.content.Context;

import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;
import com.smartbus.heze.main.bean.WillDoListType;
import com.smartbus.heze.main.module.WillDoListTypeContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author: Allen.
 * @date: 2018/7/25
 * @description:
 */

public class WillDoListTypePresenter implements WillDoListTypeContract.presenter {

    private Context context;
    private WillDoListTypeContract.View view;

    public WillDoListTypePresenter(Context context, WillDoListTypeContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getWillDoListType(String userId, String catKey) {
        RetrofitUtil.getInstance().initRetrofitNoSession().getWillDoListType(userId,catKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<WillDoListType>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(WillDoListType t) throws Exception {
                        view.setWillDoListType(t);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setWillDoListTypeMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
