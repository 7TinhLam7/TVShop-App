package com.example.tinhlam.tvshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TINHLAM on 10/28/2017.
 */

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arraylistloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arraylistloaisp, Context context) {
        this.arraylistloaisp = arraylistloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistloaisp.size(); //lay toan bo mang du lieu
    }

   @Override
   public Object getItem(int i) {
       return arraylistloaisp.get(i); //get tung thuoc tinh trong mang
   }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{          //lan dau load du lieu dc luu lai lan sau khong can load lai
        TextView txttenloaisp;
        ImageView imgloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);     //get layout
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp = (TextView) view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = (ImageView) view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        Loaisp loaisp = (Loaisp) getItem(i);
        viewHolder.txttenloaisp.setText(loaisp.getTen());
        Picasso.with(context).load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.icon_1)
                .error(R.drawable.icon_2)
                .into(viewHolder.imgloaisp);


        return view;
    }
}