package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
        listener.busquedaFinalizada(departamentos);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);
    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {
        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        Log.i("size departamentos", todos.size() + "");
        List<Departamento> resultado = new ArrayList<Departamento>();
        int contador = 0;
        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        // TODO implementar: buscar todos los departamentos del sistema e ir chequeando las condiciones 1 a 1.
        // si cumplen las condiciones agregarlo a los resultados.

        FormBusqueda busquedaActual = busqueda[0];

        for( Departamento d: todos ){
            if(
                (busquedaActual.getPermiteFumar() == ! d.getNoFumador()) &&
                (ciudadBuscada.equals(d.getCiudad())) &&
                //(busquedaActual.getHuespedes() == )&& TODO: VER TEMA HUESPEDES
                (busquedaActual.getPrecioMaximo() == null   ||  busquedaActual.getPrecioMaximo() >= d.getPrecio()) &&
                (busquedaActual.getPrecioMinimo() == null   ||   busquedaActual.getPrecioMinimo() <= d.getPrecio())
            )   {
                        resultado.add(d);

                }
        }


        return resultado;
    }
}
