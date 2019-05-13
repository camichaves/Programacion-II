package ar.edu.um.programacion2_2019.hilos.servicio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImportarVenta {

    protected String archivo;
    protected ILogeador log;
    protected ExecutorService es1;
    protected AlmacenVentas almacen;

    public ImportarVenta() {}

    public ImportarVenta(String archivo, ILogeador log,AlmacenVentas av) {
        this.archivo = archivo;
        this.log = log;
        this.almacen=av;
    }

    
    public ExecutorService GetEs() {
    	
    	return es1;
    }
    
    public ExecutorService importarVentas() throws InterruptedException {
    	es1 =Executors.newFixedThreadPool(100);
    	
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.archivo));
            String linea;
            
            
            
            while ((linea = br.readLine()) != null) {
            	
            	//inicia hilo
            	
            	
            	TaskImportar taskimportar = new TaskImportar(linea,log,almacen);
            	es1.submit(taskimportar);
            	//TimeUnit.MILLISECONDS.sleep(700);
            	
            	
                
                
                //termina hilo
            }
            
            //br.close();
            es1.shutdown();
            es1.awaitTermination(1, TimeUnit.SECONDS);
           
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return es1;
       
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public ILogeador getLog() {
        return log;
    }

    public void setLog(ILogeador log) {
        this.log = log;
    }
}
