package com.ubp.student.kumpulanhaditsshahih.clients.users;

import com.ubp.student.kumpulanhaditsshahih.clients.model.UsersModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dizzay on 7/20/2017.
 */

public interface UsersService {

    @FormUrlEncoded
    @POST("users/first")
    Call<UsersModel> first(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("users/update-token")
    Call<UsersModel> updateToken(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("users/last-seen")
    Call<UsersModel> lastSeen(@FieldMap Map<String, String> map);




}
