package com.smartbus.heze.fileapprove.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.fileapprove.bean.DepartBudgetWill;
import com.smartbus.heze.fileapprove.bean.NoEndPerson;
import com.smartbus.heze.fileapprove.bean.NoHandlerPerson;
import com.smartbus.heze.fileapprove.bean.NormalPerson;
import com.smartbus.heze.fileapprove.bean.WillDoUp;
import com.smartbus.heze.fileapprove.module.DepartBudgetWillContract;
import com.smartbus.heze.fileapprove.module.NoEndContract;
import com.smartbus.heze.fileapprove.module.NoHandlerContract;
import com.smartbus.heze.fileapprove.module.NormalContract;
import com.smartbus.heze.fileapprove.module.WillDoContract;
import com.smartbus.heze.fileapprove.presenter.DepartBudgetWillPresenter;
import com.smartbus.heze.fileapprove.presenter.NoEndPresenter;
import com.smartbus.heze.fileapprove.presenter.NoHandlerPresenter;
import com.smartbus.heze.fileapprove.presenter.NormalPresenter;
import com.smartbus.heze.fileapprove.presenter.WillDoPresenter;
import com.smartbus.heze.http.base.AlertDialogCallBackP;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.http.views.MyAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 各部门的预算单待办
 */
public class AdverBudgeWillActivity extends BaseActivity implements DepartBudgetWillContract.View
        , NormalContract.View, NoEndContract.View, NoHandlerContract.View, WillDoContract.View {

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
    @BindView(R.id.tvLeader)
    TextView tvLeader;
    @BindView(R.id.tvLeader1)
    TextView tvLeader1;
    @BindView(R.id.tvLeader2)
    TextView tvLeader2;
    @BindView(R.id.btnUp)
    Button btnUp;

    String mainId = "";
    String destType = "";
    String leaderCode = "";
    String leaderName = "";
    String zhibiao;
    String destName, uId, signaName;
    String activityName, taskId;
    String[] bigNametemp = null;
    String[] bigCodetemp = null;
    NormalPresenter normalPresenter;
    NoEndPresenter noEndPersenter;
    NoHandlerPresenter noHandlerPresenter;
    WillDoPresenter willDoPresenter;
    DepartBudgetWillPresenter departBudgetWillPresenter;
    List<String> selectList = new ArrayList<>();
    List<String> namelist = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    List<DepartBudgetWill.TransBean> destTypeList = new ArrayList<>();

    int allNum1 = 0, allNum2 = 0, allNum3 = 0, allNum4 = 0, allNum5 = 0;
    double moneyS1 = 0.0, moneyS2 = 0.0, moneyS3 = 0.0, moneyS4 = 0.0, moneyS5 = 0.0;
    double longS1 = 0.0, longS2 = 0.0, longS3 = 0.0, longS4 = 0.0, longS5 = 0.0;
    double wideS1 = 0.0, wideS2 = 0.0, wideS3 = 0.0, wideS4 = 0.0, wideS5 = 0.0;
    double AllMoney1 = 0.0, AllMoney2 = 0.0, AllMoney3 = 0.0, AllMoney4 = 0.0, AllMoney5 = 0.0;
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
    @BindView(R.id.btnLR)
    Button btnLR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        btnLR.setVisibility(View.GONE);
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

        Intent intent = getIntent();
        activityName = intent.getStringExtra("activityName");
        taskId = intent.getStringExtra("taskId");
        normalPresenter = new NormalPresenter(this, this);
        noEndPersenter = new NoEndPresenter(this, this);
        noHandlerPresenter = new NoHandlerPresenter(this, this);
        willDoPresenter = new WillDoPresenter(this, this);
        Log.e("sessionLogin ", taskId + "-" + activityName);
        departBudgetWillPresenter = new DepartBudgetWillPresenter(this, this);
        departBudgetWillPresenter.getDepartBudgetWill(activityName, taskId, Constant.ADVER_DEFID);
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
        setDataBack();
        map.put("back", "true");
        map.put("useTemplate", "");
        willDoPresenter.getWillDo(map);
    }

    @OnClick({R.id.btnUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnUp:
                if (etLeader.getVisibility() == View.VISIBLE
                        || etLeader1.getVisibility() == View.VISIBLE
                        || etLeader2.getVisibility() == View.VISIBLE) {
                    if (etLeader.getText().toString().equals("")
                            && etLeader1.getText().toString().equals("")
                            && etLeader2.getText().toString().equals("")) {
                        Toast.makeText(this, "请填写意见", Toast.LENGTH_SHORT).show();
                    } else {
                        getSomeData();
                    }
                } else {
                    getSomeData();
                }
                break;
        }
    }

    public void getSomeData() {
        if (destTypeList.size() != 0) {
            if (destTypeList.size() == 1) {
                destType = destTypeList.get(0).getDestType();
                destName = destTypeList.get(0).getDestination();
                if (destType.equals("decision") || destType.equals("fork") || destType.equals("join")) {
                    normalPresenter.getNormalPerson(taskId, destName, "false");
                } else if (destType.indexOf("end") == -1) {
                    noEndPersenter.getNoEndPerson(taskId, destName, "false");
                } else {
                    noHandlerPresenter.getNoHandlerPerson(taskId);
                }
                signaName = destTypeList.get(0).getName();
            } else {
                namelist.clear();
                for (int i = 0; i < destTypeList.size(); i++) {
                    namelist.add(destTypeList.get(i).getDestination());
                }
                MyAlertDialog.MyListAlertDialog(this, namelist, new AlertDialogCallBackP() {
                    @Override
                    public void oneselect(final String data) {
                        destName = data;
                        for (int i = 0; i < destTypeList.size(); i++) {
                            if (destName.equals(destTypeList.get(i).getDestination())) {
                                signaName = destTypeList.get(i).getName();
                                destType = destTypeList.get(i).getDestType();
                            }
                        }
                        if (destType.equals("decision") || destType.equals("fork") || destType.equals("join")) {
                            normalPresenter.getNormalPerson(taskId, destName, "false");
                        } else if (destType.indexOf("end") == -1) {
                            noEndPersenter.getNoEndPerson(taskId, destName, "false");
                        } else {
                            noHandlerPresenter.getNoHandlerPerson(taskId);
                        }
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

    private void setData() {
        map.put("defId", Constant.ADVER_DEFID);
        map.put("startFlow", "true");
        map.put("formDefId", Constant.ADVER_FORMDEFIS);
        map.put("depName", tvDepartment.getText().toString());
        map.put("createDate", tvTime.getText().toString());
        map.put("bzly", etUse.getText().toString());
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
        map.put("project", etName.getText().toString());
        map.put("type", "公告");

        map.put("hjsl1", tvAllNum.getText().toString());
        map.put("mainId", mainId);
        map.put("taskId", taskId);
        map.put("hjsl2", "");
        map.put("hjje1", tvAllMoney.getText().toString());
        map.put("hjje2", "");
        map.put("signalName", signaName);
        map.put("destName", destName);
        map.put("zhibiao", zhibiao);
        if (tvLeader.getVisibility() == View.VISIBLE) {
            if (!tvLeader.getText().toString().equals("")) {
                map.put("cwk", tvLeader.getText().toString());
            }
        } else {
            map.put("cwk", etLeader.getText().toString());
            map.put("comments", etLeader.getText().toString());
        }
        if (tvLeader1.getVisibility() == View.VISIBLE) {
            if (!tvLeader1.getText().toString().equals("")) {
                map.put("fgyj", tvLeader1.getText().toString());
            }
        } else {
            map.put("fgyj", etLeader1.getText().toString());
            map.put("comments", etLeader1.getText().toString());
        }
        if (tvLeader2.getVisibility() == View.VISIBLE) {
            if (!tvLeader2.getText().toString().equals("")) {
                map.put("jlyj", tvLeader2.getText().toString());
            }
        } else {
            map.put("jlyj", etLeader2.getText().toString());
            map.put("comments", etLeader2.getText().toString());
        }
    }

    private void setDataBack() {
        map.put("defId", Constant.ADVER_DEFID);
        map.put("startFlow", "true");
        map.put("formDefId", Constant.ADVER_FORMDEFIS);
        map.put("depName", tvDepartment.getText().toString());
        map.put("createDate", tvTime.getText().toString());
        map.put("bzly", etUse.getText().toString());
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
        map.put("project", etName.getText().toString());
        map.put("type", "公告");

        map.put("hjsl1", tvAllNum.getText().toString());
        map.put("mainId", mainId);
        map.put("taskId", taskId);
        map.put("hjsl2", "");
        map.put("hjje1", tvAllMoney.getText().toString());
        map.put("hjje2", "");
        map.put("destName", destTypeList.get(0).getDestination());
        map.put("zhibiao", zhibiao);
    }

    @Override
    public void setDepartBudgetWill(DepartBudgetWill s) {
        zhibiao = s.getMainform().get(0).getZhibiao().toString();
        etName.setText(s.getMainform().get(0).getProject().toString());
        etNum.setText(s.getMainform().get(0).getNums().toString());
        etUse.setText(s.getMainform().get(0).getBzly().toString());
        etName1.setText(s.getMainform().get(0).getXm1().toString());
        etName2.setText(s.getMainform().get(0).getXm2().toString());
        etName3.setText(s.getMainform().get(0).getXm3().toString());
        etName4.setText(s.getMainform().get(0).getXm4().toString());
        etName5.setText(s.getMainform().get(0).getXm5().toString());

        etDepartment1.setText(s.getMainform().get(0).getDw1().toString());
        etDepartment2.setText(s.getMainform().get(0).getDw2().toString());
        etDepartment3.setText(s.getMainform().get(0).getDw3().toString());
        etDepartment4.setText(s.getMainform().get(0).getDw4().toString());
        etDepartment5.setText(s.getMainform().get(0).getDw5().toString());

        etMoney1.setText(s.getMainform().get(0).getDj1().toString());
        etMoney2.setText(s.getMainform().get(0).getDj2().toString());
        etMoney3.setText(s.getMainform().get(0).getDj3().toString());
        etMoney4.setText(s.getMainform().get(0).getDj4().toString());
        etMoney5.setText(s.getMainform().get(0).getDj5().toString());

        etNum1.setText(s.getMainform().get(0).getSl1().toString());
        etNum2.setText(s.getMainform().get(0).getSl2().toString());
        etNum3.setText(s.getMainform().get(0).getSl3().toString());
        etNum4.setText(s.getMainform().get(0).getSl4().toString());
        etNum5.setText(s.getMainform().get(0).getSl5().toString());

        etAllMoney1.setText(s.getMainform().get(0).getJe1().toString());
        etAllMoney2.setText(s.getMainform().get(0).getJe2().toString());
        etAllMoney3.setText(s.getMainform().get(0).getJe3().toString());
        etAllMoney4.setText(s.getMainform().get(0).getJe4().toString());
        etAllMoney5.setText(s.getMainform().get(0).getJe5().toString());

        etLong1.setText(s.getMainform().get(0).getHigh1().toString());
        etLong2.setText(s.getMainform().get(0).getHigh2().toString());
        etLong3.setText(s.getMainform().get(0).getHigh3().toString());
        etLong4.setText(s.getMainform().get(0).getHigh4().toString());
        etLong5.setText(s.getMainform().get(0).getHigh5().toString());

        etWide1.setText(s.getMainform().get(0).getWide1().toString());
        etWide2.setText(s.getMainform().get(0).getWide2().toString());
        etWide3.setText(s.getMainform().get(0).getWide3().toString());
        etWide4.setText(s.getMainform().get(0).getWide4().toString());
        etWide5.setText(s.getMainform().get(0).getWide5().toString());

        etName.setText(s.getMainform().get(0).getProject().toString());

        tvAllNum.setText(s.getMainform().get(0).getHjsl1());
        tvAllMoney.setText(s.getMainform().get(0).getHjje1());
        tvDepartment.setText(s.getMainform().get(0).getDepName());
        tvTime.setText(s.getMainform().get(0).getCreateDate());
        mainId = String.valueOf(s.getMainform().get(0).getMainId());
        String leader = s.getMainform().get(0).getCwk();
        String leader1 = s.getMainform().get(0).getFgyj();
        String leader2 = s.getMainform().get(0).getJlyj();
        String move = s.getFormRights();
        try {
            JSONObject jsonObject = new JSONObject(move);
            String cwMove = jsonObject.getString("cwk");
            String fgMove = jsonObject.getString("fgyj");
            String zjlMove = jsonObject.getString("jlyj");
            if (cwMove.equals("2")) {
                tvLeader.setVisibility(View.GONE);
                etLeader.setVisibility(View.VISIBLE);
                if (leader != null && leader.length() != 0) {
                    etLeader.setText(leader);
                }
            } else {
                tvLeader.setVisibility(View.VISIBLE);
                etLeader.setVisibility(View.GONE);
                if (leader != null && leader.length() != 0) {
                    tvLeader.setText(leader);
                }
            }

            if (fgMove.equals("2")) {
                tvLeader1.setVisibility(View.GONE);
                etLeader1.setVisibility(View.VISIBLE);
                if (leader1 != null && leader1.length() != 0) {
                    etLeader1.setText(leader1);
                }
            } else {
                tvLeader1.setVisibility(View.VISIBLE);
                etLeader1.setVisibility(View.GONE);
                if (leader1 != null && leader1.length() != 0) {
                    tvLeader1.setText(leader1);
                }
            }

            if (zjlMove.equals("2")) {
                tvLeader2.setVisibility(View.GONE);
                etLeader2.setVisibility(View.VISIBLE);
                if (leader2 != null && leader2.length() != 0) {
                    etLeader2.setText(leader2);
                }
            } else {
                tvLeader2.setVisibility(View.VISIBLE);
                etLeader2.setVisibility(View.GONE);
                if (leader2 != null && leader2.length() != 0) {
                    tvLeader2.setText(leader2);
                }
            }

            for (int i = 0; i < s.getTrans().size(); i++) {
                destTypeList.add(s.getTrans().get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDepartBudgetWillMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNormalPerson(NormalPerson s) {
        if (s.getData() != null) {
            leaderName = s.getData().get(0).getUserNames();
            leaderCode = s.getData().get(0).getUserCodes();
            bigNametemp = leaderName.split(",");
            bigCodetemp = leaderCode.split(",");
            setDialog();
        }
    }

    @Override
    public void setNormalPersonMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNoEndPerson(NoEndPerson s) {
        if (s.getData() != null) {
            leaderName = s.getData().get(0).getUserNames();
            leaderCode = s.getData().get(0).getUserCodes();
            bigNametemp = leaderName.split(",");
            bigCodetemp = leaderCode.split(",");
            setDialog();
        }
    }

    @Override
    public void setNoEndPersonMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNoHandlerPerson(NoHandlerPerson s) {
        setData();
        map.put("flowAssignId", destName + "|" + uId);
        willDoPresenter.getWillDo(map);
    }

    @Override
    public void setNoHandlerPersonMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWillDo(WillDoUp s) {
        if (s.isSuccess()) {
            Toast.makeText(this, "数据提交成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, s.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setWillDoMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void setDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog3 = alertDialogBuilder.create();
//                alertDialogBuilder.setTitle("java EE 常用框架");
        // 参数介绍
        // 第一个参数：弹出框的信息集合，一般为字符串集合
        // 第二个参数：被默认选中的，一个布尔类型的数组
        // 第三个参数：勾选事件监听
        final boolean[] checkedItems = new boolean[bigNametemp.length + 1];
        for (int i = 0; i < bigNametemp.length; i++) {
            checkedItems[i] = false;
        }
        new AlertDialog.Builder(this)
                .setTitle("选择审核人")//标题栏
                .setMultiChoiceItems(bigNametemp, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // dialog：不常使用，弹出框接口
                        // which：勾选或取消的是第几个
                        // isChecked：是否勾选
                        if (isChecked) {
                            // 选中
                            checkedItems[which] = isChecked;
                        } else {
                            // 取消选中
                            checkedItems[which] = isChecked;
                        }
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //TODO 业务逻辑代码
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        selectList.add(bigCodetemp[i]);
                    }
                }
                getListData();
                setData();
                // 关闭提示框
                alertDialog3.dismiss();
                map.put("flowAssignId", destName + "|" + uId);
                willDoPresenter.getWillDo(map);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO 业务逻辑代码

                // 关闭提示框
                alertDialog3.dismiss();
            }
        }).show();
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

}
