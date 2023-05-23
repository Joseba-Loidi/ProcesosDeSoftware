/* DELETE 'VideoClubDB' database*/
DROP SCHEMA IF EXISTS VideoClubDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'%';
/* CREATE 'VideoClubDB' DATABASE */
CREATE SCHEMA IF NOT EXISTS VideoClubDB;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'%' IDENTIFIED BY 'spq';
GRANT ALL ON VideoClubDB.* TO 'spq'@'%';
