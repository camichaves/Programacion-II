package ar.edu.um.programacion2_2019.hilos.servicio;

import ar.edu.um.programacion2_2019.hilos.entidades.Venta;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AlmacenVentas {
    protected BlockingQueue<Venta> cola;
    protected ILogeador log;

    public AlmacenVentas() {
        this.cola = new LinkedBlockingQueue<Venta>();
    }

    public AlmacenVentas(ILogeador log) {
        this.log = log;
        this.cola = new LinkedBlockingQueue<Venta>();
    }

    public void push(Venta venta) {
        try {
            this.cola.put(venta);
            this.log.info("Almacenada venta: "+venta);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Venta pop() {
        Venta retorno = null;
        try {
        	
            retorno=this.cola.take();
        	
            this.log.info("Recuperada venta: "+retorno);
        
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public boolean estaVacio() {
        if(this.cola.isEmpty()) {
            return true;
        }
        return false;
    }

    public ILogeador getLog() {
        return log;
    }

    public void setLog(ILogeador log) {
        this.log = log;
    }
    public int size () {
    	return this.cola.size();
    }
}
