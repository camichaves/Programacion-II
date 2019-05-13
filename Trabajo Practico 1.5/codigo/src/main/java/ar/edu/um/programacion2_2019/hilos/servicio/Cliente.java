/**
 * 
 */
package ar.edu.um.programacion2_2019.hilos.servicio;

/**
 * @author Camila
 *
 */

import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends Conexion {
	public Cliente() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            //Se enviarán mensajes
            
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Camila Chaves,5790098765586789,444,VISA,todo bien,1000\r\n"); 
                salidaServidor.writeUTF("Guille Casasola,3254575754567864,555,MC,ok,3000\r\n"); 
                salidaServidor.writeUTF("Vale Herrero,6778934534237450,111,VISA,mal,123");
            

            cs.close();//Fin de la conexión

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
