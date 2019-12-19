package com.example.traininghomeworkorganizer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traininghomeworkorganizer.R;
import com.example.traininghomeworkorganizer.database.HomeworkEntity;

import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkHolder>{

    List<HomeworkEntity> homework;
    HomeworkDelegate homeworkDelegate;


    public interface HomeworkDelegate{
        void HomeworkSelected(HomeworkDelegate homeworkDelegate);
    }

    public HomeworkAdapter(List<HomeworkEntity> homework, HomeworkDelegate homeworkDelegate) {
        this.homework = homework;
        this.homeworkDelegate = homeworkDelegate;
    }

    @NonNull
    @Override
    public HomeworkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homework_item_layout, parent, false);

        return new HomeworkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkHolder holder, int position) {
        holder.weekTextView.setText("Week " + homework.get(position).getWeek());

        if(homework.get(position).getDay() != 5)
            holder.dayTextView.setText("Day " + homework.get(position).getDay());
        else
            holder.dayTextView.setText("Weekend");

        holder.subjectTextView.setText(homework.get(position).getSubject());

        if(homework.get(position).isCompleted())
            holder.completedTextView.setText("true");
        else
            holder.completedTextView.setText("false");

    }

    @Override
    public int getItemCount() {
        return homework.size();
    }

    class HomeworkHolder extends RecyclerView.ViewHolder{

        TextView weekTextView;
        TextView dayTextView;
        TextView subjectTextView;
        TextView completedTextView;

        public HomeworkHolder(@NonNull View itemView) {
            super(itemView);

            weekTextView = itemView.findViewById(R.id.week_tv_item);
            dayTextView = itemView.findViewById(R.id.day_tv_item);
            subjectTextView = itemView.findViewById(R.id.subject_tv_item);
            completedTextView = itemView.findViewById(R.id.completed_tv_item);
        }
    }
}
