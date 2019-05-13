/**
 * 
 */
package ar.edu.um.programacion2_2019.hilos.servicio;

/**
 * @author Camila
 *
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Servidor extends Conexion {
	
	protected ExecutorService es1;
	protected AlmacenVentas almacen;
	
	public Servidor(AlmacenVentas av) throws IOException{super("servidor"); this.almacen=av;} //Se usa el constructor para servidor de Conexion

    
	
	
	 public ExecutorService importardecliente() throws InterruptedException, IOException {
		 
	    	es1 =Executors.newFixedThreadPool(100);
	    	
	    	SocketAddress localaddr = new InetSocketAddress("127.0.0.1",8040);
	    	ss.bind(localaddr);
	            
	            
	            while (  (cs = ss.accept())!=null) {
	            	
	            	//inicia hilo
	            	
	            	
	            	TaskAtenderCliente taskatendercliente = new TaskAtenderCliente(cs,ss,almacen);
	            	es1.submit(taskatendercliente);
	            	
	            	
	            	
	                
	                
	                //termina hilo
	            }
	            
	            //br.close();
	            es1.shutdown();
	            es1.awaitTermination(60, TimeUnit.SECONDS);
	           
	        
			return es1;
	       
	    }
	
	
	
	
	/*
	public void startServer()//Método para iniciar el servidor
    {
        try
        {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");

            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            while((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                //Se muestra por pantalla el mensaje recibido
                System.out.println(mensajeServidor);
            }

            System.out.println("Fin de la conexión");

            ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
	
*/
}
