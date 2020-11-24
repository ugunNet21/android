package com.ubp.student.kumpulanhaditsshahih.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.util.MyPref;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class BabAdapter extends RecyclerView.Adapter<BabAdapter.ViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(BabModel model);
    }

    public interface OnItemFavClickListener {
        void onItemClick(BabModel model);
    }

    private ArrayList<BabModel> list;
    private Context context;
    private final OnItemClickListener listener;
    private final OnItemFavClickListener listenerFav;
    private String spanndable = null;

    public BabAdapter(Context context, ArrayList<BabModel> list, OnItemClickListener listener, OnItemFavClickListener listenerFav, String spanndable) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        this.listenerFav = listenerFav;
        this.spanndable = spanndable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BabModel model = list.get(position);
        KitabModel kitabModel = KitabModel.findById(KitabModel.class, model.getIdKitab());
        ImamModel imamModel = ImamModel.findById(ImamModel.class, kitabModel.getIdImam());
        int sizeFont = MyPref.getInt(context, Static.KEY_FONT);
        if(sizeFont == 0){
            holder.tvJudul.setTextSize(Static.FONT_KECIL);
            holder.tvKitab.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
        }else if(sizeFont == 1){
            holder.tvJudul.setTextSize(Static.FONT_SEDANG);
            holder.tvKitab.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
        }else if(sizeFont == 2){
            holder.tvJudul.setTextSize(Static.FONT_BESAR);
            holder.tvKitab.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
        }else if(sizeFont == 3){
            holder.tvJudul.setTextSize(Static.FONT_SANGAT_BESAR);
            holder.tvKitab.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
        }
        holder.tvJudul.setText(model.getNama());
        holder.tvDeskripsi.setVisibility(View.GONE);
        holder.tvImam.setText(imamModel.getNamaImam());
        holder.tvKitab.setText("Kitab : "+kitabModel.getNama());
        Glide.with(context).load(R.drawable.nav).into(holder.ivThumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(model);
            }
        });
        if(model.isFavorite()){
            Glide.with(context).load(R.drawable.ic_action_fav_true).into(holder.ivFav);
        }else{
            Glide.with(context).load(R.drawable.ic_action_fav_false).into(holder.ivFav);
        }
        holder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerFav.onItemClick(model);
            }
        });
        if(spanndable != null){
//            Spannable spannable = new SpannableString(kitabModel.getNama());
//            spannable.setSpan(new ForegroundColorSpan(Color.YELLOW), kitabModel.getNama().indexOf(spanndable), kitabModel.getNama().indexOf(spanndable)+spanndable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            holder.tvJudul.setText(spannable);
            String set = model.getNama().replace(spanndable, "<font color='green'>"+spanndable+"</font>");
            holder.tvJudul.setText(Html.fromHtml(set));
        }
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
        @BindView(R.id.tv_imam)
        TextView tvImam;
        @BindView(R.id.tv_kitab)
        TextView tvKitab;
        @BindView(R.id.iv_fav)
        ImageView ivFav;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
