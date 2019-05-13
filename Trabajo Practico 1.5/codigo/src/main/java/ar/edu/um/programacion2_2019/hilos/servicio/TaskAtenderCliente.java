/**
 * 
 */
package ar.edu.um.programacion2_2019.hilos.servicio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Camila
 *
 */
public class TaskAtenderCliente implements Callable<Boolean> {

	protected Socket soc;
	protected ServerSocket ss;
	protected ILogeador log;
	protected AlmacenVentas almacen;
	
	public TaskAtenderCliente(Socket cs,ServerSocket ss, AlmacenVentas av) {
		// TODO Auto-generated constructor stub
		
		this.soc=cs;
		this.ss=ss;
		this.almacen=av;
		
	}
	
	public Boolean call() throws Exception{
	
	System.out.println("Cliente en línea");

    //Se obtiene el flujo de salida del cliente para enviarle mensajes
	
    DataOutputStream salidaCliente = new DataOutputStream(soc.getOutputStream());

    //Se le envía un mensaje al cliente usando su flujo de salida
    salidaCliente.writeUTF("Petición recibida y aceptada");

    //Se obtiene el flujo entrante desde el cliente
    BufferedReader entrada = new BufferedReader(new InputStreamReader(soc.getInputStream()));
    String mensajeServidor=null;

    ExecutorService es1 =Executors.newFixedThreadPool(100);
    while((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
    {
        //Se muestra por pantalla el mensaje recibido
        //System.out.println(mensajeServidor);

    	TaskImportar taskimportar = new TaskImportar(mensajeServidor,log, almacen);
    	es1.submit(taskimportar);
    }

    System.out.println("Fin de la conexión");

    ss.close();//Se finaliza la conexión con el cliente
	return true;
}



}


