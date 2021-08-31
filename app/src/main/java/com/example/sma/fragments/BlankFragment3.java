package com.example.sma.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sma.R;
import com.example.sma.Util.AppConstant;
import com.sonu.libraries.materialstepper.StepFragment;


public class BlankFragment3 extends StepFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false);
    }

    @Override
    public String getStepTitle() {
        return AppConstant.OTHER_INFORMATION;
    }

    @Override
    public boolean canSkip() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if(canGoBack()) {
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

}