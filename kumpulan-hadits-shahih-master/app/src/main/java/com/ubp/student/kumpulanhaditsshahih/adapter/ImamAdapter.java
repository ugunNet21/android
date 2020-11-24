package com.ubp.student.kumpulanhaditsshahih.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.util.MyPref;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class ImamAdapter extends RecyclerView.Adapter<ImamAdapter.ViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(ImamModel model);
    }

    private ArrayList<ImamModel> list;
    private Context context;
    private final OnItemClickListener listener;

    public ImamAdapter(Context context, ArrayList<ImamModel> list, OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imam, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImamModel model = list.get(position);
        int sizeFont = MyPref.getInt(context, Static.KEY_FONT);
        if(sizeFont == 0){
            holder.tvJudul.setTextSize(Static.FONT_KECIL);
        }else if(sizeFont == 1){
            holder.tvJudul.setTextSize(Static.FONT_SEDANG);
        }else if(sizeFont == 2){
            holder.tvJudul.setTextSize(Static.FONT_BESAR);
        }else if(sizeFont == 3){
            holder.tvJudul.setTextSize(Static.FONT_SANGAT_BESAR);
        }
        holder.tvJudul.setText(model.getNamaImam());
        Glide.with(context).load(R.drawable.nav).into(holder.ivThumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_deskripsi)
        TextView tvDeskripsi;
        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
