package com.ubp.student.kumpulanhaditsshahih.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.adapter.BabAdapter;
import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.contract.BabContract;
import com.ubp.student.kumpulanhaditsshahih.presenter.BabPresenter;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tr.xip.errorview.ErrorView;

public class FavouriteActivity extends AppCompatActivity implements BabContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.error_view)
    ErrorView errorView;
    private BabPresenter presenter;
    BabAdapter babAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);
        setTitle("Favorit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new BabPresenter(this);
        setToAdapter(presenter.getAllDataFav(), null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) FavouriteActivity.this.getSystemService(Context.SEARCH_SERVICE);

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
                    ArrayList<BabModel> list = presenter.searchByText(newText);
                    setToAdapter(list, newText);
                    return false;
                }
            });
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(FavouriteActivity.this.getComponentName()));
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

    private void setToAdapter(ArrayList<BabModel> list, String spanndable) {
        if(list.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            babAdapter = new BabAdapter(getApplicationContext(), list, new BabAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BabModel model) {
                    Intent intent = new Intent(getApplicationContext(), HaditsActivity.class);
                    intent.putExtra(Static.BAB_ID, model.getId());
                    startActivity(intent);
                }
            }, new BabAdapter.OnItemFavClickListener() {
                @Override
                public void onItemClick(BabModel model) {
                    if (model.isFavorite()) {
                        Toast.makeText(getApplicationContext(), Static.REMOVE_FAVOURITE, Toast.LENGTH_SHORT).show();
                        BabModel babModel = BabModel.findById(BabModel.class, model.getId());
                        babModel.setFavorite(false);
                        babModel.save();
                        model.setFavorite(false);
                    } else {
                        Toast.makeText(getApplicationContext(), Static.ADD_FAVOURITE, Toast.LENGTH_SHORT).show();
                        BabModel babModel = BabModel.findById(BabModel.class, model.getId());
                        babModel.setFavorite(true);
                        babModel.save();
                        model.setFavorite(true);
                    }
                    babAdapter.notifyDataSetChanged();
                }
            }, spanndable);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(babAdapter);
            errorView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else{
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo_app_big);
            errorView.setImageBitmap(Bitmap.createScaledBitmap(bm, 300, 300, false));
            errorView.setVisibility(View.VISIBLE);
            errorView.setTitle("Anda belum menambahkan favorit.");
            errorView.showSubtitle(false);
            errorView.showRetryButton(false);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void doShowData(ArrayList<BabModel> list) {

    }
}
