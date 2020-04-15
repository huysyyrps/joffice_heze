package com.smartbus.heze.fileapprove.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
import com.smartbus.heze.fileapprove.bean.BackData;
import com.smartbus.heze.fileapprove.bean.CapitalApprovalCheckType;
import com.smartbus.heze.fileapprove.bean.DepartmentDataBean;
import com.smartbus.heze.fileapprove.bean.OnePerson;
import com.smartbus.heze.fileapprove.bean.TwoPerson;
import com.smartbus.heze.fileapprove.module.DepartBudgeCheckTypeContract;
import com.smartbus.heze.fileapprove.module.DepartBudgeLRContract;
import com.smartbus.heze.fileapprove.module.OneContract;
import com.smartbus.heze.fileapprove.module.TwoContract;
import com.smartbus.heze.fileapprove.module.UPYSDContract;
import com.smartbus.heze.fileapprove.presenter.DepartBudgeCheckTypePresenter;
import com.smartbus.heze.fileapprove.presenter.DepartBudgetLRPresenter;
import com.smartbus.heze.fileapprove.presenter.OnePresenter;
import com.smartbus.heze.fileapprove.presenter.TwoPresenter;
import com.smartbus.heze.fileapprove.presenter.UPYSDPresenter;
import com.smartbus.heze.http.base.AlertDialogCallBackP;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.utils.time_select.CustomDatePickerDay;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.http.views.MyAlertDialog;
import com.smartbus.heze.oaflow.bean.CheckType;

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

/**
 * 广告预算单
 */
