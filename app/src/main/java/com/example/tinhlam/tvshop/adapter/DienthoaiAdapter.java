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

//import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by TINHLAM on 11/3/2017.
 */

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public DienthoaiAdapter(Context context, ArrayList<Sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydienthoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttendienthoai,txtluotxem, txtthongtindienthoai;
        public ImageView imgdienthoai;
        public RatingBar ratingBar;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);     //get layout
            view = inflater.inflate(R.layout.dong_dienthoai,null);
            viewHolder.txttendienthoai = (TextView) view.findViewById(R.id.textviewtendienthoai);
            viewHolder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            viewHolder.txtluotxem = (TextView) view.findViewById(R.id.txtluotxem);
            viewHolder.imgdienthoai = (ImageView )  view.findViewById(R.id.imageviewdienthoai);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttendienthoai.setText(sanpham.getTen());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.ratingBar.setRating(sanpham.getDiemtb());
        viewHolder.txtluotxem.setText(String.valueOf(sanpham.getLuotxem()));


        if(sanpham.getHinhspdt() != null && !sanpham.getHinhspdt().isEmpty()) {
            Picasso.with(context).load(sanpham.getHinhspdt())
                    .placeholder(R.drawable.icon_1)
                    .error(R.drawable.newicon)
                    .into(viewHolder.imgdienthoai);
        }

        return view;
    }
}
