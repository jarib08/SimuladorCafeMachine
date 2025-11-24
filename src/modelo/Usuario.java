package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Rol {
        OPERADOR,
        TECNICO
    }

    private String nombre;
    private Rol rol;

    public Usuario(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean tienePermiso(String accion) {
        if (rol == Rol.TECNICO) {
            return true;
        }

        if (rol == Rol.OPERADOR) {
            return !accion.equals("GESTIONAR_USUARIOS");
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", nombre, rol);
    }
}
