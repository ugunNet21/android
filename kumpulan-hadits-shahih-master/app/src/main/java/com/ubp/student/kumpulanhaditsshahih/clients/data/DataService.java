package com.ubp.student.kumpulanhaditsshahih.clients.data;

import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.HaditsModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Dizzay on 7/20/2017.
 */

public interface DataService {

    @POST("imam/all")
    Call<ArrayList<ImamModel>> imamData();
    @POST("kitab/all")
    Call<ArrayList<KitabModel>> kitabData();
    @POST("bab/all")
    Call<ArrayList<BabModel>> babData();
    @POST("hadits/all")
    Call<ArrayList<HaditsModel>> haditsData();



}
