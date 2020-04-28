package com.smartbus.heze.checkup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartbus.heze.R;
import com.smartbus.heze.checkup.bean.RCJCNewList;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.views.Header;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RCJCNewDetailActivity extends BaseActivity {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvDrive)
    TextView tvDrive;
    @BindView(R.id.tvLine)
    TextView tvLine;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvFound)
    TextView tvFound;
    @BindView(R.id.tvFH)
    TextView tvFH;
    @BindView(R.id.tvZG)
    TextView tvZG;
    @BindView(R.id.tvCheckPerson)
    TextView tvCheckPerson;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;
    @BindView(R.id.ll3)
    LinearLayout ll3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        RCJCNewList.ResultBean bean = (RCJCNewList.ResultBean) intent.getSerializableExtra("bean");
        tvCarNo.setText(bean.getCarNo());
        tvDepartment.setText(bean.getDepName());
        tvDrive.setText(bean.getDriverName());
        tvLine.setText(bean.getLineCode());
        if (bean.getContentzt().equals("1")) {
            tvContent.setText("有问题");
        } else {
            tvContent.setText("无问题");
        }
        tvFound.setText(bean.getWay());
        if (bean.getCheckzt().equals("1")) {
            tvFH.setText("复核");
        } else {
            tvFH.setText("未复合");
        }
        if (bean.getRectification().equals("1")) {
            tvZG.setText("是");
        } else {
            tvZG.setText("不是");
        }
        tvCheckPerson.setText(bean.getExaminer());
        tvQuestion.setText(bean.getQuestion());
        tvResult.setText(bean.getResult());
        tvRemarks.setText(bean.getNote());
        tvTime.setText(bean.getKaoheDate()+" "+bean.getKaoheTime());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_rcjcnew_detail;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }
}
