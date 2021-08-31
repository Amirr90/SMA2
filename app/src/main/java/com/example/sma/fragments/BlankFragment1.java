package com.example.sma.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sma.R;
import com.example.sma.Util.AppConstant;
import com.example.sma.Util.Utils;
import com.example.sma.addNewStudentUtils.InitViews;
import com.example.sma.databinding.FragmentBlank1Binding;
import com.sonu.libraries.materialstepper.StepFragment;


public class BlankFragment1 extends StepFragment {


    FragmentBlank1Binding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBlank1Binding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new InitViews(binding, requireActivity());

    }

    @Override
    public String getStepTitle() {
        return AppConstant.STUDENT_INFORMATION;
    }

    @Override
    public boolean canSkip() {
        return false;
    }


    @Override
    public void onBackPressed() {
        if (canGoBack()) {
            onLeftClicked();
        }
    }

    @Override
    public StepFragment onRightCLicked() {
        customNextClick();
        return this;
    }

    private void customNextClick() {
        Toast.makeText(requireActivity(), "Next Clicked !!", Toast.LENGTH_SHORT).show();
        super.onRightCLicked();
    }


}