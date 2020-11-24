package com.ubp.student.kumpulanhaditsshahih;

import com.google.firebase.messaging.FirebaseMessaging;
import com.orm.SugarContext;

/**
 * Created by Dizzay on 6/9/2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
