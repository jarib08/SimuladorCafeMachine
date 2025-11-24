package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Bebida implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private double precio;
    private Map<String, Double> receta;

    public Bebida(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.receta = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Map<String, Double> getReceta() {
        return receta;
    }

    public void agregarIngredienteReceta(String nombreIngrediente, double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.receta.put(nombreIngrediente, cantidad);
    }

    public void eliminarIngredienteReceta(String nombreIngrediente) {
        this.receta.remove(nombreIngrediente);
    }

    public boolean tieneIngrediente(String nombreIngrediente) {
        return this.receta.containsKey(nombreIngrediente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - $%.2f\n", nombre, precio));
        sb.append("Ingredientes:\n");
        for (Map.Entry<String, Double> entry : receta.entrySet()) {
            sb.append(String.format("  - %s: %.2f ml/g\n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
