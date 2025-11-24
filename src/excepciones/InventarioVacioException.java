package excepciones;

public class InventarioVacioException extends Exception {

    public InventarioVacioException(String mensaje) {
        super(mensaje);
    }
}
