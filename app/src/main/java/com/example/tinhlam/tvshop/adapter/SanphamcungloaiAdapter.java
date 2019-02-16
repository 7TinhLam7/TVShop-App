package com.example.tinhlam.tvshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.model.Sanpham;
import com.example.tinhlam.tvshop.model.Sanphamcungloai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TINHLAM on 11/8/2017.
 */

public class SanphamcungloaiAdapter extends BaseAdapter {
    ArrayList<Sanphamcungloai> arraylistsanphamcungloai;
    Context context;



    public SanphamcungloaiAdapter(ArrayList<Sanphamcungloai> arraylistsanphamcungloai, Context context) {
        this.arraylistsanphamcungloai = arraylistsanphamcungloai;
        this.context = context;
    }

    @Override
    public int getCount() {
        return(arraylistsanphamcungloai == null)  ? 0 : arraylistsanphamcungloai.size(); //lay toan bo mang du lieu

    }

    @Override
    public Object getItem(int i) {
        return arraylistsanphamcungloai.get(i); //get tung thuoc tinh trong mang
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{          //lan dau load du lieu dc luu lai lan sau khong can load lai
        TextView txtGia, txtDuongdan;
        ImageView imghinhnhacc;
        Button btnnhacc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);     //get layout
            view = inflater.inflate(R.layout.dong_sp_cung_loai,null);
            viewHolder.txtGia = (TextView) view.findViewById(R.id.textviewgia);
            //viewHolder.btnnhacc= (Button) view.findViewById(R.id.btnnhacc);

            viewHolder.imghinhnhacc = (ImageView) view.findViewById(R.id.imageviewnhacc);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        final Sanphamcungloai sanphamcungloai = (Sanphamcungloai) getItem(i);
        viewHolder.txtGia.setText(Integer.toString(sanphamcungloai.getGia()) + "VND");
       // viewHolder.btnnhacc.setText(sanphamcungloai.getDuongdan());

//        viewHolder.txtDuongdan.setBackgroundResource(R.drawable.mua3);

        //Picasso .with(context).load(R.drawable.mua3);
       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sanphamcungloai.getDuongdan()));
              browserIntent.setFlags(browserIntent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(browserIntent);
           }
       });

        Picasso

                .with(context)
                .load(sanphamcungloai.getHinhnhacc())
                .placeholder(R.drawable.icon_1)
                .error(R.drawable.icon_2)
                .into(viewHolder.imghinhnhacc)


        ;




        return view;
    }
}

