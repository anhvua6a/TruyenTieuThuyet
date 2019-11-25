package com.example.truyentieuthuyet.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chuong {

    @PrimaryKey@NonNull
    public int id;

    @ColumnInfo(name = "sttChuong")
    public String sttChuong;

    @ColumnInfo(name = "tenChuong")
    public String tenChuong;

    @ColumnInfo(name = "noiDung")
    public String noiDung;

}
