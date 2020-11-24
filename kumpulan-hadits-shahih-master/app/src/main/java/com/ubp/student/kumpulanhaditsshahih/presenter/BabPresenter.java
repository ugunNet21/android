package com.ubp.student.kumpulanhaditsshahih.presenter;

import com.ubp.student.kumpulanhaditsshahih.clients.model.BabModel;
import com.ubp.student.kumpulanhaditsshahih.clients.model.NotifModel;
import com.ubp.student.kumpulanhaditsshahih.contract.BabContract;
import com.ubp.student.kumpulanhaditsshahih.repository.BabRepository;

import java.util.ArrayList;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class BabPresenter implements BabContract.Presenter {

    private BabContract.View view;
    private BabContract.Repository repository;

    public BabPresenter(BabContract.View view) {
        this.view = view;
        this.repository = new BabRepository();
    }

    @Override
    public void doGetData(int id) {
        view.doShowData(repository.getAllData(id));
    }

    @Override
    public ArrayList<BabModel> searchByText(String text) {
        return repository.searchByText(text);
    }

    @Override
    public ArrayList<BabModel> getAllDataFav() {
        return repository.getAllDataFav();
    }

    @Override
    public ArrayList<BabModel> getAllDataFavSearchByText(String text) {
        return repository.getAllDataFavSearchByText(text);
    }

    @Override
    public ArrayList<NotifModel> getAllDataNotif() {
        return repository.getAllDataNotif();
    }
}
