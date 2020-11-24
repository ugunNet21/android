package com.ubp.student.kumpulanhaditsshahih.repository;

import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.contract.KitabContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class KitabRepository implements KitabContract.Repository {

    @Override
    public ArrayList<KitabModel> getAllData() {
        List<KitabModel> data = KitabModel.listAll(KitabModel.class, "nama asc");
        ArrayList<KitabModel> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }

    @Override
    public ArrayList<KitabModel> searchByText(String text) {
        List<KitabModel> data = KitabModel.findWithQuery(KitabModel.class, "select * from KITAB_MODEL where nama like '%"+text+"%'");
        ArrayList<KitabModel> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }

}
