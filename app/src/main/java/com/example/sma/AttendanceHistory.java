package com.example.sma;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sma.model.Attendance;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static com.example.sma.Util.Utils.ATTENDANCE;
import static com.example.sma.Util.Utils.DAY;
import static com.example.sma.Util.Utils.IMAGE;
import static com.example.sma.Util.Utils.MONTH;
import static com.example.sma.Util.Utils.NAME;
import static com.example.sma.Util.Utils.PRESENT;
import static com.example.sma.Util.Utils.ROLL_NUMBER;
import static com.example.sma.Util.Utils.hideAlertDialog;

public class AttendanceHistory extends AppCompatActivity {
    private static final String TAG = "AttendanceHistory";

    RelativeLayout RecLay;
    ConstraintLayout noAttendanceLayout;

    int mMonth, mDay, mYear, present = 0, absent = 0;

    RecyclerView recyclerView;
    List<Attendance> attendanceList = new ArrayList<>();
    AttendanceHistoryAdapter adapter;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history);
        Toolbar toolbar = findViewById(R.id._at_hi_toolbar);
        setToolbar(toolbar, "Select Date");

        RecLay = findViewById(R.id.rec_lay);
        noAttendanceLayout = findViewById(R.id.noAttendanceLay);


        recyclerView = findViewById(R.id.a_h_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new AttendanceHistoryAdapter(attendanceList, this);
        recyclerView.setAdapter(adapter);

        setRowCalender();

    }

    private void setRowCalender() {

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.row_calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();


        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
                int mYear = date.get(Calendar.YEAR);
                int mMonth = date.get(Calendar.MONTH) + 1;
                int mDay = date.get(Calendar.DAY_OF_MONTH);
                hideAlertDialog();
                loadData(mYear, mMonth, mDay);

            }
        });
        horizontalCalendar.centerCalendarToPosition(3);
        horizontalCalendar.positionOfDate(startDate);


        Calendar calendar = horizontalCalendar.getDateAt(2);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 2;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        loadData(mYear, mMonth, mDay);

    }

    private void loadData(int mYear, int mMonth, int mDay) {
        Log.d(TAG, "loadData: " + mYear + mMonth + mDay);
        present = absent = 0;
        final String date = mDay + "-" + mMonth + "-" + mYear;
        String cla = "Class " + getIntent().getStringExtra("CLASS");
        firestore.collection(ATTENDANCE)
                .document(String.valueOf(mYear))
                .collection(cla)
                .whereEqualTo(MONTH, mMonth)
                .whereEqualTo(DAY, mDay)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    hideAlertDialog();
                    if (queryDocumentSnapshots.getDocuments().size() == 0) {
                        Toast.makeText(AttendanceHistory.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        Objects.requireNonNull(getSupportActionBar()).setTitle("Select Date");
                        attendanceList.clear();
                        adapter.notifyDataSetChanged();

                        noAttendanceLayout.setVisibility(View.VISIBLE);
                        RecLay.setVisibility(View.GONE);
                        return;
                    }

                    attendanceList.clear();
                    DocumentSnapshot snapshot = queryDocumentSnapshots.getDocuments().get(0);
                    List<Map<String, Object>> mapList = (List<Map<String, Object>>) snapshot.get(ATTENDANCE);
                    for (int a = 0; a < mapList.size(); a++) {
                        String name = mapList.get(a).get(NAME).toString();
                        Boolean status = (Boolean) mapList.get(a).get(PRESENT);
                        String image = (String) mapList.get(a).get(IMAGE);
                        Long rollNumber = (Long) mapList.get(a).get(ROLL_NUMBER);
                        attendanceList.add(new Attendance(name, status, image, String.valueOf(rollNumber)));
                        if (status != null) {
                            if (status)
                                present++;
                            else
                                absent++;
                        }

                    }

                    TextView mPresent = findViewById(R.id.present_tv);
                    TextView mAbsent = findViewById(R.id.absent_tv);
                    TextView mTotal = findViewById(R.id.total_tv);

                    mPresent.setText(String.format("%d Presents", present));
                    mAbsent.setText(String.format("%d Absent", absent));
                    mTotal.setText(String.format("%d Total", attendanceList.size()));

                    RecLay.setVisibility(View.VISIBLE);
                    noAttendanceLayout.setVisibility(View.GONE);

                    Objects.requireNonNull(getSupportActionBar()).setTitle("Date: " + date);
                    adapter.notifyDataSetChanged();

                }).addOnFailureListener(e -> {
                    Toast.makeText(AttendanceHistory.this, "No data found,try again", Toast.LENGTH_SHORT).show();
                    RecLay.setVisibility(View.GONE);
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Select Date");
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void setToolbar(Toolbar toolbar, String id) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(id);
    }

    private class AttendanceHistoryAdapter extends RecyclerView.Adapter<AttendanceHistory.MyHolder> {
        List<Attendance> list;
        Context context;
        Dialog dialog;

        public AttendanceHistoryAdapter(List<Attendance> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public AttendanceHistory.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_student_view3, parent, false);
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.profile_dialog_view);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final AttendanceHistory.MyHolder holder, final int position) {

            holder.textView.setText(list.get(position).getName());
            Boolean isPresent = list.get(position).getPresent();
            if (isPresent)
                holder.imageView.setBorderColor(context.getResources().getColor(R.color.colorPrimaryDark));
            else
                holder.imageView.setBorderColor(Color.RED);

            final String image = list.get(position).getImage();
            if (image != null && !image.equalsIgnoreCase(""))
                Picasso.with(context).load(image)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .placeholder(R.drawable.profile)
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(context).load(image).placeholder(R.drawable.profile).into(holder.imageView);
                            }
                        });


            holder.layout.setOnClickListener(v -> showProfileDialog(list.get(position)));
        }

        private void showProfileDialog(Attendance attendance) {
            TextView mStuName = dialog.findViewById(R.id.s_name);
            TextView mStuRollNumber = dialog.findViewById(R.id.s_roll_number);
            TextView mStuFatherName = dialog.findViewById(R.id.s_father_name);
            final CircleImageView mStuProfile = dialog.findViewById(R.id.s_profile);
            final Button mViewProfile = dialog.findViewById(R.id.view_stdent_profile_btn);
            Button dismissBtn = dialog.findViewById(R.id.dismiss_btn);

            mStuFatherName.setVisibility(View.GONE);
            mStuName.setText(attendance.getName());
            final String image = attendance.getImage();
            if (image != null && !image.equals(""))
                Picasso.with(context).load(image).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.profile).into(mStuProfile, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(image).placeholder(R.drawable.profile).into(mStuProfile);
                    }
                });

            else
                mStuProfile.setImageResource(R.drawable.profile);


            mViewProfile.setOnClickListener(v -> {
                dialog.dismiss();
                /*startActivity(new Intent(AttendanceHistory.this, StudentProfile.class)
                        .putExtra(STU_ID, snapshot.getId()));*/
            });

            mViewProfile.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final CircleImageView imageView;
        private final RelativeLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.card_Home);
            textView = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.home_rec_imageView3);
        }
    }
}
