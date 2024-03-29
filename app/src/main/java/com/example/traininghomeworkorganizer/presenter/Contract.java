package com.example.traininghomeworkorganizer.presenter;

import com.example.traininghomeworkorganizer.database.HomeworkEntity;

import java.util.List;

public interface Contract {

    interface HomeworkPresenter{
        void insertHomework(HomeworkEntity homeworkEntity);
        void getHomework();
        void getday1();
        void getday2();
        void getday3();
        void getday4();
        void getday5();
    }

    interface HomeworkView{
        void displayHomework(List<HomeworkEntity> homework);
        void homeworkIsEmpty();
        void displayError(String errorString);
    }
}
