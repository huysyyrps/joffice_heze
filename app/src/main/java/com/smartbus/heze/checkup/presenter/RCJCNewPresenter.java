package com.smartbus.heze.checkup.presenter;

import android.content.Context;

import com.smartbus.heze.checkup.module.RCJCNewContract;
import com.smartbus.heze.fileapprove.bean.BackData;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2020/4/26.
 */

public class RCJCNewPresenter implements RCJCNewContract.presenter {
    private Context context;
    private RCJCNewContract.View view;

    public RCJCNewPresenter(Context context, RCJCNewContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getRCJCNew(Map<String, String> map) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getRcjcNew(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<BackData>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(BackData s) throws Exception {
                        if (s.isSuccess()){
                            view.setUPYSD(s);
                        }else {
                            view.setUPYSDMessage("");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setUPYSDMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
