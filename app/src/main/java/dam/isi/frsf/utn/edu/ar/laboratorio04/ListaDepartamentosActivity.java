package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

public class ListaDepartamentosActivity extends AppCompatActivity implements BusquedaFinalizadaListener<Departamento> {

    private TextView tvEstadoBusqueda;
    private ListView listViewAlojamientos;
    private DepartamentoAdapter departamentosAdapter;
    private List<Departamento> listaDepartamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamientos);
        listaDepartamentos = new ArrayList<>();
        listViewAlojamientos = (ListView ) findViewById(R.id.listaAlojamientos);
        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Boolean esBusqueda = intent.getExtras().getBoolean("esBusqueda");
        listaDepartamentos =Departamento.getAlojamientosDisponibles();

        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this, listaDepartamentos);
        listViewAlojamientos.setAdapter(departamentosAdapter);

        if(esBusqueda){
            FormBusqueda fb = (FormBusqueda ) intent.getSerializableExtra("frmBusqueda");
            new BuscarDepartamentosTask(ListaDepartamentosActivity.this).execute(fb);
            tvEstadoBusqueda.setText("Buscando....");
            tvEstadoBusqueda.setVisibility(View.VISIBLE);
        }else{
            tvEstadoBusqueda.setVisibility(View.GONE);
        }
    }

    @Override
    public void busquedaFinalizada(List<Departamento> listaDepartamento) {
        //TODO implementar


        Log.i("TAMAÑO LISTA", listaDepartamento.size() + "");
        if(listaDepartamento.size() > 0){
            //listaDepartamentos.clear();

            //listaDepartamentos.addAll(listaDepartamento);
            //listaDepartamentos.add(listaDepartamento.get(0));
            departamentosAdapter.clear();
            Log.i("LISTA DESPUES DE CLEAR:",departamentosAdapter.getCount() +"");
            departamentosAdapter.addAll(listaDepartamento);
            Log.i("LISTA DESPUES D ADDALL:",departamentosAdapter.getCount() +"");
            //departamentosAdapter.add(listaDepartamento.get(0));
            tvEstadoBusqueda.setVisibility(View.INVISIBLE);

            departamentosAdapter.notifyDataSetChanged();
            listViewAlojamientos.setVisibility(View.VISIBLE);
            Log.i("Primer elemento",departamentosAdapter.getItem(1).toString() +"");

        } else{
            departamentosAdapter.clear();
            tvEstadoBusqueda.setVisibility(View.VISIBLE);
            tvEstadoBusqueda.setText("No hay departamentos con estas caracteristicas"); //TODO: SACAR A RES VALUES STRING
        }

    }

    @Override
    public void busquedaActualizada(String msg) {
        tvEstadoBusqueda.setText(" Buscando..."+msg);
    }

}
