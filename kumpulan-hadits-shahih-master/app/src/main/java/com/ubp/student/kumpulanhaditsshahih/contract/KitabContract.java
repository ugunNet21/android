package com.ubp.student.kumpulanhaditsshahih.contract;

import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public interface KitabContract {

    interface Presenter {
        void doGetData();
        ArrayList<KitabModel> searchByText(String text);
    }

    interface View {
        void doShowData(ArrayList<KitabModel> list);
    }

    interface Repository{
        ArrayList<KitabModel> getAllData();
        ArrayList<KitabModel> searchByText(String text);
    }
}
