package ar.edu.um.programacion2_2019.hilos.servicio;

import java.util.concurrent.Callable;

import ar.edu.um.programacion2_2019.hilos.entidades.Venta;

public class TaskVenta implements Callable<Boolean>{
	
	Venta venta;
	ILogeador log;
	
	public TaskVenta(Venta venta, ILogeador log) {
		
	this.venta=venta;
	this.log=log;
	
		// TODO Auto-generated constructor stub
	}

	public Boolean call() throws Exception{
		
		IProcesador procesador;
        boolean verificarT = false;
        boolean autorizar = false;
    	
    	
        procesador = ProcesadorFactory.getProcesador(venta,this.log);
        verificarT = procesador.verificarTarjeta();
        if (verificarT) {
            autorizar=procesador.autorizar();
            
        }
        if(verificarT && autorizar) {
            procesador.capturar();
            
            procesador.finalizar();
            
        }
		
		return true;
	}
}
