package com.example.truyentieuthuyet.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Truyen {

    @PrimaryKey@NonNull
    public int ID;


    @ColumnInfo(name = "tenTruyen")
    public String tenTruyen;

    @ColumnInfo(name = "theLoai")
    public String theLoai;

    @ColumnInfo(name = "tacGia")
    public String tacGia;
}
