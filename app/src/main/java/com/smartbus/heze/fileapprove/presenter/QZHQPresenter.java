package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.bean.QZHQ;
import com.smartbus.heze.fileapprove.module.QZHQContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class QZHQPresenter implements QZHQContract.presenter {

    private Context context;
    private QZHQContract.View view;

    public QZHQPresenter(Context context, QZHQContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getQZHQ(String runId) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getqzhq(runId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<QZHQ>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(QZHQ s) throws Exception {
                        view.setQZHQ(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setQZHQMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
