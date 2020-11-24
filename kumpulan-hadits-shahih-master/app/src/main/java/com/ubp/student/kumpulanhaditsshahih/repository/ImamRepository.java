package com.ubp.student.kumpulanhaditsshahih.repository;

import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.contract.ImamContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class ImamRepository implements ImamContract.Repository {

    @Override
    public ArrayList<ImamModel> getAllData() {
        List<ImamModel> data = KitabModel.listAll(ImamModel.class);
        ArrayList<ImamModel> list = new ArrayList<>();
        list.addAll(data);
        return list;
    }

}
