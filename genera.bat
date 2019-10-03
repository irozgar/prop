@echo off

rmdir /S /Q .\bin

mkdir .\bin

cd .\src
for /r %%a in (*.java) do ( javac -encoding ISO-8859-1 -d ..\bin "%%a" )

cd ..\bin

jar cfe habitaciones.jar habitaciones.dominio.controladores.test.TestInterfaz .\*
move /y habitaciones.jar ..\habitaciones.jar

cd ..