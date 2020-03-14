package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.bean.CapitalApprovalCheckType;
import com.smartbus.heze.fileapprove.module.CapitalApprovalLRContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class CapitalApprovalLRPresenter implements CapitalApprovalLRContract.presenter {

    private Context context;
    private CapitalApprovalLRContract.View view;

    public CapitalApprovalLRPresenter(Context context, CapitalApprovalLRContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getCapitalApprovalLR(Map<String, String> map) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getCapitalApprovalLR(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CapitalApprovalCheckType>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CapitalApprovalCheckType s) throws Exception {
                        view.setCapitalApprovalLR(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setCapitalApprovalLRMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
