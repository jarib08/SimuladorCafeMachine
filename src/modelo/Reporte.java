package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reporte {

    public static void generarReporteConsumo(List<Orden> ordenes) {
        if (ordenes.isEmpty()) {
            System.out.println("\nNo hay ordenes registradas para generar reporte de consumo");
            return;
        }

        Map<String, Integer> consumoPorBebida = new HashMap<>();

        for (Orden orden : ordenes) {
            String nombreBebida = orden.getBebida().getNombre();
            int cantidad = orden.getCantidad();

            consumoPorBebida.put(nombreBebida,
                    consumoPorBebida.getOrDefault(nombreBebida, 0) + cantidad);
        }

        System.out.println("\n========== REPORTE DE CONSUMO ==========");
        System.out.println("Total de ordenes: " + ordenes.size());
        System.out.println("\nConsumo por bebida:");

        for (Map.Entry<String, Integer> entry : consumoPorBebida.entrySet()) {
            System.out.printf("  %s: %d unidades\n", entry.getKey(), entry.getValue());
        }

        System.out.println("========================================\n");
    }

    public static void generarReporteInventario(Inventario inventario) {
        if (inventario.estaVacio()) {
            System.out.println("\nEl inventario esta vacio");
            return;
        }

        System.out.println("\n========== REPORTE DE INVENTARIO ==========");

        Map<String, Double> niveles = inventario.obtenerNivelInventario();

        for (Map.Entry<String, Double> entry : niveles.entrySet()) {
            String estado;
            double cantidad = entry.getValue();

            if (cantidad < 100) {
                estado = "[CRITICO]";
            } else if (cantidad < 300) {
                estado = "[BAJO]";
            } else if (cantidad < 600) {
                estado = "[MEDIO]";
            } else {
                estado = "[OPTIMO]";
            }

            System.out.printf("  %s: %.2f ml/g %s\n", entry.getKey(), cantidad, estado);
        }

        System.out.println("===========================================\n");
    }

    public static void generarReporteVentas(List<Orden> ordenes) {
        if (ordenes.isEmpty()) {
            System.out.println("\nNo hay ordenes registradas para generar reporte de ventas");
            return;
        }

        double totalVentas = 0.0;
        Map<String, Double> ventasPorBebida = new HashMap<>();

        for (Orden orden : ordenes) {
            String nombreBebida = orden.getBebida().getNombre();
            double totalOrden = orden.getTotal();

            totalVentas += totalOrden;
            ventasPorBebida.put(nombreBebida,
                    ventasPorBebida.getOrDefault(nombreBebida, 0.0) + totalOrden);
        }

        System.out.println("\n========== REPORTE DE VENTAS ==========");
        System.out.println(String.format("Total de ventas: $%.2f", totalVentas));
        System.out.println(String.format("Promedio por orden: $%.2f", totalVentas / ordenes.size()));
        System.out.println("\nVentas por bebida:");

        for (Map.Entry<String, Double> entry : ventasPorBebida.entrySet()) {
            double porcentaje = (entry.getValue() / totalVentas) * 100;
            System.out.printf("  %s: $%.2f (%.1f%%)\n",
                    entry.getKey(), entry.getValue(), porcentaje);
        }

        System.out.println("=======================================\n");
    }

    public static void generarEstadisticas(List<Orden> ordenes) {
        if (ordenes.isEmpty()) {
            System.out.println("\nNo hay ordenes registradas para generar estadisticas");
            return;
        }

        String bebidaMasVendida = "";
        int maxCantidad = 0;
        Map<String, Integer> consumoPorBebida = new HashMap<>();

        for (Orden orden : ordenes) {
            String nombreBebida = orden.getBebida().getNombre();
            int cantidad = orden.getCantidad();

            int total = consumoPorBebida.getOrDefault(nombreBebida, 0) + cantidad;
            consumoPorBebida.put(nombreBebida, total);

            if (total > maxCantidad) {
                maxCantidad = total;
                bebidaMasVendida = nombreBebida;
            }
        }

        System.out.println("\n========== ESTADISTICAS ==========");
        System.out.println("Bebida mas vendida: " + bebidaMasVendida + " (" + maxCantidad + " unidades)");
        System.out.println("Total de bebidas diferentes vendidas: " + consumoPorBebida.size());
        System.out.println("==================================\n");
    }
}
