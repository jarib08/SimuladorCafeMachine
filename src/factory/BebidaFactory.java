package factory;

import modelo.Bebida;

public class BebidaFactory {

    public static Bebida crearEspresso() {
        Bebida bebida = new Bebida("Espresso", 3500.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Agua", 30.0);
        return bebida;
    }

    public static Bebida crearCappuccino() {
        Bebida bebida = new Bebida("Cappuccino", 5000.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Leche", 100.0);
        bebida.agregarIngredienteReceta("Crema", 20.0);
        return bebida;
    }

    public static Bebida crearLatte() {
        Bebida bebida = new Bebida("Latte", 4500.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Leche", 200.0);
        return bebida;
    }

    public static Bebida crearAmericano() {
        Bebida bebida = new Bebida("Americano", 3000.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Agua", 200.0);
        return bebida;
    }

    public static Bebida crearMocaccino() {
        Bebida bebida = new Bebida("Mocaccino", 5500.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Leche", 100.0);
        bebida.agregarIngredienteReceta("Chocolate", 20.0);
        bebida.agregarIngredienteReceta("Crema", 15.0);
        return bebida;
    }

    public static Bebida crearMacchiato() {
        Bebida bebida = new Bebida("Macchiato", 4000.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Leche", 50.0);
        bebida.agregarIngredienteReceta("Crema", 10.0);
        return bebida;
    }

    public static Bebida crearCaramelMacchiato() {
        Bebida bebida = new Bebida("Caramel Macchiato", 6000.0);
        bebida.agregarIngredienteReceta("Cafe", 30.0);
        bebida.agregarIngredienteReceta("Leche", 150.0);
        bebida.agregarIngredienteReceta("Caramelo", 20.0);
        bebida.agregarIngredienteReceta("Crema", 15.0);
        return bebida;
    }

    public static Bebida crearBebidaPersonalizada(String nombre, double precio) {
        return new Bebida(nombre, precio);
    }
}
