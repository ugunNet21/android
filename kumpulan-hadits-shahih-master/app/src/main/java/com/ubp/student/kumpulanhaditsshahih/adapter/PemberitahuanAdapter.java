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
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.NotifModel;
import com.ubp.student.kumpulanhaditsshahih.util.MyPref;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class PemberitahuanAdapter extends RecyclerView.Adapter<PemberitahuanAdapter.ViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(NotifModel model);
    }

    public interface OnItemFavClickListener {
        void onItemClick(NotifModel model);
    }

    private ArrayList<NotifModel> list;
    private Context context;
    private final OnItemClickListener listener;
    private final OnItemFavClickListener listenerFav;
    private String spanndable = null;

    public PemberitahuanAdapter(Context context, ArrayList<NotifModel> list, OnItemClickListener listener, OnItemFavClickListener listenerFav, String spanndable) {
        this.list = list;
        this.context = context;
        this.listener = listener;
        this.listenerFav = listenerFav;
        this.spanndable = spanndable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pemberitahuan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NotifModel model = list.get(position);
        KitabModel kitabModel = KitabModel.findById(KitabModel.class, model.getIdKitab());
        int sizeFont = MyPref.getInt(context, Static.KEY_FONT);
        if(sizeFont == 0){
            holder.tvJudul.setTextSize(Static.FONT_KECIL);
            holder.tvKitab.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
            holder.tvTime.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
        }else if(sizeFont == 1){
            holder.tvJudul.setTextSize(Static.FONT_SEDANG);
            holder.tvKitab.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
            holder.tvTime.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
        }else if(sizeFont == 2){
            holder.tvJudul.setTextSize(Static.FONT_BESAR);
            holder.tvKitab.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
            holder.tvTime.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
        }else if(sizeFont == 3){
            holder.tvJudul.setTextSize(Static.FONT_SANGAT_BESAR);
            holder.tvKitab.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
            holder.tvImam.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
            holder.tvTime.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
        }

        holder.tvJudul.setText(model.getNama());
        holder.tvDeskripsi.setVisibility(View.GONE);
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
        if(getDateInMillis(model.getCreatedAt()) == null || getDateInMillis(model.getCreatedAt()).isEmpty()){
            holder.tvTime.setText("Baru saja");
        }else {
            holder.tvTime.setText(getDateInMillis(model.getCreatedAt()));
        }
        if(spanndable != null){
            String set = model.getNama().replace(spanndable, "<font color='green'>"+spanndable+"</font>");
            holder.tvJudul.setText(Html.fromHtml(set));
        }
        holder.tvKitab.setText("Kitab : "+kitabModel.getNama());
    }

    public static String getDateInMillis(String srcDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt = dateTimeFormatter.parseDateTime(srcDate);
        DateTime now = new DateTime();
        Period period = new Period(dt, now);

        PeriodFormatter formatter = null;

        if(period.getHours() <= 1 && period.getDays() <= 0){
            formatter = new PeriodFormatterBuilder()
//                .appendSeconds().appendSuffix(" seconds ago\n")
                .appendMinutes().appendSuffix(" menit")
//                    .appendHours().appendSuffix(" hours ago\n")
//                    .appendDays().appendSuffix(" days ago\n")
//                .appendWeeks().appendSuffix(" weeks ago\n")
//                .appendMonths().appendSuffix(" months ago\n")
//                .appendYears().appendSuffix(" years ago\n")
                    .printZeroNever()
                    .toFormatter();
        }else if(period.getHours() > 1 && period.getDays() <= 0){
            formatter = new PeriodFormatterBuilder()
//                .appendSeconds().appendSuffix(" seconds ago\n")
//                .appendMinutes().appendSuffix(" minutes ago\n")
                    .appendHours().appendSuffix(" jam ")
//                .appendWeeks().appendSuffix(" weeks ago\n")
//                .appendMonths().appendSuffix(" months ago\n")
//                .appendYears().appendSuffix(" years ago\n")
                    .printZeroNever()
                    .toFormatter();
        }else if(period.getDays() > 0 && period.getDays() <= 7){
            formatter = new PeriodFormatterBuilder()
//                .appendSeconds().appendSuffix(" seconds ago\n")
//                .appendMinutes().appendSuffix(" minutes ago\n")
//                    .appendHours().appendSuffix(" jam ")
                    .appendDays().appendSuffix(" hari")
//                .appendWeeks().appendSuffix(" weeks ago\n")
//                .appendMonths().appendSuffix(" months ago\n")
//                .appendYears().appendSuffix(" years ago\n")
                    .printZeroNever()
                    .toFormatter();
        }

        String elapsed;
        if(formatter != null) {
            elapsed = formatter.print(period);
        }else{
            elapsed = srcDate;
        }
        return elapsed;
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
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
