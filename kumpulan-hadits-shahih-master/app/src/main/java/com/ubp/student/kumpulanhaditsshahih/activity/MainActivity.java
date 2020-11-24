package com.ubp.student.kumpulanhaditsshahih.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ubp.student.kumpulanhaditsshahih.BulkData;
import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.adapter.ImamAdapter;
import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.contract.ImamContract;
import com.ubp.student.kumpulanhaditsshahih.presenter.ImamPresenter;
import com.ubp.student.kumpulanhaditsshahih.util.MyPref;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ImamContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ImamContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        Toolbar toolbar = initToolbar();
        initNavigationDrawer(toolbar);

        initPresenter();
        new BulkData().lastSeen(getApplicationContext());
        final MaterialDialog materialDialog = new MaterialDialog.Builder(MainActivity.this)
                .title("Loading..")
                .content("Memuat data..")
                .cancelable(false).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!MyPref.getBoolean(getApplicationContext(), Static.LOADED)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            materialDialog.show();
                        }
                    });
//                    https://goo.gl/tWqd5Q
                    BulkData bulkData = new BulkData();
                    bulkData.imamData(getApplicationContext());
                    bulkData.kitabData(getApplicationContext());
                    bulkData.babData(getApplicationContext());
                    bulkData.haditsData(getApplicationContext());
                    bulkData.registerFirst(getApplicationContext());
                    MyPref.putBoolean(getApplicationContext(), Static.LOADED, true);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            materialDialog.dismiss();
                            presenter.doGetData();
                        }
                    });
                }
            }
        }).start();

    }

    private void initNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initPresenter() {
        presenter = new ImamPresenter(this);
        presenter.doGetData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.main, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
//        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.favourite) {
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_favourite) {
            startActivity(new Intent(getApplicationContext(), FavouriteActivity.class));
        }else if (id == R.id.nav_sumber) {
            startActivity(new Intent(getApplicationContext(), SumberActivity.class));
        }else if (id == R.id.nav_tentang) {
            startActivity(new Intent(getApplicationContext(), TentangActivity.class));
        }else if (id == R.id.nav_notif) {
            startActivity(new Intent(getApplicationContext(), PemberitahuanActivity.class));
        }else if (id == R.id.nav_setting) {
            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void doShowData(ArrayList<ImamModel> list) {

        setAdapter(list);
    }

    private void setAdapter(ArrayList<ImamModel> list) {
//        ArrayList<ImamModel> list1 = new ArrayList<>();
//        list1.addAll(list);
//        list1.addAll(list);
//        list1.addAll(list);
//        list1.addAll(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        ImamAdapter imamAdapter = new ImamAdapter(getApplicationContext(), list, new ImamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImamModel model) {
                Intent intent = new Intent(getApplicationContext(), KitabActivity.class);
                intent.putExtra(Static.IMAM_ID, model.getId());
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(imamAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(String data){
        presenter.doGetData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}