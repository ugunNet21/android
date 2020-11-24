package com.ubp.student.kumpulanhaditsshahih.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.contract.DeskripsiContract;
import com.ubp.student.kumpulanhaditsshahih.model.BabModel2;
import com.ubp.student.kumpulanhaditsshahih.model.HaditsModel2;
import com.ubp.student.kumpulanhaditsshahih.presenter.DeskripsiPresenter;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeskripsiActivity extends AppCompatActivity implements DeskripsiContract.View {

    @BindView(R.id.iv_thumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.tv_judul)
    TextView tvJudul;
    @BindView(R.id.tv_isi)
    TextView tvIsi;
    private DeskripsiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);
        ButterKnife.bind(this);
        setActionBar();
//        initPresenter();
//        setTitle();

    }

    private void initPresenter() {
        presenter = new DeskripsiPresenter(this);
    }

    private void setTitle() {
        BabModel2 babModel2 = (BabModel2) getIntent().getSerializableExtra(Static.BAB_MODEL);
        if (babModel2 != null) {
            setTitle("BAB : " + babModel2.getBab());
            tvJudul.setText(babModel2.getBab());
            presenter.doGetData(babModel2.getHaditsKode(), babModel2.getKitabKode(), babModel2.getBabKode());
        }
    }

    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void doShowData(ArrayList<HaditsModel2> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for(HaditsModel2 haditsModel2 :list){
            stringBuilder.append(haditsModel2.getHadits()+"\n\n");
        }
        tvIsi.setText(stringBuilder.toString());
    }
}
