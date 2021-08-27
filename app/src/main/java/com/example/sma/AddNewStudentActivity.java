package com.example.sma;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.sma.Util.Utils;
import com.example.sma.databinding.ActivityAddNewStudentBinding;

import java.util.Objects;

public class AddNewStudentActivity extends AppCompatActivity {

    ActivityAddNewStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);
        setToolbar(binding.toolbar);
    }


    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(Utils.ADD_STUDENT);
    }
}