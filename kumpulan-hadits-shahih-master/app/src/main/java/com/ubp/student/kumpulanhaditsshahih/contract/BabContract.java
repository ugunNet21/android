package com.ubp.student.kumpulanhaditsshahih.contract;

import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.NotifModel;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public interface BabContract {

    interface Presenter {
        void doGetData(int id);
        ArrayList<BabModel> searchByText(String text);
        ArrayList<BabModel> getAllDataFav();
        ArrayList<BabModel> getAllDataFavSearchByText(String text);
        ArrayList<NotifModel> getAllDataNotif();
    }

    interface View {
        void doShowData(ArrayList<BabModel> list);
    }

    interface Repository{
        ArrayList<BabModel> getAllData(int id);
        ArrayList<BabModel> searchByText(String text);
        ArrayList<BabModel> getAllDataFav();
        ArrayList<BabModel> getAllDataFavSearchByText(String text);
        ArrayList<NotifModel> getAllDataNotif();
    }
}
