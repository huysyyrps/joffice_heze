package com.smartbus.heze.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
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
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/4/12.
 * 待办列表
 */

public class Fragment02 extends Fragment implements WillDoListTypeContract.View {
    View view;
    Unbinder unbinder;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment02, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        presenter = new WillDoListTypePresenter(getActivity(), this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        beanList.clear();
        String userId = new SharedPreferencesHelper(getActivity(),"login").getData(getActivity(),"userId","");
        presenter.getWillDoListType(userId,"FLOW");
//        beanList.clear();
//        willDoListPresenter.getWillDoList();
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
            baseAdapter = new BaseRecyclerAdapter<WillDoListType.DataBean>(getActivity(), R.layout.adapter_willdo_item, beanList) {
                @Override
                public void convert(BaseViewHolder holder, final WillDoListType.DataBean o) {
                    holder.setText(R.id.textView, o.getTypeName());
                    if (o.getUserId()!=null&&!o.getUserId().equals("")){
                        if (Integer.valueOf(o.getUsePrice()) == 0){
                            holder.setGoneTextView(R.id.tvNum);
                        } else if (Integer.valueOf(o.getUsePrice())>100){
                            holder.setText(R.id.tvNum, "...");
                        }else {
                            holder.setText(R.id.tvNum, String.valueOf(o.getUsePrice()));
                        }
                    }

                    holder.setOnClickListener(R.id.textView, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), WillDoListActivity.class);
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
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

}
