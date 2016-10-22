package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by ari on 21/10/2016.
 */


public class CustomReceiver extends BroadcastReceiver{

    private Context mContext = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (mContext == null) this.mContext = context;

        Long millis = System.currentTimeMillis();

        if(millis % 3 == 0){

            Reserva reserva = (Reserva) intent.getSerializableExtra("reserva");
            String descripcionReserva = reserva.toString();

//            Intent notificationIntent = new Intent(context, ListaReservaActivity.class);
//            PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            /*SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String ringtoneString = sharedPreferences.getString("pref_ringtone", "DEFAULT_SOUND");
            Uri ringtoneUri = Uri.parse(ringtoneString);*/

//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                    .setContentTitle("Reserva Confirmada")
//                    .setContentText(descripcionReserva)
//                    .setSmallIcon(R.drawable.ic_menu_gallery)
//                    .setContentIntent(pi)
//                    .setAutoCancel(true)
//                    .setSound(ringtoneUri);

//            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//            manager.notify(1, builder.build());
            Intent broadcastIntent = new Intent("dam.isi.frsf.utn.edu.ar.laboratorio04.notificatorReceiver");
            broadcastIntent.putExtra("title", "Reserva Confirmada.");
            broadcastIntent.putExtra("text", descripcionReserva);
            broadcastIntent.putExtra("icon", R.drawable.ic_menu_gallery);
            context.sendBroadcast(broadcastIntent);

            cancelarAlarma();
        }
    }

    private void cancelarAlarma() {
        Intent i = new Intent(this.mContext, CustomReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 1,i, 0);
        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);

    }
}
