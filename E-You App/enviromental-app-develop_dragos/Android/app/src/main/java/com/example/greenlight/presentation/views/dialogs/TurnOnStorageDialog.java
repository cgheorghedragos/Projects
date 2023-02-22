package com.example.greenlight.presentation.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class TurnOnStorageDialog {
    private Context context;

    public TurnOnStorageDialog(Context context){
        this.context = context;
    }

    public void showDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("Storage error");

        alertDialog.setMessage("Please enable the storage to continue using the app..");

        // On pressing Settings button
        alertDialog.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                        context.startActivity(intent);
                    }
                });
        alertDialog.show();
    }

}
