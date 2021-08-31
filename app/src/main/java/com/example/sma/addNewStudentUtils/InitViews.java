package com.example.sma.addNewStudentUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.fragment.app.FragmentActivity;

import com.example.sma.R;
import com.example.sma.Util.Utils;
import com.example.sma.databinding.FragmentBlank1Binding;

public class InitViews {
    FragmentBlank1Binding binding;
    FragmentActivity requireActivity;

    public InitViews(FragmentBlank1Binding binding, FragmentActivity requireActivity) {
        this.binding = binding;
        this.requireActivity = requireActivity;
        Utils.setupDropDownMenu(binding, requireActivity);

        initListener();
    }

    private void initListener() {
        binding.inputLayDOB.setEndIconOnClickListener(v -> {
            showSelectAgeDialog();
        });    }


    private void showSelectAgeDialog() {
        LayoutInflater inflater = (LayoutInflater) requireActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_picker, null, false);
        final DatePicker myDatePicker = view.findViewById(R.id.myDatePicker);

        myDatePicker.setCalendarViewShown(false);
        myDatePicker.setMaxDate(System.currentTimeMillis());
        new AlertDialog.Builder(requireActivity).setView(view)
                .setTitle(R.string.set_date)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    int month = myDatePicker.getMonth() + 1;
                    int day = myDatePicker.getDayOfMonth();
                    int year = myDatePicker.getYear();
                    binding.editTextTextPersonDob.setText(day + "/" + month + "/" + year);
                    dialog.cancel();
                }).show();
    }
}

