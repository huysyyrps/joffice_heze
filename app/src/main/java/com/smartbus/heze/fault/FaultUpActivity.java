package com.smartbus.heze.fault;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.smartbus.heze.ApiAddress;
import com.smartbus.heze.R;
import com.smartbus.heze.checkup.activitydata.CarCodeActivity;
import com.smartbus.heze.checkup.activitydata.LineCodeActivity;
import com.smartbus.heze.checkup.bean.CarCodeData;
import com.smartbus.heze.checkup.bean.LineCodeData;
import com.smartbus.heze.checkup.bean.UpData;
import com.smartbus.heze.fault.bean.AboutData;
import com.smartbus.heze.fault.module.AboutDataContract;
import com.smartbus.heze.fault.module.UpDataContract;
import com.smartbus.heze.fault.presenter.AboutDataPresenter;
import com.smartbus.heze.fault.presenter.UpDataPresenter;
import com.smartbus.heze.fileapprove.activity.WorkOnePersonActivity;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.base.ProgressDialogUtil;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.UriTrytoPath;
import com.smartbus.heze.http.utils.time_select.CustomDatePickerDay;
import com.smartbus.heze.http.views.Header;
import com.smartbus.heze.oasheet.presenter.NoPresenter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.smartbus.heze.http.base.Constant.TAG_FOUR;
import static com.smartbus.heze.http.base.Constant.TAG_ONE;

