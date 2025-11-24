package persistencia;

import java.io.*;

public class GestorArchivos {

    private static final String CARPETA_DATOS = "datos/";

    static {
        File carpeta = new File(CARPETA_DATOS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    public static void guardarObjeto(Object objeto, String nombreArchivo) {
        String rutaCompleta = CARPETA_DATOS + nombreArchivo;

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(rutaCompleta))) {
            oos.writeObject(objeto);
            System.out.println("Datos guardados en: " + rutaCompleta);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static Object cargarObjeto(String nombreArchivo) {
        String rutaCompleta = CARPETA_DATOS + nombreArchivo;
        File archivo = new File(rutaCompleta);

        if (!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no existe");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(rutaCompleta))) {
            Object objeto = ois.readObject();
            System.out.println("Datos cargados desde: " + rutaCompleta);
            return objeto;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            return null;
        }
    }

    public static boolean existeArchivo(String nombreArchivo) {
        File archivo = new File(CARPETA_DATOS + nombreArchivo);
        return archivo.exists();
    }

    public static void eliminarArchivo(String nombreArchivo) {
        File archivo = new File(CARPETA_DATOS + nombreArchivo);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo " + nombreArchivo + " eliminado");
            } else {
                System.err.println("No se pudo eliminar el archivo " + nombreArchivo);
            }
        }
    }

    public static void limpiarDatos() {
        File carpeta = new File(CARPETA_DATOS);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    archivo.delete();
                }
                System.out.println("Todos los datos han sido eliminados");
            }
        }
    }
}
