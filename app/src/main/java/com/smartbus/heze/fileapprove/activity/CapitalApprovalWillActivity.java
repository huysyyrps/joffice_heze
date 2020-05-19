package com.smartbus.heze.fileapprove.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
import com.smartbus.heze.fileapprove.bean.CapitalApprovalWill;
import com.smartbus.heze.fileapprove.bean.NoEndPerson;
import com.smartbus.heze.fileapprove.bean.NoHandlerPerson;
import com.smartbus.heze.fileapprove.bean.NormalPerson;
import com.smartbus.heze.fileapprove.bean.QZHQ;
import com.smartbus.heze.fileapprove.bean.QZLR;
import com.smartbus.heze.fileapprove.bean.WillDoUp;
import com.smartbus.heze.fileapprove.module.CapitalApprovalWillCheckTypeContract;
import com.smartbus.heze.fileapprove.module.CapitalApprovalWillContract;
import com.smartbus.heze.fileapprove.module.NoEndContract;
import com.smartbus.heze.fileapprove.module.NoHandlerContract;
import com.smartbus.heze.fileapprove.module.NormalContract;
import com.smartbus.heze.fileapprove.module.QZHQContract;
import com.smartbus.heze.fileapprove.module.QZLRContract;
import com.smartbus.heze.fileapprove.module.WillDoContract;
import com.smartbus.heze.fileapprove.presenter.CapitalApprovalWillCheckTypePresenter;
import com.smartbus.heze.fileapprove.presenter.CapitalApprovalWillPresenter;
import com.smartbus.heze.fileapprove.presenter.NoEndPresenter;
import com.smartbus.heze.fileapprove.presenter.NoHandlerPresenter;
import com.smartbus.heze.fileapprove.presenter.NormalPresenter;
import com.smartbus.heze.fileapprove.presenter.QZHQPresenter;
import com.smartbus.heze.fileapprove.presenter.QZLRPresenter;
import com.smartbus.heze.fileapprove.presenter.WillDoPresenter;
import com.smartbus.heze.http.base.AlertDialogCallBackP;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.base.SignatureView;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.http.views.MyAlertDialog;
import com.smartbus.heze.oaflow.bean.CheckType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资金审批待办
 */
