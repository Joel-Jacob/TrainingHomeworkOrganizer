package com.example.traininghomeworkorganizer.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "homework")
public class HomeworkEntity {

    @PrimaryKey(autoGenerate = true)
    private int homeworkId;

    @ColumnInfo(name = "week")
    private int week;

    @ColumnInfo(name = "day")
    private int day;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "completed")
    private boolean completed;

    public HomeworkEntity(int homeworkId, int week, int day, String subject, boolean completed) {
        this.homeworkId = homeworkId;
        this.week = week;
        this.day = day;
        this.subject = subject;
        this.completed = completed;
    }

    @Ignore
    public HomeworkEntity(int week, int day, String subject, boolean completed) {
        this.week = week;
        this.day = day;
        this.subject = subject;
        this.completed = completed;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
