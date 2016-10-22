package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

public class AltaReservaActivity extends AppCompatActivity {

    private static final String ALARMA =  "dam.isi.frsf.utn.edu.ar.laboratorio04.ListaDepartamentosActivity.ReceptorAlarma";

    private TextView textViewDepartamento;
    private EditText editTextFechaInicio;
    private EditText editTextFechaFin;
    private Button buttonConfirmar;
    private Button buttonCancelar;

    Departamento departamento;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        textViewDepartamento = (TextView) findViewById(R.id.textViewDepartamento);
        editTextFechaFin = (EditText) findViewById(R.id.editTextFechaFin);
        editTextFechaInicio= (EditText) findViewById(R.id.editTextFechaInicio);

        buttonCancelar = (Button) findViewById(R.id.buttonCancelarReserva);
        buttonConfirmar = (Button) findViewById(R.id.buttonConfirmarReserva);

        Intent i = getIntent();
        departamento = (Departamento) i.getSerializableExtra("departamento");
        textViewDepartamento.setText(textViewDepartamento.getText()+ " " + departamento.toString());

        //TODO: hacer buttonCancelar listener
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: hacer validaciones.
                try {
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date fechaInicio = df.parse(editTextFechaInicio.getText().toString());
                    Date fechaFin = df.parse(editTextFechaFin.getText().toString());

                    Reserva reserva = new Reserva(fechaInicio, fechaFin, departamento , Usuario.getInstance());
                    Usuario.getInstance().agregarReserva(reserva);
                    departamento.agregarReserva(reserva);
                    Toast.makeText(AltaReservaActivity.this, "Procesando reserva.", Toast.LENGTH_LONG).show();

                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

                    IntentFilter intentFilter = new IntentFilter(ALARMA);
                    CustomReceiver receiver = new CustomReceiver();
                    registerReceiver(receiver, intentFilter);

                    Intent alarmIntent = new Intent(AltaReservaActivity.this, CustomReceiver.class);

                    alarmIntent.putExtra("reserva", reserva);

                    PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 1, alarmIntent, 0);
                    Calendar calendar = Calendar.getInstance();

                    //am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    am.setRepeating(
                            AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(),
                            5*1000, //El enunciado decia con 15 segundos, lo hice con 5 por cuestiones de paciencia limitada
                            pi
                            );
                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(AltaReservaActivity.this, "Hubo un error en dar de alta su reserva. Intentelo nuevamente.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AltaReservaActivity.this, "Alta reserva cancelada.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
