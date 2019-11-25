package com.example.truyentieuthuyet.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.truyentieuthuyet.dao.ChuongDAO;
import com.example.truyentieuthuyet.dao.TruyenDAO;
import com.example.truyentieuthuyet.model.Chuong;
import com.example.truyentieuthuyet.model.Truyen;

@Database(entities = {Truyen.class, Chuong.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TruyenDAO truyenDAO();
    public abstract ChuongDAO chuongDAO();

}
