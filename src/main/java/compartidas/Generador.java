package compartidas;



//import compartidas.*;

/**
 * @author Sandra
 *
 */
/**
 * @author Grupo 41.1
 *
 */


import java.io.IOException;
import java.util.*;

public class Generador<T extends Contenido, E extends Casilla<T> > {

    private int tiempoMax;
    private int tiempoTranscurrido;


    //CONSTRUCTORA

    /**
     * @pre Cierto
     * @post Se crea un objeto de la clase generador.
     */
    public Generador() {
        tiempoMax = -1;
        tiempoTranscurrido = 0;
    }

    /**
     * @throws IOException
     * @throws NumberFormatException
     * @pre cjntContenido.size() != 0, el tiempo se define en min. si tiempo = -1 sin limite de tiempo.
     * @post Se genera una solucion a partir de la asignacion del cjnt de contenido a asignar en el contenedor cumpliendo con las restricciones propias de cada contenido. En el caso que no se pueda generar o se agote el tiempo retorna un error.
     */
    public void generar(Contenedor<T, E> contenedor, ArrayList<T> contenidos, int tiempo) throws Exception {

        List<T> cjntContAssig = new ArrayList<T>();
        tiempoMax = tiempo;
        Stack<T> contenidosSt = new Stack<T>();

        ListIterator<T> it = contenidos.listIterator(contenidos.size());
        while(it.hasPrevious()) {
            T t = it.previous();
            //System.out.println(t.id);
            contenidosSt.push(t);
        }

        try {
            backtracking(contenedor, contenidosSt, cjntContAssig);
        } catch (Exception e) {
            String message = e.getMessage();
            System.err.println(message);
            throw e;

        }
    }
    /**
     * @pre Cierto
     * @post Retorna cierto si el tiempo de ejecucion ha sido declarado como infinito o si tiempTranscurrido < tiempoMax.
     */
    private boolean tiempo() {
        tiempoTranscurrido++;
        if((tiempoMax != -1) && (tiempoTranscurrido > tiempoMax)) return false;
        else return true;
    }

    /**
     * @throws IOException
     * @throws NumberFormatException
     * @pre Cierto
     * @post Genera una solucion de la asignacion del conjunto de contenidos cumpliendo con sus restricciones. En caso que no se pueda generar, retorna un error.
     */
    private void backtracking(Contenedor<T, E> solucion, Stack<T> contenidos, List<T> cjntContAssig) throws Exception {

        if(tiempo()) {
            if(contenidos.isEmpty()) {
                System.out.println("Finalizado.");
                return;
            } else {

                T contenidoActual = contenidos.peek();
                contenidos.pop();


                for(int i = 0; i < solucion.getFilas(); i++) {
                    for(int j = 0; j < solucion.getColumnas(); j++) {

                        //miramos si la posicion no esta llena y si cabe el contenido que queremos anadir
                        if((!solucion.estaLlena(new Posicion(i, j))) && (solucion.cabeContenido(new Posicion(i, j), contenidoActual))) {
                            boolean anadido = solucion.anadirContenido(new Posicion(i, j), contenidoActual);
                            if(anadido) {
                                //solucion valida?
                                if(cumpleRestr(contenidoActual, cjntContAssig)) {
                                    cjntContAssig.add(contenidoActual);
                                    backtracking(solucion, contenidos, cjntContAssig);
                                    //SI la solucion no ha fallado, retornamos
                                    if(!solucion.tieneFallo()) {
                                        return;
                                    } else {
                                        boolean quitar = solucion.quitarContenido(new Posicion(i, j), contenidoActual);
                                        if(!quitar) System.out.println("error al desasignar contenido!");
                                        cjntContAssig.remove(contenidoActual);//sacamos de contenido asignado, el contenido actual
                                        solucion.resetFallo();
                                    }
                                } else {
                                    boolean quitado = solucion.quitarContenido(new Posicion(i, j), contenidoActual);
                                    if(!quitado) System.out.println("error al desasignar contenido!");
                                }
                            }
                        }

                    }

                }
                //NO SE ENCUENTRA SOLUCION PARA ESTA SOLUCION PARCIAL
                solucion.setFallo();
                contenidos.push(contenidoActual);

            }

            if(cjntContAssig.size() == 0) {
                //NO HAY SOLUCION
                throw new Exception("No existe solucion posible");

            } else return;

        }


        solucion.vaciarContenedor();
        throw new Exception("Tiempo agotado.");

    }

    /**
     * @throws IOException
     * @throws NumberFormatException
     * @pre contenidoActual es de tipo contenido.
     * @post Retorna cierto si el contenidoActual cumple sus restricciones.
     */
    @SuppressWarnings("unchecked")
    private boolean cumpleRestr(T contenidoActual, List<T> cjntContAssig) throws NumberFormatException, IOException {

        CjtoRestricciones restricciones = new CjtoRestricciones();
        return restricciones.satisfaceRestricciones(contenidoActual, (List<Contenido>) cjntContAssig);


    }


}