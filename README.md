# PROP 41.2

Projecte del grup 41.2 de l'assignatura de PROP del quatrimestre de primavera de 2013

## Compilacion en Linux
Ejecutar el comando `$ make jar`

## Compilacion en Windows
Ejecutar el archivo `genera.bat`

Al acabar la compilacion por cualquiera de los 2 métodos se iniciara automaticamente un controlador en el que se pueden generar una habitacion y muebles y restricciones para probar el programa

### Ejecucion
  Para ejecutar basta con ejecutar el archivo *habitaciones.jar*. Para ello hay que ejecutar el comando `java -jar habitaciones.jar` estando en el directorio principal del proyecto, que es donde esta el archivo *habitaciones.jar*

## Uso del programa

Lo primero que vemos son 2 ventanas. Una es un menu con las acciones que se pueden realizar. La otra muestra los datos con los que se intentara generar la habitacion.

### Explicacion de las opciones
 - Crear habitacion: Para crear una habitacion nueva
 - Anadir mueble: Para anadir muebles
 - Borrar mueble: Para borrar muebles
 - Anadir restriccion: Para anadir restricciones entre muebles, puertas y ventanas
 - Borrar restriccion: Para borrar restricciones
 - Generar solucion: Para generar una distribucion de los muebles en la habitacion teniendo en cuenta las restricciones
 - Guardar habitacion: Para guardar los datos en un fichero XML
 - Cargar habitacion: Para cargar los datos desde un fichero XML
 - Borrar Todo: Elimina todos los datos y deja el programa en su estado inicial
 - Salir: Permite cerrar el programa

### Pruebas
Hay varios archivos XML guardados en el directorio test con los que se hacen distintas pruebas

 - no_generable_restricciones_contradictorias.xml: No puede generar la habitación porque las restricciones se contradicen
 - no_generable_no_mueble_mayor_habitacion.xml: Un mueble es mayor que el tamaño de la habitacion por lo que no se puede colocar y por tanto no se genera la habitacion
 - no_generable_no_cabe_mueble.xml: Los muebles individualmente cabrian, pero todos juntos no caben
 - no_generable_no_cumple_restr.xml: Los muebles por si solos cabrian en la habitacion, pero no es posible cumplir una restriccion
 - multiples_cambios_por_restr.xml: Genera la habitacion despues de hacer varios cambios varias veces
 - mueble_mismo_tamano_hab.xml: La habitacion tiene el mismo tamaño que el mueble, por lo que este ocupa toda la habitacion.
 - muebles_ocupan_toda_habitacion.xml: Diversos muebles ocupan toda la superficie de la habitacion
 
**NOTA** Estas pruebas se pueden ejecutar y comprobar de manera automática ejecutando el script
`$ ./run-tests.sh`

### Estructura de los archivos XML
Los archivos XML tendran la siguiente estructura:
  ```xml
  <?xml version="1.0" encoding="UTF-8" standalone="no"?>
  <root>
    <Habitacion>
        <filas>11</filas>
       <columnas>11</columnas>
    </Habitacion>
    <Mueble>
        <id>0</id>
        <color_red>111</color_red>
        <color_green>111</color_green>
        <tipo>MESA</tipo>
        <color_blue>111</color_blue>
        <anchura>1</anchura>
        <longitud>1</longitud>
    </Mueble>
    <Mueble>
        <id>1</id>
        <color_red>222</color_red>
        <color_green>222</color_green>
        <tipo>SILLA</tipo>
        <color_blue>222</color_blue>
        <anchura>1</anchura>
        <longitud>1</longitud>
    </Mueble>
    <Restriccion>
        <id>0</id>
        <distancia>7</distancia>
        <Tipo>DistanciaMin</Tipo>
        <objeto2_id>1</objeto2_id>
        <objeto1_id>0</objeto1_id>
    </Restriccion>
    <Restriccion>
        <id>1</id>
        <distancia>2</distancia>
        <Tipo>DistanciaMax</Tipo>
        <objeto2_id>1</objeto2_id>
        <objeto1_id>0</objeto1_id>
    </Restriccion>
  </root>
  ```

Para anadir un mueble o una restriccion basta con anadir una nueva seccion segun lo que se quiera anadir con todos los campos con sus correspondientes valores:
 - Para mueble:
  - id: Identificador del mueble. Un entero que debe ser unico
  - color_red: Valor entero de 0 a 255 que indica el rojo en sistema RGB
  - color_green: Valor entero de 0 a 255 que indica el verde en sistema RGB
  - color_blue: Valor entero de 0 a 255 que indica el azul en sistema RGB
  - tipo: El tipo de mueble. Puede ser MESA, SILLA...
  - anchura: numero de baldosas que ocupa el mueble a lo ancho
  - longitud: numero de baldosas que ocupa el mueble a lo largo

 - Para Restriccion:
  - id: Identificador de la restriccion. Un entero que debe ser unico
  - tipo: tipo de restriccion. Puede ser DistanciaMax, DistanciaMin, LimiteMax, LimiteMin
  - Si el tipo es DistanciaMax o DistanciaMin
   - distancia: Numero de baldosas que se tienen en cuenta en la restriccion
   - objeto1_id: identificador del primer objeto al que se aplica la restriccion.
   - objeto2_id: identificador del segundo objeto al que se aplica la restriccion. Debe ser diferente de objeto1_id
