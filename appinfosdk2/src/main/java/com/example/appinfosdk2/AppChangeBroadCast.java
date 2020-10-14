package com.example.appinfosdk2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;




public class AppChangeBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            showNotification(context, "package: ", packageName + " Installed", 0);
        }
        else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            showNotification(context, "Package: ", packageName + " Uninstalled", 1);
        }
    }

    NotificationManager notificationManager;
    NotificationCompat.Builder notification;

    public void showNotification(Context context, String title, String description, int id) {

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("1", title, importance);
            channel.setVibrationPattern(new long[]{1000, 1000});
            notificationManager.createNotificationChannel(channel);
        }

        notification = new NotificationCompat.Builder(context, "Tessaract");
        //notification.setColor(context.getResources().getColor(R.color.colorPrimary));
        notification.setContentTitle(title);
        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(description));
        notification.setContentText(description);
        //notification.setSmallIcon(R.drawable.ic_icon);
        notification.setSound(soundUri);
        notification.setVibrate(new long[]{1000, 1000});
        notification.setChannelId("1");
        notification.setAutoCancel(true);
        notificationManager.notify(id, notification.build());
    }
}
