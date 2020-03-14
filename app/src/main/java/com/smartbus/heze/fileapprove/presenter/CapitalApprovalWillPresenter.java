package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.bean.CapitalApprovalWill;
import com.smartbus.heze.fileapprove.module.CapitalApprovalWillContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class CapitalApprovalWillPresenter implements CapitalApprovalWillContract.presenter {

    private Context context;
    private CapitalApprovalWillContract.View view;

    public CapitalApprovalWillPresenter(Context context, CapitalApprovalWillContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getCapitalApprovalWill(String activityName, String taskId, String defId) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getCapitalApproval(activityName,taskId,defId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CapitalApprovalWill>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CapitalApprovalWill s) throws Exception {
                        view.setCapitalApprovalWill(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setCapitalApprovalWillMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
