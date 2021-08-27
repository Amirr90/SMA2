package com.example.sma.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sma.Student.fragments.AttendanceFragment;
import com.example.sma.TeacherFragments.TeacherOtherFragment;
import com.example.sma.TeacherFragments.TeacherParentFragment;
import com.example.sma.TeacherFragments.TeacherPersonalFragment;
import com.example.sma.TeacherFragments.TeacherTransportFragment;
import com.google.firebase.firestore.DocumentSnapshot;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String TeacherId;
    DocumentSnapshot snapshot;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, String TeacherId, DocumentSnapshot snapshot) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.TeacherId = TeacherId;
        this.snapshot = snapshot;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TeacherPersonalFragment(snapshot);
            case 1:
                return new TeacherParentFragment(snapshot);
            case 2:
                return new TeacherTransportFragment(snapshot);
            case 3:
                return new TeacherOtherFragment(snapshot);
            case 4:
                return new AttendanceFragment(snapshot);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
