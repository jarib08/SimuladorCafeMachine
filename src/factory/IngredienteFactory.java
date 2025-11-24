package factory;

import modelo.Ingrediente;

public class IngredienteFactory {

    public static Ingrediente crearCafe(double cantidad) {
        return new Ingrediente("Cafe", cantidad);
    }

    public static Ingrediente crearLeche(double cantidad) {
        return new Ingrediente("Leche", cantidad);
    }

    public static Ingrediente crearAzucar(double cantidad) {
        return new Ingrediente("Azucar", cantidad);
    }

    public static Ingrediente crearChocolate(double cantidad) {
        return new Ingrediente("Chocolate", cantidad);
    }

    public static Ingrediente crearAgua(double cantidad) {
        return new Ingrediente("Agua", cantidad);
    }

    public static Ingrediente crearCanela(double cantidad) {
        return new Ingrediente("Canela", cantidad);
    }

    public static Ingrediente crearCrema(double cantidad) {
        return new Ingrediente("Crema", cantidad);
    }

    public static Ingrediente crearVainilla(double cantidad) {
        return new Ingrediente("Vainilla", cantidad);
    }

    public static Ingrediente crearCaramelo(double cantidad) {
        return new Ingrediente("Caramelo", cantidad);
    }

    public static Ingrediente crearIngredientePersonalizado(String nombre, double cantidad) {
        return new Ingrediente(nombre, cantidad);
    }
}
