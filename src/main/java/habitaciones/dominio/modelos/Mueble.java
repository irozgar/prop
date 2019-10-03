package habitaciones.dominio.modelos;

/**
 * La clase representa un mueble con sus características que son
 * longitud, anchura, tipo de mueble y color.
 */

import compartidas.*;
import habitaciones.dominio.modelos.enums.*;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.gestion.Objeto;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Mueble extends Contenido {
    protected int longitud;
    protected int anchura;
    protected TMueble tipo;
    protected Color color;

    protected List<Baldosa> casillasOcupadas;


    public Mueble() {
        super();
    }
    /**
     * Creamos un mueble nuevo con dadas características
     *
     * @param id El mueble recibe un identificador
     * @param tipo Se define el tipo del mueble
     * @param color Al mueble se le asigna un color
     * @param longitud La longitud del mueble
     * @param anchura La anchura del mueble
     */
    public Mueble(int id, TMueble tipo, Color color, int longitud, int anchura) {
        // Llamamos a super para que inicialice todo menos id y tipo. Estos
        // los hacemos a mano
        super();
        restConten = new ArrayList<RestriccionContenedor>();
        this.id = id;
        this.tipo = tipo;
        casillasOcupadas = new ArrayList<Baldosa> ();
        this.color = color;
        this.longitud = longitud;
        this.anchura = anchura;
    }

    public int getRed() {
        return color.getRed();
    }

    public int getGreen() {
        return color.getGreen();
    }

    public int getBlue() {
        return color.getBlue();
    }

    /**
     * Crea la copia del mueble pasado como parámetro
     *
     * @param mueb Un mueble con sus características
     */
    public Mueble(Mueble mueb) {
        this.id = mueb.id;
        this.assignat = mueb.assignat;
        this.longitud = mueb.longitud;
        this.anchura = mueb.anchura;
        this.tipo = mueb.tipo;
        this.color = new Color(mueb.color);

        // Copia las casillas
        this.casillasOcupadas  = new ArrayList<Baldosa> ();
        Iterator<Baldosa> coIt = mueb.casillasOcupadas.iterator();
        while (coIt.hasNext()) {
            casillasOcupadas.add(coIt.next());
        }

        restricciones = new ArrayList<RestriccionContenidos>();
        Iterator<RestriccionContenidos> reCtIt = mueb.restricciones.iterator();
        while (reCtIt.hasNext()) {
            RestriccionContenidos orig = reCtIt.next();

            if (orig instanceof RestriccionDistancia) {
                // Escoge cual de los 2 muebles para pasarlo como mueble 2 a la instancia nueva de restriccion
                Mueble mueble1 = ((RestriccionDistancia) orig).getMueble1();
                Mueble mueble2 = ((RestriccionDistancia) orig).getMueble2();

                Mueble segundo;
                if (mueble1.id == mueb.id) {
                    segundo = mueble2;
                } else {
                    segundo = mueble1;
                }

                if (orig instanceof RestriccionDistanciaMaxima) {
                    RestriccionDistanciaMaxima copia = new RestriccionDistanciaMaxima((RestriccionDistancia) orig);
                    copia.setMueble1(this);
                    copia.setMueble2(segundo);
                    restricciones.add(copia);
                } else if (orig instanceof RestriccionDistanciaMinima) {
                    RestriccionDistanciaMinima copia = new RestriccionDistanciaMinima((RestriccionDistancia) orig);
                    copia.setMueble1(this);
                    copia.setMueble2(segundo);
                    restricciones.add(copia);
                }
            } else if (orig instanceof RestriccionLimite) {
                if (orig instanceof RestriccionLimiteMax) {
                    RestriccionLimiteMax copia = new RestriccionLimiteMax((RestriccionLimite) orig);
                    copia.reemplazaMueble(mueb, this);
                    restricciones.add(copia);
                } else if (orig instanceof RestriccionLimiteMin) {
                    RestriccionLimiteMin copia = new RestriccionLimiteMin((RestriccionLimite) orig);
                    copia.reemplazaMueble(mueb, this);
                    restricciones.add(copia);
                }
            }
        }
        restConj = new ArrayList<RestriccionConjuntos>();
        restConten = new ArrayList<RestriccionContenedor>();
    }

    /**
     * @return Devuelve la longitud del mueble
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * @return Devuelve la anchura del mueble
     */
    public int getAnchura() {
        return anchura;
    }

    /**
     * @return El metodo devuelve el tipo del mueble
     */
    public TMueble getTipoMueble() {
        return tipo;
    }

    /**
     * @return El metodo devuelve el color del mueble
     */
    public Color getColor() {
        return color;
    }

    /**
     * Al mueble se le asigna un nuevo tipo
     *
     * @param tipo EL tipo del mueble
     */
    public void setTipo(TMueble tipo) {
        this.tipo = tipo;
    }

    /**
     * Al mueble se le asigna una nueva longitud
     *
     * @param l La longitud del mueble
     */
    public void setLongitud(int l) {
        longitud = l;
    }

    /**
     * Al mueble se le asigna un nuevo color
     *
     * @param col El color del mueble
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * @return Devuelve la lista de casillas que ocupa el mueble
     */
    public List<Baldosa> getCasillas() {
        return casillasOcupadas;
    }

    /**
     * Marcar la casilla que ocupa el mueble como ocupada
     *
     * @param baldosa La baldosa que ocupa el mueble
     */
    public void asignarCasilla(Baldosa baldosa) {
        casillasOcupadas.add(baldosa);
    }

    /**
     * Marcar la casilla que ocupa el mueble como no ocupada
     *
     * @param baldosa La baldosa que ocupa el mueble
     */
    public void desasignarCasilla(Baldosa baldosa) {
        for(int i = 0; i < casillasOcupadas.size(); i++) {
            Baldosa ocupada = casillasOcupadas.get(i);
            if (ocupada.getX() == baldosa.getX() &&
                    ocupada.getY() == baldosa.getY()
               ) {
                casillasOcupadas.remove(baldosa);
                return;
            }
        }
    }

    /**
     * Desocupa todas las casillas que ocupa el mueble
     */
    public void desasignarTodas() {
        casillasOcupadas = new ArrayList<Baldosa> ();
    }

    public String toString() {
        String text = "Mueble " + id;
        text += " (" + longitud + ", " + anchura + ") ";
        text += tipo;
        return text;
    }

    public Objeto toObjeto() {
        Objeto objeto = new Objeto();
        objeto.setTipo("Mueble");
        objeto.addAtributo("id", id);
        objeto.addAtributo("longitud", longitud);
        objeto.addAtributo("anchura", anchura);
        objeto.addAtributo("tipo", tipo);
        objeto.addAtributo("color_red", color.getRed());
        objeto.addAtributo("color_green", color.getGreen());
        objeto.addAtributo("color_blue", color.getBlue());
        return objeto;
    }
}
