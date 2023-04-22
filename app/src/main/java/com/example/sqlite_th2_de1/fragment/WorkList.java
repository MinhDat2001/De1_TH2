package com.example.sqlite_th2_de1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.activity.WorkDetailActivity;
import com.example.sqlite_th2_de1.adapter.WorkListAdapter;
import com.example.sqlite_th2_de1.database.WorkDAO;
import com.example.sqlite_th2_de1.model.Work;

import java.util.List;

public class WorkList extends Fragment {
    RecyclerView workListRecycleView;
    WorkListAdapter workListAdapter;
    List<Work> mWorkList;
    private WorkDAO mWorkDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work_list, container, false);

        mWorkDAO = new WorkDAO(getContext());
        mWorkList = mWorkDAO.getAllWorks();

        workListRecycleView = view.findViewById(R.id.work_list);
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mWorkDAO.open();
        mWorkList = mWorkDAO.getAllWorks();
        mWorkDAO.close();
        workListAdapter.setWorkList(mWorkList);
    }
}
