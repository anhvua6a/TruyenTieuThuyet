package com.example.truyentieuthuyet.ui.truyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.truyentieuthuyet.R;
import com.example.truyentieuthuyet.database.AppDatabase;
import com.example.truyentieuthuyet.model.Chuong;

import java.util.List;

public class ChuongAdapter extends BaseAdapter {
    List<Chuong> arrChuong ;
    Context context;
    AppDatabase appDatabase;

    public ChuongAdapter(List<Chuong> arrChuong, Context context) {
        this.arrChuong = arrChuong;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrChuong.size();
    }

    @Override
    public Object getItem(int position) {
        return arrChuong.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
        TextView tvTenChuong;
        TextView tvSoChuong;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_chuong, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTenChuong = convertView.findViewById(R.id.tvtenChuong);
            viewHolder.tvSoChuong = convertView.findViewById(R.id.tvsoChuong);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();


        }
        return convertView;
    }}
