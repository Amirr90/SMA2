package com.example.sma.appViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sma.databinding.RvParentViewBinding;
import com.example.sma.databinding.ViewStudentAttendanceBinding;

public class AppViewHolder extends RecyclerView.ViewHolder {

    ViewStudentAttendanceBinding viewStudentAttendanceBinding;
    public RvParentViewBinding rvParentViewBinding;

    public AppViewHolder(@NonNull RvParentViewBinding rvParentViewBinding) {
        super(rvParentViewBinding.getRoot());
        this.rvParentViewBinding = rvParentViewBinding;
    }

    public AppViewHolder(@NonNull ViewStudentAttendanceBinding viewStudentAttendanceBinding) {
        super(viewStudentAttendanceBinding.getRoot());
        this.viewStudentAttendanceBinding = viewStudentAttendanceBinding;
    }

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
