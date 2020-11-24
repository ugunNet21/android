package com.ubp.student.kumpulanhaditsshahih.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.model.BabModel2;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/10/2017.
 */

public class BabGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<BabModel2> list;

    public BabGridAdapter(Context context, ArrayList<BabModel2> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BabModel2 getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_bab_grid, null);
        }

//        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView textView = (TextView) convertView.findViewById(R.id.tv_text);

        BabModel2 babModel2 = list.get(position);
        textView.setText(babModel2.getBab());
        return convertView;
    }
}
