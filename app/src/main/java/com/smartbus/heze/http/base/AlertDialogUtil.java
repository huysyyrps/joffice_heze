package com.smartbus.heze.http.base;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    public void showSmallDialog1(String description, String reason) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_with_one_title, null, false);
            TextView tv_content = (TextView) view.findViewById(R.id.content);
            TextView tvTitle = (TextView) view.findViewById(R.id.title);
            tvTitle.setText(reason);
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

    public void showDialogEditText(final Context context, final AlertDialogBack alertDialogBack){
        if (dialog == null || !dialog.isShowing()) {
            dialog = new AlertDialog.Builder(context).create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.getWindow().clearFlags
                    (WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode
                    (WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_item, null, false);
            dialog.getWindow().setContentView(view);
            final EditText editText = (EditText) view.findViewById(R.id.editText);
            TextView tv_yes = (TextView) view.findViewById(R.id.yes);
            TextView tv_no = (TextView) view.findViewById(R.id.no);
            tv_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    alertDialogBack.cancel();
                }
            });

            tv_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (editText.getText().toString().equals("")){
                        Toast.makeText(context, "请填写驳回意见", Toast.LENGTH_SHORT).show();
                    }else {
                        alertDialogBack.confirm(editText.getText().toString());
                    }
                }
            });
        }
    }

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
