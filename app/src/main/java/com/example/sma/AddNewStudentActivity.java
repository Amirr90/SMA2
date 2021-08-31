package com.example.sma;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sma.databinding.ActivityAddNewStudentBinding;
import com.example.sma.fragments.BlankFragment1;
import com.example.sma.fragments.BlankFragment2;
import com.example.sma.fragments.BlankFragment3;
import com.sonu.libraries.materialstepper.OnLastStepNextListener;

public class AddNewStudentActivity extends AppCompatActivity {

    ActivityAddNewStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initHorizontalStepper();
    }

    private void initHorizontalStepper() {
        binding.materialStepper.setFragmentManager(getSupportFragmentManager());

        //adding steps
        binding.materialStepper.addStep(new BlankFragment1());
        binding.materialStepper.addStep(new BlankFragment2());
        binding.materialStepper.addStep(new BlankFragment3());

        binding.materialStepper.setOnLastStepNextListener(() -> {
            Toast.makeText(this, "Adding Student !!", Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public void onBackPressed() {
        if (binding.materialStepper.onBackPressed() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Not Saved !!")
                    .setMessage("All the information will lost ??")
                    .setPositiveButton("SAVE", (dialog, id) -> {
                        dialog.cancel();
                    })
                    .setNegativeButton("EXIT", (dialog, id) ->
                            super.onBackPressed())
                    .show();

        }

    }
}