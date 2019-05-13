package ar.edu.um.programacion2_2019.hilos.servicio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ar.edu.um.programacion2_2019.hilos.entidades.Venta;


public class ProcesarVenta  {
    protected AlmacenVentas almacen;
    protected ILogeador log;
    protected ExecutorService es_imp;

    public ProcesarVenta() {}

    public ProcesarVenta(AlmacenVentas almacen, ILogeador log, ExecutorService es) {
        this.almacen = almacen;
        this.log = log;
        this.es_imp=es;
    }

    public void procesarTodo() throws InterruptedException {
    	ExecutorService es =Executors.newFixedThreadPool(100);
		
        this.log.info("Se comienza a procesar todo");
        while(!almacen.estaVacio() || !es_imp.isTerminated() ) {
        	
        	//inicio hilo
        	if(!almacen.estaVacio()) {
        	Venta venta = almacen.pop();
        	TaskVenta taskventa = new TaskVenta(venta,log);
        	es.submit(taskventa);
        	}
        	
        	      	
        	
            //fin hilo
        }
        es.shutdown();
    	es.awaitTermination(10, TimeUnit.SECONDS);
        this.log.info("Finaliza el procesamiento de todo");
    }

  
    public AlmacenVentas getAlmacen() {
        return almacen;
    }

    public void setAlmacen(AlmacenVentas almacen) {
        this.almacen = almacen;
    }

    public ILogeador getLog() {
        return log;
    }

    public void setLog(ILogeador log) {
        this.log = log;
    }
}
