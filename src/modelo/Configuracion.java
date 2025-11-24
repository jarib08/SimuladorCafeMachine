package modelo;

import excepciones.ConfiguracionInvalidaException;
import java.io.Serializable;

public class Configuracion implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Configuracion instancia;

    private String modo;
    private String idioma;

    private Configuracion() {
        this.modo = "AUTOMATICO";
        this.idioma = "ESPANOL";
    }

    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public String getModo() {
        return modo;
    }

    public void cambiarModo(String modo) throws ConfiguracionInvalidaException {
        if (!modo.equals("AUTOMATICO") && !modo.equals("MANUAL")) {
            throw new ConfiguracionInvalidaException("Modo invalido. Use AUTOMATICO o MANUAL");
        }
        this.modo = modo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void cambiarIdioma(String idioma) throws ConfiguracionInvalidaException {
        if (!idioma.equals("ESPANOL") && !idioma.equals("INGLES")) {
            throw new ConfiguracionInvalidaException("Idioma invalido. Use ESPANOL o INGLES");
        }
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return String.format("Modo: %s | Idioma: %s", modo, idioma);
    }
}
