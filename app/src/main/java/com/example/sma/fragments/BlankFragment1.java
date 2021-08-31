package com.example.sma.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sma.Util.AppConstant;
import com.example.sma.addNewStudentUtils.InitViews;
import com.example.sma.databinding.FragmentBlank1Binding;
import com.sonu.libraries.materialstepper.StepFragment;


public class BlankFragment1 extends StepFragment {

    private static final String TAG = "BlankFragment1";

    FragmentBlank1Binding binding;
    InitViews initViews;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlank1Binding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews = new InitViews(binding, requireActivity(), this);

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
        super.onRightCLicked();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            initViews.setImageUri(data.getData(), requestCode);
        }
    }
}