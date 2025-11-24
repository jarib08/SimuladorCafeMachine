package modelo;

import excepciones.*;
import factory.BebidaFactory;
import factory.IngredienteFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MaquinaCafe implements Serializable {

    private static final long serialVersionUID = 1L;

    private Inventario inventario;
    private List<Bebida> menuBebidas;
    private List<Orden> ordenes;
    private Configuracion configuracion;

    public MaquinaCafe() {
        this.inventario = new Inventario();
        this.menuBebidas = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.configuracion = Configuracion.getInstance();
    }

    public void inicializarSistema() {
        cargarInventarioInicial();
        cargarBebidasPredefinidas();
        System.out.println("Sistema inicializado correctamente");
    }

    public void cargarInventarioInicial() {
        inventario.agregarIngrediente(IngredienteFactory.crearCafe(1000.0));
        inventario.agregarIngrediente(IngredienteFactory.crearLeche(2000.0));
        inventario.agregarIngrediente(IngredienteFactory.crearAgua(3000.0));
        inventario.agregarIngrediente(IngredienteFactory.crearAzucar(1500.0));
        inventario.agregarIngrediente(IngredienteFactory.crearChocolate(800.0));
        inventario.agregarIngrediente(IngredienteFactory.crearCrema(600.0));
        inventario.agregarIngrediente(IngredienteFactory.crearCanela(300.0));
        inventario.agregarIngrediente(IngredienteFactory.crearVainilla(400.0));
        inventario.agregarIngrediente(IngredienteFactory.crearCaramelo(500.0));
    }

    public void cargarBebidasPredefinidas() {
        menuBebidas.add(BebidaFactory.crearEspresso());
        menuBebidas.add(BebidaFactory.crearAmericano());
        menuBebidas.add(BebidaFactory.crearCappuccino());
        menuBebidas.add(BebidaFactory.crearLatte());
        menuBebidas.add(BebidaFactory.crearMocaccino());
        menuBebidas.add(BebidaFactory.crearMacchiato());
        menuBebidas.add(BebidaFactory.crearCaramelMacchiato());
    }

    public Bebida seleccionarBebida(String nombre) throws BebidaNoEncontradaException {
        for (Bebida bebida : menuBebidas) {
            if (bebida.getNombre().equalsIgnoreCase(nombre)) {
                return bebida;
            }
        }
        throw new BebidaNoEncontradaException("La bebida '" + nombre + "' no existe en el menu");
    }

    public void prepararBebida(Bebida bebida, int cantidad) throws IngredienteNoDisponibleException,
            InventarioVacioException,
            OrdenNoGeneradaException {
        if (inventario.estaVacio()) {
            throw new InventarioVacioException("El inventario esta vacio. Recargue los ingredientes antes de continuar");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        if (!inventario.verificarDisponibilidad(bebida)) {
            throw new IngredienteNoDisponibleException("No hay suficientes ingredientes para preparar " + bebida.getNombre());
        }

        System.out.println("\n--- INICIANDO PREPARACION ---");
        System.out.println("Bebida: " + bebida.getNombre());
        System.out.println("Cantidad: " + cantidad);

        if (configuracion.getModo().equals("MANUAL")) {
            simularPreparacionManual(bebida, cantidad);
        } else {
            simularPreparacionAutomatica(bebida, cantidad);
        }

        inventario.reducirIngredientes(bebida, cantidad);

        Orden orden = generarOrden(bebida, cantidad);

        System.out.println("\nPreparacion completada exitosamente");
        System.out.println(orden.generarRecibo());
    }

    private void simularPreparacionAutomatica(Bebida bebida, int cantidad) {
        System.out.println("\nModo: AUTOMATICO");
        System.out.println("Procesando...");

        try {
            Thread.sleep(1000);
            System.out.println("Calentando agua...");
            Thread.sleep(1000);
            System.out.println("Moliendo cafe...");
            Thread.sleep(1000);
            System.out.println("Preparando " + bebida.getNombre() + "...");
            Thread.sleep(1500);
            System.out.println("Agregando ingredientes...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void simularPreparacionManual(Bebida bebida, int cantidad) {
        System.out.println("\nModo: MANUAL");
        System.out.println("Siga las instrucciones:");
        System.out.println("1. Coloque el filtro");
        System.out.println("2. Agregue el cafe molido");
        System.out.println("3. Presione el boton de inicio");
        System.out.println("4. Espere a que termine la preparacion");
    }

    public Orden generarOrden(Bebida bebida, int cantidad) throws OrdenNoGeneradaException {
        try {
            Orden orden = new Orden(bebida, cantidad);
            ordenes.add(orden);
            return orden;
        } catch (Exception e) {
            throw new OrdenNoGeneradaException("No se pudo generar la orden: " + e.getMessage());
        }
    }

    public void agregarBebidaAlMenu(Bebida bebida) {
        menuBebidas.add(bebida);
        System.out.println("Bebida '" + bebida.getNombre() + "' agregada al menu");
    }

    public void eliminarBebidaDelMenu(String nombre) throws BebidaNoEncontradaException {
        Bebida bebida = seleccionarBebida(nombre);
        menuBebidas.remove(bebida);
        System.out.println("Bebida '" + nombre + "' eliminada del menu");
    }

    public void mostrarMenuBebidas() {
        if (menuBebidas.isEmpty()) {
            System.out.println("No hay bebidas disponibles en el menu");
            return;
        }

        System.out.println("\n========== MENU DE BEBIDAS ==========");
        for (int i = 0; i < menuBebidas.size(); i++) {
            Bebida b = menuBebidas.get(i);
            System.out.printf("%d. %s - $%.2f\n", (i + 1), b.getNombre(), b.getPrecio());
        }
        System.out.println("=====================================\n");
    }

    public void realizarMantenimiento() {
        System.out.println("\n--- MANTENIMIENTO DEL SISTEMA ---");
        System.out.println("Recargando inventario...");
        cargarInventarioInicial();
        System.out.println("Inventario recargado exitosamente");
        System.out.println("---------------------------------\n");
    }

    public void mostrarResumen() {
        System.out.println("\n========== RESUMEN DEL SISTEMA ==========");
        System.out.println("Total de bebidas en menu: " + menuBebidas.size());
        System.out.println("Total de ordenes generadas: " + ordenes.size());
        System.out.println("Configuracion actual: " + configuracion.toString());
        System.out.println("=========================================\n");
    }

    public Inventario getInventario() {
        return inventario;
    }

    public List<Bebida> getMenuBebidas() {
        return new ArrayList<>(menuBebidas);
    }

    public List<Orden> getOrdenes() {
        return new ArrayList<>(ordenes);
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }
}
