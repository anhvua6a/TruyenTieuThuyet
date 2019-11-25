package com.example.truyentieuthuyet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.truyentieuthuyet.model.Chuong;
import com.example.truyentieuthuyet.model.Truyen;

import java.util.List;
@Dao
public interface ChuongDAO {



        @Query("SELECT * FROM chuong")
        List<Chuong> getAll();

        @Insert
        long [] insertAll(Chuong... chuongs);

        @Delete
        void delete(Chuong chuong);
}
