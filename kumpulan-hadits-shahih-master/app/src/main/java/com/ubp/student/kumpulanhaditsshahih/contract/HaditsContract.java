package com.ubp.student.kumpulanhaditsshahih.contract;

import com.ubp.student.kumpulanhaditsshahih.clients.model.HaditsModel;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public interface HaditsContract {

    interface Presenter {
        void doGetData(int id);
    }

    interface View {
        void doShowData(ArrayList<HaditsModel> list);
    }

    interface Repository{
        ArrayList<HaditsModel> getAllData(int id);
    }
}
