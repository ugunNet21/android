package com.ubp.student.kumpulanhaditsshahih.clients;

import android.content.Context;

import com.ubp.student.kumpulanhaditsshahih.clients.data.DataService;
import com.ubp.student.kumpulanhaditsshahih.clients.users.UsersService;
import com.ubp.student.kumpulanhaditsshahih.util.Static;

public class ApiUtils {

    public static String API = Static.BASE_URL_PROD;

    public static DataService DataService(Context context){
        return RetrofitClient.getClient(context, API).create(DataService.class);
    }

    public static UsersService UsersService(Context context){
        return RetrofitClient.getClient(context, API).create(UsersService.class);
    }

}
