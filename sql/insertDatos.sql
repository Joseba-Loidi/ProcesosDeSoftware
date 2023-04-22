use videoclubdb;

insert into admin values('admin1','admin1');
insert into admin values('admin2','admin2');

insert into user values('joseba', 'joseba.loidi@opendeusto.es', 'joseba');
insert into user values('paula', 'paula.asua1@opendeusto.es', 'paula');
insert into user values('iker', 'ikermarcelo@opendeusto.es', 'iker');
insert into user values('maria', 'maria.mardones@opendeusto.es', 'maria');

insert into pelicula values('1282h', 'ACCION', '160', 'Fast & Furius 8', '4');
insert into pelicula values('122hv', 'AVENTURA', '90', 'Super Mario Bros: La pelicula', '5');
insert into pelicula values('1228z', 'AVENTURA', '90', 'El rey Leon', '5');
insert into pelicula values('1567j', 'TERROR', '120', 'Smile', '3');

insert into user_peliculas values('joseba','1228z', '1');
insert into user_peliculas values('paula','122hv', '2');
insert into user_peliculas values('iker','1282h', '3');

select * from pelicula;