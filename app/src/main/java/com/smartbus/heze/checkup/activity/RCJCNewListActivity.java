package com.smartbus.heze.checkup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.refreshview.CustomRefreshView;
import com.smartbus.heze.R;
import com.smartbus.heze.checkup.bean.RCJCNewList;
import com.smartbus.heze.checkup.module.RCJCNewListContract;
import com.smartbus.heze.checkup.presenter.RCJCNewListPresenter;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.ProgressDialogUtil;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.http.views.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RCJCNewListActivity extends BaseActivity implements RCJCNewListContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.ivAddRcjc)
    ImageView ivAddRcjc;
    @BindView(R.id.recyclerView)
    CustomRefreshView recyclerView;
    @BindView(R.id.llNoContent)
    LinearLayout llNoContent;
    @BindView(R.id.ll)
    LinearLayout ll;
    BaseRecyclerAdapter baseRecyclerAdapter;
    RCJCNewListPresenter rcjcNewListPresenter;
    List<RCJCNewList.ResultBean> beanList = new ArrayList<>();

    int limit = 20;
    int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.getRecyclerView().setLayoutManager(manager);
        baseRecyclerAdapter = new BaseRecyclerAdapter<RCJCNewList.ResultBean>(this, R.layout.rcjc_item_layout, beanList) {
            @Override
            public void convert(BaseViewHolder holder, final RCJCNewList.ResultBean bean) {
                holder.setText(R.id.tvCarId, bean.getCarNo());
                holder.setText(R.id.tvMemo, bean.getResult());
                holder.setText(R.id.tvCheckPerson, bean.getExaminer());
                holder.setOnClickListener(R.id.ll, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RCJCNewListActivity.this, RCJCNewDetailActivity.class);
                        intent.putExtra("bean", bean);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(baseRecyclerAdapter);
        baseRecyclerAdapter.notifyDataSetChanged();
        rcjcNewListPresenter = new RCJCNewListPresenter(this, this);
        rcjcNewListPresenter.getRCJCNewList(start, limit);
        setClient();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_rcjcnew_list;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    /**
     * 滑动监听
     */
    private void setClient() {
        recyclerView.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                beanList.clear();
                start = 0;
                rcjcNewListPresenter.getRCJCNewList(start, limit);
            }

            @Override
            public void onLoadMore() {
                start += 20;
                rcjcNewListPresenter.getRCJCNewList(start, limit);
            }
        });
    }

    @OnClick(R.id.ivAddRcjc)
    public void onViewClicked() {
        Intent intent = new Intent(this, RCJCNewActivity.class);
        startActivity(intent);
    }

    @Override
    public void setRCJCNewList(RCJCNewList rcjcNewList) {
        if (rcjcNewList.getTotalCounts() == 0 && beanList.size() == 0) {
            if (recyclerView != null) {
                recyclerView.setVisibility(View.GONE);
                llNoContent.setVisibility(View.VISIBLE);
            }
        } else if (rcjcNewList.getTotalCounts() == 0 && beanList.size() != 0) {
            if (recyclerView != null) {
                recyclerView.complete();
                recyclerView.onNoMore();
            }
        } else if (rcjcNewList.getTotalCounts() != 0) {
            recyclerView.setVisibility(View.VISIBLE);
            llNoContent.setVisibility(View.GONE);
            for (int i = 0; i < rcjcNewList.getResult().size(); i++) {
                beanList.add(rcjcNewList.getResult().get(i));
            }
        }
        if (rcjcNewList.getTotalCounts() < 20) {
            if (recyclerView != null) {
                baseRecyclerAdapter.notifyDataSetChanged();
                recyclerView.complete();
                recyclerView.onNoMore();
            }
        } else {
            if (recyclerView != null) {
                baseRecyclerAdapter.notifyDataSetChanged();
                recyclerView.complete();
            }
        }
        if (start == 0) {
            ProgressDialogUtil.stopLoad();
            if (recyclerView != null) {
                recyclerView.complete();
            }
        }
    }

    @Override
    public void setRCJCNewListMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
