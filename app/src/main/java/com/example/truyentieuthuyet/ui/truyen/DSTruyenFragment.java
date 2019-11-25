package com.example.truyentieuthuyet.ui.truyen;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.truyentieuthuyet.R;
import com.example.truyentieuthuyet.database.AppDatabase;
import com.example.truyentieuthuyet.model.Truyen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DSTruyenFragment extends Fragment  {
    private ListView lvList;
    TruyenAdapter truyenAdapter;
    List<Truyen> dsTruyen;
    AppDatabase appDatabase;
    FloatingActionButton fab;
    EditText edtTen, edtTacGia,edtID;
    Spinner spnTheLoai;
    Button btnAddTruyen;
    String TheLoai;
    SearchView svSearch;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_truyen, container, false);


//        svSearch=root.findViewById(R.id.svSearch);
//        svSearch.setOnQueryTextListener(this);


        lvList = root.findViewById(R.id.lvList);
        dsTruyen = new ArrayList<>();
        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "duan1.db").allowMainThreadQueries().build();
        dsTruyen = appDatabase.truyenDAO().getAll();
        truyenAdapter = new TruyenAdapter(dsTruyen, getContext());
        lvList.setAdapter(truyenAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getContext(), ChiTietTruyenActivity.class);
                Bundle b = new Bundle();
                b.putString("tacGia", dsTruyen.get(position).tacGia);
                b.putString("tenTruyen", dsTruyen.get(position).tenTruyen);
                b.putString("theLoai", dsTruyen.get(position).theLoai);
                intent.putExtras(b);
                startActivity(intent);


            }
        });
//
        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_truyen, null);
                alertDialog.setView(view);
                edtID=view.findViewById(R.id.edtId);
                edtTen = view.findViewById(R.id.edtTenTruyen);
                edtTacGia = view.findViewById(R.id.edtTacGia);
                btnAddTruyen = view.findViewById(R.id.btnAddTruyen);

                // Spiner
                spnTheLoai = view.findViewById(R.id.spnTheLoai);
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
//                        Intent intent = new Intent(getContext(), ChiTietTruyenActivity.class);
//                        Bundle b = new Bundle();
//                        b.putString("tenTruyen", dsTruyen.get(i).tenTruyen);
//                        b.putString("tacGia", dsTruyen.get(i).tacGia);
//                        b.putString("theLoai", dsTruyen.get(i).theLoai);
//                        intent.putExtras(b);
//                        startActivity(intent);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btnAddTruyen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Truyen truyen = new Truyen();
                        truyen.ID=Integer.parseInt(edtID.getText().toString());
                        truyen.tenTruyen = edtTen.getText().toString();
                        truyen.tacGia = edtTacGia.getText().toString();
                        truyen.theLoai = TheLoai;
                        long[] result = appDatabase.truyenDAO().insertAll(truyen);
                        if (result != null) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            truyenAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();

            }
        });


        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
//        Toast.makeText(getContext(), ""+dsTruyen.size(), Toast.LENGTH_SHORT).show();
        return root;
    }


//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        String text = newText;
//        truyenAdapter.filter(text);
//        return false;
//    }
}