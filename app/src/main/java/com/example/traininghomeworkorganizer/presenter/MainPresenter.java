package com.example.traininghomeworkorganizer.presenter;

import androidx.room.Room;

import com.example.traininghomeworkorganizer.database.HomeworkDatabase;
import com.example.traininghomeworkorganizer.database.HomeworkEntity;
import com.example.traininghomeworkorganizer.view.MainActivity;

import java.util.List;

public class MainPresenter implements Contract.HomeworkPresenter {

    private Contract.HomeworkView homeworkView;
    private HomeworkDatabase homeworkDatabase;
    private List<HomeworkEntity> homework;

    public MainPresenter(Contract.HomeworkView homeworkView) {
        this.homeworkView = homeworkView;

        try{
            homeworkDatabase = Room.databaseBuilder(
                    ((MainActivity)homeworkView).getApplicationContext(),
                    HomeworkDatabase.class,
                    "room.db")
                    .allowMainThreadQueries()
                    .build();

        }catch (Exception e){
            homeworkView.displayError("Failed to create database");
        }
    }

    @Override
    public void insertHomework(HomeworkEntity homeworkEntity) {
        try{
            homeworkDatabase.homeworkDAO().insertNewHomework(homeworkEntity);
        }catch (Exception e){
            homeworkView.displayError("Failed to insert" + homeworkEntity.getSubject());
        }
    }

    @Override
    public void getHomework() {
        homework = homeworkDatabase.homeworkDAO().getAllHomework();

        if(homework.isEmpty())
            homeworkView.homeworkIsEmpty();
        else
            homeworkView.displayHomework(homework);
    }
}
