package com.ubp.student.kumpulanhaditsshahih.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.adapter.KitabAdapter;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.contract.KitabContract;
import com.ubp.student.kumpulanhaditsshahih.presenter.KitabPresenter;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KitabActivity extends AppCompatActivity implements KitabContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private KitabContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitab);
        ButterKnife.bind(this);
        setTitle("Kitab");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initPresenter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) KitabActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ArrayList<KitabModel> list = presenter.searchByText(newText);
                    setToAdapter(list);
                    return false;
                }
            });
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(KitabActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
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
        presenter = new KitabPresenter(this);
        presenter.doGetData();
    }

    @Override
    public void doShowData(ArrayList<KitabModel> list) {
        setToAdapter(list);
    }

    private void setToAdapter(ArrayList<KitabModel> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        KitabAdapter adapter = new KitabAdapter(getApplicationContext(), list, new KitabAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(KitabModel model) {
                Intent intent = new Intent(getApplicationContext(), BabActivity.class);
                intent.putExtra(Static.KITAB_ID, model.getId());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
