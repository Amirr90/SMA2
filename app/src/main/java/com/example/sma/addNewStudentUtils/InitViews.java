package com.example.sma.addNewStudentUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.fragment.app.FragmentActivity;

import com.example.sma.R;
import com.example.sma.Util.AppConstant;
import com.example.sma.Util.Utils;
import com.example.sma.databinding.FragmentBlank1Binding;
import com.example.sma.fragments.BlankFragment1;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class InitViews {
    FragmentBlank1Binding binding;
    FragmentActivity requireActivity;
    BlankFragment1 blankFragment1;

    public InitViews(FragmentBlank1Binding binding, FragmentActivity requireActivity, BlankFragment1 blankFragment1) {
        this.binding = binding;
        this.requireActivity = requireActivity;
        this.blankFragment1 = blankFragment1;
        Utils.setupDropDownMenu(binding, requireActivity);

        initListener();
    }

    private void initListener() {
        binding.inputLayDOB.setEndIconOnClickListener(v -> showSelectAgeDialog());

        binding.profileImage.setOnClickListener(v -> selectImage(AppConstant.STUDENT_PROFILE_IMAGE_CODE));
        binding.textView29.setOnClickListener(v -> selectImage(AppConstant.STUDENT_PROFILE_IMAGE_CODE));
        binding.tvAddDocument.setOnClickListener(v -> selectImage(AppConstant.SELECT_STUDENT_DOCUMENT));
    }

    private void selectImage(int reqCode) {
        ImagePicker.Companion.with(blankFragment1)
                .crop(4f, 5f)
                .compress(512)
                .maxResultSize(1080, 1080)
                .start(reqCode);
    }


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

    public void setImageUri(Uri imageUri, int reqCode) {
        if (reqCode == AppConstant.STUDENT_PROFILE_IMAGE_CODE) {
            binding.profileImage.setImageURI(imageUri);
            binding.textView29.setText(R.string.changeImage);
        } else if (reqCode == AppConstant.SELECT_STUDENT_DOCUMENT) {

        }
    }
}

