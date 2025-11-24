package modelo;

import excepciones.IngredienteNoDisponibleException;
import excepciones.InventarioVacioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Ingrediente> ingredientes;

    public Inventario() {
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        Ingrediente existente = buscarIngredientePorNombre(ingrediente.getNombre());
        if (existente != null) {
            existente.agregarCantidad(ingrediente.getCantidadDisponible());
        } else {
            ingredientes.add(ingrediente);
        }
    }

    public void eliminarIngrediente(String nombre) throws IngredienteNoDisponibleException {
        Ingrediente ingrediente = buscarIngredientePorNombre(nombre);
        if (ingrediente == null) {
            throw new IngredienteNoDisponibleException("El ingrediente " + nombre + " no existe en el inventario");
        }
        ingredientes.remove(ingrediente);
    }

    public Ingrediente buscarIngrediente(String nombre) throws IngredienteNoDisponibleException {
        Ingrediente ingrediente = buscarIngredientePorNombre(nombre);
        if (ingrediente == null) {
            throw new IngredienteNoDisponibleException("El ingrediente " + nombre + " no existe en el inventario");
        }
        return ingrediente;
    }

    private Ingrediente buscarIngredientePorNombre(String nombre) {
        for (Ingrediente ing : ingredientes) {
            if (ing.getNombre().equalsIgnoreCase(nombre)) {
                return ing;
            }
        }
        return null;
    }

    public void actualizarCantidad(String nombre, double cantidad) throws IngredienteNoDisponibleException {
        Ingrediente ingrediente = buscarIngrediente(nombre);
        ingrediente.setCantidadDisponible(cantidad);
    }

    public boolean verificarDisponibilidad(Bebida bebida) {
        Map<String, Double> receta = bebida.getReceta();

        for (Map.Entry<String, Double> entry : receta.entrySet()) {
            String nombreIngrediente = entry.getKey();
            double cantidadRequerida = entry.getValue();

            Ingrediente ingrediente = buscarIngredientePorNombre(nombreIngrediente);
            if (ingrediente == null || ingrediente.getCantidadDisponible() < cantidadRequerida) {
                return false;
            }
        }
        return true;
    }

    public void reducirIngredientes(Bebida bebida, int cantidad) throws IngredienteNoDisponibleException {
        Map<String, Double> receta = bebida.getReceta();

        for (Map.Entry<String, Double> entry : receta.entrySet()) {
            String nombreIngrediente = entry.getKey();
            double cantidadRequerida = entry.getValue() * cantidad;

            Ingrediente ingrediente = buscarIngrediente(nombreIngrediente);

            if (ingrediente.getCantidadDisponible() < cantidadRequerida) {
                throw new IngredienteNoDisponibleException(
                        "No hay suficiente " + nombreIngrediente + ". Disponible: "
                        + ingrediente.getCantidadDisponible() + " ml/g, Requerido: " + cantidadRequerida + " ml/g"
                );
            }

            ingrediente.reducirCantidad(cantidadRequerida);
        }
    }

    public Map<String, Double> obtenerNivelInventario() {
        Map<String, Double> niveles = new HashMap<>();
        for (Ingrediente ing : ingredientes) {
            niveles.put(ing.getNombre(), ing.getCantidadDisponible());
        }
        return niveles;
    }

    public boolean estaVacio() {
        return ingredientes.isEmpty();
    }

    public List<Ingrediente> getIngredientes() {
        return new ArrayList<>(ingredientes);
    }

    public void verificarAlertasBajoStock() {
        System.out.println("\n--- ALERTAS DE INVENTARIO ---");
        boolean hayAlertas = false;

        for (Ingrediente ing : ingredientes) {
            if (ing.getCantidadDisponible() < 100) {
                System.out.println("ALERTA: " + ing.getNombre() + " tiene bajo stock ("
                        + String.format("%.2f", ing.getCantidadDisponible()) + " ml/g)");
                hayAlertas = true;
            }
        }

        if (!hayAlertas) {
            System.out.println("Todos los ingredientes tienen stock suficiente");
        }
        System.out.println("-----------------------------\n");
    }

    @Override
    public String toString() {
        if (ingredientes.isEmpty()) {
            return "Inventario vacio";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n=== INVENTARIO ===\n");
        for (Ingrediente ing : ingredientes) {
            sb.append(ing.toString()).append("\n");
        }
        return sb.toString();
    }
}
