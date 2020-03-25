package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.bean.CapitalApprovalCheckType;
import com.smartbus.heze.fileapprove.module.DepartBudgeLRContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class DepartBudgetLRPresenter implements DepartBudgeLRContract.presenter {

    private Context context;
    private DepartBudgeLRContract.View view;

    public DepartBudgetLRPresenter(Context context, DepartBudgeLRContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getDepartBudgeLR(Map<String, String> map) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getDepartDubgeLR(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CapitalApprovalCheckType>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CapitalApprovalCheckType s) throws Exception {
                        view.setDepartBudgeLR(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setDepartBudgeLRMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
