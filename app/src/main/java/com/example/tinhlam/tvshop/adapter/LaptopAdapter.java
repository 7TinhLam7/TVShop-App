package com.example.tinhlam.tvshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TINHLAM on 11/6/2017.
 */

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraylaptop;

    public LaptopAdapter(Context context, ArrayList<Sanpham> arraylaptop) {
        this.context = context;
        this.arraylaptop = arraylaptop;
    }

    @Override
    public int getCount() {
        return arraylaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenlaptop,txtdiemtblaptop, txtluotxem;
        public ImageView imglaptop;
        public RatingBar ratingBar;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);     //get layout
            view = inflater.inflate(R.layout.dong_laptop,null);
            viewHolder.txttenlaptop = (TextView) view.findViewById(R.id.textviewtenlaptop);
            viewHolder.txtluotxem = (TextView) view.findViewById(R.id.txtluotxem);
            viewHolder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            viewHolder.imglaptop = (ImageView )  view.findViewById(R.id.imageviewlaptop);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenlaptop.setText(sanpham.getTen());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.ratingBar.setRating(sanpham.getDiemtb());
        viewHolder.txtluotxem.setText(String.valueOf(sanpham.getLuotxem()));
        if(sanpham.getHinhspdt() != null && !sanpham.getHinhspdt().isEmpty()) {
            Picasso.with(context).load(sanpham.getHinhspdt())
                    .placeholder(R.drawable.icon_1)
                    .error(R.drawable.newicon)
                    .into(viewHolder.imglaptop);
        }

        return view;
    }
}

