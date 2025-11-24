package interfaz;

import excepciones.*;
import factory.BebidaFactory;
import factory.IngredienteFactory;
import modelo.*;
import persistencia.GestorArchivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfazConsola {

    private MaquinaCafe maquina;
    private Scanner scanner;
    private Usuario usuarioActivo;
    private List<Usuario> usuarios;

    public InterfazConsola() {
        this.scanner = new Scanner(System.in);
        this.usuarios = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        MaquinaCafe maquinaCargada = (MaquinaCafe) GestorArchivos.cargarObjeto("maquina.dat");
        List<Usuario> usuariosCargados = (List<Usuario>) GestorArchivos.cargarObjeto("usuarios.dat");

        if (maquinaCargada != null) {
            this.maquina = maquinaCargada;
            System.out.println("Datos del sistema cargados correctamente");
        } else {
            System.out.println("Inicializando sistema por primera vez...");
            this.maquina = new MaquinaCafe();
            maquina.inicializarSistema();
        }

        if (usuariosCargados != null && !usuariosCargados.isEmpty()) {
            this.usuarios = usuariosCargados;
            System.out.println("Usuarios cargados correctamente");
        } else {
            crearUsuariosPredeterminados();
        }
    }

    private void crearUsuariosPredeterminados() {
        usuarios.add(new Usuario("Admin", Usuario.Rol.TECNICO));
        usuarios.add(new Usuario("Jarib", Usuario.Rol.OPERADOR));
        System.out.println("Usuarios predeterminados creados");
    }

    public void iniciar() {
        mostrarBienvenida();

        if (!iniciarSesion()) {
            System.out.println("No se pudo iniciar sesion. Saliendo del sistema...");
            return;
        }

        boolean continuar = true;

        while (continuar) {
            try {
                mostrarMenuPrincipal();
                int opcion = leerOpcion();

                switch (opcion) {
                    case 1:
                        menuInventario();
                        break;
                    case 2:
                        menuBebidas();
                        break;
                    case 3:
                        menuSimulacion();
                        break;
                    case 4:
                        menuReportes();
                        break;
                    // case 5:
                    //     menuConfiguracion();
                    //     break;
                    case 5:
                        menuUsuarios();
                        break;
                    case 6:
                        guardarYSalir();
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (Exception e) {
                manejarExcepcion(e);
            }
        }
    }

    private void mostrarBienvenida() {
        System.out.println("\n================================================");
        System.out.println("    SIMULADOR DE MAQUINA DE CAFE");
        System.out.println("    Universidad del Magdalena");
        System.out.println("================================================\n");
    }

    private boolean iniciarSesion() {
        System.out.println("\n--- INICIO DE SESION ---");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                usuarioActivo = usuario;
                System.out.println("Bienvenido, " + usuario.getNombre() + " (" + usuario.getRol() + ")");
                return true;
            }
        }

        System.out.println("Usuario no encontrado");
        return false;
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("Usuario: " + usuarioActivo.getNombre());
        System.out.println("1. Gestionar Inventario");
        System.out.println("2. Gestionar Bebidas");
        System.out.println("3. Simular Preparacion de Bebida");
        System.out.println("4. Ver Reportes");
        // System.out.println("5. Configuracion");
        System.out.println("5. Gestionar Usuarios");
        System.out.println("6. Guardar y Salir");
        System.out.println("====================================");
        System.out.print("Seleccione una opcion: ");
    }

    private void menuInventario() {
        System.out.println("\n--- GESTION DE INVENTARIO ---");
        System.out.println("1. Ver inventario actual");
        System.out.println("2. Agregar ingrediente");
        System.out.println("3. Eliminar ingrediente");
        System.out.println("4. Actualizar cantidad");
        System.out.println("5. Verificar alertas de stock");
        System.out.println("6. Recargar inventario completo");
        System.out.println("7. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();

        try {
            switch (opcion) {
                case 1:
                    System.out.println(maquina.getInventario().toString());
                    break;
                case 2:
                    agregarIngrediente();
                    break;
                case 3:
                    eliminarIngrediente();
                    break;
                case 4:
                    actualizarCantidadIngrediente();
                    break;
                case 5:
                    maquina.getInventario().verificarAlertasBajoStock();
                    break;
                case 6:
                    maquina.realizarMantenimiento();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    private void agregarIngrediente() {
        System.out.println("\n--- AGREGAR INGREDIENTE ---");
        System.out.println("1. Cafe");
        System.out.println("2. Leche");
        System.out.println("3. Agua");
        System.out.println("4. Azucar");
        System.out.println("5. Chocolate");
        System.out.println("6. Crema");
        System.out.println("7. Canela");
        System.out.println("8. Vainilla");
        System.out.println("9. Caramelo");
        System.out.println("10. Ingrediente personalizado");
        System.out.print("Seleccione el tipo de ingrediente: ");

        int tipo = leerOpcion();
        System.out.print("Ingrese la cantidad (ml/g): ");
        double cantidad = leerDouble();

        Ingrediente ingrediente = null;

        switch (tipo) {
            case 1:
                ingrediente = IngredienteFactory.crearCafe(cantidad);
                break;
            case 2:
                ingrediente = IngredienteFactory.crearLeche(cantidad);
                break;
            case 3:
                ingrediente = IngredienteFactory.crearAgua(cantidad);
                break;
            case 4:
                ingrediente = IngredienteFactory.crearAzucar(cantidad);
                break;
            case 5:
                ingrediente = IngredienteFactory.crearChocolate(cantidad);
                break;
            case 6:
                ingrediente = IngredienteFactory.crearCrema(cantidad);
                break;
            case 7:
                ingrediente = IngredienteFactory.crearCanela(cantidad);
                break;
            case 8:
                ingrediente = IngredienteFactory.crearVainilla(cantidad);
                break;
            case 9:
                ingrediente = IngredienteFactory.crearCaramelo(cantidad);
                break;
            case 10:
                System.out.print("Ingrese el nombre del ingrediente: ");
                String nombre = scanner.nextLine();
                ingrediente = IngredienteFactory.crearIngredientePersonalizado(nombre, cantidad);
                break;
            default:
                System.out.println("Opcion invalida");
                return;
        }

        maquina.getInventario().agregarIngrediente(ingrediente);
        System.out.println("Ingrediente agregado exitosamente");
    }

    private void eliminarIngrediente() throws IngredienteNoDisponibleException {
        System.out.print("\nIngrese el nombre del ingrediente a eliminar: ");
        String nombre = scanner.nextLine();
        maquina.getInventario().eliminarIngrediente(nombre);
        System.out.println("Ingrediente eliminado exitosamente");
    }

    private void actualizarCantidadIngrediente() throws IngredienteNoDisponibleException {
        System.out.print("\nIngrese el nombre del ingrediente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la nueva cantidad: ");
        double cantidad = leerDouble();
        maquina.getInventario().actualizarCantidad(nombre, cantidad);
        System.out.println("Cantidad actualizada exitosamente");
    }

    private void menuBebidas() {
        System.out.println("\n--- GESTION DE BEBIDAS ---");
        System.out.println("1. Ver menu de bebidas");
        System.out.println("2. Agregar bebida predefinida");
        System.out.println("3. Crear bebida personalizada");
        System.out.println("4. Eliminar bebida");
        System.out.println("5. Ver detalles de una bebida");
        System.out.println("6. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();

        try {
            switch (opcion) {
                case 1:
                    maquina.mostrarMenuBebidas();
                    break;
                case 2:
                    agregarBebidaPredefinida();
                    break;
                case 3:
                    crearBebidaPersonalizada();
                    break;
                case 4:
                    eliminarBebida();
                    break;
                case 5:
                    verDetallesBebida();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    private void agregarBebidaPredefinida() {
        System.out.println("\n--- BEBIDAS PREDEFINIDAS ---");
        System.out.println("1. Espresso");
        System.out.println("2. Americano");
        System.out.println("3. Cappuccino");
        System.out.println("4. Latte");
        System.out.println("5. Mocaccino");
        System.out.println("6. Macchiato");
        System.out.println("7. Caramel Macchiato");
        System.out.print("Seleccione la bebida: ");

        int opcion = leerOpcion();
        Bebida bebida = null;

        switch (opcion) {
            case 1:
                bebida = BebidaFactory.crearEspresso();
                break;
            case 2:
                bebida = BebidaFactory.crearAmericano();
                break;
            case 3:
                bebida = BebidaFactory.crearCappuccino();
                break;
            case 4:
                bebida = BebidaFactory.crearLatte();
                break;
            case 5:
                bebida = BebidaFactory.crearMocaccino();
                break;
            case 6:
                bebida = BebidaFactory.crearMacchiato();
                break;
            case 7:
                bebida = BebidaFactory.crearCaramelMacchiato();
                break;
            default:
                System.out.println("Opcion invalida");
                return;
        }

        maquina.agregarBebidaAlMenu(bebida);
    }

    private void crearBebidaPersonalizada() {
        System.out.println("\n--- CREAR BEBIDA PERSONALIZADA ---");
        System.out.print("Nombre de la bebida: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();

        Bebida bebida = BebidaFactory.crearBebidaPersonalizada(nombre, precio);

        System.out.print("Cuantos ingredientes tendra la bebida? ");
        int numIngredientes = leerOpcion();

        for (int i = 0; i < numIngredientes; i++) {
            System.out.print("Nombre del ingrediente " + (i + 1) + ": ");
            String nombreIng = scanner.nextLine();
            System.out.print("Cantidad (ml/g): ");
            double cantidad = leerDouble();
            bebida.agregarIngredienteReceta(nombreIng, cantidad);
        }

        maquina.agregarBebidaAlMenu(bebida);
    }

    private void eliminarBebida() throws BebidaNoEncontradaException {
        System.out.print("\nIngrese el nombre de la bebida a eliminar: ");
        String nombre = scanner.nextLine();
        maquina.eliminarBebidaDelMenu(nombre);
    }

    private void verDetallesBebida() throws BebidaNoEncontradaException {
        System.out.print("\nIngrese el nombre de la bebida: ");
        String nombre = scanner.nextLine();
        Bebida bebida = maquina.seleccionarBebida(nombre);
        System.out.println("\n" + bebida.toString());
    }

    private void menuSimulacion() {
        System.out.println("\n--- SIMULACION DE PREPARACION ---");
        maquina.mostrarMenuBebidas();

        try {
            System.out.print("Ingrese el nombre de la bebida: ");
            String nombreBebida = scanner.nextLine();

            Bebida bebida = maquina.seleccionarBebida(nombreBebida);

            System.out.print("Ingrese la cantidad: ");
            int cantidad = leerOpcion();

            maquina.prepararBebida(bebida, cantidad);

        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    private void menuReportes() {
        System.out.println("\n--- REPORTES ---");
        System.out.println("1. Reporte de consumo");
        System.out.println("2. Reporte de inventario");
        System.out.println("3. Reporte de ventas");
        System.out.println("4. Estadisticas generales");
        System.out.println("5. Resumen del sistema");
        System.out.println("6. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");

        int opcion = leerOpcion();

        switch (opcion) {
            case 1:
                Reporte.generarReporteConsumo(maquina.getOrdenes());
                break;
            case 2:
                Reporte.generarReporteInventario(maquina.getInventario());
                break;
            case 3:
                Reporte.generarReporteVentas(maquina.getOrdenes());
                break;
            case 4:
                Reporte.generarEstadisticas(maquina.getOrdenes());
                break;
            case 5:
                maquina.mostrarResumen();
                break;
            case 6:
                return;
            default:
                System.out.println("Opcion invalida");
        }
    }

    // private void menuConfiguracion() {
    //     System.out.println("\n--- CONFIGURACION ---");
    //     System.out.println("Configuracion actual: " + maquina.getConfiguracion().toString());
    //     System.out.println("\n1. Cambiar modo de simulacion");
    //     System.out.println("2. Cambiar idioma");
    //     System.out.println("3. Volver al menu principal");
    //     System.out.print("Seleccione una opcion: ");

    //     int opcion = leerOpcion();

    //     try {
    //         switch (opcion) {
    //             case 1:
    //                 cambiarModo();
    //                 break;
    //             case 2:
    //                 cambiarIdioma();
    //                 break;
    //             case 3:
    //                 return;
    //             default:
    //                 System.out.println("Opcion invalida");
    //         }
    //     } catch (ConfiguracionInvalidaException e) {
    //         manejarExcepcion(e);
    //     }
    // }

    // private void cambiarModo() throws ConfiguracionInvalidaException {
    //     System.out.println("\n1. AUTOMATICO");
    //     System.out.println("2. MANUAL");
    //     System.out.print("Seleccione el modo: ");
    //     int opcion = leerOpcion();

    //     String modo = (opcion == 1) ? "AUTOMATICO" : "MANUAL";
    //     maquina.getConfiguracion().cambiarModo(modo);
    //     System.out.println("Modo cambiado a: " + modo);
    // }

    // private void cambiarIdioma() throws ConfiguracionInvalidaException {
    //     System.out.println("\n1. ESPANOL");
    //     System.out.println("2. INGLES");
    //     System.out.print("Seleccione el idioma: ");
    //     int opcion = leerOpcion();

    //     String idioma = (opcion == 1) ? "ESPANOL" : "INGLES";
    //     maquina.getConfiguracion().cambiarIdioma(idioma);
    //     System.out.println("Idioma cambiado a: " + idioma);
    // }

    private void menuUsuarios() {
        try {
            if (!usuarioActivo.tienePermiso("GESTIONAR_USUARIOS")) {
                throw new UsuarioNoAutorizadoException("No tiene permisos para gestionar usuarios");
            }

            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Agregar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    verUsuarios();
                    break;
                case 2:
                    agregarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
        } catch (UsuarioNoAutorizadoException e) {
            manejarExcepcion(e);
        }
    }

    private void verUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).toString());
        }
    }

    private void agregarUsuario() {
        System.out.print("\nNombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.println("1. OPERADOR");
        System.out.println("2. TECNICO");
        System.out.print("Seleccione el rol: ");
        int opcion = leerOpcion();

        Usuario.Rol rol = (opcion == 1) ? Usuario.Rol.OPERADOR : Usuario.Rol.TECNICO;
        usuarios.add(new Usuario(nombre, rol));
        System.out.println("Usuario agregado exitosamente");
    }

    private void eliminarUsuario() {
        verUsuarios();
        System.out.print("\nIngrese el numero del usuario a eliminar: ");
        int indice = leerOpcion() - 1;

        if (indice >= 0 && indice < usuarios.size()) {
            Usuario usuario = usuarios.remove(indice);
            System.out.println("Usuario " + usuario.getNombre() + " eliminado");
        } else {
            System.out.println("Indice invalido");
        }
    }

    private void guardarYSalir() {
        System.out.println("\nGuardando datos del sistema...");
        GestorArchivos.guardarObjeto(maquina, "maquina.dat");
        GestorArchivos.guardarObjeto(usuarios, "usuarios.dat");
        System.out.println("Datos guardados exitosamente");
        System.out.println("\nGracias por usar el Simulador de Maquina de Cafe");
        System.out.println("Hasta pronto!");
    }

    private int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Ingrese un numero.");
            return -1;
        }
    }

    private double leerDouble() {
        try {
            double valor = Double.parseDouble(scanner.nextLine());
            return valor;
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Ingrese un numero valido.");
            return 0.0;
        }
    }

    private void manejarExcepcion(Exception e) {
        System.err.println("\nERROR: " + e.getMessage());
    }
}
