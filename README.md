VIDEOCLUB
============================


Ejecute el siguiente comando para compilar:

      mvn clean compile

Mejorar las clases de base de datos:
      
      mvn datanucleus:enhance
      
Ejecute el siguiente comando para crear un esquema de base de datos:
      
      mvn datanucleus:schema-create

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

Para realizar verificaciones adicionales en el artefacto construido:

    mvn verify

Para generar documentación e informes sobre el proyecto:

    mvn site
    
Las pruebas de integración se pueden realizar con el siguiente comando. Se lanzará un servidor HTTP Grizzly incorporado para realizar llamadas reales a la API REST y a la base de datos MySQL.
    
    mvn verify -Pintegration-tests
    
Las pruebas de rendimiento se pueden realizar con el siguiente comando.

	mvn verify -Pperformance-tests

Generar documentación de Javadoc

	mvn javadoc:javadoc

