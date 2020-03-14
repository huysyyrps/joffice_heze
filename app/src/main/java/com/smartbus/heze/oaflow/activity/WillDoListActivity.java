package com.smartbus.heze.oaflow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
import com.smartbus.heze.fileapprove.activity.BorrowAccidentWillActivity;
import com.smartbus.heze.fileapprove.activity.CapitalApprovalWillActivity;
import com.smartbus.heze.fileapprove.activity.CurrencyAccidentWillActivity;
import com.smartbus.heze.fileapprove.activity.DepartBudgetWillActivity;
import com.smartbus.heze.fileapprove.activity.DocumentLZWillActivity;
import com.smartbus.heze.fileapprove.activity.FileCirculateWillActivity;
import com.smartbus.heze.fileapprove.activity.HuiQianWillActivity;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.main.bean.WillDoList;
import com.smartbus.heze.main.module.WillDoListContract;
import com.smartbus.heze.main.presenter.WillDoListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WillDoListActivity extends BaseActivity implements WillDoListContract.View{

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llNoContent)
    LinearLayout llNoContent;

    @BindView(R.id.ll)
    LinearLayout ll;

    float mPosY = 0;
    float mPosX = 0;
    float mCurPosY = 0;
    float mCurPosX = 0;
    String userId = "";
    String proTypeId = "";
    BaseRecyclerAdapter adapter;
    List<WillDoList.ResultBean> beanList = new ArrayList<>();
    WillDoListPresenter willDoListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        proTypeId = getIntent().getStringExtra("proTypeId");
        willDoListPresenter = new WillDoListPresenter(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        userId = new SharedPreferencesHelper(this,"login").getData(this,"userId","");
        willDoListPresenter.getWillDoList(proTypeId);
        setGestureListener();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_will_do_list;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        beanList.clear();
        willDoListPresenter.getWillDoList(proTypeId);
    }

    /**
     * 设置上下滑动作监听器
     *
     * @author jczmdeveloper
     */
    private void setGestureListener() {
        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mCurPosY - mPosY > 0 && (Math.abs(mCurPosY - mPosY) > 25)) {
                            beanList.clear();
                            willDoListPresenter.getWillDoList(proTypeId);
                        } else if (mCurPosY - mPosY < 0
                                && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向上滑动
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void setWillDoList(WillDoList willDoList) {
        if (willDoList.getTotalCounts()==0) {
            recyclerView.setVisibility(View.GONE);
            llNoContent.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            llNoContent.setVisibility(View.GONE);
            for (int i = 0; i < willDoList.getResult().size(); i++) {
                beanList.add(willDoList.getResult().get(i));
            }
            adapter = new BaseRecyclerAdapter<WillDoList.ResultBean>(this, R.layout.adapter_easy_item, beanList) {
                @Override
                public void convert(BaseViewHolder holder, final WillDoList.ResultBean o) {
                    holder.setText(R.id.textView, o.getTaskName());
                    holder.setOnClickListener(R.id.textView, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (o.getFormDefId().equals(Constant.YSD_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, DepartBudgetWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.BORROWACCIDENT_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, BorrowAccidentWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.CURRENCYACCIDENT_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, CurrencyAccidentWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.HUIQIAN_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, HuiQianWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.FILECIR_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, FileCirculateWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.DOCUMENTLZ_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, DocumentLZWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.USERDLEAVE_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, UserdLeaveWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.ADDWORK_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, AddWorkWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.OLDWORK_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, OldWorkWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.ATWORK_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, AtWorkWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.CHECKWORK_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, CheckWorkWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                            if (o.getFormDefId().equals(Constant.CAPITALAPPROVAL_FORMDEFIS)) {
                                Intent intent = new Intent(WillDoListActivity.this, CapitalApprovalWillActivity.class);
                                intent.putExtra("activityName", o.getActivityName());
                                intent.putExtra("taskId", o.getTaskId());
                                startActivity(intent);
                            }
                        }
                    });
                }
            };
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setWillDoListMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
