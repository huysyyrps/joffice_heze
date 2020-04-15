package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.module.CapitalApprovalWillCheckTypeContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;
import com.smartbus.heze.oaflow.bean.CheckType;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class CapitalApprovalWillCheckTypePresenter implements CapitalApprovalWillCheckTypeContract.presenter {

    private Context context;
    private CapitalApprovalWillCheckTypeContract.View view;

    public CapitalApprovalWillCheckTypePresenter(Context context, CapitalApprovalWillCheckTypeContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getCapitalApprovalCheckType(String runId, String vocationId, String destName, String mycomments) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getCapitalApprovalWillCheckType(runId,vocationId,destName,mycomments).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CheckType>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CheckType s) throws Exception {
                        view.setCapitalApprovalWillCheckType(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setCapitalApprovalWillCheckTypeMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
