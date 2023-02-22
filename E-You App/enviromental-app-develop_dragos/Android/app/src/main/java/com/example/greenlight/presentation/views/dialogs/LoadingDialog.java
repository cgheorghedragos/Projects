package com.example.greenlight.presentation.views.dialogs;

import android.app.Dialog;
import android.content.Context;

import com.example.greenlight.R;

public class LoadingDialog {
    private Dialog dialog;
    private Context context;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.create();
        dialog.show();

    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


}
