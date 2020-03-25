package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.module.DepartBudgeCheckTypeContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;
import com.smartbus.heze.oaflow.bean.CheckType;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class DepartBudgeCheckTypePresenter implements DepartBudgeCheckTypeContract.presenter {

    private Context context;
    private DepartBudgeCheckTypeContract.View view;

    public DepartBudgeCheckTypePresenter(Context context, DepartBudgeCheckTypeContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getDepartBudgeCheckType(String runId, String vocationId) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getDepartBudgeCheckType(runId,vocationId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CheckType>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CheckType s) throws Exception {
                        view.setDepartBudgeCheckType(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setDepartBudgeCheckTypeMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
