package com.ubp.student.kumpulanhaditsshahih.repository;

import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.NotifModel;
import com.ubp.student.kumpulanhaditsshahih.contract.BabContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class BabRepository implements BabContract.Repository {

    @Override
    public ArrayList<BabModel> getAllData(int id) {
        List<BabModel> list = BabModel.find(BabModel.class, "id_kitab = ?", new String[]{String.valueOf(id)}, null, "nama asc", null);
        ArrayList<BabModel> models = new ArrayList<>();
        models.addAll(list);
        return models;
    }

    @Override
    public ArrayList<BabModel> searchByText(String text) {
        List<BabModel> data = KitabModel.findWithQuery(BabModel.class, "select * from BAB_MODEL where nama like '%"+text+"%' order by nama asc");
        ArrayList<BabModel> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }

    @Override
    public ArrayList<BabModel> getAllDataFav() {
        List<BabModel> list = BabModel.findWithQuery(BabModel.class, "select * from BAB_MODEL where IS_FAVORITE = 1 order by nama asc");
        ArrayList<BabModel> models = new ArrayList<>();
        models.addAll(list);
        return models;
    }

    @Override
    public ArrayList<BabModel> getAllDataFavSearchByText(String text) {
        List<BabModel> data = KitabModel.findWithQuery(BabModel.class, "select * from BAB_MODEL where nama like '%"+text+"%' and IS_FAVORITE = true order by nama asc");
        ArrayList<BabModel> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }

    @Override
    public ArrayList<NotifModel> getAllDataNotif() {
        List<NotifModel> data = KitabModel.listAll(NotifModel.class, "created_at desc");
        ArrayList<NotifModel> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }
}
