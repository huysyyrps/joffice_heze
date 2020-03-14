package com.smartbus.heze.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.main.bean.WillDoListType;
import com.smartbus.heze.main.module.WillDoListTypeContract;
import com.smartbus.heze.main.presenter.WillDoListTypePresenter;
import com.smartbus.heze.oaflow.activity.WillDoListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WillDoListTypeActivity extends BaseActivity implements WillDoListTypeContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llNoContent)
    LinearLayout llNoContent;

    String proTypeId;
    BaseRecyclerAdapter baseAdapter;
    private WillDoListTypePresenter presenter;
    List<WillDoListType.DataBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        presenter = new WillDoListTypePresenter(this, this);
        String userId = new SharedPreferencesHelper(this,"login").getData(this,"userId","");
        presenter.getWillDoListType(userId,"FLOW");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_will_do_list_type;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    @Override
    public void setWillDoListType(WillDoListType willDoListType) {
        if (willDoListType.getData().size() == 0) {
            recyclerView.setVisibility(View.GONE);
            llNoContent.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            llNoContent.setVisibility(View.GONE);
            for (int i = 0; i < willDoListType.getData().size(); i++) {
                beanList.add(willDoListType.getData().get(i));
            }
            baseAdapter = new BaseRecyclerAdapter<WillDoListType.DataBean>(this, R.layout.adapter_easy_item, beanList) {
                @Override
                public void convert(BaseViewHolder holder, final WillDoListType.DataBean o) {
                    holder.setText(R.id.textView, o.getTypeName());
                    holder.setOnClickListener(R.id.textView, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(WillDoListTypeActivity.this, WillDoListActivity.class);
                            intent.putExtra("proTypeId", o.getProTypeId());
                            startActivity(intent);
                        }
                    });
                }
            };
            recyclerView.setAdapter(baseAdapter);
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setWillDoListTypeMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
