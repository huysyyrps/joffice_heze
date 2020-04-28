package com.smartbus.heze.checkup.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.smartbus.heze.ApiAddress;
import com.smartbus.heze.R;
import com.smartbus.heze.SharedPreferencesHelper;
import com.smartbus.heze.checkup.activitydata.CarCodeActivity;
import com.smartbus.heze.checkup.activitydata.UserCodeActivity;
import com.smartbus.heze.checkup.bean.CarCodeData;
import com.smartbus.heze.checkup.bean.UserCodeData;
import com.smartbus.heze.checkup.module.RCJCNewContract;
import com.smartbus.heze.checkup.presenter.RCJCNewPresenter;
import com.smartbus.heze.fault.bean.AboutData;
import com.smartbus.heze.fault.module.AboutDataContract;
import com.smartbus.heze.fault.presenter.AboutDataPresenter;
import com.smartbus.heze.fileapprove.bean.BackData;
import com.smartbus.heze.http.base.BaseActivity;
import com.smartbus.heze.http.base.Constant;
import com.smartbus.heze.http.base.ProgressDialogUtil;
import com.smartbus.heze.http.base.showBottomDialog;
import com.smartbus.heze.http.utils.MainUtil;
import com.smartbus.heze.http.utils.time_select.CustomDatePickerMin;
import com.smartbus.heze.http.views.Header;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import static com.smartbus.heze.http.base.Constant.TAG_ONE;
import static com.smartbus.heze.http.base.Constant.TAG_TWO;

public class RCJCNewActivity extends BaseActivity implements AboutDataContract.View, RCJCNewContract.View {

