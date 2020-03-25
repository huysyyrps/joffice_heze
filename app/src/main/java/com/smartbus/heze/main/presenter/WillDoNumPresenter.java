package com.smartbus.heze.main.presenter;

import android.content.Context;

import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;
import com.smartbus.heze.main.bean.WillDoNum;
import com.smartbus.heze.main.module.WillDoNumContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author: Allen.
 * @date: 2018/7/25
 * @description:
 */

public class WillDoNumPresenter implements WillDoNumContract.presenter {

    private Context context;
    private WillDoNumContract.View view;

    public WillDoNumPresenter(Context context, WillDoNumContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getWillDoNum(String userId) {
        RetrofitUtil.getInstance().initRetrofitNoSession().getWillDoNum(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<WillDoNum>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(WillDoNum t) throws Exception {
                        view.setWillDoNum(t);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setWillDoNumMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