public class CapitalApprovalWillActivity extends BaseActivity implements CapitalApprovalWillContract.View
        , NormalContract.View, NoEndContract.View, NoHandlerContract.View, WillDoContract.View
        , CapitalApprovalWillCheckTypeContract.View, QZLRContract.View, QZHQContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvLeader)
    TextView tvLeader;
    @BindView(R.id.etLeader)
    EditText etLeader;
    @BindView(R.id.tvLeader1)
    TextView tvLeader1;
    @BindView(R.id.etLeader1)
    EditText etLeader1;
    @BindView(R.id.tvLeader2)
    TextView tvLeader2;
    @BindView(R.id.etLeader2)
    EditText etLeader2;
    @BindView(R.id.tvLeader3)
    TextView tvLeader3;
    @BindView(R.id.etLeader3)
    EditText etLeader3;
    @BindView(R.id.tvLeader4)
    TextView tvLeader4;
    @BindView(R.id.etLeader4)
    EditText etLeader4;
    @BindView(R.id.btnUp)
    Button btnUp;
    @BindView(R.id.tvSingle)
    TextView tvSingle;
    @BindView(R.id.imLeader)
    ImageView imLeader;
    @BindView(R.id.imLeader1)
    ImageView imLeader1;
    @BindView(R.id.imLeader2)
    ImageView imLeader2;
    @BindView(R.id.imLeader3)
    ImageView imLeader3;
    @BindView(R.id.imLeader4)
    ImageView imLeader4;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.id_sign)
    SignatureView idSign;
    @BindView(R.id.no)
    TextView no;
    @BindView(R.id.yes)
    TextView yes;
    @BindView(R.id.llSingle)
    LinearLayout llSingle;
    @BindView(R.id.llData)
    LinearLayout llData;
    @BindView(R.id.llLeader)
    LinearLayout llLeader;
    @BindView(R.id.llLeader1)
    LinearLayout llLeader1;
    @BindView(R.id.llLeader2)
    LinearLayout llLeader2;
    @BindView(R.id.llLeader3)
    LinearLayout llLeader3;
    @BindView(R.id.llLeader4)
    LinearLayout llLeader4;

    String mainId = "";
    String destType = "";
    String leaderCode = "";
    String leaderName = "";
    String mycomments = "";
    String userId = "";
    String fullname = "";
    String ksMove = "";
    String cwMove = "";
    String zjlMove = "";
    String cwspMove = "";
    String cwjlMove = "";
    String qzList = "";
    String tag = "";
    String dataUrl_save;
    String runId, accidentLoanId;
    String destName, uId, signaName;
    String activityName, taskId;
    String[] bigNametemp = null;
    String[] bigCodetemp = null;
    NormalPresenter normalPresenter;
    NoEndPresenter noEndPersenter;
    NoHandlerPresenter noHandlerPresenter;
    WillDoPresenter willDoPresenter;
    QZLRPresenter qzlrpresenter;
    QZHQPresenter qzhqPresenter;
    public static boolean makeImahe = false;
    private List<Bitmap> bitmaps = new ArrayList<>();
    private List<File> uploadFiles = new ArrayList<>();
    CapitalApprovalWillPresenter capitalApprovalWillPresenter;
    CapitalApprovalWillCheckTypePresenter borrowAccidentWillCheckTypePresenter;
    List<String> selectList = new ArrayList<>();
    List<String> namelist = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    Map<String, String> mapqz = new HashMap<>();
    List<QZHQ.DataBean> qzhqList = new ArrayList<>();
    List<CapitalApprovalWill.TransBean> destTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        activityName = intent.getStringExtra("activityName");
        taskId = intent.getStringExtra("taskId");
        userId = new SharedPreferencesHelper(this, "login").getData(this, "userId", "");
        fullname = new SharedPreferencesHelper(this, "login").getData(this, "userName1", "");
        if (!fullname.equals("殷正红")) {
            tvSingle.setVisibility(View.GONE);
        }
        normalPresenter = new NormalPresenter(this, this);
        noEndPersenter = new NoEndPresenter(this, this);
        noHandlerPresenter = new NoHandlerPresenter(this, this);
        willDoPresenter = new WillDoPresenter(this, this);
        qzlrpresenter = new QZLRPresenter(this, this);
        qzhqPresenter = new QZHQPresenter(this, this);
        Log.e("sessionLogin ", taskId + "-" + activityName);
        capitalApprovalWillPresenter = new CapitalApprovalWillPresenter(this, this);
        borrowAccidentWillCheckTypePresenter = new CapitalApprovalWillCheckTypePresenter(this, this);
        capitalApprovalWillPresenter.getCapitalApprovalWill(activityName, taskId, Constant.CAPITALAPPROVAL_DEFID);
        idSign.setSignatureCallBack(new SignatureView.ISignatureCallBack() {
            @Override
            public void onSignCompeleted(View view, Bitmap bitmap) {
                String fileDir = getExternalCacheDir() + "signature/";
                String path = fileDir + "sign.png";
                File file = new File(fileDir);
                if (!file.exists()) {
                    file.mkdir();
                }
                bitmaps.add(bitmap);
                try {
                    idSign.save(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadFiles.add(new File(path));
                drawBitmaps(bitmap);
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_capital_approval_will;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        if (etLeader.getVisibility() == View.VISIBLE || etLeader1.getVisibility() == View.VISIBLE
                || etLeader2.getVisibility() == View.VISIBLE || etLeader3.getVisibility() == View.VISIBLE
                || etLeader4.getVisibility() == View.VISIBLE) {
            if (etLeader.getText().toString().equals("") && etLeader1.getText().toString().equals("")
                    && etLeader2.getText().toString().equals("") && etLeader3.getText().toString().equals("")
                    && etLeader4.getText().toString().equals("")) {
                Toast.makeText(this, "输入意见后再次点击驳回", Toast.LENGTH_SHORT).show();
            } else {
                setDataBack();
                map.put("back", "true");
                map.put("useTemplate", "");
                willDoPresenter.getWillDo(map);
            }
        } else {
            setDataBack();
            map.put("back", "true");
            map.put("useTemplate", "");
            willDoPresenter.getWillDo(map);
        }
    }

    private void drawBitmaps(Bitmap bitmap) {
        if (ksMove.equals("3")) {
            tvLeader.setVisibility(View.GONE);
            etLeader.setVisibility(View.VISIBLE);
            llLeader.setVisibility(View.VISIBLE);
            imLeader.setImageBitmap(bitmap);
        }

        if (cwMove.equals("3")) {
            tvLeader1.setVisibility(View.GONE);
            etLeader1.setVisibility(View.VISIBLE);
            llLeader1.setVisibility(View.VISIBLE);
            imLeader1.setImageBitmap(bitmap);
        }

        if (zjlMove.equals("3")) {
            tvLeader2.setVisibility(View.GONE);
            etLeader2.setVisibility(View.VISIBLE);
            llLeader2.setVisibility(View.VISIBLE);
            imLeader2.setImageBitmap(bitmap);
        }

        if (cwspMove.equals("3")) {
            tvLeader3.setVisibility(View.GONE);
            etLeader3.setVisibility(View.VISIBLE);
            llLeader3.setVisibility(View.VISIBLE);
            imLeader3.setImageBitmap(bitmap);
        }

//                if (cwjlMove.equals("3")) {
//                    tvLeader4.setVisibility(View.GONE);
//                    etLeader4.setVisibility(View.VISIBLE);
//                    if (leader4 != null && leader4.length() != 0) {
////                        etLeader4.setText(leader4);
//                    }
//                } else {
//                    tvLeader4.setVisibility(View.VISIBLE);
//                    etLeader4.setVisibility(View.GONE);
//                    if (leader4 != null && leader4.length() != 0) {
//                        tvLeader4.setText(leader4);
//                        if (imleader4 != null && imleader4.length() != 0) {
//                            imLeader4.setImageBitmap(base64ToBitmap(imleader4));
//                        }
//                    }
//                }
    }

    @OnClick({R.id.tvSingle, R.id.btnUp, R.id.no, R.id.yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSingle:
                llSingle.setVisibility(View.VISIBLE);
                llData.setVisibility(View.GONE);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                makeImahe = false;
                break;
            case R.id.no:
                llSingle.setVisibility(View.GONE);
                llData.setVisibility(View.VISIBLE);
                break;
            case R.id.yes:
                llSingle.setVisibility(View.GONE);
                llData.setVisibility(View.VISIBLE);
                idSign.setMakeImage();
                break;
            case R.id.btnUp:
                if (etLeader.getVisibility() == View.VISIBLE
                        || etLeader1.getVisibility() == View.VISIBLE
                        || etLeader2.getVisibility() == View.VISIBLE
                        || etLeader3.getVisibility() == View.VISIBLE) {
                    if (etLeader.getText().toString().equals("")
                            && etLeader1.getText().toString().equals("")
                            && etLeader2.getText().toString().equals("")
                            && etLeader3.getText().toString().equals("")) {
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

    private void setData(String tag) {
        map.put("taskId", taskId);
        map.put("signalName", signaName);
        map.put("destName", destName);
        map.put("mainId", mainId);
        map.put("formDefId", Constant.CAPITALAPPROVAL_FORMDEFIS);
        map.put("sbdate", tvTime.getText().toString());
//        map.put("dataUrl_save", dataUrl_save);
        map.put("sbdepName", tvDepartment.getText().toString());
        map.put("sbrname", tvName.getText().toString());
        map.put("sbtype", tvType.getText().toString());
        map.put("sbmemo", tvContent.getText().toString());

        if (tvLeader.getVisibility() == View.VISIBLE) {
            if (!tvLeader.getText().toString().equals("")) {
                map.put("keshiyijian", tvLeader.getText().toString());
            }
        } else {
            map.put("keshiyijian", etLeader.getText().toString());
            map.put("comments", etLeader.getText().toString());
            mycomments = etLeader.getText().toString();
            if (tag.equals("")) {
                map.put("keshiqz", qzList);
            }
        }
        if (tvLeader1.getVisibility() == View.VISIBLE) {
            if (!tvLeader1.getText().toString().equals("")) {
                map.put("caiwuyijian", tvLeader1.getText().toString());
            }
        } else {
            map.put("caiwuyijian", etLeader1.getText().toString());
            map.put("comments", etLeader1.getText().toString());
            mycomments = etLeader1.getText().toString();
            if (tag.equals("1")) {
                map.put("caiwuqz", qzList);
            }
        }
        if (tvLeader2.getVisibility() == View.VISIBLE) {
            if (!tvLeader2.getText().toString().equals("")) {
                map.put("zongjingliyijian", tvLeader2.getText().toString());
            }
        } else {
            map.put("zongjingliyijian", etLeader2.getText().toString());
            map.put("comments", etLeader2.getText().toString());
            mycomments = etLeader2.getText().toString();
            if (tag.equals("2")) {
                map.put("zjlqz", qzList);
            }
        }
        if (tvLeader3.getVisibility() == View.VISIBLE) {
            if (!tvLeader3.getText().toString().equals("")) {
                map.put("caiwujingli", tvLeader3.getText().toString());
            }
        } else {
            map.put("caiwujingli", etLeader3.getText().toString());
            map.put("comments", etLeader3.getText().toString());
            mycomments = etLeader3.getText().toString();
            if (tag.equals("3")) {
                map.put("caiwujingliqz", qzList);
            }
        }

        if (tvLeader4.getVisibility() == View.VISIBLE) {
            if (!tvLeader4.getText().toString().equals("")) {
                map.put("caiwuke", tvLeader4.getText().toString());
            }
        } else {
            map.put("caiwuke", etLeader4.getText().toString());
            map.put("comments", etLeader4.getText().toString());
            mycomments = etLeader4.getText().toString();
            if (tag.equals("4")) {
                map.put("caiwushenpiqz", qzList);
            }
        }
    }

    private void setDataBack() {
        map.put("taskId", taskId);
        map.put("destName", destTypeList.get(0).getDestination());
        map.put("mainId", mainId);
        map.put("formDefId", Constant.CAPITALAPPROVAL_FORMDEFIS);
        map.put("sbdate", tvTime.getText().toString());
        map.put("dataUrl_save", dataUrl_save);
        map.put("sbdepName", tvDepartment.getText().toString());
        map.put("sbrname", tvName.getText().toString());
        map.put("sbtype", tvType.getText().toString());
        map.put("sbmemo", tvContent.getText().toString());
        if (tvLeader.getVisibility() == View.VISIBLE) {
            if (!tvLeader.getText().toString().equals("")) {
                map.put("keshiyijian", tvLeader.getText().toString());
            }
        } else {
            map.put("keshiyijian", etLeader.getText().toString());
            map.put("comments", etLeader.getText().toString());
            mycomments = etLeader.getText().toString();
        }
        if (tvLeader1.getVisibility() == View.VISIBLE) {
            if (!tvLeader1.getText().toString().equals("")) {
                map.put("caiwuyijian", tvLeader1.getText().toString());
            }
        } else {
            map.put("caiwuyijian", etLeader1.getText().toString());
            map.put("comments", etLeader1.getText().toString());
            mycomments = etLeader1.getText().toString();
        }
        if (tvLeader2.getVisibility() == View.VISIBLE) {
            if (!tvLeader2.getText().toString().equals("")) {
                map.put("zongjingliyijian", tvLeader2.getText().toString());
            }
        } else {
            map.put("zongjingliyijian", etLeader2.getText().toString());
            map.put("comments", etLeader2.getText().toString());
            mycomments = etLeader2.getText().toString();
        }
        if (tvLeader3.getVisibility() == View.VISIBLE) {
            if (!tvLeader3.getText().toString().equals("")) {
                map.put("caiwujingli", tvLeader3.getText().toString());
            }
        } else {
            map.put("caiwujingli", etLeader3.getText().toString());
            map.put("comments", etLeader3.getText().toString());
            mycomments = etLeader3.getText().toString();
        }

        if (tvLeader4.getVisibility() == View.VISIBLE) {
            if (!tvLeader4.getText().toString().equals("")) {
                map.put("caiwuke", tvLeader4.getText().toString());
            }
        } else {
            map.put("caiwuke", etLeader4.getText().toString());
            map.put("comments", etLeader4.getText().toString());
            mycomments = etLeader4.getText().toString();
        }
    }

    @Override
    public void setCapitalApprovalWill(CapitalApprovalWill s) {
        if (s != null) {
            tvTime.setText(s.getMainform().get(0).getSbdate().toString());
            tvDepartment.setText(s.getMainform().get(0).getSbdepName().toString());
            tvName.setText(s.getMainform().get(0).getSbrname().toString());
            tvType.setText(s.getMainform().get(0).getSbtype().toString());
            tvContent.setText(s.getMainform().get(0).getSbmemo());
            runId = s.getMainform().get(0).getRunId();
            dataUrl_save = s.getMainform().get(0).getDataUrl_save().toString();
            accidentLoanId = dataUrl_save.split("id=")[1];
            mainId = String.valueOf(s.getMainform().get(0).getMainId());
            String leader = s.getMainform().get(0).getKeshiyijian();
            String leader1 = s.getMainform().get(0).getCaiwuyijian();
            String leader2 = s.getMainform().get(0).getZongjingliyijian();
            String leader3 = s.getMainform().get(0).getCaiwujingli();
            String leader4 = s.getMainform().get(0).getCaiwuke();
            String move = s.getFormRights();
            try {
                JSONObject jsonObject = new JSONObject(move);
                ksMove = jsonObject.getString("keshiyijian");
                cwMove = jsonObject.getString("caiwuyijian");
                zjlMove = jsonObject.getString("zongjingli");
                cwspMove = jsonObject.getString("caiwujingli");
                cwjlMove = jsonObject.getString("caiwuke");
                if (ksMove.equals("3")) {
                    tvLeader.setVisibility(View.GONE);
                    etLeader.setVisibility(View.VISIBLE);
                    if (leader != null && leader.length() != 0) {
//                        etLeader.setText(leader);
                    }
                } else {
                    tvLeader.setVisibility(View.VISIBLE);
                    etLeader.setVisibility(View.GONE);
                    if (leader != null && leader.length() != 0) {
                        tvLeader.setText(leader);
                    }
                }

                if (cwMove.equals("3")) {
                    tvLeader1.setVisibility(View.GONE);
                    etLeader1.setVisibility(View.VISIBLE);
                    if (leader1 != null && leader1.length() != 0) {
//                        etLeader1.setText(leader1);
                    }
                } else {
                    tvLeader1.setVisibility(View.VISIBLE);
                    etLeader1.setVisibility(View.GONE);
                    if (leader1 != null && leader1.length() != 0) {
                        tvLeader1.setText(leader1);
                    }
                }

                if (zjlMove.equals("3")) {
                    tvLeader2.setVisibility(View.GONE);
                    etLeader2.setVisibility(View.VISIBLE);
                    if (leader2 != null && leader2.length() != 0) {
//                        etLeader2.setText(leader2);
                    }
                } else {
                    tvLeader2.setVisibility(View.VISIBLE);
                    etLeader2.setVisibility(View.GONE);
                    if (leader2 != null && leader2.length() != 0) {
                        tvLeader2.setText(leader2);
                    }
                }

                if (cwspMove.equals("3")) {
                    tvLeader3.setVisibility(View.GONE);
                    etLeader3.setVisibility(View.VISIBLE);
                    if (leader3 != null && leader3.length() != 0) {
//                        etLeader3.setText(leader3);
                    }
                } else {
                    tvLeader3.setVisibility(View.VISIBLE);
                    etLeader3.setVisibility(View.GONE);
                    if (leader3 != null && leader3.length() != 0) {
                        tvLeader3.setText(leader3);
                    }
                }

                if (cwjlMove.equals("3")) {
                    tvLeader4.setVisibility(View.GONE);
                    etLeader4.setVisibility(View.VISIBLE);
                    if (leader4 != null && leader4.length() != 0) {
//                        etLeader4.setText(leader4);
                    }
                } else {
                    tvLeader4.setVisibility(View.VISIBLE);
                    etLeader4.setVisibility(View.GONE);
                    if (leader4 != null && leader4.length() != 0) {
                        tvLeader4.setText(leader4);
                    }
                }

                if (ksMove.equals("3")) {
                    tvLeader1.setTextColor(getResources().getColor(R.color.holo_red_light));
                    tvLeader2.setTextColor(getResources().getColor(R.color.holo_red_light));
                    tvLeader3.setTextColor(getResources().getColor(R.color.holo_red_light));
                    tvLeader4.setTextColor(getResources().getColor(R.color.holo_red_light));
                }
                if (cwMove.equals("3")) {
                    tvLeader2.setTextColor(getResources().getColor(R.color.holo_red_light));
                    tvLeader3.setTextColor(getResources().getColor(R.color.holo_red_light));
                    tvLeader4.setTextColor(getResources().getColor(R.color.holo_red_light));
                }
                if (zjlMove.equals("3")) {
                    tvLeader3.setTextColor(getResources().getColor(R.color.holo_red_light));
                    tvLeader4.setTextColor(getResources().getColor(R.color.holo_red_light));
                }
                if (cwspMove.equals("3")) {
                    tvLeader4.setTextColor(getResources().getColor(R.color.holo_red_light));
                }
                for (int i = 0; i < s.getTrans().size(); i++) {
                    destTypeList.add(s.getTrans().get(i));
                }
                qzhqPresenter.getQZHQ(runId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setCapitalApprovalWillMessage(String s) {
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


        if (tvLeader.getVisibility() == View.VISIBLE && tvLeader1.getVisibility() == View.VISIBLE
                && tvLeader2.getVisibility() == View.VISIBLE && tvLeader3.getVisibility() == View.VISIBLE
                && tvLeader4.getVisibility() == View.VISIBLE) {
            willDoPresenter.getWillDo(map);
        } else {
            if (imLeader2.getDrawable() != null) {
                tag = "2";
                mapqz.clear();
                mapqz.put("userId", userId);
                mapqz.put("fullname", fullname);
                mapqz.put("runId", runId);
                mapqz.put("picString", bitmapToBase64(((BitmapDrawable) ((ImageView) imLeader2).getDrawable()).getBitmap()));
                mapqz.put("activityName", activityName);
                mapqz.put("fieldName", "zjlqz");
                qzlrpresenter.getQZLR(mapqz);
            } else {
                setData(tag);
                map.put("flowAssignId", destName + "|" + uId);
                if (!mycomments.equals("同意") && !mycomments.equals("不同意")) {
                    map.clear();
                    Toast.makeText(CapitalApprovalWillActivity.this, "意见请填写同意或不同意", Toast.LENGTH_SHORT).show();
                } else {
                    willDoPresenter.getWillDo(map);
                }
            }
        }
    }

    @Override
    public void setNoHandlerPersonMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWillDo(WillDoUp s) {
        if (s.isSuccess()) {
            borrowAccidentWillCheckTypePresenter.getCapitalApprovalCheckType(runId, accidentLoanId, destName, mycomments);
        } else {
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

                // 关闭提示框
                alertDialog3.dismiss();
                if (tvLeader.getVisibility() == View.VISIBLE && tvLeader1.getVisibility() == View.VISIBLE
                        && tvLeader2.getVisibility() == View.VISIBLE && tvLeader3.getVisibility() == View.VISIBLE
                        && tvLeader4.getVisibility() == View.VISIBLE) {
                    willDoPresenter.getWillDo(map);
                } else {
                    if (imLeader2.getDrawable() != null) {
                        tag = "2";
                        mapqz.clear();
                        mapqz.put("userId", userId);
                        mapqz.put("fullname", fullname);
                        mapqz.put("runId", runId);
                        mapqz.put("picString", bitmapToBase64(((BitmapDrawable) ((ImageView) imLeader2).getDrawable()).getBitmap()));
                        mapqz.put("activityName", activityName);
                        mapqz.put("fieldName", "zjlqz");
                        qzlrpresenter.getQZLR(mapqz);
                    } else {
                        setData(tag);
                        map.put("flowAssignId", destName + "|" + uId);
                        if (!mycomments.equals("同意") && !mycomments.equals("不同意")) {
                            map.clear();
                            Toast.makeText(CapitalApprovalWillActivity.this, "意见请填写同意或不同意", Toast.LENGTH_SHORT).show();
                        } else {
                            willDoPresenter.getWillDo(map);
                        }
                    }
                }
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
        uId = "";
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
    public void setCapitalApprovalWillCheckType(CheckType s) {
        if (s.isSuccess()) {
            Toast.makeText(this, "数据提交成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void setCapitalApprovalWillCheckTypeMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 签章录入
     *
     * @param s
     */
    @Override
    public void setQZLR(QZLR s) {
        if (s.isSuccess()) {
            qzList = s.getPicId();
        }
        setData(tag);
        map.put("flowAssignId", destName + "|" + uId);
        willDoPresenter.getWillDo(map);
//        if (tag.equals("")){
//            setData(tag);
//            map.put("flowAssignId", destName + "|" + uId);
//        }else if (tag.equals("1")){
//            setData(tag);
//            map.put("flowAssignId", destName + "|" + uId);
//        }else if (tag.equals("2")){
//            setData(tag);
//            map.put("flowAssignId", destName + "|" + uId);
//        }
    }

    @Override
    public void setQZLRMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取签章
     *
     * @param s
     */
    @Override
    public void setQZHQ(QZHQ s) {
        if (s.isSuccess()) {
            for (int i = 0; i < s.getData().size(); i++) {
//                qzhqList.add(s.getData().get(i));
                if (s.getData().get(i).getFieldName().equals("keshiqz")
                        && s.getData().get(i).getPicString() != null
                        && !s.getData().get(i).getPicString().equals("")) {
                    imLeader.setImageBitmap(base64ToBitmap(s.getData().get(i).getPicString()));
                    llLeader.setVisibility(View.VISIBLE);
                }
                if (s.getData().get(i).getFieldName().equals("caiwuqz")
                        && s.getData().get(i).getPicString() != null
                        && !s.getData().get(i).getPicString().equals("")) {
                    imLeader1.setImageBitmap(base64ToBitmap(s.getData().get(i).getPicString()));
                    llLeader1.setVisibility(View.VISIBLE);
                }
                if (s.getData().get(i).getFieldName().equals("zjlqz")
                        && s.getData().get(i).getPicString() != null
                        && !s.getData().get(i).getPicString().equals("")) {
                    imLeader2.setImageBitmap(base64ToBitmap(s.getData().get(i).getPicString()));
                    llLeader2.setVisibility(View.VISIBLE);
                }
                if (s.getData().get(i).getFieldName().equals("caiwujingliqz")
                        && s.getData().get(i).getPicString() != null
                        && !s.getData().get(i).getPicString().equals("")) {
                    imLeader3.setImageBitmap(base64ToBitmap(s.getData().get(i).getPicString()));
                    llLeader3.setVisibility(View.VISIBLE);
                }
                if (s.getData().get(i).getFieldName().equals("caiushenpiqz")
                        && s.getData().get(i).getPicString() != null
                        && !s.getData().get(i).getPicString().equals("")) {
                    imLeader4.setImageBitmap(base64ToBitmap(s.getData().get(i).getPicString()));
                    llLeader4.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void setQZHQMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /*
 * bitmap转base64
 * */
    private static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
