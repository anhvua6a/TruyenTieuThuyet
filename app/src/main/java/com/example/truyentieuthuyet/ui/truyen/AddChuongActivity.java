package com.example.truyentieuthuyet.ui.truyen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truyentieuthuyet.R;
import com.example.truyentieuthuyet.database.AppDatabase;
import com.example.truyentieuthuyet.model.Chuong;

import java.util.ArrayList;
import java.util.List;

public class AddChuongActivity extends AppCompatActivity {
    EditText edtsoChuong, edtTenChuong, edtNoiDung,edtID;
    AppDatabase appDatabase;
    List<Chuong> dsChuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chuong);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        init();
        appDatabase= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"duan1.db").allowMainThreadQueries().build();
    }

    public void init(){
        edtNoiDung=findViewById(R.id.edtNoiDung);
        edtsoChuong=findViewById(R.id.edtsoChuong);
        edtTenChuong=findViewById(R.id.edtTenChuong);
        edtID=findViewById(R.id.edtId);
    }
    public void AddChuong(View view) {

        Chuong chuong = new Chuong();
        chuong.id=Integer.parseInt(edtID.getText().toString());
        chuong.sttChuong=edtsoChuong.getText().toString();
        chuong.tenChuong=edtTenChuong.getText().toString();
        chuong.noiDung=edtNoiDung.getText().toString();


        long[] result = appDatabase.chuongDAO().insertAll(chuong);
        if (result != null){
            Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddChuongActivity.this,ChiTietTruyenActivity.class));
        }else {
            Toast.makeText(getApplicationContext(),"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
        }

    }
}
