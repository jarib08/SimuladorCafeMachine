package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int contadorOrdenes = 1;

    private int idOrden;
    private Bebida bebida;
    private int cantidad;
    private double total;
    private LocalDateTime fecha;

    public Orden(Bebida bebida, int cantidad) {
        this.idOrden = contadorOrdenes++;
        this.bebida = bebida;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.total = calcularTotal();
    }

    public int getIdOrden() {
        return idOrden;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double calcularTotal() {
        return bebida.getPrecio() * cantidad;
    }

    public String generarRecibo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder recibo = new StringBuilder();

        recibo.append("\n========================================\n");
        recibo.append("           RECIBO DE ORDEN\n");
        recibo.append("========================================\n");
        recibo.append(String.format("Orden No: %d\n", idOrden));
        recibo.append(String.format("Fecha: %s\n", fecha.format(formatter)));
        recibo.append("----------------------------------------\n");
        recibo.append(String.format("Bebida: %s\n", bebida.getNombre()));
        recibo.append(String.format("Precio unitario: $%.2f\n", bebida.getPrecio()));
        recibo.append(String.format("Cantidad: %d\n", cantidad));
        recibo.append("----------------------------------------\n");
        recibo.append(String.format("TOTAL: $%.2f\n", total));
        recibo.append("========================================\n");

        return recibo.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Orden #%d - %s x%d - $%.2f [%s]",
                idOrden, bebida.getNombre(), cantidad, total, fecha.format(formatter));
    }
}
