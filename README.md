Jersey + DataNucleus + MySQL
============================

This example relies on the DataNucleus Maven plugin. Check the database configuration in the *datanucleus.properties* file and the JDBC driver dependency specified in the *pom.xml* file. In addition, the project contains the server and client example codes.

Run the following command to build everything and enhance the DB classes:

      mvn clean compile

Run the following command to create database schema for this sample.

      mvn datanucleus:schema-create

To launch the server run the command

    mvn jetty:run

Now, the client sample code can be executed in a new command window with

    mvn exec:java -Pclient

