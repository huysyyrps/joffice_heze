package com.smartbus.heze.checkup.activitydata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.checkup.bean.CarCode;
import com.smartbus.heze.checkup.bean.CarCodeData;
import com.smartbus.heze.checkup.module.CarCodeContract;
import com.smartbus.heze.checkup.presenter.CarCodePresenter;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.utils.BaseRecyclerAdapter;
import com.smartbus.heze.http.utils.BaseViewHolder;
import com.smartbus.heze.http.utils.azlistview.EditTextWithDel;
import com.smartbus.heze.http.utils.azlistview.PinyinUtils;
import com.smartbus.heze.http.views.Header;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarCodeActivity extends BaseActivity implements CarCodeContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditTextWithDel etSearch;

    String tag;
    BaseRecyclerAdapter adapter;
    CarCodePresenter carCodePresenter;
    List<CarCodeData> beanListData = new ArrayList<>();
    List<CarCodeData> beanListData1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tag = getIntent().getStringExtra("tag");
        if (tag.equals("carCode")){
            header.setTvTitle(getResources().getString(R.string.car_code1));
        }else if (tag.equals("carNo")){
            header.setTvTitle(getResources().getString(R.string.car_no));
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        carCodePresenter = new CarCodePresenter(this, this);
        beanListData = DataSupport.findAll(CarCodeData.class);
        beanListData1 = DataSupport.findAll(CarCodeData.class);
        if (beanListData.size() != 0) {
            setAdapter(beanListData);
        } else {
            carCodePresenter.getCarCode();
        }

        //根据输入框输入值的改变来过滤搜索
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }
        });
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<CarCodeData> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = beanListData1;
            setAdapter(mSortList);
        } else {
            mSortList.clear();
            if (tag.equals("carCode")){
                for (CarCodeData sortModel : beanListData1) {
                    String name = sortModel.getBusCode();
                    if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                        mSortList.add(sortModel);
                    }
                }
            }else if (tag.equals("carNo")){
                for (CarCodeData sortModel : beanListData1) {
                    String name = sortModel.getCarNo();
                    if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                        mSortList.add(sortModel);
                    }
                }
            }
            setAdapter(mSortList);
//            beanListData.clear();
//            beanListData = mSortList;
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_line_code;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    /**
     * 获取线路编号
     *
     * @param s
     */
    @Override
    public void setCarCode(CarCode s) {
        if (s != null) {
            for (int i = 0; i < s.getData().size(); i++) {
                CarCodeData bean = new CarCodeData();
                beanListData.add(bean);
                beanListData1.add(bean);
                bean.getCarId(s.getData().get(i).getCarId());
                bean.setBusCode(s.getData().get(i).getBusCode());
                bean.setCarNo(s.getData().get(i).getCarNo());
                bean.setCarType(s.getData().get(i).getCarType());
                bean.setDepName(s.getData().get(i).getDepName());
                bean.setDepId(s.getData().get(i).getDepId());
                bean.setMaterialType(s.getData().get(i).getMaterialType());
                bean.save();
            }
        }
        setAdapter(beanListData);
    }

    @Override
    public void setCarCodeMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void setAdapter(List<CarCodeData> mSortList) {
        adapter = new BaseRecyclerAdapter<CarCodeData>(this, R.layout.adapter_easy_item, mSortList) {
            @Override
            public void convert(BaseViewHolder holder, final CarCodeData o) {
                if (tag.equals("carCode")){
                    holder.setText(R.id.textView, o.getBusCode());
                }else if (tag.equals("carNo")){
                    holder.setText(R.id.textView, o.getCarNo());
                }

                holder.setOnClickListener(R.id.textView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("carCode", o);
                        setResult(Constant.TAG_TWO, intent);
                        finish(); //结束当前的activity的生命周期
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
