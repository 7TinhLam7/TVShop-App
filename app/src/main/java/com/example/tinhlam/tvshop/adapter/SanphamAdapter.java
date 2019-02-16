package com.example.tinhlam.tvshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.activity.ChiTietSanPhamActivity;
import com.example.tinhlam.tvshop.model.Sanpham;
import com.example.tinhlam.tvshop.model.SanphamScan;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by TINHLAM on 10/29/2017.
 */

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.Itemholder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @Override
    public Itemholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        Itemholder itemholder = new Itemholder(v);
        return itemholder;
    }



    @Override
    public void onBindViewHolder(Itemholder holder, int position) {
        Sanpham sanpham = arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTen());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
       holder.ratingBar.setRating(sanpham.getDiemtb());
        if(sanpham.getHinhspdt() != null && !sanpham.getHinhspdt().isEmpty()) {
            Picasso.with(context).load(sanpham.getHinhspdt())
                    .placeholder(R.drawable.icon_1)
                    .error(R.drawable.newicon)
                    .into(holder.imghinhsanpham);
        }
    }

    @Override
    public int getItemCount() {
       return (arraysanpham == null)  ? 0 : arraysanpham.size();

    }

    public class Itemholder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtdiemtb;
        public RatingBar ratingBar;
        public Itemholder(View itemView) {
            super(itemView);
            imghinhsanpham = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            txttensanpham = (TextView) itemView.findViewById(R.id.textviewtensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    int i=getAdapterPosition();
                    intent.putExtra("thong tin san pham", arraysanpham.get(i));
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context,arraysanpham.get(i).getTen());
                    context.startActivity(intent);
                }
            });

        }
    }



}