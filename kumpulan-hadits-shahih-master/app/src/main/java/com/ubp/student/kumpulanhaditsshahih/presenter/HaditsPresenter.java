package com.ubp.student.kumpulanhaditsshahih.presenter;

import com.ubp.student.kumpulanhaditsshahih.contract.HaditsContract;
import com.ubp.student.kumpulanhaditsshahih.repository.HaditsRepository;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class HaditsPresenter implements HaditsContract.Presenter {

    private HaditsContract.View view;
    private HaditsContract.Repository repository;

    public HaditsPresenter(HaditsContract.View view) {
        this.view = view;
        this.repository = new HaditsRepository();
    }

    @Override
    public void doGetData(int id) {
        view.doShowData(repository.getAllData(id));
    }
}
