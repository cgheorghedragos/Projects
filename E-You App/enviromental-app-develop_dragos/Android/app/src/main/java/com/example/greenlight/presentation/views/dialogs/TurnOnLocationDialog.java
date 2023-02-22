package com.example.greenlight.presentation.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.example.greenlight.R;

public class TurnOnLocationDialog {
    private Context context;

    public TurnOnLocationDialog(Context context){
        this.context = context;
    }

    public void showDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("Localization error");

        alertDialog.setMessage("Please enable the location to continue using the app..");

        // On pressing Settings button
        alertDialog.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }
                });
        alertDialog.show();
    }

}
