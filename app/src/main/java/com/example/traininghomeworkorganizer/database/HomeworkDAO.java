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

    @Query("SELECT * FROM homework WHERE day IS 1")
    public List<HomeworkEntity> getday1Homework();

    @Query("SELECT * FROM homework WHERE day IS 2")
    public List<HomeworkEntity> getday2Homework();

    @Query("SELECT * FROM homework WHERE day IS 3")
    public List<HomeworkEntity> getday3Homework();

    @Query("SELECT * FROM homework WHERE day IS 4")
    public List<HomeworkEntity> getday4Homework();

    @Query("SELECT * FROM homework WHERE day IS 5")
    public List<HomeworkEntity> getday5Homework();

    @Insert
    void insertNewHomework(HomeworkEntity homeworkEntity);

    @Delete
    void deleteNewHomework(HomeworkEntity homeworkEntity);

    @Update
    void updateEntity(HomeworkEntity homeworkEntity);


}
