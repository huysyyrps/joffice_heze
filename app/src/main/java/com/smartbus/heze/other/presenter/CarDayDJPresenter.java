package com.smartbus.heze.other.presenter;

import android.content.Context;

import com.smartbus.heze.fileapprove.bean.InitBackData;
import com.smartbus.heze.http.base.BaseObserverNoEntry;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.RetrofitUtil;
import com.smartbus.heze.oaflow.module.OldWorkContract;
import com.smartbus.heze.other.bean.CarDayDj;
import com.smartbus.heze.other.module.CarDayDJContract;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @description:
 */

public class CarDayDJPresenter implements CarDayDJContract.presenter {

    private Context context;
    private CarDayDJContract.View view;

    public CarDayDJPresenter(Context context, CarDayDJContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getCarDayDJ(Map<String, String> map) {
        RetrofitUtil.getInstance().initRetrofitSetSession().getCarDayDJ(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverNoEntry<CarDayDj>(context, MainUtil.getData) {
                    @Override
                    protected void onSuccees(CarDayDj s) throws Exception {
                        if (s.isSuccess()){
                            view.setCarDayDJ(s);
                        }else {
                            view.setCarDayDJMessage("");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setCarDayDJMessage("失败了----->" + e.getMessage());
                    }
                });
    }
}
