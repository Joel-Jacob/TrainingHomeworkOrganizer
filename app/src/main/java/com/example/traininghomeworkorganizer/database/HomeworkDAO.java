package com.example.traininghomeworkorganizer.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HomeworkDAO {

    @Query("SELECT * FROM homework ORDER BY week, day")
    public List<HomeworkEntity> getAllHomework();

    @Insert
    void insertNewHomework(HomeworkEntity homeworkEntity);

    @Delete
    void deleteNewHomework(HomeworkEntity homeworkEntity);

    @Update
    void updateEntity(HomeworkEntity homeworkEntity);


}
