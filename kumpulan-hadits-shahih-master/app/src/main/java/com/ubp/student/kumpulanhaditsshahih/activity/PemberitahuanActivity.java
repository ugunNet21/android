package com.ubp.student.kumpulanhaditsshahih.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.adapter.PemberitahuanAdapter;
import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.NotifModel;
import com.ubp.student.kumpulanhaditsshahih.contract.BabContract;
import com.ubp.student.kumpulanhaditsshahih.presenter.BabPresenter;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tr.xip.errorview.ErrorView;

public class PemberitahuanActivity extends AppCompatActivity implements BabContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.error_view)
    ErrorView errorView;
    private BabPresenter presenter;
    long id;
    PemberitahuanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemberitahuan);
        ButterKnife.bind(this);
        setTitle("Pemberitahuan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initPresenter();
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

    private void initPresenter() {
        presenter = new BabPresenter(this);
        setToAdapter(presenter.getAllDataNotif(), null);

    }

    private void setToAdapter(ArrayList<NotifModel> list, String spanndable) {
        if (list.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            adapter = new PemberitahuanAdapter(getApplicationContext(), list, new PemberitahuanAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(NotifModel model) {
                    Intent intent = new Intent(getApplicationContext(), HaditsActivity.class);
                    intent.putExtra(Static.BAB_ID, model.getId());
                    startActivity(intent);
                }
            }, new PemberitahuanAdapter.OnItemFavClickListener() {
                @Override
                public void onItemClick(NotifModel model) {

                }
            }, spanndable);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            errorView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo_app_big);
            errorView.setImageBitmap(Bitmap.createScaledBitmap(bm, 300, 300, false));
            errorView.setVisibility(View.VISIBLE);
            errorView.setTitle("Anda belum mempunyai pemberitahuan.");
            errorView.showSubtitle(false);
            errorView.showRetryButton(false);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void doShowData(ArrayList<BabModel> list) {

    }
}
