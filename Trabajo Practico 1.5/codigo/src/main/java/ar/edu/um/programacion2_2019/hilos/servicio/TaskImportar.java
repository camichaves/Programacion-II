package ar.edu.um.programacion2_2019.hilos.servicio;

import java.util.concurrent.Callable;

import ar.edu.um.programacion2_2019.hilos.entidades.TarjetaCredito;
import ar.edu.um.programacion2_2019.hilos.entidades.Venta;

public class TaskImportar implements Callable<Boolean> {
	
	public TaskImportar(String linea2, ILogeador log2, AlmacenVentas almacen) {
		// TODO Auto-generated constructor stub
		
		this.linea=linea2;
		this.log=log2;
		this.almacen=almacen;
	}
	
	String linea;
	ILogeador log;
	AlmacenVentas almacen;
	
	public Boolean call() throws Exception{
	

        log.info("Recuperado del archivo "+linea);
        String[] datos = linea.split(",");
        TarjetaCredito tarjeta = new TarjetaCredito(datos[0],datos[1],datos[2],datos[3]);
        Venta venta = new Venta(datos[4], Float.valueOf(datos[5]), tarjeta);
        almacen.push(venta);
        return true;
	}
	
	
}
	


