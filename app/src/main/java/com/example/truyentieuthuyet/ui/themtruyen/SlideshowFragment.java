package com.example.truyentieuthuyet.ui.themtruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.truyentieuthuyet.R;
import com.example.truyentieuthuyet.database.AppDatabase;
import com.example.truyentieuthuyet.model.Truyen;
import com.example.truyentieuthuyet.ui.truyen.ChiTietTruyenActivity;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    EditText edtTen, edtTacGia;
    Spinner spnTheLoai;
    Button btnAddTruyen;
    String TheLoai;
    List<Truyen> dsTruyen;
    AppDatabase appDatabase;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        edtTen=root.findViewById(R.id.edtTenTruyen);
        edtTacGia=root.findViewById(R.id.edtTacGia);
        btnAddTruyen=root.findViewById(R.id.btnAddTruyen);

        // Spiner
        spnTheLoai=root.findViewById(R.id.spnTheLoai);
        final List<String> list = new ArrayList<>();
        list.add("Tiên Hiệp");
        list.add("Kiếm Hiệp");
        list.add("Ngôn Tình");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
        spnTheLoai.setAdapter(adapter);
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TheLoai = list.get(spnTheLoai.getSelectedItemPosition());
//                Intent intent = new Intent(getContext(), ChiTietTruyenActivity.class);
//
//                Bundle b = new Bundle();
//                b.putString("tenTruyen", dsTruyen.get(i).tenTruyen);
//                b.putString("tacGia", dsTruyen.get(i).tacGia);
//                b.putString("theLoai", dsTruyen.get(i).theLoai);
//                intent.putExtras(b);
//                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "duan1.db").allowMainThreadQueries().build();
        btnAddTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Truyen truyen = new Truyen();
                truyen.tenTruyen = edtTen.getText().toString();
                truyen.tacGia = edtTacGia.getText().toString();
                truyen.theLoai = TheLoai;
                long[] result = appDatabase.truyenDAO().insertAll(truyen);
                if (result != null) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}