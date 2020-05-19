package com.smartbus.heze.fileapprove.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.bean.QZLR;
import com.smartbus.heze.fileapprove.module.QZLRContract;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class QZLRPresenter implements QZLRContract.presenter {

    private Context context;
    private QZLRContract.View view;

    public QZLRPresenter(Context context, QZLRContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getQZLR(Map<String, String> map) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getqzlr(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<QZLR>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(QZLR s) throws Exception {
                        view.setQZLR(s);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setQZLRMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
