package com.user.songratingsystem.createChannel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.user.songratingsystem.BuildConfig;

public class CreateChannel {
    Context context;
    public final static String Notify_1 = "Notification 1";
    public final static String Notify_2 = "Notification 2";

    public CreateChannel(Context context) {
        this.context = context;
    }

    public void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notify1 = new NotificationChannel(Notify_1,"Notify_1", NotificationManager.IMPORTANCE_HIGH);
            notify1.setDescription("This is Notification 1");

            NotificationChannel notify2 = new NotificationChannel(Notify_2,"Notify_2",NotificationManager.IMPORTANCE_LOW);
            notify2.setDescription("This is Notification 2");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notify1);
            manager.createNotificationChannel(notify2);

        }
    }
}
