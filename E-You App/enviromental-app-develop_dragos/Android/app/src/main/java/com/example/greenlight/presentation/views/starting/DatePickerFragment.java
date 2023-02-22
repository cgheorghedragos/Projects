package com.example.greenlight.presentation.views.starting;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.widget.DatePicker;

import com.example.greenlight.presentation.viewmodel.SignUpViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.support.AndroidSupportInjection;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private final int MIN_AGE = 18;

    @Inject
    public SignUpViewModel signUpViewModel ;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setupViews();

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR) - MIN_AGE ;
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it

        DatePickerDialog picker;
        int style = AlertDialog.THEME_HOLO_LIGHT;

        picker = new DatePickerDialog(requireContext(),style,this, year, month, day);

        // setting the maximum choosable time
        c.set(year,month,day);
        picker.getDatePicker().setMaxDate(c.getTimeInMillis());

        return picker;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder birthday = new StringBuilder();

        birthday.append(year);
        birthday.append("/");
        birthday.append(month);
        birthday.append("/");
        birthday.append(dayOfMonth);

        signUpViewModel.updateBirthday(birthday.toString());
    }

    private void setupViews(){
    }

}