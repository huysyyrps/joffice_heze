package com.smartbus.heze.http.base;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.smartbus.heze.R;


public class AlertDialogUtil {
    private Context context;
    AlertDialog dialog;

    public AlertDialogUtil(Context context) {
        this.context = context;
    }

    public void showDialog(String description, final AlertDialogCallBack alertDialogCallBack) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_with_title, null, false);
            TextView tv_content = (TextView) view.findViewById(R.id.content);
            TextView tv_yes = (TextView) view.findViewById(R.id.yes);
            TextView tv_no = (TextView) view.findViewById(R.id.no);
            tv_content.setText(description);
            tv_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    alertDialogCallBack.cancel();
                }
            });

            tv_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    alertDialogCallBack.confirm();
                }
            });
            dialog.getWindow().setContentView(view);
        }
    }
    public void showSmallDialog(String description) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_with_one_title, null, false);
            TextView tv_content = (TextView) view.findViewById(R.id.content);
            TextView tv_yes = (TextView) view.findViewById(R.id.yes);
            tv_content.setText(description);
            tv_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.getWindow().setContentView(view);
        }
    }

//    public void showDialogEditText(final Context context, final String runId, final BaseRequestBackLisenter backLisenter){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("追回原因");
//        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_item, null);
//        //    设置我们自己定义的布局文件作为弹出框的Content
//        builder.setView(view);
//        final EditText username = (EditText) view.findViewById(R.id.editText);
//        username.setHint("最多输入50字");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                final String a = username.getText().toString().trim();
//                if (a.equals("")){
//                    Toast.makeText(context, "请填写追回原因", Toast.LENGTH_SHORT).show();
//                }else {
//                    ProgressDialogUtil.startLoad(context, "提交数据中");
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String url = Constant.BASE_URL2 + Constant.BACKFLOW + runId+"&reason="+a;
//                            DBHandler dbA = new DBHandler();
//                            String res = dbA.FlowBack(url);
//                            String msg = null;
//                            if (res.equals("获取数据失败") || res.equals("")) {
//                                backLisenter.fail("提交数据失败");
//                            } else {
//                                try {
//                                    JSONObject jsonObject = new JSONObject(res);
//                                    msg = jsonObject.getString("msg");
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                backLisenter.success(msg);
//                            }
//                        }
//                    }).start();
//                }
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.show();
//    }

//    public void showListDialog(List<String> dialogList,final AlertDialogCallBack alertDialogCallBack) {
//        if (dialog == null || !dialog.isShowing()) {
//            dialog = new AlertDialog.Builder(context).create();
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.show();
//            LayoutInflater inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.list_dialog, null, false);
//            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//            TextView tvNo = (TextView) view.findViewById(R.id.tvNo);
//            LinearLayoutManager manager = new LinearLayoutManager(context);
//            recyclerView.setLayoutManager(manager);
//            ListDialogAdapter adapter = new ListDialogAdapter(context,dialogList);
//            recyclerView.setAdapter(adapter);
//            adapter.sendOnGetAdapterPositionListener(new ListDialogAdapter.OnGetAdapterPositionListener() {
//                @Override
//                public void getAdapterPosition(int position) {
//                    alertDialogCallBack.getData(position);
//                }
//            });
//            tvNo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.getWindow().setContentView(view);
//        }
//    }
}
