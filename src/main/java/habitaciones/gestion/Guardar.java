package habitaciones.gestion;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.restricciones.*;
import compartidas.*;

public class Guardar {
    private ArrayList<Objeto> list;

    public Guardar() {
        list = new ArrayList<Objeto>();
    }

    public void anadirObjeto(Objeto o) {
        list.add(o);
    }

    public static void guardar(String path,
                               Habitacion habitacion,
                               ArrayList<Mueble> muebles,
                               ArrayList<RestriccionDistancia> rDist,
                               ArrayList<RestriccionLimite> rLimite,
                               ArrayList<ContenidoPared> contenidosPared
                              ) throws java.io.FileNotFoundException {

        ArrayList<Objeto> list = new ArrayList<Objeto>();
        list.add(habitacion.toObjeto());
        for (Mueble mueble : muebles) {
            list.add(mueble.toObjeto());
        }

        for (RestriccionDistancia restr : rDist) {
            list.add(restr.toObjeto());
        }

        for (ContenidoPared cp : contenidosPared) {
            list.add(cp.toObjeto());
        }

        crear(path, list);
    }

    public void guardar(String path) {
        crear(path, list);
    }

    private static void crear(String path, ArrayList<Objeto> lista) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            for (int i = 0; i < lista.size(); i++) {
                Element element = doc.createElement(lista.get(i).getTipo());
                rootElement.appendChild(element);
                Set<String> atrs = lista.get(i).getAtributos();
                Iterator<String> it_atrs = atrs.iterator();
                while (it_atrs.hasNext()) {
                    String atributo = it_atrs.next();
                    Element atr = doc.createElement(atributo);
                    String valor = lista.get(i).getValor(atributo).toString();
                    atr.appendChild(doc.createTextNode(valor));
                    element.appendChild(atr);
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
