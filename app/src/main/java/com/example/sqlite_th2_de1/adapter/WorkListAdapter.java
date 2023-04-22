package com.example.sqlite_th2_de1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.model.Work;

import java.util.List;

public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.ViewHolder> {
    private List<Work> mWorkList;

    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Work work);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView nameTextView;
        public TextView desTextView;
        public TextView dateTextView;
        public TextView statusTextView;
        public TextView colabTextView;

        public ViewHolder(View v) {
            super(v);
            idTextView = v.findViewById(R.id.idTextView);
            nameTextView = v.findViewById(R.id.nameTextView);
            desTextView = v.findViewById(R.id.desTextView);
            dateTextView = v.findViewById(R.id.dateTextView);
            statusTextView = v.findViewById(R.id.statusTextView);
            colabTextView = v.findViewById(R.id.colabTextView);
        }

        public void bind(final Work work, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(work);
                }
            });
        }
    }

    public WorkListAdapter(Context context, List<Work> workList) {
        this.context = context;
        mWorkList = workList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Work work = mWorkList.get(position);
        holder.idTextView.setText(Integer.toString(work.getId()));
        holder.nameTextView.setText(work.getName());
        holder.desTextView.setText(work.getDescription());
        holder.dateTextView.setText(work.getDate());
        holder.statusTextView.setText(work.getStatus());
        holder.colabTextView.setText(work.getCollaborate() ? "Làm chung" : "Một mình");
        holder.bind(work, listener);
    }

    @Override
    public int getItemCount() {
        return mWorkList.size();
    }

    public void setWorkList(List<Work> workList) {
        mWorkList = workList;
        notifyDataSetChanged();
    }
}
