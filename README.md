VIDEOCLUB
============================


Ejecute el siguiente comando para compilar:

      mvn clean compile

Para crear la base de datos y el usuario con ciertos permisos en el servidor local (Password=root):
      
      mysql –uroot -p < sql/db.sql

Mejorar las clases de base de datos:
      
      mvn datanucleus:enhance
 
Ejecute el siguiente comando para crear un esquema de base de datos:
      
      mvn datanucleus:schema-create

Para insertar datos de ejemplo, usar el siguiente comando:

	  mysql –uroot -p < sql/insertDatos.sql

Para lanzar el servidor:

      mvn jetty:run

Para lanzar el cliente debemos de ejecutar este comando en otra ventana:

      mvn exec:java -Pclient

Test
============================

Para compilar y ejecutar las pruebas unitarias:

    mvn test

Limpiar el directorio, compilar y ejecutar todos los teses unitarios, además de actualizar JaCoCo:
    
    mvn clean test
    
Las pruebas de integración se pueden realizar con el siguiente comando. Se lanzará un servidor HTTP Grizzly incorporado para realizar llamadas reales a la API REST y a la base de datos MySQL.
    
    mvn verify -Pintegration-tests
    
Las pruebas de rendimiento se pueden realizar con el siguiente comando.

	mvn verify -Pperformance-tests

Generar documentación de Javadoc

	mvn javadoc:javadoc

