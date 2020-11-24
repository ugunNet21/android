package com.ubp.student.kumpulanhaditsshahih.contract;

import com.ubp.student.kumpulanhaditsshahih.model.HaditsModel2;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public interface DeskripsiContract {

    interface Presenter {
        void doGetData(int hadits, int kitab, int bab);
    }

    interface View {
        void doShowData(ArrayList<HaditsModel2> list);
    }

    interface Repository{
        ArrayList<HaditsModel2> getAllData(int hadits, int kitab, int bab);
    }
}
