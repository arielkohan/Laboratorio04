package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

public class ListaReservaActivity extends AppCompatActivity {

    private List<Reserva> reservas;

    private ListView listViewReservas;
    private TextView textViewSinReservas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reserva);

        textViewSinReservas = (TextView) findViewById(R.id.textViewSinReservas);
        listViewReservas = (ListView) findViewById(R.id.listViewReservas);


        reservas = Usuario.getInstance().getReservas();
        if(reservas == null || reservas.isEmpty())
            textViewSinReservas.setVisibility(View.VISIBLE);
        else{
            textViewSinReservas.setVisibility(View.GONE);
            ArrayAdapter<Reserva> adapter = new ArrayAdapter<>(ListaReservaActivity.this, android.R.layout.simple_list_item_1,reservas);
            listViewReservas.setAdapter(adapter);
        }





    }
}
