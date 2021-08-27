package com.example.sma;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sma.Util.Utils;
import com.example.sma.appViewHolder.AppViewHolder;
import com.example.sma.databinding.ActivityAdmissionBinding;
import com.example.sma.databinding.RvParentViewBinding;
import com.example.sma.model.AdmissionDashboardModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdmissionActivity extends AppCompatActivity {

    ActivityAdmissionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admission);

        setToolbar(binding.toolbar);
    }


    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Admission");
    }

    @Override
    protected void onStart() {
        super.onStart();

        initRecView();
    }

    private void initRecView() {
        binding.recAdmission.setItemAnimator(new DefaultItemAnimator());
        binding.recAdmission.setAdapter(new AdmissionAdapter());
    }

    private class AdmissionAdapter extends RecyclerView.Adapter<AppViewHolder> {
        @NonNull
        @Override
        public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            RvParentViewBinding rvParentViewBinding = RvParentViewBinding.inflate(layoutInflater, parent, false);
            return new AppViewHolder(rvParentViewBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {


            AdmissionDashboardModel admissionDashboardModel = getItem().get(position);
            holder.rvParentViewBinding.setAdmissionDashboardModel(admissionDashboardModel);
            holder.rvParentViewBinding.cardHome.setOnClickListener(v -> {
                Intent intent;
                if (admissionDashboardModel.getTitle().equalsIgnoreCase(Utils.ADD_STUDENT)) {
                    intent = new Intent(AdmissionActivity.this, AddNewStudentActivity.class);
                    startActivity(intent);
                } else
                    Snackbar.make(v, "coming soon", Snackbar.LENGTH_SHORT).show();


            });
        }

        @Override
        public int getItemCount() {
            return getItem().size();
        }
    }

    private List<AdmissionDashboardModel> getItem() {
        List<AdmissionDashboardModel> list = new ArrayList<>();
        list.add(new AdmissionDashboardModel(Utils.ADD_STUDENT, R.drawable.attendance));
        list.add(new AdmissionDashboardModel(Utils.ADD_FACULTY, R.drawable.attendance));
        list.add(new AdmissionDashboardModel(Utils.ADD_WORKER, R.drawable.attendance));
        list.add(new AdmissionDashboardModel(Utils.ADD_NOTICE, R.drawable.attendance));
        return list;
    }
}