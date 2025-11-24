package modelo;

import java.io.Serializable;

public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private double cantidadDisponible;

    public Ingrediente(String nombre, double cantidadDisponible) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void reducirCantidad(double cantidad) throws IllegalArgumentException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa");
        }
        if (cantidad > this.cantidadDisponible) {
            throw new IllegalArgumentException("No hay suficiente cantidad disponible de " + this.nombre);
        }
        this.cantidadDisponible -= cantidad;
    }

    public void agregarCantidad(double cantidad) throws IllegalArgumentException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a agregar no puede ser negativa");
        }
        this.cantidadDisponible += cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f ml/g", nombre, cantidadDisponible);
    }
}
