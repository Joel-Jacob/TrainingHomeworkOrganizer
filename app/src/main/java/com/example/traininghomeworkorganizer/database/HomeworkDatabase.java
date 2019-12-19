package com.example.traininghomeworkorganizer.database;

import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {HomeworkEntity.class})
public abstract class HomeworkDatabase extends RoomDatabase {

    public abstract HomeworkDAO homeworkDAO();
}
