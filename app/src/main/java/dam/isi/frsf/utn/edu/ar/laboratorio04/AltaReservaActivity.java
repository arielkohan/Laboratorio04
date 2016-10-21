package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

public class AltaReservaActivity extends AppCompatActivity {
    private TextView textViewDepartamento;
//    private TextView textViewPrecio;
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
        usuario = (Usuario) i.getSerializableExtra("usuario");
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

                    Reserva reserva = new Reserva(fechaInicio, fechaFin, departamento , usuario);
                    usuario.agregarReserva(reserva);
                    departamento.agregarReserva(reserva);
                    Toast.makeText(AltaReservaActivity.this, "Reservado.", Toast.LENGTH_LONG).show();
                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
