package com.ubp.student.kumpulanhaditsshahih.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.orm.SugarRecord;
import com.ubp.student.kumpulanhaditsshahih.R;
import com.ubp.student.kumpulanhaditsshahih.activity.PemberitahuanActivity;
import com.ubp.student.kumpulanhaditsshahih.clients.model.NotifModel;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dizzay on 7/22/2017.
 */

public class MessageService extends FirebaseMessagingService {

    private static final String TAG = "Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().get("data"));
            String json = remoteMessage.getData().get("data");
            NotifModel notifModel = new Gson().fromJson(json, NotifModel.class);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            notifModel.setCreatedAt(reportDate);
            SugarRecord.save(notifModel);
            sendNotification(notifModel);
        }

    }

    public void sendNotification(NotifModel notifModel) {
        int requestID = (int) System.currentTimeMillis();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ubp_logo)
                        .setContentTitle("Hadits Hari Ini")
                        .setContentText(notifModel.getNama());
        Intent resultIntent = new Intent(this, PemberitahuanActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        requestID,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1001, mBuilder.build());
    }

}
