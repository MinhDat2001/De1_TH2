package com.example.sqlite_th2_de1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.Utils.WorkDateComparator;
import com.example.sqlite_th2_de1.activity.WorkDetailActivity;
import com.example.sqlite_th2_de1.adapter.WorkListAdapter;
import com.example.sqlite_th2_de1.database.WorkDAO;
import com.example.sqlite_th2_de1.model.Status;
import com.example.sqlite_th2_de1.model.Work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorkFind extends Fragment {

    RecyclerView workListRecycleView;
    WorkListAdapter workListAdapter;
    List<Work> mWorkList;
    String work, des;
    String status;
    EditText workEdt, desEdt;
    TextView rank;
    Button findBtn;
    TextView records;
    private WorkDAO mWorkDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work_find, container, false);

        mWorkDAO = new WorkDAO(getContext());
        mWorkList = mWorkDAO.getAllWorks();

        workListRecycleView = view.findViewById(R.id.book_find_list);
        workListRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        workListAdapter = new WorkListAdapter(getContext(), mWorkList);
        workListRecycleView.setAdapter(workListAdapter);

        workListAdapter.setOnItemClickListener(new WorkListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Work work) {
                Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                Log.d("Tuan", "onItemClick: " + work.toString());
                intent.putExtra("work", work);
                startActivity(intent);
            }
        });

        workEdt = view.findViewById(R.id.editTextWorkName);
        desEdt = view.findViewById(R.id.editTextWorkDescription);
        findBtn = view.findViewById(R.id.findBtn);
        records = view.findViewById(R.id.sizeRecord);
        rank = view.findViewById(R.id.rank);

        List<Status> statusList = new ArrayList<>();
        statusList.add(new Status("Chưa thực hiện", mWorkDAO.searchByStatus("Chưa thực hiện").size()));
        statusList.add(new Status("Đang thực hiện", mWorkDAO.searchByStatus("Đang thực hiện").size()));
        statusList.add(new Status("Đã thực hiện", mWorkDAO.searchByStatus("Đã thực hiện").size()));

        Collections.sort(statusList, new Comparator<Status>() {
            @Override
            public int compare(Status s1, Status s2) {
                return s2.getTotal().compareTo(s1.getTotal());
            }
        });
        String ssss = "";
        for (Status s : statusList) {
            ssss += s.getName() + ": " + s.getTotal().toString() + "\n";
        }
        rank.setText(ssss);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(workEdt.getText().toString().trim()) && (TextUtils.isEmpty(desEdt.getText().toString().trim()))) {
                    mWorkList = mWorkDAO.getAllWorks();
                    records.setText("Tất cả bản ghi:");
                    workListAdapter.setWorkList(mWorkList);
                } else if (TextUtils.isEmpty(workEdt.getText().toString())) {
                    des = desEdt.getText().toString().toLowerCase();
                    Log.d("tuan", "onClick: " + des);
                    mWorkList = mWorkDAO.searchByDes(des);
                    Collections.sort(mWorkList, Work.dateComparator);
                    workListAdapter.setWorkList(mWorkList);
                } else if (TextUtils.isEmpty(desEdt.getText().toString())) {
                    work = workEdt.getText().toString().toLowerCase();
                    Log.d("tuan", "onClick: " + work);
                    mWorkList = mWorkDAO.searchByWork(work);
                    Collections.sort(mWorkList, Work.dateComparator);
                    workListAdapter.setWorkList(mWorkList);
                } else {
                    des = desEdt.getText().toString().toLowerCase();
                    work = workEdt.getText().toString().toLowerCase();
                    Log.d("tuan", "onClick: " + work);
                    mWorkList = mWorkDAO.searchByWorkAndDes(work, des);
                    Collections.sort(mWorkList, Work.dateComparator);
                    workListAdapter.setWorkList(mWorkList);
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mWorkList = mWorkDAO.getAllWorks();
        workListAdapter.setWorkList(mWorkList);
    }
}
