package com.ubp.student.kumpulanhaditsshahih.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.ubp.student.kumpulanhaditsshahih.BulkData;

/**
 * Created by Dizzay on 7/22/2017.
 */

public class InstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "IserJekService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        if(refreshedToken != null){
            sendRegistrationToServer(refreshedToken);
        }
    }
    private void sendRegistrationToServer(final String token) {
        new BulkData().updateToken(getApplicationContext(), token);
    }

}
