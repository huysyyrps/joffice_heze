package com.smartbus.heze.oaflow.presenter;

import android.content.Context;

import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;
import com.smartbus.heze.oaflow.bean.CheckType;
import com.smartbus.heze.oaflow.module.CheckCheckTypeContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class CheckWorkCheckTypePresenter implements CheckCheckTypeContract.presenter {

    private Context context;
    private CheckCheckTypeContract.View view;

    public CheckWorkCheckTypePresenter(Context context, CheckCheckTypeContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getCheckType(String runId, String vocationId) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getCheckCheckType(runId,vocationId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CheckType>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CheckType s) throws Exception {
                        view.setCheckType(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setCheckTypeMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
