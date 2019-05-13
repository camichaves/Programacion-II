package ar.edu.um.programacion2_2019.hilos.servicio;

public interface IProcesador {
    public boolean verificarTarjeta();
    public boolean autorizar();
    public boolean capturar();
    public boolean finalizar();
}
