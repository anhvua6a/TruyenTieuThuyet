package com.example.truyentieuthuyet.ui.truyen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyentieuthuyet.R;
import com.example.truyentieuthuyet.database.AppDatabase;
import com.example.truyentieuthuyet.model.Chuong;
import com.example.truyentieuthuyet.model.Truyen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChiTietTruyenActivity extends AppCompatActivity {
    TextView tvTenTruyen, tvTGTruyen, tvTLTruyen;
    FloatingActionButton fabAddChuong;
    ListView lvListChuong;
    List<Chuong> dsChuong;
    AppDatabase appDatabase;
    ChuongAdapter chuongAdapter;
    ImageView imgAvt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);
        init();
        iconBack();

        final Intent in = getIntent();
        Bundle b = in.getExtras();

        if (b != null) {
            tvTenTruyen.setText(b.getString("tenTruyen"));
            tvTLTruyen.setText(b.getString("theLoai"));
            tvTGTruyen.setText(b.getString("tacGia"));
        }
        if (tvTLTruyen.getText().toString().equals("Kiếm Hiệp")) {
            imgAvt.setImageResource(R.drawable.kiemhiep);
        } else if (tvTLTruyen.getText().toString().equals("Tiên Hiệp")) {
            imgAvt.setImageResource(R.drawable.tienhiep);
        } else if (tvTLTruyen.getText().toString().equals("Ngôn Tình")) {
            imgAvt.setImageResource(R.drawable.ngontinh);
        }

            dsChuong = new ArrayList<>();
            appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "duan1.db").allowMainThreadQueries().build();
            dsChuong = appDatabase.chuongDAO().getAll();
            chuongAdapter = new ChuongAdapter(dsChuong, getApplicationContext());
            lvListChuong.setAdapter(chuongAdapter);
            lvListChuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ChiTietTruyenActivity.this, ReadStoryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("soChuong", dsChuong.get(i).sttChuong);
                    bundle.putString("tenChuong", dsChuong.get(i).tenChuong);
                    bundle.putString("noiDung", dsChuong.get(i).noiDung);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });

            fabAddChuong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ChiTietTruyenActivity.this, AddChuongActivity.class));
                }
            });
        }

    private void iconBack() {
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    private void init () {
            tvTenTruyen = findViewById(R.id.tvTenTruyen);
            tvTGTruyen = findViewById(R.id.tvTGTruyen);
            tvTLTruyen = findViewById(R.id.tvTLTruyen);
            fabAddChuong = findViewById(R.id.fabAddChuong);
            lvListChuong = findViewById(R.id.lvListChuong);
            imgAvt = findViewById(R.id.imgAvt);
        }

        public void Add (View view){
        }

}
