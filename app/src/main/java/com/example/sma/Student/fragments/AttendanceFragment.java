package com.example.sma.Student.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.sma.Adapter.AttendanceAdapter;
import com.example.sma.databinding.FragmentAttandenceBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AttendanceFragment extends Fragment {
    FragmentAttandenceBinding binding;
    DocumentSnapshot snapshot;
    AttendanceAdapter adapter;
    private static final String TAG = "AttendanceFragment";

    public AttendanceFragment(DocumentSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttandenceBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initCalender();

        initAttendanceRecords();
    }

    private void initAttendanceRecords() {

        binding.recAttendance.setItemAnimator(new DefaultItemAnimator());
        binding.recAttendance.setAdapter(new AttendanceAdapter());
    }

    private void initCalender() {

        final CompactCalendarView compactCalendarView = binding.compactcalendarView;
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Adding Events
        Event ev1 = new Event(Color.GREEN, 1629984029031L, "Some extra data that I want to store.");
        compactCalendarView.addEvent(ev1);

        Event ev2 = new Event(Color.RED, 1619984029031L);
        compactCalendarView.addEvent(ev2);


        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });

        compactCalendarView.setUseThreeLetterAbbreviation(true);
    }
}