public class FaultUpActivity extends BaseActivity implements AboutDataContract.View, UpDataContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.tvLineNo)
    TextView tvLineNo;
    @BindView(R.id.tvDriverCode)
    TextView tvDriverCode;
    @BindView(R.id.etDriver)
    EditText etDriver;
    @BindView(R.id.etWorkDate)
    EditText etWorkDate;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etSex)
    EditText etSex;
    @BindView(R.id.etCarType)
    EditText etCarType;
    @BindView(R.id.etWorkNowTime)
    EditText etWorkNowTime;
    @BindView(R.id.etSafeMonth)
    EditText etSafeMonth;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.spinnerWeather)
    Spinner spinnerWeather;
    @BindView(R.id.spinnerAddressType)
    Spinner spinnerAddressType;
    @BindView(R.id.spinnerSGType)
    Spinner spinnerSGType;
    @BindView(R.id.spinnerSGZR)
    Spinner spinnerSGZR;
    @BindView(R.id.spinnerSGXZ)
    Spinner spinnerSGXZ;
    @BindView(R.id.spinnerSGLB)
    Spinner spinnerSGLB;
    @BindView(R.id.etSWRS)
    EditText etSWRS;
    @BindView(R.id.etSIWRS)
    EditText etSIWRS;
    @BindView(R.id.etSGAddress)
    EditText etSGAddress;
    @BindView(R.id.etSGReason)
    EditText etSGReason;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.imAdd01)
    ImageView imAdd01;
    @BindView(R.id.imAdd02)
    ImageView imAdd02;
    @BindView(R.id.imAdd03)
    ImageView imAdd03;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    Intent intent;
    int imageNum = 0;
    @BindView(R.id.tvDriverCode1)
    TextView tvDriverCode1;
    @BindView(R.id.tvJBRName)
    TextView tvJBRName;
    @BindView(R.id.tvJBRName1)
    TextView tvJBRName1;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.etReason)
    EditText etReason;
    private Uri imageUri;
    String atPhoto = "";
    String fileName1 = "";
    String fileName2 = "";
    String fileName3 = "";
    String busCode = "";
    String depName = "";
    String depNameId = "";
    public static File tempFile;
    NoPresenter noPresenter;
    AboutDataPresenter abboutDataPresenter;
    UpDataPresenter upDataPresenter;
    ArrayAdapter<String> weathwerAdapter;
    ArrayAdapter<String> addressLBAdapter;
    ArrayAdapter<String> sglxAdapter;
    ArrayAdapter<String> sgzrAdapter;
    ArrayAdapter<String> sgxzAdapter;
    ArrayAdapter<String> sglbAdapter;
    List<String> listWeathwer = new ArrayList<String>();
    List<String> listAddressLB = new ArrayList<String>();
    List<String> listSglx = new ArrayList<String>();
    List<String> listSgzr = new ArrayList<String>();
    List<String> listSgxz = new ArrayList<String>();
    List<String> listSglb = new ArrayList<String>();
    private CustomDatePickerDay customDatePicker;
    private static final int MY_PERMISSIONS_MY_UP_IMAGE = 1;

    File tmpDir;
    boolean tag = false;
    String dirPath = "temp";
    //图片list
    private ArrayList<String> mResults = new ArrayList<>();
    //图片地址
    private ArrayList<String> photoPath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initDatePicker();
        listWeathwer.add("晴天");
        listWeathwer.add("阴天");
        listWeathwer.add("雨、雪、雾");
        weathwerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWeathwer);
        weathwerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeather.setAdapter(weathwerAdapter);

        listAddressLB.add("十字路口");
        listAddressLB.add("站牌处");
        listAddressLB.add("场站、单方");
        listAddressLB.add("拥堵");
        listAddressLB.add("郊区");
        addressLBAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAddressLB);
        addressLBAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddressType.setAdapter(addressLBAdapter);

        listSglx.add("变道");
        listSglx.add("转弯");
        listSglx.add("观察不周");
        listSglx.add("未确保安全车距");
        listSglx.add("未按规范操作");
        listSglx.add("注意力分散");
        listSglx.add("其他");
        sglxAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSglx);
        sglxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSGType.setAdapter(sglxAdapter);

        listSgzr.add("全部");
        listSgzr.add("主要");
        listSgzr.add("同等");
        listSgzr.add("次要");
        listSgzr.add("无责");
        sgzrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSgzr);
        sgzrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSGZR.setAdapter(sgzrAdapter);

        listSgxz.add("台帐事故");
        listSgxz.add("无责事故");
        listSgxz.add("非台账事故");
        sgxzAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSgxz);
        sgxzAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSGXZ.setAdapter(sgxzAdapter);

        listSglb.add("轻微一般事故");
        listSglb.add("重大事故");
        listSglb.add("特大事故");
        listSglb.add("上报事故");
        sglbAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSglb);
        sglbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSGLB.setAdapter(sglbAdapter);

        abboutDataPresenter = new AboutDataPresenter(this, this);
        upDataPresenter = new UpDataPresenter(this, this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_fault_up;
    }

    @Override
    protected boolean isHasHeader() {
        return true;
    }

    @Override
    protected void rightClient() {
        if (tvCarNo.getText().toString().equals("")) {
            Toast.makeText(this, "车牌号不能为空", Toast.LENGTH_SHORT).show();
        } else if (tvLineNo.getText().toString().equals("")) {
            Toast.makeText(this, "线路编号不能为空", Toast.LENGTH_SHORT).show();
        } else if (tvDriverCode.getText().toString().equals("")) {
            Toast.makeText(this, "驾驶员不能为空", Toast.LENGTH_SHORT).show();
        }else if (tvJBRName.getText().toString().equals("")) {
            Toast.makeText(this, "经办人不能为空", Toast.LENGTH_SHORT).show();
        }else if (etMoney.getText().toString().equals("")) {
            Toast.makeText(this, "赔款金额不能为空", Toast.LENGTH_SHORT).show();
        }else if (etReason.getText().toString().equals("")) {
            Toast.makeText(this, "处理结果不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!fileName1.equals("") && fileName2.equals("") && fileName3.equals("")) {
                atPhoto = fileName1;
            }
            if (!fileName2.equals("") && fileName3.equals("")) {
                atPhoto = fileName1 + "," + fileName2;
            }
            if (!fileName3.equals("")) {
                atPhoto = fileName1 + "," + fileName2 + "," + fileName3;
            }
            String mileType = "";
            if (rb1.isChecked()) {
                mileType = "0";
            } else if (rb2.isChecked()) {
                mileType = "1";
            }
            String date = tvDate.getText().toString();
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            String second = String.valueOf(c.get(Calendar.SECOND));
            int week_month = c.get(Calendar.WEEK_OF_MONTH);
            int week_year = c.get(Calendar.WEEK_OF_YEAR);
            int week_day = c.get(Calendar.DAY_OF_WEEK);
            int week_day_month = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
            if (Integer.valueOf(second)<10){
                second = "0"+second;
            }
            upDataPresenter.getUpData("", date.split(" ")[0], date.split(" ")[1]+":"+second, busCode, tvCarNo.getText().toString()
                    , tvLineNo.getText().toString(), spinnerWeather.getSelectedItem().toString(), spinnerAddressType.getSelectedItem().toString()
                    , spinnerSGType.getSelectedItem().toString(), spinnerSGZR.getSelectedItem().toString(), spinnerSGXZ.getSelectedItem().toString()
                    , spinnerSGLB.getSelectedItem().toString(), tvDriverCode.getText().toString(), etSWRS.getText().toString()
                    , etSIWRS.getText().toString(), etSGAddress.getText().toString(), etSGReason.getText().toString()
                    , depNameId, depName, atPhoto, mileType,tvJBRName1.getText().toString(),etMoney.getText().toString()
                    , etReason.getText().toString());
        }
    }

    /**
     * 选择时间
     */
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tvDate.setText(now);
        customDatePicker = new CustomDatePickerDay(this, new CustomDatePickerDay.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                tvDate.setText(time);
            }
            // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        }, "2000-01-01 00:00", "2030-01-01 00:00");
        // 不显示时和分
        customDatePicker.showSpecificTime(true);
        // 不允许循环滚动
        customDatePicker.setIsLoop(false);
    }

    /**
     * 获取关联数据
     *
     * @param s
     */
    @Override
    public void setAboutData(AboutData s) {
        if (s.isSuccess()) {
            tvDriverCode1.setText(s.getData().getFullname().toString());
            tvDriverCode.setText(s.getData().getJsy().toString());
            tvLineNo.setText(s.getData().getXlbh().toString());
        }
    }

    @Override
    public void setAboutData(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 数据提交
     *
     * @param s
     */
    @Override
    public void setUpData(UpData s) {
        if (s.isSuccess()) {
            Toast.makeText(this, "数据提交成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void setUpData(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tvDate, R.id.tvCarNo, R.id.ll2, R.id.imAdd01, R.id.imAdd02, R.id.imAdd03
            , R.id.tvLineNo, R.id.tvDriverCode1,R.id.tvJBRName1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvLineNo:
                intent = new Intent(this, LineCodeActivity.class);
                startActivityForResult(intent, Constant.TAG_TWO);
                break;
            case R.id.tvDriverCode1:
                intent = new Intent(this, WorkOnePersonActivity.class);
                startActivityForResult(intent, Constant.TAG_THERE);
                break;
            case R.id.tvJBRName1:
                intent = new Intent(this, WorkOnePersonActivity.class);
                startActivityForResult(intent, Constant.TAG_FIVE);
                break;
            case R.id.tvDate:
                customDatePicker.show(tvDate.getText().toString());
                break;
            case R.id.tvCarNo:
                intent = new Intent(this, CarCodeActivity.class);
                intent.putExtra("tag", "carNo");
                startActivityForResult(intent, TAG_ONE);
                break;
            case R.id.ll2:
                if (ll3.getVisibility() == View.VISIBLE) {
                    ll3.setVisibility(View.GONE);
                } else if (ll3.getVisibility() == View.GONE) {
                    ll3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imAdd01:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Matisse.from(FaultUpActivity.this)
                            .choose(MimeType.ofAll(), false)//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(3)//可选的最大数
                            .capture(false)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.hy.powerplatform.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .thumbnailScale(0.7f)  //图片缩放比例
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(TAG_FOUR);//
                }
                break;
            case R.id.imAdd02:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Matisse.from(FaultUpActivity.this)
                            .choose(MimeType.ofAll(), false)//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(3)//可选的最大数
                            .capture(false)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.hy.powerplatform.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .thumbnailScale(0.7f)  //图片缩放比例
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(TAG_FOUR);//
                }
                break;
            case R.id.imAdd03:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Matisse.from(FaultUpActivity.this)
                            .choose(MimeType.ofAll(), false)//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(3)//可选的最大数
                            .capture(false)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.hy.powerplatform.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .thumbnailScale(0.7f)  //图片缩放比例
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(TAG_FOUR);//
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_MY_UP_IMAGE:
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Matisse.from(FaultUpActivity.this)
                            .choose(MimeType.ofAll(), false)//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(8)//可选的最大数
                            .capture(true)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.hy.powerplatform.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .thumbnailScale(0.7f)  //图片缩放比例
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(TAG_FOUR);//
                } else {
                    Toast.makeText(this, "权限被拒绝，请手动开启", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    public void openCamera(Activity activity) {
        //获取系統版本
        int currentapiVersion = Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, TAG_FOUR);
    }

    /**
     * 判断sdcard是否被挂载
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAG_ONE:
                if (resultCode == Constant.TAG_TWO) {
                    CarCodeData carCodeData = (CarCodeData) data.getSerializableExtra("carCode");
                    tvCarNo.setText(carCodeData.getCarNo());
                    busCode = carCodeData.getBusCode();
                    depName = carCodeData.getDepName();
                    depNameId = carCodeData.getDepId();
                    Log.e("XXX", busCode + "");
                    String date = tvDate.getText().toString();
                    String rq = date.split(" ")[0];
                    String sj = date.split(" ")[1];
                    abboutDataPresenter.getAboutData(busCode, rq, sj);
                }
                break;
            case Constant.TAG_TWO:
                if (resultCode == TAG_ONE) {
                    LineCodeData lineCodeData = (LineCodeData) data.getSerializableExtra("lineCode");
                    tvLineNo.setText(lineCodeData.getLineCode());
                }
                break;
            case Constant.TAG_THERE:
                if (resultCode == TAG_ONE) {
                    if (data != null) {
                        tvDriverCode.setText(data.getStringExtra("ecard"));
                        tvDriverCode1.setText(data.getStringArrayListExtra("beanId").get(0));
                    }
                }
                break;
            case Constant.TAG_FIVE:
                if (resultCode == TAG_ONE) {
                    if (data != null) {
                        tvJBRName.setText(data.getStringExtra("ecard"));
                        tvJBRName1.setText(data.getStringArrayListExtra("beanId").get(0));
                    }
                }
                break;
            case TAG_FOUR:
                List<Uri> result = new ArrayList<>();
                /** 调用原生文件管理器，根据返回选择的文件，来进行操作 **/
                if (resultCode == Activity.RESULT_OK) {
                    result = Matisse.obtainResult(data);
                }
                for (int i = 0; i < result.size(); i++) {
//                    if (result.size() != 1) {
                    String path = UriTrytoPath.getImageAbsolutePath(this, result.get(i));
                    mResults.add(path);
//                    } else {
//                        BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上
//                        opts.inSampleSize = 4;
//                        Bitmap cbitmap01 = null;
//                        try {
//                            cbitmap01 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(0));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Matrix matrix = new Matrix();
//                        matrix.setScale(0.1f, 0.1f);
//                        Bitmap bm = Bitmap.createBitmap(cbitmap01, 0, 0, cbitmap01.getWidth(),
//                                cbitmap01.getHeight(), matrix, true);
//                        imAdd01.setImageBitmap(cbitmap01);
//                        wssaveImageToSD(bm, "temp");
//                        tag = true;
//                    }
                }
                if (resultCode == RESULT_OK) {
                    Bitmap cbitmap01 = null;
                    Bitmap cbitmap02 = null;
                    Bitmap cbitmap03 = null;
                    if (result.size() == 1) {
                        try {
                            cbitmap01 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(0));
                            imAdd01.setImageBitmap(cbitmap01);
                            //图片存储到本地
                            saveImageToSD(mResults, "temp");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.size() == 2) {
                        try {
                            cbitmap01 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(0));
                            cbitmap02 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(1));
                            imAdd01.setVisibility(View.VISIBLE);
                            imAdd02.setVisibility(View.VISIBLE);
                            imAdd03.setVisibility(View.VISIBLE);
                            imAdd01.setImageBitmap(cbitmap01);
                            imAdd02.setImageBitmap(cbitmap02);
                            imAdd03.setVisibility(View.VISIBLE);
                            //图片存储到本地
                            saveImageToSD(mResults, "temp");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (mResults.size() == 3) {
                        try {
                            cbitmap01 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(0));
                            cbitmap02 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(1));
                            cbitmap03 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.get(2));
                            imAdd01.setVisibility(View.VISIBLE);
                            imAdd02.setVisibility(View.VISIBLE);
                            imAdd03.setVisibility(View.VISIBLE);
                            imAdd01.setImageBitmap(cbitmap01);
                            imAdd02.setImageBitmap(cbitmap02);
                            imAdd03.setImageBitmap(cbitmap03);
                            //图片存储到本地
                            saveImageToSD(mResults, "temp");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
        }
    }

    /**
     * 图片保存到本地
     *
     * @param dirPath
     */
    private void saveImageToSD(ArrayList<String> mResults, String dirPath) {
        //新建文件夹用于存放裁剪后的图片
        tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + dirPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        for (int i = 0; i < mResults.size(); i++) {
            Bitmap cbitmap = BitmapFactory.decodeFile(mResults.get(i));
            Matrix matrix = new Matrix();
            matrix.setScale(0.1f, 0.1f);
            Bitmap bm = Bitmap.createBitmap(cbitmap, 0, 0, cbitmap.getWidth(),
                    cbitmap.getHeight(), matrix, true);
            //新建文件存储裁剪后的图片
            File img = new File(tmpDir.getAbsolutePath() + "/" + "shigu" + String.valueOf(i) + ".png");
            try {
                //打开文件输出流
                FileOutputStream fos = new FileOutputStream(img);
                //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
                bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                //刷新输出流
                fos.flush();
                //关闭输出流
                fos.close();
                //返回File类型的Uri
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoPath.add(String.valueOf(img));
        }
        upImage();
    }

    private void wssaveImageToSD(Bitmap cbitmap01, String dirPath) {
        //新建文件夹用于存放裁剪后的图片
        tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + dirPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }

        Random random = new Random();
        //新建文件存储裁剪后的图片
        File img = new File(tmpDir.getAbsolutePath() + "/" + "shigu0" + ".png");
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(img);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            cbitmap01.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
            //返回File类型的Uri
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //getImageFromSD(dirPath);
        Map<String, String> map = new HashMap<>();
        final RequestParams params = new RequestParams();
        ProgressDialogUtil.startLoad(FaultUpActivity.this, "图片上传中");
        File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu0" + ".png");
        String filepath = "shigu0" + ".png";
        try {
            params.put("upload", file);
            params.put("fullname", filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 执行post请求
        // 异步的客户端对象
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30000);
        String url = ApiAddress.mainApi + ApiAddress.faultupimage;
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int arg0, String arg1) {
                super.onSuccess(arg0, arg1);
                try {
                    Log.i("XXX", "XXX");
                    JSONObject jsonObject = new JSONObject(arg1);
                    fileName1 = jsonObject.getString("msg");
                    Message message = new Message();
                    message.what = TAG_ONE;
                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                super.onFailure(statusCode, headers, responseBody, error);
                Log.i("XXX", "XXXXX");
                Message message = new Message();
                message.what = Constant.TAG_TWO;
                handler.sendMessage(message);
            }
        });
    }

    /**
     * 上传图片
     */
    private void upImage() {
        ProgressDialogUtil.startLoad(this, MainUtil.upData);
        if (mResults.size() == 1) {
            if (imAdd01.getDrawable() != null) {
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu0" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "shigu0" + ".png";
                try {
                    params.put("upload", file);
                    params.put("fullname", filepath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 执行post请求
                // 异步的客户端对象
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(30000);
                String url = ApiAddress.mainApi + ApiAddress.faultupimage;
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        super.onSuccess(arg0, arg1);
                        try {
                            Log.i("XXX", "XXX");
                            JSONObject jsonObject = new JSONObject(arg1);
                            fileName1 = jsonObject.getString("msg");
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        super.onFailure(statusCode, headers, responseBody, error);
                        Log.i("XXX", "XXXXX");
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }
                });
            }
        } else if (mResults.size() == 2) {
            if (imAdd01.getDrawable() != null) {
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu0" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "shigu0" + ".png";
                try {
                    params.put("upload", file);
                    params.put("fullname", filepath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 执行post请求
                // 异步的客户端对象
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(30000);
                String url = ApiAddress.mainApi + ApiAddress.faultupimage;
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        super.onSuccess(arg0, arg1);
                        try {
                            Log.i("XXX", "XXX");
                            JSONObject jsonObject = new JSONObject(arg1);
                            fileName1 = jsonObject.getString("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        super.onFailure(statusCode, headers, responseBody, error);
                        Log.i("XXX", "XXXXX");
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }
                });
            }
            if (imAdd02.getDrawable() != null) {
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu1" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "shigu1" + ".png";
                try {
                    params.put("upload", file);
                    params.put("fullname", filepath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 执行post请求
                // 异步的客户端对象
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(30000);
                String url = ApiAddress.mainApi + ApiAddress.faultupimage;
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        super.onSuccess(arg0, arg1);
                        try {
                            Log.i("XXX", "XXX");
                            JSONObject jsonObject = new JSONObject(arg1);
                            fileName2 = jsonObject.getString("msg");
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        super.onFailure(statusCode, headers, responseBody, error);
                        Log.i("XXX", "XXXXX");
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }
                });
            }
        } else if (mResults.size() == 3) {
            if (imAdd01.getDrawable() != null) {
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu0" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "shigu0" + ".png";
                try {
                    params.put("upload", file);
                    params.put("fullname", filepath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 执行post请求
                // 异步的客户端对象
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(30000);
                String url = ApiAddress.mainApi + ApiAddress.faultupimage;
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        super.onSuccess(arg0, arg1);
                        try {
                            Log.i("XXX", "XXX");
                            JSONObject jsonObject = new JSONObject(arg1);
                            fileName1 = jsonObject.getString("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        super.onFailure(statusCode, headers, responseBody, error);
                        Log.i("XXX", "XXXXX");
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }
                });
            }
            if (imAdd02.getDrawable() != null) {
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu1" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "shigu1" + ".png";
                try {
                    params.put("upload", file);
                    params.put("fullname", filepath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 执行post请求
                // 异步的客户端对象
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(30000);
                String url = ApiAddress.mainApi + ApiAddress.faultupimage;
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        super.onSuccess(arg0, arg1);
                        try {
                            Log.i("XXX", "XXX");
                            JSONObject jsonObject = new JSONObject(arg1);
                            fileName2 = jsonObject.getString("msg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        super.onFailure(statusCode, headers, responseBody, error);
                        Log.i("XXX", "XXXXX");
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }
                });
            }
            if (imAdd03.getDrawable() != null) {
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "shigu2" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "shigu2" + ".png";
                try {
                    params.put("upload", file);
                    params.put("fullname", filepath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 执行post请求
                // 异步的客户端对象
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(30000);
                String url = ApiAddress.mainApi + ApiAddress.faultupimage;
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        super.onSuccess(arg0, arg1);
                        try {
                            Log.i("XXX", "XXX");
                            JSONObject jsonObject = new JSONObject(arg1);
                            fileName3 = jsonObject.getString("msg");
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        super.onFailure(statusCode, headers, responseBody, error);
                        Log.i("XXX", "XXXXX");
                        Message message = new Message();
                        message.what = Constant.TAG_TWO;
                        handler.sendMessage(message);
                    }
                });
            }
        }
//        final AsyncHttpClient client = new AsyncHttpClient();
//        client.setTimeout(60000);
//        final String url = ApiAddress.mainApi + ApiAddress.faultupimage;
//        final RequestParams params = new RequestParams();
//        try {
//            params.put("upload", tempFile);
//            params.put("fullname", tempFile.getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ProgressDialogUtil.startLoad(this, MainUtil.upData);
//        client.post(url, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int arg0, String arg1) {
//                super.onSuccess(arg0, arg1);
//                Log.i("XXX", arg1);
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(arg1.toString());
//                    if (imageNum == 1) {
//                        fileName1 = jsonObject.getString("msg");
//                    } else if (imageNum == 2) {
//                        fileName2 = jsonObject.getString("msg");
//                    } else if (imageNum == 3) {
//                        fileName3 = jsonObject.getString("msg");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Message message = new Message();
//                message.what = TAG_ONE;
//                handler.sendMessage(message);
//            }
//
//            @Override
//            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
//                super.onFailure(statusCode, headers, responseBody, error);
//                Log.i("XXX", "XXXXX");
//                Message message = new Message();
//                message.what = Constant.TAG_TWO;
//                handler.sendMessage(message);
//            }
//        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TAG_ONE:
                    Toast.makeText(FaultUpActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(FaultUpActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };

    @OnClick(R.id.tvJBRName1)
    public void onViewClicked() {
    }
}