package com.ubp.student.kumpulanhaditsshahih.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.HaditsModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.contract.HaditsContract;
import com.ubp.student.kumpulanhaditsshahih.presenter.HaditsPresenter;
import com.ubp.student.kumpulanhaditsshahih.util.MyPref;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HaditsActivity extends AppCompatActivity implements HaditsContract.View {

    @BindView(R.id.iv_fav)
    ImageView ivFav;
    @BindView(R.id.tv_bagikan)
    TextView tvBagikan;
    @BindView(R.id.tv_salin)
    TextView tvSalin;
    BabModel babModel;
    long id;
    @BindView(R.id.tv_bab)
    TextView tvBab;
    @BindView(R.id.tv_imam)
    TextView tvImam;
    @BindView(R.id.tv_kitab)
    TextView tvKitab;
    @BindView(R.id.tv_isi)
    TextView tvIsi;
    @BindView(R.id.id_ukuran)
    TextView tvUkuran;
    private HaditsContract.Presenter presenter;
    boolean isResume = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hadits, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadits);
        ButterKnife.bind(this);
        setTitle();
        backpresButton();
        getIDData();
        initFavorite();
        initPresenter();
        initOnClick();
        initTextData();
        initFontSize();
        tvUkuran.setVisibility(View.GONE);
        fontSizeInit();
    }

    void fontSizeInit() {
        int sizeFont = MyPref.getInt(getApplicationContext(), Static.KEY_FONT);
        if(sizeFont == 0){
            tvBab.setTextSize(Static.FONT_KECIL+2);
            tvKitab.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
            tvImam.setTextSize(Static.FONT_KECIL-Static.FONT_UNDER);
            tvIsi.setTextSize(Static.FONT_KECIL);
            tvBagikan.setTextSize(Static.FONT_KECIL);
            tvSalin.setTextSize(Static.FONT_KECIL);
        }else if(sizeFont == 1){
            tvBab.setTextSize(Static.FONT_SEDANG+2);
            tvKitab.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
            tvImam.setTextSize(Static.FONT_SEDANG-Static.FONT_UNDER);
            tvIsi.setTextSize(Static.FONT_SEDANG);
            tvBagikan.setTextSize(Static.FONT_SEDANG);
            tvSalin.setTextSize(Static.FONT_SEDANG);
        }else if(sizeFont == 2){
            tvBab.setTextSize(Static.FONT_BESAR+2);
            tvKitab.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
            tvImam.setTextSize(Static.FONT_BESAR-Static.FONT_UNDER);
            tvIsi.setTextSize(Static.FONT_BESAR);
            tvBagikan.setTextSize(Static.FONT_BESAR);
            tvSalin.setTextSize(Static.FONT_BESAR);
        }else if(sizeFont == 3){
            tvBab.setTextSize(Static.FONT_SANGAT_BESAR+2);
            tvKitab.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
            tvImam.setTextSize(Static.FONT_SANGAT_BESAR-Static.FONT_UNDER);
            tvIsi.setTextSize(Static.FONT_SANGAT_BESAR);
            tvBagikan.setTextSize(Static.FONT_SANGAT_BESAR);
            tvSalin.setTextSize(Static.FONT_SANGAT_BESAR);
        }
    }

    private void initFontSize() {
        MyPref.putBoolean(getApplicationContext(), Static.LATEST_HADITS, true);
        int sizeFont = MyPref.getInt(getApplicationContext(), Static.KEY_FONT);
        if(sizeFont == 0){
            tvIsi.setTextSize(Static.FONT_KECIL);
        }else if(sizeFont == 1){
            tvIsi.setTextSize(Static.FONT_SEDANG);
        }else if(sizeFont == 2){
            tvIsi.setTextSize(Static.FONT_BESAR);
        }else if(sizeFont == 3){
            tvIsi.setTextSize(Static.FONT_SANGAT_BESAR);
        }
    }

    private void initTextData() {
        KitabModel kitabModel = KitabModel.findById(KitabModel.class, babModel.getIdKitab());
        ImamModel imamModel = ImamModel.findById(ImamModel.class, kitabModel.getIdImam());
        tvBab.setText(babModel.getNama());
        tvKitab.setText(kitabModel.getNama());
        tvImam.setText(imamModel.getNamaImam());
    }

    private void getIDData() {
        id = getIntent().getLongExtra(Static.BAB_ID, 0);
        if (MyPref.getBoolean(getApplicationContext(), Static.LATEST_HADITS)) {
            id = MyPref.getInt(getApplicationContext(), Static.LATEST_HADITS_KEY);
            isResume = true;
        } else {
            MyPref.putInt(getApplicationContext(), Static.LATEST_HADITS_KEY, Integer.valueOf((int) id));
        }
        babModel = BabModel.findById(BabModel.class, id);
    }

    private void initFavorite() {
        if (babModel.isFavorite()) {
            Glide.with(getApplicationContext()).load(R.drawable.ic_action_fav_true).into(ivFav);
        } else {
            Glide.with(getApplicationContext()).load(R.drawable.ic_action_fav_false).into(ivFav);
        }
    }

    private void initPresenter() {
        presenter = new HaditsPresenter(this);
        presenter.doGetData((int) id);
    }

    private void initOnClick() {
        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (babModel.isFavorite()) {
                    Toast.makeText(getApplicationContext(), Static.REMOVE_FAVOURITE, Toast.LENGTH_SHORT).show();
                    babModel.setFavorite(false);
                    Glide.with(getApplicationContext()).load(R.drawable.ic_action_fav_false).into(ivFav);
                } else {
                    Toast.makeText(getApplicationContext(), Static.ADD_FAVOURITE, Toast.LENGTH_SHORT).show();
                    babModel.setFavorite(true);
                    Glide.with(getApplicationContext()).load(R.drawable.ic_action_fav_true).into(ivFav);
                }
                babModel.save();
            }
        });

        tvSalin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salin();
            }
        });

        tvBagikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                berbagi();
            }
        });
    }

    private void berbagi() {
        KitabModel kitabModel = KitabModel.findById(KitabModel.class, babModel.getIdKitab());
        ImamModel imamModel = ImamModel.findById(ImamModel.class, kitabModel.getIdImam());
        String textShared = "HR. " + imamModel.getNamaImam() + "\nKitab " + kitabModel.getNama() + "\nBAB : " + babModel.getNama() + "\n" + tvIsi.getText().toString() + "Aplikasi Kumpulan Hadits-Hadits Shahih oleh Universitas Buana Perjuangan Karawang";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, textShared);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void salin() {
        KitabModel kitabModel = KitabModel.findById(KitabModel.class, babModel.getIdKitab());
        ImamModel imamModel = ImamModel.findById(ImamModel.class, kitabModel.getIdImam());
        String textShared = "HR. " + imamModel.getNamaImam() + "\nKitab " + kitabModel.getNama() + "\nBAB : " + babModel.getNama() + "\n\n" + tvIsi.getText().toString() + "Aplikasi Kumpulan Hadits-Hadits Shahih oleh Universitas Buana Perjuangan Karawang";
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(babModel.getNama(), textShared);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Berhasil di salin", Toast.LENGTH_SHORT).show();
    }

    private void backpresButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setTitle() {
        setTitle("Isi Hadits");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (cleartask()) {
                    MyPref.putBoolean(getApplicationContext(), Static.LATEST_HADITS, false);
                } else {
                    MyPref.putBoolean(getApplicationContext(), Static.LATEST_HADITS, false);
                    finish();
                }
                return true;
            case R.id.action_salin:
                salin();
                return true;
            case R.id.action_bagikan:
                berbagi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void doShowData(ArrayList<HaditsModel> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (HaditsModel haditsModel :
                list) {
            stringBuilder.append(haditsModel.getIsi() + "<br><br>");
        }
        tvIsi.setText(Html.fromHtml(stringBuilder.toString()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(!cleartask()){
            MyPref.putBoolean(getApplicationContext(), Static.LATEST_HADITS, false);
            finish();
        }else {
            MyPref.putBoolean(getApplicationContext(), Static.LATEST_HADITS, true);
        }
    }

    private boolean cleartask() {
        if (isResume) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return isResume;
    }
}
