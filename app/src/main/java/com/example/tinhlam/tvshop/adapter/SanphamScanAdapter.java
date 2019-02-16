package com.example.tinhlam.tvshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tinhlam.tvshop.R;
import com.example.tinhlam.tvshop.activity.SanphamScancungloaiActivity;
import com.example.tinhlam.tvshop.model.SanphamScan;
import com.example.tinhlam.tvshop.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TINHLAM on 11/29/2017.
 */

public class SanphamScanAdapter extends BaseAdapter {
    ArrayList<SanphamScan> arraylistsanpham;
    Context context;

    public SanphamScanAdapter(ArrayList<SanphamScan> arraylistsanpham, Context context) {
        this.arraylistsanpham = arraylistsanpham;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistsanpham.size(); //lay toan bo mang du lieu
    }

    @Override
    public Object getItem(int position) {
        return arraylistsanpham.get(position); //get tung thuoc tinh trong mang
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{          //lan dau load du lieu dc luu lai lan sau khong can load lai
        TextView txtLuotxem, txttenspscan;
        ImageView imghinhsp;
        RatingBar ratingBar;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);     //get layout
            view = inflater.inflate(R.layout.dong_sp_scan,null);
            viewHolder.txtLuotxem = (TextView) view.findViewById(R.id.textviewluotxem);
            viewHolder.txttenspscan = (TextView) view.findViewById(R.id.txttensanphamscan);
            viewHolder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            viewHolder.imghinhsp = (ImageView) view.findViewById(R.id.imageviewhinhsp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        SanphamScan SanphamScan = (SanphamScan) getItem(position);
        viewHolder.txttenspscan.setText(SanphamScan.getTen());
        viewHolder.txtLuotxem.setText(Integer.toString(SanphamScan.getLuotxem()));
        viewHolder.ratingBar.setRating(SanphamScan.getDiemtb());

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, SanphamScancungloaiActivity.class);


                intent.putExtra("thong tin san pham", arraylistsanpham.get(position));
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                CheckConnection.ShowToast_Short(context, String.valueOf(arraylistsanpham.get(position).getID()));
                context.startActivity(intent);
            }
        });


        Picasso.with(context).load(SanphamScan.getHinhspdt())
                .placeholder(R.drawable.icon_1)
                .error(R.drawable.icon_2)
                .into(viewHolder.imghinhsp);


        return view;
    }
}
