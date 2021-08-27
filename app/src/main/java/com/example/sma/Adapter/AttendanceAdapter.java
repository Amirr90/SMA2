package com.example.sma.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sma.appViewHolder.AppViewHolder;
import com.example.sma.databinding.ViewStudentAttendanceBinding;

public class AttendanceAdapter extends RecyclerView.Adapter<AppViewHolder> {
    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewStudentAttendanceBinding viewStudentAttendanceBinding = ViewStudentAttendanceBinding.inflate(layoutInflater, parent, false);
        return new AppViewHolder(viewStudentAttendanceBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
