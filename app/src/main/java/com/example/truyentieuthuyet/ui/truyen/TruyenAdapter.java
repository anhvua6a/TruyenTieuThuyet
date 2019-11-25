package com.example.truyentieuthuyet.ui.truyen;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.example.truyentieuthuyet.R;
import com.example.truyentieuthuyet.dao.TruyenDAO;

import com.example.truyentieuthuyet.database.AppDatabase;
import com.example.truyentieuthuyet.model.Truyen;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TruyenAdapter extends BaseAdapter {
    List<Truyen> arrTruyen;

    Context context;
    AppDatabase appDatabase;


    public TruyenAdapter(List<Truyen> arrTruyen, Context context) {
        this.arrTruyen = arrTruyen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTruyen.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgBook;
        TextView tvName;
        TextView tvTacGia;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_truyen,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imgBook = convertView.findViewById(R.id.imgBook);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvTacGia = convertView.findViewById(R.id.tvTacgia);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDelete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();


        }
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "duan1.db").allowMainThreadQueries().build();


        viewHolder.tvName.setText(arrTruyen.get(position).tenTruyen);
        viewHolder.tvTacGia.setText(arrTruyen.get(position).tacGia);
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Truyen truyen = arrTruyen.get(position);
                arrTruyen.remove(truyen);
                appDatabase.truyenDAO().delete(truyen);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
//    public void filter(String charText){
//        charText = charText.toLowerCase(Locale.getDefault());
//        arrTruyen.clear();
//        if (charText.length()==0){
//            arrTruyen.addAll(arrListTruyen);
//        }else {
//            for (Truyen tr : arrTruyen){
//                if (tr.tenTruyen.toLowerCase(Locale.getDefault()).contains(charText)){
//                    arrTruyen.add(tr);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
