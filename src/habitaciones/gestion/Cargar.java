package habitaciones.gestion;

import java.io.*;
import java.util.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import compartidas.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.restricciones.*;

import habitaciones.dominio.modelos.enums.*;
import compartidas.*;

public class Cargar {
    private ArrayList<Objeto> lista;
    private Habitacion habitacion;
    private ArrayList<Mueble> muebles;
    private ArrayList<RestriccionDistancia> rDist;
    private ArrayList<RestriccionLimite> rLim;
    private ArrayList<ContenidoPared> contenidosPared;

    public Cargar(String path) throws java.io.FileNotFoundException {
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            llenarLista(doc);
            asignarObjetos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    public ArrayList<RestriccionDistancia> getRestriccionesDist() {
        return rDist;
    }

    public ArrayList<ContenidoPared> getContenidosPared() {
        return contenidosPared;
    }

    public ArrayList<Objeto> getLista() {
        return lista;
    }

    private void llenarLista(Document doc) {
        lista = new ArrayList<Objeto>();
        Node root = doc.getElementsByTagName("root").item(0);
        Node nodeObjeto = root.getFirstChild();
        while (nodeObjeto != null) {
            Objeto o = new Objeto();
            o.setTipo(nodeObjeto.getNodeName());
            Node nodeAtributo = nodeObjeto.getFirstChild();
            while (nodeAtributo != null) {
                String atributo = nodeAtributo.getNodeName();
                String valor = nodeAtributo.getTextContent();
                o.addAtributo(atributo, valor);
                nodeAtributo = nodeAtributo.getNextSibling();
            }
            lista.add(o);
            nodeObjeto = nodeObjeto.getNextSibling();
        }
    }

    private void asignarObjetos() {
        muebles = new ArrayList<Mueble>();
        rDist = new ArrayList<RestriccionDistancia>();
        contenidosPared = new ArrayList<ContenidoPared>();
        Iterator<Objeto> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Objeto objeto = iterator.next();
            if (objeto.getTipo().equals("Habitacion"))
                habitacion = getHabitacion(objeto);
            if (objeto.getTipo().equals("Mueble"))
                muebles.add(getMueble(objeto));
            if (objeto.getTipo().equals("Restriccion"))
                rDist.add(getRestriccion(objeto));
            if (objeto.getTipo().equals("ContenidoPared"))
                contenidosPared.add(getContenidoPared(objeto));
        }
    }

    private Habitacion getHabitacion(Objeto objeto) {
        int filas = Integer.parseInt((String)objeto.getValor("filas"));
        int columnas = Integer.parseInt((String)objeto.getValor("columnas"));
        return new Habitacion(filas, columnas);
    }

    private Mueble getMueble(Objeto objeto) {
        int id = Integer.parseInt((String)objeto.getValor("id"));
        int longitud = Integer.parseInt((String)objeto.getValor("longitud"));
        int anchura = Integer.parseInt((String)objeto.getValor("anchura"));
        String stipo = objeto.getValor("tipo").toString();
        TMueble tipo = TMueble.DESCONOCIDO;
        if (stipo.equals("MESA")) tipo = TMueble.MESA;
        else if (stipo.equals("MESA")) tipo = TMueble.MESA;
        else if (stipo.equals("SILLA")) tipo = TMueble.SILLA;
        else if (stipo.equals("ARMARIO")) tipo = TMueble.ARMARIO;
        else if (stipo.equals("ELECTR_COCINA")) tipo = TMueble.ELECTR_COCINA;
        else if (stipo.equals("CAMA")) tipo = TMueble.CAMA;

        int red   = Integer.parseInt((String)objeto.getValor("color_red"));
        int green = Integer.parseInt((String)objeto.getValor("color_green"));
        int blue  = Integer.parseInt((String)objeto.getValor("color_blue"));
        // TODO el cast del tipo de mueble
        return new Mueble(id, tipo, new Color(red,green,blue), longitud, anchura);
    }

    private RestriccionDistancia getRestriccion(Objeto objeto) {
        int id = Integer.parseInt((String)objeto.getValor("id"));
        String tipo = objeto.getValor("Tipo").toString();
        if (tipo.equals("DistanciaMin") || tipo.equals("DistanciaMax")) {
            int distancia = Integer.parseInt((String) objeto.getValor("distancia"));
            int id1 = Integer.parseInt((String) objeto.getValor("objeto1_id"));
            int id2 = Integer.parseInt((String) objeto.getValor("objeto2_id"));
            boolean fm1 = false;
            boolean fm2 = false;
            Mueble m1 = new Mueble();
            Mueble m2 = new Mueble();

            // Busca los muebles
            Iterator<Mueble> mueblesIt = muebles.iterator();
            while (mueblesIt.hasNext() && (!fm1 || !fm2)) {
                Mueble mueble = mueblesIt.next();
                if (mueble.getId() == id1) {
                    m1 = mueble;
                    fm1 = true;
                } else if (mueble.getId() == id2) {
                    m2 = mueble;
                    fm2 = true;
                }
            }

            // Muebles encontrados reccreamos la restriccion
            if (fm1 && fm2) {
                if (tipo.equals("DistanciaMin")) {
                    return new RestriccionDistanciaMinima(id, distancia, m1, m2);
                } else {
                    return new RestriccionDistanciaMaxima(id, distancia, m1, m2);
                }
            }
        }

        return null;
    }

    private ContenidoPared getContenidoPared(Objeto objeto) {
        int id = Integer.parseInt((String)objeto.getValor("id"));
        String stipo = objeto.getValor("tipo").toString();
        TContenidoPared tipo;
        if (stipo.equals("PUERTA")) tipo = TContenidoPared.PUERTA;
        else tipo = TContenidoPared.VENTANA;

        int tamano = Integer.parseInt((String) objeto.getValor("tamano"));
        int pos_x  = Integer.parseInt((String) objeto.getValor("pos_x"));
        int pos_y  = Integer.parseInt((String) objeto.getValor("pos_y"));

        return new ContenidoPared(id, tipo, tamano, new Posicion(pos_x, pos_y));
    }
}
