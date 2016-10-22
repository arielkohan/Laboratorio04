package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

public class NotificatorBroadcastReceiver extends BroadcastReceiver {
    public NotificatorBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String  title = intent.getStringExtra("title"),
                text = intent.getStringExtra("text");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String ringtoneString = sharedPreferences.getString("pref_ringtone", "DEFAULT_SOUND");
        Uri ringtoneUri = Uri.parse(ringtoneString);

        int icon = intent.getIntExtra("icon", R.drawable.ic_menu_slideshow);

        sendNotification(context, title, text, ringtoneUri, icon);

    }

    private void sendNotification(Context context, String title, String text, Uri ringtoneUri, int icon) {

        Intent notificationIntent = new Intent(context, ListaReservaActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setSound(ringtoneUri);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1, builder.build());
    }
}
