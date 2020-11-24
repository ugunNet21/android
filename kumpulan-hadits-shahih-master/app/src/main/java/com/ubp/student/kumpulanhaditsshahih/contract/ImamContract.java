package com.ubp.student.kumpulanhaditsshahih.contract;

import com.ubp.student.kumpulanhaditsshahih.clients.model.ImamModel;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public interface ImamContract {

    interface Presenter {
        void doGetData();
    }

    interface View {
        void doShowData(ArrayList<ImamModel> list);
    }

    interface Repository{
        ArrayList<ImamModel> getAllData();
    }
}
