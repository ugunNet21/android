package com.ubp.student.kumpulanhaditsshahih.repository;

import com.ubp.student.kumpulanhaditsshahih.clients.model.HaditsModel;
import com.ubp.student.kumpulanhaditsshahih.contract.HaditsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class HaditsRepository implements HaditsContract.Repository {

    @Override
    public ArrayList<HaditsModel> getAllData(int id) {
//        List<HaditsModel> list = HaditsModel2.findWithQuery(HaditsModel.class, "select * from BAB_MODEL where HADITS_KODE = ? and KITAB_KODE = ?", String.valueOf(hadits), String.valueOf(kitab));
//        List<HaditsModel> list = HaditsModel.listAll(HaditsModel.class);
        List<HaditsModel> list = HaditsModel.find(HaditsModel.class, "id_bab = ?", new String[]{String.valueOf(id)});
        ArrayList<HaditsModel> models = new ArrayList<>();
        models.addAll(list);
        return models;
    }
}
