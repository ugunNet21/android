package com.ubp.student.kumpulanhaditsshahih.presenter;

import com.ubp.student.kumpulanhaditsshahih.clients.model.KitabModel;
import com.ubp.student.kumpulanhaditsshahih.contract.KitabContract;
import com.ubp.student.kumpulanhaditsshahih.repository.KitabRepository;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class KitabPresenter implements KitabContract.Presenter {

    private KitabContract.View view;
    private KitabContract.Repository repository;

    public KitabPresenter(KitabContract.View view) {
        this.view = view;
        this.repository = new KitabRepository();

    }

    @Override
    public void doGetData() {
        view.doShowData(repository.getAllData());
    }

    @Override
    public ArrayList<KitabModel> searchByText(String text) {
        return repository.searchByText(text);
    }
}
