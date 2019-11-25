package com.example.truyentieuthuyet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.truyentieuthuyet.model.Chuong;
import com.example.truyentieuthuyet.model.Truyen;

import java.util.List;

@Dao
public interface TruyenDAO {

    @Query("SELECT * FROM truyen")
    List<Truyen> getAll();

    @Insert
    long [] insertAll(Truyen... truyens);

    @Delete
    void delete(Truyen truyen);



}
