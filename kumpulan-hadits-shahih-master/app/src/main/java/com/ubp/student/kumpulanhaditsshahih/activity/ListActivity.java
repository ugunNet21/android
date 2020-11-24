package com.ubp.student.kumpulanhaditsshahih.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.adapter.BabGridAdapter;
import com.ubp.student.kumpulanhaditsshahih.model.BabModel2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.gridView)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        ArrayList<BabModel2> models = new ArrayList<>();
        BabModel2 babModel2 = new BabModel2(1, 1, 1, "Al-Bukhori");
        models.add(babModel2);
        babModel2 = new BabModel2(1, 1, 1, "Muslim");
        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd xxxxxxxxxxax");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd vavav");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd asdasdas");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd aewqeeq");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd qasd1231");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd asdasxzcxzca123");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd 43636dgfd");
//        models.add(babModel2);
//        babModel2 = new BabModel2(1, 1, 1, "asdasdasd axzckmzk");
//        models.add(babModel2);

        BabGridAdapter babGridAdapter = new BabGridAdapter(getApplicationContext(), models);
        gridView.setAdapter(babGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