public class AdverBudgetActivity extends BaseActivity implements OneContract.View
        , TwoContract.View, UPYSDContract.View, DepartBudgeLRContract.View, DepartBudgeCheckTypeContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.etName1)
    EditText etName1;
    @BindView(R.id.etName2)
    EditText etName2;
    @BindView(R.id.etName3)
    EditText etName3;
    @BindView(R.id.etName4)
    EditText etName4;
    @BindView(R.id.etName5)
    EditText etName5;
    @BindView(R.id.etDepartment1)
    EditText etDepartment1;
    @BindView(R.id.etDepartment2)
    EditText etDepartment2;
    @BindView(R.id.etDepartment3)
    EditText etDepartment3;
    @BindView(R.id.etDepartment4)
    EditText etDepartment4;
    @BindView(R.id.etDepartment5)
    EditText etDepartment5;
    @BindView(R.id.etMoney1)
    EditText etMoney1;
    @BindView(R.id.etMoney2)
    EditText etMoney2;
    @BindView(R.id.etMoney3)
    EditText etMoney3;
    @BindView(R.id.etMoney4)
    EditText etMoney4;
    @BindView(R.id.etMoney5)
    EditText etMoney5;
    @BindView(R.id.etNum1)
    EditText etNum1;
    @BindView(R.id.etNum2)
    EditText etNum2;
    @BindView(R.id.etNum3)
    EditText etNum3;
    @BindView(R.id.etNum4)
    EditText etNum4;
    @BindView(R.id.etNum5)
    EditText etNum5;
    @BindView(R.id.etAllMoney1)
    EditText etAllMoney1;
    @BindView(R.id.etAllMoney2)
    EditText etAllMoney2;
    @BindView(R.id.etAllMoney3)
    EditText etAllMoney3;
    @BindView(R.id.etAllMoney4)
    EditText etAllMoney4;
    @BindView(R.id.etAllMoney5)
    EditText etAllMoney5;
    @BindView(R.id.tvAllNum)
    TextView tvAllNum;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;
    @BindView(R.id.etUse)
    EditText etUse;
    @BindView(R.id.etLeader)
    EditText etLeader;
    @BindView(R.id.etLeader1)
    EditText etLeader1;
    @BindView(R.id.etLeader2)
    EditText etLeader2;
    @BindView(R.id.btnUp)
    Button btnUp;

    String uId = "";
    String isShow = "true";
    String userDepart = "";
    String userCode = "";
    String userName = "";
    String[] nametemp = null;
    String[] codetemp = null;
    String depId = "", depName = "";
    OnePresenter ysdOnePersenter;
    TwoPresenter ysdTwoPersenter;
    UPYSDPresenter upYsdPersenter;
    Map<String, String> map = new HashMap<>();
    List<String> namelist = new ArrayList<>();
    List<String> codeList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    List<String> selectList = new ArrayList<>();
    List<String> namelist1 = new ArrayList<>();
    List<TwoPerson.DataBean> dataList = new ArrayList<>();
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.etLong1)
    EditText etLong1;
    @BindView(R.id.etLong2)
    EditText etLong2;
    @BindView(R.id.etLong3)
    EditText etLong3;
    @BindView(R.id.etLong4)
    EditText etLong4;
    @BindView(R.id.etLong5)
    EditText etLong5;
    @BindView(R.id.etWide1)
    EditText etWide1;
    @BindView(R.id.etWide2)
    EditText etWide2;
    @BindView(R.id.etWide3)
    EditText etWide3;
    @BindView(R.id.etWide4)
    EditText etWide4;
    @BindView(R.id.etWide5)
    EditText etWide5;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.tvLeader1)
    TextView tvLeader1;
    @BindView(R.id.tvLeader)
    TextView tvLeader;
    @BindView(R.id.tvLeader2)
    TextView tvLeader2;
    @BindView(R.id.btnLR)
    Button btnLR;
    Map<String, String> firstmap = new HashMap<>();
    private CustomDatePickerDay customDatePicker1;
    DepartBudgetLRPresenter departBudgetLRPresenter;
    DepartBudgeCheckTypePresenter departBudgeCheckTypePresenter;
    String vocationId = "";
    String selectTag = "1";
    double longS1 = 0.0, longS2 = 0.0, longS3 = 0.0, longS4 = 0.0, longS5 = 0.0;
    double wideS1 = 0.0, wideS2 = 0.0, wideS3 = 0.0, wideS4 = 0.0, wideS5 = 0.0;
    int allNum1 = 0, allNum2 = 0, allNum3 = 0, allNum4 = 0, allNum5 = 0;
    double moneyS1 = 0.0, moneyS2 = 0.0, moneyS3 = 0.0, moneyS4 = 0.0, moneyS5 = 0.0;
    double AllMoney1 = 0.0, AllMoney2 = 0.0, AllMoney3 = 0.0, AllMoney4 = 0.0, AllMoney5 = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        header.setRightTv(false);
        initDatePicker();
        departBudgetLRPresenter = new DepartBudgetLRPresenter(this, this);
        departBudgeCheckTypePresenter = new DepartBudgeCheckTypePresenter(this, this);
        etAllMoney1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    AllMoney1 = (Double.valueOf(s.toString()));
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                } else {
                    AllMoney1 = 0.0;
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                }
            }
        });
        etAllMoney2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    AllMoney2 = (Double.valueOf(s.toString()));
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                } else {
                    AllMoney2 = 0.0;
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                }
            }
        });
        etAllMoney3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    AllMoney3 = (Double.valueOf(s.toString()));
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                } else {
                    AllMoney3 = 0.0;
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                }
            }
        });
        etAllMoney4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    AllMoney4 = (Double.valueOf(s.toString()));
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                } else {
                    AllMoney4 = 0.0;
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                }
            }
        });
        etAllMoney5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    AllMoney5 = (Double.valueOf(s.toString()));
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                } else {
                    AllMoney5 = 0.0;
                    tvAllMoney.setText(String.valueOf(AllMoney1 + AllMoney2 + AllMoney3 + AllMoney4 + AllMoney5));
                }
            }
        });

        etNum1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    allNum1 = (Integer.valueOf(s.toString()));
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    if (!etMoney1.getText().toString().equals("")&&!etLong1.getText().toString().equals("") &&!etWide1.getText().toString().equals("")) {
                        etAllMoney1.setText(String.valueOf(Double.valueOf(etMoney1.getText().toString()) * allNum1 * Double.valueOf(etLong1.getText().toString()) * Double.valueOf(etWide1.getText().toString())));
                    } else {
                        etAllMoney1.setText(String.valueOf(0 * allNum1));
                    }
                } else {
                    allNum1 = 0;
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    etAllMoney1.setText(String.valueOf(0 * moneyS1));
                }
            }
        });
        etNum2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    allNum2 = (Integer.valueOf(s.toString()));
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    if (!etMoney2.getText().toString().equals("")&&!etLong2.getText().toString().equals("") &&!etWide2.getText().toString().equals("")) {
                        etAllMoney2.setText(String.valueOf(Double.valueOf(etMoney2.getText().toString())
                                * allNum2*Double.valueOf(etLong2.getText().toString())*Double.valueOf(etWide2.getText().toString())));
                    } else {
                        etAllMoney2.setText(String.valueOf(0 * allNum2));
                    }
                } else {
                    allNum2 = 0;
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    etAllMoney2.setText(String.valueOf(0 * moneyS2));
                }
            }
        });
        etNum3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    allNum3 = (Integer.valueOf(s.toString()));
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    if (!etMoney3.getText().toString().equals("")&&!etLong3.getText().toString().equals("") &&!etWide3.getText().toString().equals("")) {
                        etAllMoney3.setText(String.valueOf(Double.valueOf(etMoney3.getText().toString())
                                * allNum3*Double.valueOf(etLong3.getText().toString())*Double.valueOf(etWide3.getText().toString())));
                    } else {
                        etAllMoney3.setText(String.valueOf(0 * allNum3));
                    }
                } else {
                    allNum3 = 0;
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    etAllMoney3.setText(String.valueOf(0 * moneyS3));
                }
            }
        });
        etNum4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    allNum4 = (Integer.valueOf(s.toString()));
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    if (!etMoney4.getText().toString().equals("")&&!etLong4.getText().toString().equals("") &&!etWide4.getText().toString().equals("")) {
                        etAllMoney4.setText(String.valueOf(Double.valueOf(etMoney4.getText().toString())
                                * allNum4*Double.valueOf(etLong4.getText().toString())*Double.valueOf(etWide4.getText().toString())));
                    } else {
                        etAllMoney4.setText(String.valueOf(0 * allNum4));
                    }
                } else {
                    allNum4 = 0;
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    etAllMoney4.setText(String.valueOf(0 * moneyS4));
                }
            }
        });
        etNum5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    allNum5 = (Integer.valueOf(s.toString()));
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    if (!etMoney5.getText().toString().equals("")&&!etLong5.getText().toString().equals("") &&!etWide5.getText().toString().equals("")) {
                        etAllMoney5.setText(String.valueOf(Double.valueOf(etMoney5.getText().toString())
                                * allNum5*Double.valueOf(etLong5.getText().toString())*Double.valueOf(etWide5.getText().toString())));
                    } else {
                        etAllMoney5.setText(String.valueOf(0 * allNum5));
                    }
                } else {
                    allNum5 = 0;
                    tvAllNum.setText(String.valueOf(allNum1 + allNum2 + allNum3 + allNum4 + allNum5));
                    etAllMoney5.setText(String.valueOf(0 * moneyS5));
                }
            }
        });

        etMoney1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    moneyS1 = (Double.valueOf(s.toString()));
                    if (!etNum1.getText().toString().equals("")&&!etLong1.getText().toString().equals("") &&!etWide1.getText().toString().equals("")) {
                        etAllMoney1.setText(String.valueOf(Double.valueOf(etNum1.getText().toString())
                                * moneyS1*Double.valueOf(etLong1.getText().toString())*Double.valueOf(etWide1.getText().toString())));
                    } else {
                        etAllMoney1.setText(String.valueOf(0 * moneyS1));
                    }
                } else {
                    etAllMoney1.setText(String.valueOf(0 * moneyS1));
                }
            }
        });
        etMoney2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    moneyS2 = (Double.valueOf(s.toString()));
                    if (!etNum2.getText().toString().equals("")&&!etLong2.getText().toString().equals("") &&!etWide2.getText().toString().equals("")) {
                        etAllMoney2.setText(String.valueOf(Double.valueOf(etNum2.getText().toString())
                                * moneyS2*Double.valueOf(etLong2.getText().toString())*Double.valueOf(etWide2.getText().toString())));
                    } else {
                        etAllMoney2.setText(String.valueOf(0 * moneyS2));
                    }
                } else {
                    etAllMoney2.setText(String.valueOf(0 * moneyS2));
                }
            }
        });
        etMoney3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    moneyS3 = (Double.valueOf(s.toString()));
                    if (!etNum3.getText().toString().equals("")&&!etLong3.getText().toString().equals("") &&!etWide3.getText().toString().equals("")) {
                        etAllMoney3.setText(String.valueOf(Double.valueOf(etNum3.getText().toString())
                                * moneyS3*Double.valueOf(etLong3.getText().toString())*Double.valueOf(etWide3.getText().toString())));
                    } else {
                        etAllMoney3.setText(String.valueOf(0 * moneyS3));
                    }
                } else {
                    etAllMoney3.setText(String.valueOf(0 * moneyS3));
                }
            }
        });
        etMoney4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    moneyS4 = (Double.valueOf(s.toString()));
                    if (!etNum4.getText().toString().equals("")&&!etLong4.getText().toString().equals("") &&!etWide4.getText().toString().equals("")) {
                        etAllMoney4.setText(String.valueOf(Double.valueOf(etNum4.getText().toString())
                                * moneyS4*Double.valueOf(etLong4.getText().toString())*Double.valueOf(etWide4.getText().toString())));
                    } else {
                        etAllMoney4.setText(String.valueOf(0 * moneyS4));
                    }
                } else {
                    etAllMoney4.setText(String.valueOf(0 * moneyS4));
                }
            }
        });
        etMoney5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    moneyS5 = (Double.valueOf(s.toString()));
                    if (!etNum5.getText().toString().equals("")&&!etLong5.getText().toString().equals("") &&!etWide5.getText().toString().equals("")) {
                        etAllMoney5.setText(String.valueOf(Double.valueOf(etNum4.getText().toString())
                                * moneyS4*Double.valueOf(etLong5.getText().toString())*Double.valueOf(etWide5.getText().toString())));
                    } else {
                        etAllMoney5.setText(String.valueOf(0 * moneyS5));
                    }
                } else {
                    etAllMoney5.setText(String.valueOf(0 * moneyS5));
                }
            }
        });

        etLong1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    longS1 = (Double.valueOf(s.toString()));
                    if (!etNum1.getText().toString().equals("")&&!etMoney1.getText().toString().equals("")&&!etWide1.getText().toString().equals("")) {
                        etAllMoney1.setText(String.valueOf(Double.valueOf(etNum1.getText().toString())
                                * longS1*Double.valueOf(etMoney1.getText().toString())*Double.valueOf(etWide1.getText().toString())));
                    } else {
                        etAllMoney1.setText(String.valueOf(0 * moneyS1));
                    }
                } else {
                    etAllMoney1.setText(String.valueOf(0 * moneyS1));
                }
            }
        });
        etLong2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    longS2 = (Double.valueOf(s.toString()));
                    if (!etNum2.getText().toString().equals("")&&!etMoney2.getText().toString().equals("")&&!etWide2.getText().toString().equals("")) {
                        etAllMoney2.setText(String.valueOf(Double.valueOf(etNum2.getText().toString())
                                * longS2*Double.valueOf(etMoney2.getText().toString())*Double.valueOf(etWide2.getText().toString())));
                    } else {
                        etAllMoney2.setText(String.valueOf(0 * moneyS2));
                    }
                } else {
                    etAllMoney2.setText(String.valueOf(0 * moneyS2));
                }
            }
        });
        etLong3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    longS3 = (Double.valueOf(s.toString()));
                    if (!etNum3.getText().toString().equals("")&&!etMoney3.getText().toString().equals("")&&!etWide3.getText().toString().equals("")) {
                        etAllMoney3.setText(String.valueOf(Double.valueOf(etNum3.getText().toString())
                                * longS3*Double.valueOf(etMoney3.getText().toString())*Double.valueOf(etWide3.getText().toString())));
                    } else {
                        etAllMoney3.setText(String.valueOf(0 * moneyS3));
                    }
                } else {
                    etAllMoney3.setText(String.valueOf(0 * moneyS3));
                }
            }
        });
        etLong4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    longS4 = (Double.valueOf(s.toString()));
                    if (!etNum4.getText().toString().equals("")&&!etMoney4.getText().toString().equals("")&&!etWide4.getText().toString().equals("")) {
                        etAllMoney4.setText(String.valueOf(Double.valueOf(etNum4.getText().toString())
                                * longS4*Double.valueOf(etMoney4.getText().toString())*Double.valueOf(etWide4.getText().toString())));
                    } else {
                        etAllMoney4.setText(String.valueOf(0 * moneyS4));
                    }
                } else {
                    etAllMoney4.setText(String.valueOf(0 * moneyS4));
                }
            }
        });
        etLong5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    longS5 = (Double.valueOf(s.toString()));
                    if (!etNum5.getText().toString().equals("")&&!etMoney5.getText().toString().equals("")&&!etWide5.getText().toString().equals("")) {
                        etAllMoney5.setText(String.valueOf(Double.valueOf(etNum5.getText().toString())
                                * longS5*Double.valueOf(etMoney5.getText().toString())*Double.valueOf(etWide5.getText().toString())));
                    }  else {
                        etAllMoney5.setText(String.valueOf(0 * moneyS5));
                    }
                } else {
                    etAllMoney5.setText(String.valueOf(0 * moneyS5));
                }
            }
        });

        etWide1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    wideS1 = (Double.valueOf(s.toString()));
                    if (!etNum1.getText().toString().equals("")&&!etMoney1.getText().toString().equals("")&&!etLong1.getText().toString().equals("")) {
                        etAllMoney1.setText(String.valueOf(Double.valueOf(etNum1.getText().toString())
                                * wideS1*Double.valueOf(etMoney1.getText().toString())*Double.valueOf(etLong1.getText().toString())));
                    } else {
                        etAllMoney1.setText(String.valueOf(0 * moneyS1));
                    }
                } else {
                    etAllMoney1.setText(String.valueOf(0 * moneyS1));
                }
            }
        });
        etWide2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    wideS2 = (Double.valueOf(s.toString()));
                    if (!etNum2.getText().toString().equals("")&&!etMoney2.getText().toString().equals("")&&!etLong2.getText().toString().equals("")) {
                        etAllMoney2.setText(String.valueOf(Double.valueOf(etNum2.getText().toString())
                                * wideS2*Double.valueOf(etMoney2.getText().toString())*Double.valueOf(etLong2.getText().toString())));
                    } else {
                        etAllMoney2.setText(String.valueOf(0 * moneyS2));
                    }
                } else {
                    etAllMoney2.setText(String.valueOf(0 * moneyS2));
                }
            }
        });
        etWide3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    wideS3 = (Double.valueOf(s.toString()));
                    if (!etNum3.getText().toString().equals("")&&!etMoney3.getText().toString().equals("")&&!etLong3.getText().toString().equals("")) {
                        etAllMoney3.setText(String.valueOf(Double.valueOf(etNum3.getText().toString())
                                * wideS3*Double.valueOf(etMoney3.getText().toString())*Double.valueOf(etLong3.getText().toString())));
                    } else {
                        etAllMoney3.setText(String.valueOf(0 * moneyS3));
                    }
                } else {
                    etAllMoney3.setText(String.valueOf(0 * moneyS3));
                }
            }
        });
        etWide4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    wideS4 = (Double.valueOf(s.toString()));
                    if (!etNum4.getText().toString().equals("")&&!etMoney4.getText().toString().equals("")&&!etLong4.getText().toString().equals("")) {
                        etAllMoney4.setText(String.valueOf(Double.valueOf(etNum4.getText().toString())
                                * wideS4*Double.valueOf(etMoney4.getText().toString())*Double.valueOf(etLong4.getText().toString())));
                    } else {
                        etAllMoney4.setText(String.valueOf(0 * moneyS4));
                    }
                } else {
                    etAllMoney4.setText(String.valueOf(0 * moneyS4));
                }
            }
        });
        etWide5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String editStr = s.toString().trim();
                int posDot = editStr.indexOf(".");
                //不允许输入3位小数,超过三位就删掉
                if (posDot > 0) {
                    if (editStr.length() - posDot - 1 > 2) {
                        s.delete(posDot + 2, posDot + 3);
                    } else {
                        //TODO...在这里写逻辑
                    }
                }
                if (!s.toString().equals("")) {
                    wideS5 = (Double.valueOf(s.toString()));
                    if (!etNum5.getText().toString().equals("")&&!etMoney5.getText().toString().equals("")&&!etLong5.getText().toString().equals("")) {
                        etAllMoney5.setText(String.valueOf(Double.valueOf(etNum5.getText().toString())
                                * wideS5*Double.valueOf(etMoney5.getText().toString())*Double.valueOf(etLong5.getText().toString())));
                    }  else {
                        etAllMoney5.setText(String.valueOf(0 * moneyS5));
                    }
                } else {
                    etAllMoney5.setText(String.valueOf(0 * moneyS5));
                }
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_adver_budget;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {

    }

    /**
     * 选择时间
     */
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tvTime.setText(now.split(" ")[0]);
        customDatePicker1 = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                tvTime.setText(time.split(" ")[0]);
            }
            // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        }, "2000-01-01 00:00", "2030-01-01 00:00");
        // 不显示时和分
        customDatePicker1.showSpecificTime(false);
        // 不允许循环滚动
        customDatePicker1.setIsLoop(false);
    }

    @OnClick({R.id.tvTime, R.id.btnUp, R.id.tvDepartment, R.id.btnLR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLR:
                if (tvDepartment.getText().toString().equals("")) {
                    Toast.makeText(this, "请选择部门", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etName.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写费用项目", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etNum.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写数量", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etUse.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写预算理由", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etName1.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写项目名称", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etDepartment1.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写单位", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etMoney1.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写项目单价", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etName1.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写数量", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (etLong1.getText().toString().equals("")||etWide1.getText().toString().equals("")) {
                    Toast.makeText(this, "长和宽不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                setDataFirst();
                departBudgetLRPresenter.getDepartBudgeLR(firstmap);
                break;
            case R.id.tvDepartment:
                Intent intent = new Intent(this, DepartmentActivity.class);
                startActivityForResult(intent, Constant.TAG_ONE);
                break;
            case R.id.tvTime:
                customDatePicker1.show(tvTime.getText().toString());
                break;
            case R.id.btnUp:
                if (selectTag.equals("2")) {
                    namelist.clear();
                    codeList.clear();
                    nameList.clear();
                    selectList.clear();
                    namelist1.clear();
                    dataList.clear();
                    if (tvDepartment.getText().toString().equals("")) {
                        Toast.makeText(this, "请选择部门", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (etName1.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写项目名称", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (etDepartment1.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写单位", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (etMoney1.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写项目单价", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (etName1.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写数量", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    ysdOnePersenter = new OnePresenter(this, this);
                    ysdOnePersenter.getOnePerson(Constant.ADVER_DEFID);
                    ysdTwoPersenter = new TwoPresenter(this, this);
                    upYsdPersenter = new UPYSDPresenter(this, this);
                } else {
                    Toast.makeText(this, "请先录入", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setDataFirst() {
        firstmap.put("defId", Constant.ADVER_DEFID);
        firstmap.put("startFlow", "true");
        firstmap.put("formDefId", Constant.ADVER_FORMDEFIS);
        firstmap.put("depId", depId);
        firstmap.put("depName", depName);
        firstmap.put("project", etName.getText().toString());
        firstmap.put("createDate", tvTime.getText().toString());
        firstmap.put("bzly", etUse.getText().toString());
        firstmap.put("xm1", etName1.getText().toString());
        firstmap.put("xm2", etName2.getText().toString());
        firstmap.put("xm3", etName3.getText().toString());
        firstmap.put("xm4", etName4.getText().toString());
        firstmap.put("xm5", etName5.getText().toString());
        firstmap.put("dw1", etDepartment1.getText().toString());
        firstmap.put("dw2", etDepartment2.getText().toString());
        firstmap.put("dw3", etDepartment3.getText().toString());
        firstmap.put("dw4", etDepartment4.getText().toString());
        firstmap.put("dw5", etDepartment5.getText().toString());
        firstmap.put("dj1", etMoney1.getText().toString());
        firstmap.put("dj2", etMoney2.getText().toString());
        firstmap.put("dj3", etMoney3.getText().toString());
        firstmap.put("dj4", etMoney4.getText().toString());
        firstmap.put("dj5", etMoney5.getText().toString());
        firstmap.put("sl1", etNum1.getText().toString());
        firstmap.put("sl2", etNum2.getText().toString());
        firstmap.put("sl3", etNum3.getText().toString());
        firstmap.put("sl4", etNum4.getText().toString());
        firstmap.put("sl5", etNum5.getText().toString());
        firstmap.put("je1", etAllMoney1.getText().toString());
        firstmap.put("je2", etAllMoney2.getText().toString());
        firstmap.put("je3", etAllMoney3.getText().toString());
        firstmap.put("je4", etAllMoney4.getText().toString());
        firstmap.put("je5", etAllMoney5.getText().toString());
        firstmap.put("high1", etLong1.getText().toString());
        firstmap.put("high2", etLong2.getText().toString());
        firstmap.put("high3", etLong3.getText().toString());
        firstmap.put("high4", etLong4.getText().toString());
        firstmap.put("high5", etLong5.getText().toString());
        firstmap.put("wide1", etWide1.getText().toString());
        firstmap.put("wide2", etWide2.getText().toString());
        firstmap.put("wide3", etWide3.getText().toString());
        firstmap.put("wide4", etWide4.getText().toString());
        firstmap.put("wide5", etWide5.getText().toString());
        firstmap.put("type", "广告");
        firstmap.put("hjsl1", tvAllNum.getText().toString());
        firstmap.put("hjsl2", "");
        firstmap.put("hjje1", tvAllMoney.getText().toString());
        firstmap.put("hjje2", "");
        String zbr = new SharedPreferencesHelper(this, "login").getData(this, "userName1", "");
        firstmap.put("zhibiao", zbr);
    }

    private void setData() {
        map.put("defId", Constant.ADVER_DEFID);
        map.put("startFlow", "true");
        map.put("formDefId", Constant.ADVER_FORMDEFIS);
        map.put("depNameDid", depId);
        map.put("depName", depName);
        map.put("createDate", tvTime.getText().toString());
        map.put("bzly", etUse.getText().toString());
        map.put("project", etName.getText().toString());
        map.put("nums", etNum.getText().toString());
        map.put("xm1", etName1.getText().toString());
        map.put("xm2", etName2.getText().toString());
        map.put("xm3", etName3.getText().toString());
        map.put("xm4", etName4.getText().toString());
        map.put("xm5", etName5.getText().toString());
        map.put("dw1", etDepartment1.getText().toString());
        map.put("dw2", etDepartment2.getText().toString());
        map.put("dw3", etDepartment3.getText().toString());
        map.put("dw4", etDepartment4.getText().toString());
        map.put("dw5", etDepartment5.getText().toString());
        map.put("dj1", etMoney1.getText().toString());
        map.put("dj2", etMoney2.getText().toString());
        map.put("dj3", etMoney3.getText().toString());
        map.put("dj4", etMoney4.getText().toString());
        map.put("dj5", etMoney5.getText().toString());
        map.put("sl1", etNum1.getText().toString());
        map.put("sl2", etNum2.getText().toString());
        map.put("sl3", etNum3.getText().toString());
        map.put("sl4", etNum4.getText().toString());
        map.put("sl5", etNum5.getText().toString());
        map.put("je1", etAllMoney1.getText().toString());
        map.put("je2", etAllMoney2.getText().toString());
        map.put("je3", etAllMoney3.getText().toString());
        map.put("je4", etAllMoney4.getText().toString());
        map.put("je5", etAllMoney5.getText().toString());
        map.put("high1", etLong1.getText().toString());
        map.put("high2", etLong2.getText().toString());
        map.put("high3", etLong3.getText().toString());
        map.put("high4", etLong4.getText().toString());
        map.put("high5", etLong5.getText().toString());
        map.put("wide1", etWide1.getText().toString());
        map.put("wide2", etWide2.getText().toString());
        map.put("wide3", etWide3.getText().toString());
        map.put("wide4", etWide4.getText().toString());
        map.put("wide5", etWide5.getText().toString());
        map.put("type", "广告");
        map.put("hjsl1", tvAllNum.getText().toString());
        map.put("hjsl2", "");
        map.put("hjje1", tvAllMoney.getText().toString());
        map.put("hjje2", "");
        map.put("dataUrl_save", "/joffice/hrm/updateFinanceAmount.do?id=" + vocationId);
        String zbr = new SharedPreferencesHelper(this, "login").getData(this, "userName1", "");
        map.put("zhibiao", zbr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.TAG_ONE && requestCode == Constant.TAG_ONE) {
            if (data != null) {
                DepartmentDataBean departmentDataBean = (DepartmentDataBean) data.getSerializableExtra("department");
                depId = departmentDataBean.getDepId();
                depName = departmentDataBean.getDepName();
                tvDepartment.setText(depName);
            }
        }
    }

    @Override
    public void setOnePerson(OnePerson s) {
        for (int i = 0; i < s.getData().size(); i++) {
            String name = s.getData().get(i).getDestination();
            namelist.add(name);
        }
        if (namelist.size() != 0) {
            if (namelist.size() == 1) {
                userDepart = namelist.get(0);
                ysdTwoPersenter.getTwoPerson(Constant.ADVER_DEFID, namelist.get(0));
            } else {
                MyAlertDialog.MyListAlertDialog(this, namelist, new AlertDialogCallBackP() {
                    @Override
                    public void oneselect(final String data) {
                        userDepart = data;
                        ysdTwoPersenter.getTwoPerson(Constant.ADVER_DEFID, data);
                    }

                    @Override
                    public void select(List<String> list) {

                    }

                    @Override
                    public void confirm() {

                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        } else {
            Toast.makeText(this, "审批人为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setOnePersonMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTwoPerson(TwoPerson s) {
        for (int i = 0; i < s.getData().size(); i++) {
            TwoPerson.DataBean bean = new TwoPerson.DataBean();
            bean.setUserNames(s.getData().get(i).getUserNames());
            bean.setUserCodes(s.getData().get(i).getUserCodes());
            dataList.add(bean);
        }
        if (dataList.size() == 1) {
            TwoPerson.DataBean bean1 = dataList.get(0);
            userCode = bean1.getUserCodes();
            userName = bean1.getUserNames();
            nametemp = userName.split(",");
            codetemp = userCode.split(",");
            if (codetemp != null) {
                for (String s1 : codetemp) {
                    codeList.add(s1);
                }
            }
            if (nametemp != null) {
                for (String s1 : nametemp) {
                    nameList.add(s1);
                }
            }
            if (codeList.size() == 1) {
                selectList.add(codeList.get(0));
                getListData();
                setData();
                map.put("flowAssignId", userDepart + "|" + uId);
                upYsdPersenter.getUPYSD(map);
            } else {
                MyAlertDialog.MyListAlertDialog(isShow, codeList, nameList, namelist1, this, new AlertDialogCallBackP() {

                    @Override
                    public void select(List<String> data) {
                        selectList = data;
                        getListData();
                        setData();
                        map.put("flowAssignId", userDepart + "|" + uId);
                        upYsdPersenter.getUPYSD(map);
                    }

                    @Override
                    public void oneselect(String s) {

                    }

                    @Override
                    public void confirm() {

                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        }
    }

    private String getListData() {
        if (selectList.size() == 1) {
            //uName = backlist.get(0).getActivityName();
            uId = selectList.get(0);
        }
        if (selectList.size() > 1) {
            for (int i = 1; i < selectList.size(); i++) {
                if (i < selectList.size() - 1) {
                    uId = uId + selectList.get(i) + ",";
                } else {
                    uId = uId + selectList.get(i);
                }
            }
            uId = selectList.get(0) + "," + uId;
        }
        return uId;
    }

    @Override
    public void setTwoPersonMessage(String s) {

    }

    /**
     * 发布
     *
     * @param s
     */
    @Override
    public void setUPYSD(BackData s) {
        if (s.isSuccess()) {
            departBudgeCheckTypePresenter.getDepartBudgeCheckType(String.valueOf(s.getRunId()), vocationId);
        }
    }

    @Override
    public void setUPYSDMessage(String s) {
        Toast.makeText(this, "提交数据失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 状态修改
     *
     * @param s
     */
    @Override
    public void setDepartBudgeCheckType(CheckType s) {
        if (s.isSuccess()) {
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            btnUp.setEnabled(false);
            btnUp.setBackgroundColor(getResources().getColor(R.color.shouye));
            finish();
        }
    }

    @Override
    public void setDepartBudgeCheckTypeMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 录入
     *
     * @param s
     */
    @Override
    public void setDepartBudgeLR(CapitalApprovalCheckType s) {
        if (s.isSuccess()) {
            vocationId = s.getId();
            selectTag = "2";
            btnUp.setEnabled(true);
            btnLR.setEnabled(false);
            btnLR.setBackgroundColor(getResources().getColor(R.color.shouye));
            Toast.makeText(this, "录入成功请提交数据", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "录入失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setDepartBudgeLRMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
