He creado la aplicación algo más compleja de lo que se pedía. Le he creado las cuatro operaciones básicas (CRUD) de un servicio
API REST con sus correspondientes JUnit. Las dependencias están gestionadas usando maven, con su debido pom.xml.

En esta aplicación, diseñada para ser una prueba técnica, se han decidido algunas cosas en su forma y diseño, las cuales se aclaran a continuación:

- En el ejercicio, para simplificarlo, he usado H2 en memoria en vez de una base de datos SQL. En una aplicación de uso real, lo haríamos con una base de datos SQL y JPA.

- En esta aplicación, debido a que todo está en local y es un prototipo para una prueba técnica, se ha usado http en vez de https para simplificarlo. 
  Sin embargo, para un entorno en producción deberíamos usar https con su correspondiente certificado SSL.

- Los datos iniciales se crean mediante un script, llamado script.sql en la ruta src/main/resources.

Para conectarse mediante un navegador a la base de datos en memoria H2:
- Conectarse por navegador a H2:
	http: //localhost:8080/h2-console
	user: sa
	password: password