    @BindView(R.id.header)
    Header header;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.etDepartment)
    EditText etDepartment;
    @BindView(R.id.tvDrive)
    TextView tvDrive;
    @BindView(R.id.etLine)
    EditText etLine;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.spContent)
    Spinner spContent;
    @BindView(R.id.spFound)
    Spinner spFound;
    @BindView(R.id.spFH)
    Spinner spFH;
    @BindView(R.id.tvCheckPerson)
    TextView tvCheckPerson;
    @BindView(R.id.etQuestion)
    EditText etQuestion;
    @BindView(R.id.etResult)
    EditText etResult;
    @BindView(R.id.etRemarks)
    EditText etRemarks;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.imAdd)
    ImageView imAdd;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnUp)
    Button btnUp;
    @BindView(R.id.spZG)
    Spinner spZG;

    File tmpDir;
    Intent intent;
    String name = "system/workTransferSheetPhotos/";
    String driverName = "";
    String drivaeCode = "";
    String busCode = "";
    String depName = "";
    String depNameId = "";
    String dirPath = "temp";
    String fileName1 = "";
    String fileName2 = "";
    String fileName3 = "";
    String atPhoto = "";
    private UriAdapter mAdapter;
    List<Uri> beanList = new ArrayList<>();
    List<Uri> beanList1 = new ArrayList<>();
    List<String> BaseList = new ArrayList<>();
    //图片list
    private ArrayList<String> mResults = new ArrayList<>();
    //图片地址
    private ArrayList<String> photoPath = new ArrayList<>();
    AboutDataPresenter abboutDataPresenter;
    Map<String, String> map = new HashMap<>();
    private showBottomDialog show = new showBottomDialog();
    private static final int MY_PERMISSIONS_MY_UP_IMAGE = 1;
    List<String> listContent = new ArrayList<String>();
    List<String> listFound = new ArrayList<String>();
    List<String> listFH = new ArrayList<String>();
    List<String> listZG = new ArrayList<String>();
    ArrayAdapter<String> contentAdapter;
    ArrayAdapter<String> foundAdapter;
    ArrayAdapter<String> fhAdapter;
    ArrayAdapter<String> zgAdapter;
    CustomDatePickerMin customDatePickerMin;
    RCJCNewPresenter rcjcNewPresenter;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "login");
        String checkName = sharedPreferencesHelper.getData(this, "userName1", "");
        tvCheckPerson.setText(checkName);
        listContent.add("无问题");
        listContent.add("有问题");
        contentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listContent);
        contentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spContent.setAdapter(contentAdapter);

        listFound.add("站点");
        listFound.add("跟车");
        foundAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listFound);
        foundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFound.setAdapter(foundAdapter);

        listFH.add("复合");
        listFH.add("未复合");
        fhAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listFH);
        fhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFH.setAdapter(fhAdapter);

        listZG.add("是");
        listZG.add("否");
        zgAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listZG);
        zgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spZG.setAdapter(zgAdapter);
        initDatePicker();
        rcjcNewPresenter = new RCJCNewPresenter(this, this);
        abboutDataPresenter = new AboutDataPresenter(this, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter = new UriAdapter());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_new_rcjc;
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
        tvTime.setText(now);
        customDatePickerMin = new CustomDatePickerMin(this, new CustomDatePickerMin.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                tvTime.setText(time);
            }
            // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        }, "2000-01-01 00:00", "2030-01-01 00:00");
        // 不允许循环滚动
        customDatePickerMin.setIsLoop(false);
    }

    @OnClick({R.id.tvCarNo, R.id.tvTime, R.id.imAdd, R.id.btnUp, R.id.tvDrive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnUp:
                if (tvCarNo.getText().toString().equals("") || etDepartment.getText().toString().equals("")
                        || tvDrive.getText().toString().equals("") || etLine.getText().toString().equals("")
                        || etQuestion.getText().toString().equals("")) {
                    Toast.makeText(this, "*号为必填项", Toast.LENGTH_SHORT).show();
                } else {
                    saveImageToSD(mResults, "temp");
                }
                break;
            case R.id.tvCarNo:
                intent = new Intent(this, CarCodeActivity.class);
                intent.putExtra("tag", "carNo");
                startActivityForResult(intent, TAG_ONE);
                break;
            case R.id.tvDrive:
                intent = new Intent(this, UserCodeActivity.class);
                intent.putExtra("tag", "userName");
                startActivityForResult(intent, Constant.TAG_THERE);
                break;
            case R.id.tvTime:
                customDatePickerMin.show(tvTime.getText().toString());
                break;
            case R.id.imAdd:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                                    , Manifest.permission.CAMERA
                                    , Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Matisse.from(RCJCNewActivity.this)
                            .choose(MimeType.ofAll(), false)//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(3)//可选的最大数
                            .capture(true)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.smartbus.heze.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .thumbnailScale(0.5f)  //图片缩放比例
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(TAG_TWO);//
                }
//                show.BottomDialog(this, new AlertDialogCallBack() {
//                    @Override
//                    public int getData(int s) {
//                        return 0;
//                    }
//
//                    @Override
//                    public void confirm() {
//                        if (ContextCompat.checkSelfPermission(RCJCNewActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//                                || ContextCompat.checkSelfPermission(RCJCNewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//                            ActivityCompat.requestPermissions(RCJCNewActivity.this,
//                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
//                                    MY_PERMISSIONS_MY_UP_IMAGE);
//                        } else {
//                            Matisse.from(RCJCNewActivity.this)
//                                    .choose(MimeType.allOf())//图片类型
//                                    .countable(true)//true:选中后显示数字;false:选中后显示对号
//                                    .maxSelectable(3)//可选的最大数
//                                    .capture(false)//选择照片时，是否显示拍照
//                                    .captureStrategy(new CaptureStrategy(true, "com.hy.powerplatform.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
//                                    .thumbnailScale(0.7f)  //图片缩放比例
//                                    .imageEngine(new GlideEngine())//图片加载引擎
//                                    .forResult(TAG_TWO);//
//                        }
//                    }
//
//                    @Override
//                    public void cancel() {
//
//                    }
//                });
                break;
        }
    }

    /**
     * 添加日常稽查数据
     */
    private void setData() {
        map.put("driverId", drivaeCode);
        map.put("driverName", driverName);
        map.put("carNo", tvCarNo.getText().toString());
        map.put("busCode", busCode);
        map.put("depId", depNameId);
        map.put("depName", depName);
        map.put("lineCode", etLine.getText().toString());
        map.put("kaoheDate", tvTime.getText().toString().split(" ")[0]);
        map.put("examiner", tvCheckPerson.getText().toString());
        map.put("note", etRemarks.getText().toString());
        map.put("question", etQuestion.getText().toString());
        if (spContent.getSelectedItem().toString().equals("有问题")) {
            map.put("contentzt", "1");
        } else {
            map.put("contentzt", "0");
        }
        map.put("way", spFound.getSelectedItem().toString());
        map.put("result", etResult.getText().toString());
        if (spFH.getSelectedItem().toString().equals("复核")) {
            map.put("checkzt", "1");
        } else {
            map.put("checkzt", "0");
        }
        map.put("kaoheTime", tvTime.getText().toString().split(" ")[1]);
        if (spZG.getSelectedItem().toString().equals("是")) {
            map.put("rectification", "1");
        } else {
            map.put("rectification", "0");
        }//是否生成表单
//        map.put("rectification", spFound.getSelectedItem().toString());//是否生成表单
//        String base = BaseList.toString();
//        base = base.substring(1);
//        base = base.substring(0, base.length() - 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String nowDate = simpleDateFormat.format(date);
        name = name+nowDate+"/";
        if (!fileName1.equals("") && fileName2.equals("") && fileName3.equals("")) {
            atPhoto = name+fileName1;
        }
        if (!fileName2.equals("") && fileName3.equals("")) {
            atPhoto = name+fileName1 + "," + name+fileName2;
        }
        if (!fileName3.equals("")) {
            atPhoto = name+fileName1 + "," + name+fileName2 + "," + name+fileName3;
        }
        map.put("photo", atPhoto);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_MY_UP_IMAGE:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                                    , Manifest.permission.CAMERA
                                    , Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_MY_UP_IMAGE);
                } else {
                    Matisse.from(RCJCNewActivity.this)
                            .choose(MimeType.ofAll(), false)//图片类型
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(3)//可选的最大数
                            .capture(true)//选择照片时，是否显示拍照
                            .captureStrategy(new CaptureStrategy(true, "com.smartbus.heze.fileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .thumbnailScale(0.5f)  //图片缩放比例
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(TAG_TWO);//
                }
                return;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAG_ONE:
                if (resultCode == Constant.TAG_TWO) {
                    tvCarNo.setText("");
                    tvDrive.setText("");
                    etLine.setText("");
                    CarCodeData carCodeData = (CarCodeData) data.getSerializableExtra("carCode");
                    tvCarNo.setText(carCodeData.getCarNo());
                    etDepartment.setText(carCodeData.getDepName());
                    busCode = carCodeData.getBusCode();
                    depName = carCodeData.getDepName();
                    depNameId = carCodeData.getDepId();
                    String date = tvTime.getText().toString();
                    String rq = date.split(" ")[0];
                    String sj = date.split(" ")[1];
                    abboutDataPresenter.getAboutData(busCode, rq, sj);
                }
                break;
            case TAG_TWO:
                if (resultCode == RESULT_OK) {
                    beanList.addAll(Matisse.obtainResult(data));
                    if (beanList.size() > 3) {
                        beanList.clear();
                        Toast.makeText(this, "最多只能选3张图片", Toast.LENGTH_SHORT).show();
                    } else if (beanList.size() == 3) {
                        imAdd.setVisibility(View.GONE);
                        beanList1.addAll(Matisse.obtainResult(data));
                        mAdapter.setData(beanList1);
                        mResults.clear();
                        for (int i = 0; i < beanList.size(); i++) {
//                            String path = UriTrytoPath.getImageAbsolutePath(this, beanList.get(i));
                            String path = getRealFilePath(this, beanList.get(i));
                            mResults.add(path);
                        }
//                        for (int i = 0; i < beanList1.size(); i++) {
//                            Bitmap photoBmp = null;
//                            try {
//                                photoBmp = getBitmapFormUri(RCJCNewActivity.this, beanList1.get(i));
//                                String s = String.valueOf(photoBmp.getRowBytes() * photoBmp.getHeight());
//                                Log.e("XXX",s);
//                                String string = bitmapToBase64(photoBmp);
//                                BaseList.add(string);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
                    } else {
                        beanList1.addAll(Matisse.obtainResult(data));
//                        if (beanList1.size() != 0) {
//                            for (int i = 0; i < beanList1.size(); i++) {
//                                Bitmap photoBmp = null;
//                                try {
//                                    photoBmp = getBitmapFormUri(RCJCNewActivity.this, beanList1.get(i));
//                                    String string = bitmapToBase64(photoBmp);
//                                    BaseList.add(string);
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                        try {
//                            Bitmap photoBmp = getBitmapFormUri(RCJCNewActivity.this, beanList1.get(0));
//                            String string = bitmapToBase64(photoBmp);
//                            Log.e("XXXX", string);
//                            imAdd1.setImageBitmap(base64ToBitmap(string));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        mAdapter.setData(beanList1);
                        mResults.clear();
                        for (int i = 0; i < beanList.size(); i++) {
                            String path = getRealFilePath(this, beanList.get(i));
                            mResults.add(path);
                        }
                    }
                }
                break;
            case Constant.TAG_THERE:
                if (resultCode == Constant.TAG_THERE) {
                    UserCodeData userCodeData = (UserCodeData) data.getSerializableExtra("userCode");
                    driverName = userCodeData.getECard();
                    drivaeCode = userCodeData.getFullname();
                }
                break;
        }
    }

    /**
     * 根据车牌号获取关联数据
     *
     * @param s
     */
    @Override
    public void setAboutData(AboutData s) {
        if (s.isSuccess()) {
            if (s.getData().getFullname() == null || s.getData().getFullname().toString().equals("")) {
                Toast.makeText(this, "没有关联数据，请手动录入驾驶员和线路", Toast.LENGTH_SHORT).show();
            } else {
                driverName = s.getData().getFullname().toString();
                drivaeCode = s.getData().getJsy().toString();
                tvDrive.setText(s.getData().getFullname().toString());
                etLine.setText(s.getData().getXlbh().toString());
            }
        }
    }

    @Override
    public void setAboutData(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUPYSD(BackData s) {
        if (s.isSuccess()) {
            Toast.makeText(this, "录入成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void setUPYSDMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private class UriAdapter extends RecyclerView.Adapter<UriAdapter.UriViewHolder> {

        private List<Uri> mUris;

        void setData(List<Uri> uris) {
            mUris = uris;
            notifyDataSetChanged();
        }

        @Override
        public UriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new UriViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.uri_item, parent, false));
        }

        @Override
        public void onBindViewHolder(UriViewHolder holder, int position) {
            Glide.with(RCJCNewActivity.this).load(mUris.get(position)).into(holder.mImg);
        }

        @Override
        public int getItemCount() {
            return mUris == null ? 0 : mUris.size();
        }

        class UriViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImg;

            UriViewHolder(View contentView) {
                super(contentView);
                mImg = (ImageView) contentView.findViewById(R.id.img);
            }
        }
    }

    /**
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 70;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
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
            File img = new File(tmpDir.getAbsolutePath() + "/" + "jicha" + String.valueOf(i) + ".png");
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

    /**
     * 上传图片
     */
    private void upImage() {
        if (mResults.size() == 1) {
            ProgressDialogUtil.startLoad(this, MainUtil.upData);
            RequestParams params = new RequestParams();
            File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "jicha" + ".png");
            String sdpath = Environment.getExternalStorageDirectory()
                    + "/" + dirPath;// 获取sdcard的根路径
            String filepath = "jicha0" + ".png";
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
            String url = ApiAddress.mainApi + ApiAddress.rcjcphotoup;
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
        } else if (mResults.size() == 2) {
            if (mResults.size() == 2) {
                ProgressDialogUtil.startLoad(this, MainUtil.upData);
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "jicha" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "jicha0" + ".png";
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
                String url = ApiAddress.mainApi + ApiAddress.rcjcphotoup;
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
            if (mResults.size() == 2) {
                ProgressDialogUtil.startLoad(this, MainUtil.upData);
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "jicha1" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "jicha1" + ".png";
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
                String url = ApiAddress.mainApi + ApiAddress.rcjcphotoup;
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
            if (mResults.size() == 3) {
                ProgressDialogUtil.startLoad(this, MainUtil.upData);
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "jicha" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "jicha0" + ".png";
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
                String url = ApiAddress.mainApi + ApiAddress.rcjcphotoup;
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
            if (mResults.size() == 3) {
                ProgressDialogUtil.startLoad(this, MainUtil.upData);
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "jicha1" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "jicha1" + ".png";
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
                String url = ApiAddress.mainApi + ApiAddress.rcjcphotoup;
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
            if (mResults.size() == 3) {
                ProgressDialogUtil.startLoad(this, MainUtil.upData);
                RequestParams params = new RequestParams();
                File file = new File(Environment.getExternalStorageDirectory() + "/" + dirPath + "/" + "jicha2" + ".png");
                String sdpath = Environment.getExternalStorageDirectory()
                        + "/" + dirPath;// 获取sdcard的根路径
                String filepath = "jicha2" + ".png";
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
                String url = ApiAddress.mainApi + ApiAddress.rcjcphotoup;
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
    }

    /**
     *  根据Uri获取文件真实地址
     */
    public static String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String realPath = null;
        if (scheme == null)
            realPath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            realPath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        realPath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (TextUtils.isEmpty(realPath)) {
            if (uri != null) {
                String uriString = uri.toString();
                int index = uriString.lastIndexOf("/");
                String imageName = uriString.substring(index);
                File storageDir;

                storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File file = new File(storageDir, imageName);
                if (file.exists()) {
                    realPath = file.getAbsolutePath();
                } else {
                    storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file1 = new File(storageDir, imageName);
                    realPath = file1.getAbsolutePath();
                }
            }
        }
        return realPath;
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TAG_ONE:
                    Toast.makeText(RCJCNewActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    setData();
                    rcjcNewPresenter.getRCJCNew(map);
                    break;
                case Constant.TAG_TWO:
                    Toast.makeText(RCJCNewActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.stopLoad();
                    break;
            }
        }
    };
}
