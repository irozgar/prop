FILES=`find ./* | grep .java`

all:
	rm -Rf bin
	mkdir bin
	javac -encoding ISO-8859-1 -sourcepath src -d bin $(FILES)

jar: all
	cd bin; jar cfe ../habitaciones.jar habitaciones.dominio.controladores.test.TestInterfaz *
	cd ..

run: jar
	java -jar habitaciones.jar

consola:
	cd bin && java habitaciones.dominio.controladores.CtrlPrincipal

#documentacion:
#	rm -Rf doc
#	mkdir doc
#	javadoc -encoding ISO-8859-1 -sourcepath src -d doc $(FILES)

#prueba:
#	cd bin && java habitaciones.dominio.controladores.test.Prueba

#tester:
#	cd bin && java habitaciones.dominio.controladores.test.Tester

interfaz:
	cd bin && java habitaciones.dominio.controladores.test.TestInterfaz
