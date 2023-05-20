VIDEOCLUB
============================


Ejecute el siguiente comando para compilar todo y mejorar las clases de base de datos:

      mvn clean compile

Ejecute el siguiente comando para crear un esquema de base de datos:

      mvn datanucleus:schema-create

Para lanzar el servidor:

    mvn jetty:run

Para lanzar el cliente debemos de ejecutar este comando en otra ventana:

    mvn exec:java -Pclient

JUnit
============================

Para compilar y ejecutar las pruebas unitarias:

    mvn test

Limpiar el directorio, compilar y ejecutar todos los teses unitarios, además de actualizar JaCoCo:
    
    mvn clean test

Para realizar verificaciones adicionales en el artefacto construido:

    mvn verify

Para generar documentación e informes sobre el proyecto:

    mvn site
