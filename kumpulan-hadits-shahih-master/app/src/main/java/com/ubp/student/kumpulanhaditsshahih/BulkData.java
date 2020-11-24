package com.ubp.student.kumpulanhaditsshahih;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.ubp.student.kumpulanhaditsshahih.clients.ApiUtils;
import com.ubp.student.kumpulanhaditsshahih.clients.data.DataService;
import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.HaditsModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.UsersModel;
import com.ubp.student.kumpulanhaditsshahih.clients.users.UsersService;
import com.ubp.student.kumpulanhaditsshahih.util.JsonLoadUtil;
import com.ubp.student.kumpulanhaditsshahih.util.MyPref;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dizzay on 6/9/2017.
 */

public class BulkData {
    
    public void imamData(Context context){
        ImamModel[] models = new Gson().fromJson(new JsonLoadUtil().loadJSONFromAsset(context, Static.IMAM_JSON_KEY), ImamModel[].class);
        for (ImamModel model :
                models) {
            SugarRecord.save(model);
        }
//        SahamLocal[] list1 = new Gson().fromJson(new JsonLoadUtil().loadJSONFromAsset(getApplicationContext()), SahamLocal[].class);
//        for (SahamLocal sahamLocal :
//                list1) {
//            SugarRecord.save(sahamLocal);
//        }
//        DataService dataService = ApiUtils.DataService(context);
//        dataService.imamData().enqueue(new Callback<ArrayList<ImamModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ImamModel>> call, Response<ArrayList<ImamModel>> response) {
//                if(response != null){
//                    for (ImamModel imamModel :
//                            response.body()) {
//                        SugarRecord.save(imamModel);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ImamModel>> call, Throwable t) {
//
//            }
//        });
    }

    public void kitabData(Context context) {
        KitabModel[] models = new Gson().fromJson(new JsonLoadUtil().loadJSONFromAsset(context, Static.KITAB_JSON_KEY), KitabModel[].class);
        for (KitabModel model :
                models) {
            SugarRecord.save(model);
        }
//        DataService dataService = ApiUtils.DataService(context);
//        dataService.kitabData().enqueue(new Callback<ArrayList<KitabModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel>> call, Response<ArrayList<com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel>> response) {
//                if (response.body() != null) {
//                    for (KitabModel kitabModel :
//                            response.body()) {
//                        SugarRecord.save(kitabModel);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel>> call, Throwable t) {
//
//            }
//        });
    }

    public void babData(Context context){
        BabModel[] models = new Gson().fromJson(new JsonLoadUtil().loadJSONFromAsset(context, Static.BAB_JSON_KEY), BabModel[].class);
        for (BabModel model :
                models) {
            SugarRecord.save(model);
        }
//        DataService dataService = ApiUtils.DataService(context);
//        dataService.babData().enqueue(new Callback<ArrayList<BabModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<BabModel>> call, Response<ArrayList<BabModel>> response) {
//                if (response.body() != null){
//                    for (BabModel babModel :
//                            response.body()) {
//                        SugarRecord.save(babModel);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<BabModel>> call, Throwable t) {
//
//            }
//        });
    }

    public void haditsData(Context context){
        HaditsModel[] models = new Gson().fromJson(new JsonLoadUtil().loadJSONFromAsset(context, Static.HADITS_JSON_KEY), HaditsModel[].class);
        for (HaditsModel model :
                models) {
            SugarRecord.save(model);
        }
//        DataService dataService = ApiUtils.DataService(context);
//        dataService.haditsData().enqueue(new Callback<ArrayList<HaditsModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<HaditsModel>> call, Response<ArrayList<HaditsModel>> response) {
//                if(response.body() != null){
//                    for (HaditsModel haditsModel :
//                            response.body()) {
//                        SugarRecord.save(haditsModel);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<HaditsModel>> call, Throwable t) {
//
//            }
//        });
    }

    public void registerFirst(final Context context){
        UsersService usersService = ApiUtils.UsersService(context);
        Map<String, String> map = new HashMap<>();
        map.put("android_version", String.valueOf(Build.VERSION.SDK_INT));
        if(getVersionApp(context) != null){
            map.put("app_version", getVersionApp(context));
        }
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        if(refreshedToken != null){
            map.put("token", refreshedToken);
        }
        usersService.first(map).enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if(response.isSuccessful() && response.body() != null){
                    SugarRecord.save(response.body());
                    MyPref.putInt(context, Static.ID_USERS, Integer.valueOf(String.valueOf(response.body().getId())));
                }
            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {

            }
        });
    }

    public void updateToken(final Context context, final String token){
        final UsersService usersService = ApiUtils.UsersService(context);
        if(MyPref.getInt(context, Static.ID_USERS) != 0){
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(MyPref.getInt(context, Static.ID_USERS)));
            map.put("token", token);
            usersService.updateToken(map).enqueue(new Callback<UsersModel>() {
                @Override
                public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                    if(response.isSuccessful()){
                        MyPref.putString(context, Static.TOKEN_KEY, token);
                    }
                }

                @Override
                public void onFailure(Call<UsersModel> call, Throwable t) {

                }
            });
        }
    }

    public void lastSeen(final Context context){
        UsersService usersService = ApiUtils.UsersService(context);
        if(MyPref.getInt(context, Static.ID_USERS) != 0){
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(MyPref.getInt(context, Static.ID_USERS)));
            usersService.lastSeen(map).enqueue(new Callback<UsersModel>() {
                @Override
                public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {

                }

                @Override
                public void onFailure(Call<UsersModel> call, Throwable t) {

                }
            });
        }
    }

    private String getVersionApp(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
