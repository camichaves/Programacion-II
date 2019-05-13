/**
 * 
 */
package ar.edu.um.programacion2_2019.hilos.servicio;
import java.io.IOException;

/**
 * @author Camila
 *
 */
public class MainCliente {

    public static void main(String[] args) throws IOException
    {
        Cliente cli = new Cliente(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    }

}
