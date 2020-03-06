package com.smartbus.heze.exam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.checkup.activitydata.CarCodeActivity;
import com.smartbus.heze.checkup.bean.CarCodeData;
import com.smartbus.heze.exam.bean.ComparList;
import com.smartbus.heze.exam.module.ComparListContract;
import com.smartbus.heze.exam.presenter.ComparListPresenter;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.http.utils.time_select.CustomDatePickerDay;
import com.smartbus.heze.http.views.Header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComparListActivity extends BaseActivity implements ComparListContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.tvCaoNo)
    TextView tvCaoNo;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.llNoContent)
    LinearLayout llNoContent;

    Intent intent;
    String orgName = "",orgId = "";
    BaseRecyclerAdapter baseRecyclerAdapter;
    List<ComparList.ResultBean> beanList = new ArrayList<>();
    ComparListPresenter comparListPresenter;
    private CustomDatePickerDay customDatePicker1, customDatePicker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        initDatePicker();
        comparListPresenter = new ComparListPresenter(this, this);
        comparListPresenter.getComparList(tvStartTime.getText().toString()
                , tvEndTime.getText().toString(), "","");
    }

    /**
     * 选择时间
     */
    private void initDatePicker() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date d = c.getTime();
        String day = format.format(d);
        tvStartTime.setText(day);
        System.out.println("过去七天：" + day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        tvEndTime.setText(now.split(" ")[0]);
        sendHttpContent();

        customDatePicker1 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvStartTime.setText(time.split(" ")[0]);
            }
        }, "2000-01-01 00:00", "2030-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

        customDatePicker2 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvEndTime.setText(time.split(" ")[0]);
            }
        }, "2000-01-01 00:00", "2030-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(false); // 显示时和分
        customDatePicker2.setIsLoop(false); // 允许循环滚动
    }

    private void sendHttpContent() {
        Date startTime = null, endTime = null;
        if (tvStartTime.getText().toString().isEmpty() || tvStartTime.getText().toString().isEmpty()) {
            Toast.makeText(this, "起止时间不能为空", Toast.LENGTH_SHORT).show();
        } else {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                startTime = sdf1.parse(tvStartTime.getText().toString());
                endTime = sdf1.parse(tvEndTime.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (startTime.getTime() > endTime.getTime()) {
            Toast.makeText(this, "请选择正确时间", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_compar_list;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        beanList.clear();
        comparListPresenter.getComparList(tvStartTime.getText().toString()
                , tvEndTime.getText().toString(), tvCaoNo.getText().toString(),orgId);
    }

    @Override
    public void setComparList(ComparList s) {
        for (int i = 0; i < s.getResult().size(); i++) {
            beanList.add(s.getResult().get(i));
        }
        if (beanList.size() == 0) {
            llNoContent.setVisibility(View.VISIBLE);
        }
        baseRecyclerAdapter = new BaseRecyclerAdapter<ComparList.ResultBean>(this, R.layout.adapter_comparlist_item, beanList) {
            @Override
            public void convert(BaseViewHolder holder, final ComparList.ResultBean resultBean) {
                holder.setText(R.id.tvDriver, resultBean.getDriverName());
                holder.setText(R.id.tvCarNo, resultBean.getCarNo());
                holder.setText(R.id.tvDate, resultBean.getKaoheDate());
                holder.setText(R.id.tvJCR, resultBean.getExaminer());
                holder.setText(R.id.tvDepartment, resultBean.getDepName());
                holder.setText(R.id.tvLine, resultBean.getLineCode());
                if (resultBean.getProjectName()==null||resultBean.getProjectName().equals("")){
                    holder.setGoneLinner(R.id.llNoItem);
                }else {
                    holder.setVisionLinner(R.id.llNoItem);
                    holder.setText(R.id.tvNoItem, resultBean.getProjectName());
                }

                if (resultBean.getNote()==null||resultBean.getNote().equals("")){
                    holder.setGoneLinner(R.id.llMemo);
                }else {
                    holder.setVisionLinner(R.id.llMemo);
                    holder.setText(R.id.tvMemo, resultBean.getNote());
                }
            }
        };
        recyclerView.setAdapter(baseRecyclerAdapter);
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setComparListMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tvStartTime, R.id.tvEndTime, R.id.tvCaoNo,R.id.tvDepartment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDepartment:
                intent = new Intent(this, DJDepartmentActivity.class);
                startActivityForResult(intent, Constant.TAG_ONE);
//                customDatePicker1.show(tvStartTime.getText().toString());
                break;
            case R.id.tvStartTime:
                customDatePicker1.show(tvStartTime.getText().toString());
                break;
            case R.id.tvEndTime:
                customDatePicker2.show(tvEndTime.getText().toString());
                break;
            case R.id.tvCaoNo:
                intent = new Intent(this, CarCodeActivity.class);
                intent.putExtra("tag", "carNo");
                startActivityForResult(intent, Constant.TAG_TWO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.TAG_ONE:
                if (resultCode == Constant.TAG_TWO) {
                    orgName = data.getStringExtra("orgName");
                    orgId = data.getStringExtra("orgId");
                    tvDepartment.setText(orgName);
                }
                break;
            case Constant.TAG_TWO:
                if (resultCode == Constant.TAG_TWO) {
                    CarCodeData carCodeData = (CarCodeData) data.getSerializableExtra("carCode");
                    tvCaoNo.setText(carCodeData.getCarNo());
                }
                break;
        }
    }

}
