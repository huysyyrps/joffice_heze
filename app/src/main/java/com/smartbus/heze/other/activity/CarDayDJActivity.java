package com.smartbus.heze.other.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.http.utils.time_select.CustomDatePickerDay;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.other.bean.CarDayDj;
import com.smartbus.heze.other.module.CarDayDJContract;
import com.smartbus.heze.other.presenter.CarDayDJPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarDayDJActivity extends BaseActivity implements CarDayDJContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    String driverName, code;
    CarDayDJPresenter carDayDJPresenter;
    Map<String, String> map = new HashMap<>();
    @BindView(R.id.llNoContent)
    LinearLayout llNoContent;
    BaseRecyclerAdapter adapter;
    List<CarDayDj.ResultBean>beanList = new ArrayList<>();
    private CustomDatePickerDay customDatePicker1, customDatePicker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initDatePicker();
        carDayDJPresenter = new CarDayDJPresenter(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        driverName = new SharedPreferencesHelper(this, "login").getData(this, "userName", "");
        code = new SharedPreferencesHelper(this, "login").getData(this, "userId", "");
        map.put("ksdate", tvStartTime.getText().toString());
        map.put("jsdate", tvEndTime.getText().toString());
        map.put("driverName", driverName);
        map.put("code", code);
        carDayDJPresenter.getCarDayDJ(map);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_car_daydj;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        map.clear();
        map.put("ksdate", tvStartTime.getText().toString());
        map.put("jsdate", tvEndTime.getText().toString());
        map.put("driverName", driverName);
        map.put("code", code);
        carDayDJPresenter = new CarDayDJPresenter(this, this);
        carDayDJPresenter.getCarDayDJ(map);
    }

    /**
     * 选择时间
     */
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tvStartTime.setText(now.split(" ")[0]);
        tvEndTime.setText(now.split(" ")[0]);
        customDatePicker1 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                tvStartTime.setText(time.split(" ")[0]);
            }
            // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        }, "2000-01-01 00:00", "2030-01-01 00:00");
        // 不显示时和分
        customDatePicker1.showSpecificTime(false);
        // 不允许循环滚动
        customDatePicker1.setIsLoop(false);
        customDatePicker2 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                tvEndTime.setText(time.split(" ")[0]);
            }
            // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        }, "2000-01-01 00:00", "2030-01-01 00:00");
        // 不显示时和分
        customDatePicker2.showSpecificTime(false);
        // 不允许循环滚动
        customDatePicker2.setIsLoop(false);
    }

    @OnClick({R.id.tvStartTime, R.id.tvEndTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvStartTime:
                customDatePicker1.show(tvStartTime.getText().toString());
                break;
            case R.id.tvEndTime:
                customDatePicker2.show(tvEndTime.getText().toString());
        }
    }

    @Override
    public void setCarDayDJ(CarDayDj s) {
        if (s.getResult().size() == 0) {
            recyclerView.setVisibility(View.GONE);
            llNoContent.setVisibility(View.VISIBLE);
        } else {
            beanList.clear();
            recyclerView.setVisibility(View.VISIBLE);
            llNoContent.setVisibility(View.GONE);
            for (int i = 0; i < s.getResult().size(); i++) {
                beanList.add(s.getResult().get(i));
            }
            adapter = new BaseRecyclerAdapter<CarDayDj.ResultBean>(this, R.layout.adapter_carday_item, beanList) {
                @Override
                public void convert(BaseViewHolder holder, final CarDayDj.ResultBean o) {
                    holder.setText(R.id.tvDate, o.getKaoheDate());
                    holder.setText(R.id.tvCar, o.getCarNo());
                    holder.setText(R.id.tvLine, o.getLineCode());
                    holder.setText(R.id.tvDriver, o.getDriverName());
                    holder.setText(R.id.tvReceiver, o.getExaminer());
                    holder.setText(R.id.tvDepartment, o.getDepName());
                    if (o.getProjectName().equals("")){
                        holder.setGoneLinner(R.id.llReason);
                    }else {
                        holder.setVisionLinner(R.id.llReason);
                        holder.setText(R.id.tvReason, o.getProjectName());
                    }
                    if (o.getNote().equals("")){
                        holder.setGoneLinner(R.id.llMemo);
                    }else {
                        holder.setVisionLinner(R.id.llMemo);
                        holder.setText(R.id.tvMemo, o.getProjectName());
                    }
                }
            };
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setCarDayDJMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
