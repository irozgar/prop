package habitaciones.dominio.modelos;

import compartidas.Contenido;
import habitaciones.gestion.Objeto;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import habitaciones.dominio.modelos.enums.*;
import compartidas.Posicion;

public class ContenidoPared extends Contenido {
    protected TContenidoPared tipo; // 0 - Puerta, 1 - Ventana
    protected int tamano;
    protected Posicion pos;

    protected ArrayList<Baldosa> casillasOcupadas;

    public ContenidoPared(int id, TContenidoPared tipo, int tamano, Posicion pos) {
        super();

        this.id     = id;
        this.tipo   = tipo;
        this.tamano = tamano;
        this.pos = pos;
        casillasOcupadas = new ArrayList<Baldosa> ();
    }

    public ContenidoPared(ContenidoPared cont) {
        id     = cont.id;
        tipo   = cont.tipo;
        tamano = cont.tamano;
        pos    = cont.pos;

        casillasOcupadas       = new ArrayList<Baldosa> ();
        Iterator<Baldosa> coIt = cont.casillasOcupadas.iterator();

        while (coIt.hasNext()) {
            casillasOcupadas.add(coIt.next());
        }
    }

    public TContenidoPared getTipoContenido() {
        return tipo;
    }

    public boolean esPuerta() {
        return tipo == TContenidoPared.PUERTA;
    }

    public int getTamano() {
        return tamano;
    }

    public int getPosicionX() {
        return pos.getX();
    }

    public int getPosicionY() {
        return pos.getY();
    }

    public Posicion getPosicion() {
        return pos;
    }

    public void setTipo(TContenidoPared tipo) {
        this.tipo = tipo;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public List<Baldosa> getCasillas() {
        return casillasOcupadas;
    }

    public void asignarCasilla(Baldosa baldosa) {
        casillasOcupadas.add(baldosa);
    }

    public void desasignarCasilla(Baldosa baldosa) {
        for (int i = 0; i < casillasOcupadas.size(); i++) {
            Baldosa ocupada = casillasOcupadas.get(i);
            if (ocupada.getX() == baldosa.getX() &&
                    ocupada.getY() == baldosa.getY()
               ) {

                casillasOcupadas.remove(baldosa);
                return;
            }
        }
    }

    public void desasignarTodas() {
        casillasOcupadas = new ArrayList<Baldosa> ();
    }

    public Objeto toObjeto() {
        Objeto objeto = new Objeto();
        objeto.setTipo("ContenidoPared");
        objeto.addAtributo("id", id);
        objeto.addAtributo("tipo", tipo);
        objeto.addAtributo("tamano", tamano);
        objeto.addAtributo("pos_x", pos.getX());
        objeto.addAtributo("pos_y", pos.getY());

        return objeto;
    }

    public String toString() {
        String text = tipo +" " + id;
        text += " (" + tamano + ") ";
        return text;
    }
}
