package com.ubp.student.kumpulanhaditsshahih.repository;

import com.ubp.student.kumpulanhaditsshahih.contract.DeskripsiContract;
import com.ubp.student.kumpulanhaditsshahih.model.HaditsModel2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class DeskripsiRepository implements DeskripsiContract.Repository {

    @Override
    public ArrayList<HaditsModel2> getAllData(int hadits, int kitab, int bab) {
        List<HaditsModel2> list = HaditsModel2.findWithQuery(HaditsModel2.class, "select * from HADITS_MODEL where HADITS_KODE = ? and KITAB_KODE = ? and BAB_KODE = ?", String.valueOf(hadits), String.valueOf(kitab), String.valueOf(bab));
        ArrayList<HaditsModel2> models = new ArrayList<>();
        models.addAll(list);
        return models;
    }
}
