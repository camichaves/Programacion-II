package ar.edu.um.programacion2_2019.hilos.principal;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import ar.edu.um.programacion2_2019.hilos.servicio.*;

public class Arranque {
    public static void main(String[] args) throws InterruptedException, IOException {
        Arranque a = new Arranque();
        a.arrancar();
    }

    public void arrancar() throws InterruptedException, IOException {
    	
    	ILogeador log = new LogeadorConsola();
    	log.info("Arrancó la aplicación");
    	AlmacenVentas almacen = new AlmacenVentas(log);
    	
    	//thread
    	Servidor serv = new Servidor(almacen); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        
        serv.importardecliente(); //Se inicia el servidor
        //thread
        
        
        
        
        String datos = "C:\\Users\\Camila\\eclipse-workspace\\Pruebas\\src\\venta_tarj_cred\\intvtas.txt";
        ImportarVenta imp = new ImportarVenta(datos,log,almacen);
        imp.importarVentas();
        ExecutorService es=imp.GetEs();
        ProcesarVenta procesar = new ProcesarVenta(almacen,log,es);
        
        procesar.procesarTodo();
        
        log.info("Terminó la aplicación");
    }
}
