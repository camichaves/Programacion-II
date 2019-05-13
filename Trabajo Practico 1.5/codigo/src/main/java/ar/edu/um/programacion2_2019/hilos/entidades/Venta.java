package ar.edu.um.programacion2_2019.hilos.entidades;

public class Venta {
    protected String descripcion;
    protected float monto;
    protected TarjetaCredito tarjeta;

    public Venta() {}

    public Venta(String descripcion, float monto, TarjetaCredito tarjeta) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", tarjeta=" + tarjeta +
                '}';
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }
}
