FILES=`find ./src/main/java/* | grep -e "\.java"`
TEST_FILES=`find ./../tests | grep .xml | sort`
BUILD_DIR=build


all:
	rm -Rf $(BUILD_DIR)
	mkdir $(BUILD_DIR)
	javac -encoding ISO-8859-1 -sourcepath src/main/java -d $(BUILD_DIR) $(FILES)

jar: all
	cd $(BUILD_DIR); jar cfe ../habitaciones.jar habitaciones.dominio.controladores.test.TestInterfaz *
	cd ..

run: jar
	java -jar habitaciones.jar

consola:
	cd $(BUILD_DIR) && java habitaciones.dominio.controladores.CtrlPrincipal

test: all
	cd $(BUILD_DIR) && java habitaciones.dominio.controladores.test.Tester $(TEST_FILES)

#documentacion:
#	rm -Rf doc
#	mkdir doc
#	javadoc -encoding ISO-8859-1 -sourcepath src -d doc $(FILES)

#prueba:
#	cd $(BUILD_DIR) && java habitaciones.dominio.controladores.test.Prueba

#tester:
#	cd $(BUILD_DIR) && java habitaciones.dominio.controladores.test.Tester

interfaz:
	cd $(BUILD_DIR) && java habitaciones.dominio.controladores.test.TestInterfaz
