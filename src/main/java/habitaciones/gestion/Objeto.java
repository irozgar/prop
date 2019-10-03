package habitaciones.gestion;

/**
 * La clase Objeto representa un objeto de un determinado tipo con valores propios a este objeto
 */

import java.util.*;

public class Objeto {
    private String tipo;
    private HashMap<String, Object> valores;

    /**
     * Crear un nuevo objeto de tipo nulo con sus valores
     */
    public Objeto() {
        tipo = "";
        valores = new HashMap<String, Object>();
    }
    /**
     * A los valores del objeto se asignan el atributo y el valor
     *
     * @param atributo El atributo del objeto
     * @param valor El valor del objeto
     */
    public void addAtributo(String atributo, Object valor) {
        valores.put(atributo, valor);
    }

    /**
     * Obtener los atributos del objeto
     * @return Devuelve una lista de valores del objeto
     */
    public Set<String> getAtributos() {
        return valores.keySet();
    }

    /**
     * Determinar y asignar el tipo del objeto
     * @param tipo El tipo del objeto
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtener el tipo del objeto
     * @return Devuelve el tipo del objeto
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtener los valores del objeto a partir de su atributo
     * @param atributo El atributo propio del objeto
     * @return Devuelve una lista de valores del objeto a partir de su atributo
     */
    public Object getValor(String atributo) {
        return valores.get(atributo);
    }
}
