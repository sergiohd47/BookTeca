# BookTeca
<p align="center">
  <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/imagenesWeb/logo.png">
</p>

## Indice
- [Video Explicativo](#id19) 
- [Introduccion](#id1)
- [Primera fase](#id2) 
    - [Segmentacion del acceso](#id3) 
    - [Entidades principales](#id4)
    - [Funcionalidad servicio interno](#id5) 
    - [Integrantes proyecto](#id6)
- [Segunda fase](#id7)
    - [Capturas pantalla](#id8)
    - [Diagrama Navegacion](#id9)
    - [Modelo Datos](#id10)
- [Tercera fase](#id11)
    - [Diagrama Navegacion](#id12)
    - [Virtualizacion](#id13)
    - [Servicio Interno](#id14)
- [Cuarta fase](#id15)
    - [Caches](#id16)
    - [Docker](#id17)
    - [Docker-Compose](#id18)
    
### Video Explicativo<a name="id19"></a>

En esta parte, se podra acceder a un video donde se explica el funcionamiento de nuestra pagina web, tanto el funcionamiento basico como la parte de la tolerancia a fallos.

<https://youtu.be/EmsmuqtaAa0>

 ### Introduccion<a name="id1"></a>
La pagina web BookTeca consiste en una pagina para la administracion de los recursos de nuestra biblioteca, la cual cuenta con el clasico servicio de prestamo de libros y revistas o peliculas, reserva de salas de trabajo en grupo o de equipos informaticos.
Los usuarios podran hacer reservas previas de libros, hacer un seguimiento de su prestamo o incluso reservar tanto equipos como salas desde su casa.

### Primera fase<a name="id2"></a>
#### Segmentacion del acceso<a name="id3"></a>
En la pagina web, segmentaremos el acceso a esta con dos partes bien distinguidas: una parte publica con acceso universal y una parte privada donde es necesario un registro.
   - ***Parte publica:*** se trata de una parte de acceso universal donde se puede ver una descripcion de nuestra bibiloteca, y diferentes aspectos como nuestra ubicacion o la manera de contactar con nosotros.  Ademas pueden ver nuestra coleccion de recursos disponibles.
   - ***Parte privada:*** parte de acceso restringida.  Podemos encontrar dentro de esta parte dos perfiles:
       - _Perfil usuario biblioteca:_ cada usuario de la biblioteca tendra una cuenta donde podran acceder a sus prestamos y consultar su estado. Ademas, podra hacer reservas online tanto de libros o revistas como de salas de trabajo o de equipos informaticos.
       - _Perfil administracion:_ usuario especial de la biblioteca, el cual actualiza el catalogo de recursos que tiene la biblioteca, pudiendo añadir libros o revistas o eliminar libros de nuestra coleccion.

#### Entidades principales<a name="id4"></a>
En cuanto a la estructura interna de la pagina web, podremos encontrar una serie entidades basicas. 

   - _Entidad Usuario Biblioteca:_ se trata del usuario registrado en la biblioteca. Este podra reservar los distintos recursos bibliograficos que existan en nuestra biblioteca. Ademas de este servicio, el usuario podra valorar y comentar los distintos recursos de la biblioteca, tanto libros, revistas como comentarios sobre las salas de trabajo o los distintos puestos informaticos que disponemos. Puede guardar una lista con sus libros y revistas favoritas.
   
   - _Entidad Administrador:_ se trata de un usario de la biblioteca con servicios especiales. Tiene la posibilidad de modificar la lista de recursos de la biblioteca, tanto eliminar como añadir nuevos.
   
   - _Entidad Libro:_ recurso bibliografico perteneciente a la categoria de libros, donde se podran buscar dependiendo de varios parametros. Los usuarios registrados podran ver su disponibilidad y podran realizar la reserva desde la web.
    Ademas, es posible que sean valorados y comentados por los usuarios o almacenados en la lista de favoritos personal de cada usuario.
   
   - _Entidad Revista:_ recurso perteneciente a la categoria de hemeroteca, donde habra una amplia coleccion de recursos de este tipo. Al igual que con los libros, se podran realizar reservas e incluso realizar valoraciones o incluirlos en listas de favoritos.
   
   - _Entidad Sala Trabajo Grupo:_ salas donde los usuarios podran realizar reuniones o trabajos de forma grupal. Dependiendo de los usuarios que vayan a estas salas, se asignaran unas o otras, dependiendo de la capacidad. El usuario, desde el servicio web, podra reservar una sala, ya que son limitadas, y elegir horario que mejor le convenga.
   
   - _Entidad Puesto Informatico:_ puestos informaticos disponibles para los usuarios. Estos puestos dependeran del tipo de SO que tengan (Windows, Linux o Mac OS). Al igual que las salas, se podran realizar reservas desde el servicio web, eligiendo el horario que mas comodo le parezca, ademas del SO que necesite.

#### Funcionalidad servicio interno<a name="id5"></a>
La funcionalidad principal del servicio interno de nuestra pagina web:
   - Envio de correo automatico relacionado con el estado de la reserva tanto de libros como de revistas (confirmacion de reserva, proxima finalizacion del prestamo, etc).
   - Envio de correo automatico para la confirmacion de una sala de trabajo en grupo como de un equipo informatico, haciendo un resumen de las fechas y el recurso asignado, ademas de los usuarios relacionados con esa reserva.
   
#### Integrantes proyecto<a name="id6"></a>
El equipo de desarrollo de la pagina web es:
   - ***Borja Martin Alonso***
      - _Correo URJC:_ b.martinal@alumnos.urjc.es
      - _Usuario GitHub:_ [BorjaPiruXx](https://github.com/BorjaPiruXx)
   - ***Daniel Molina Ballesteros***
      - _Correo URJC:_ d.molinab@alumnos.urjc.es
      - _Usuario GitHub:_ [danyelmb](https://github.com/danyelmb)
   - ***Sergio Hernandez Dominguez***
      - _Correo URJC:_ s.hernandezdo@alumnos.urjc.es
      - _Usuario GitHub:_ [sergiohd47](https://github.com/sergiohd47)
### Segunda fase<a name="id7"></a>
#### Capturas pantalla<a name="id8"></a>
   - ***Pantalla Inicio***
   
      En esta pantalla nos encontraremos cuando entremos en la pagina web. Ademas, podremos ver una seleccion de los libros y revistas del dia.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaInicio.png">
      </p>
      
  - ***Pantalla Libros***
  
      En esta pantalla tendremos un buscador donde podremos buscar nuestro libro favorito en funcion de su nombre, su autor o su editorial. En el caso de haber inciado sesion correctamente, podremos reservar los libros desde esta seccion.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaBuscadorLibrosUsuario.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaResultadoBuscadorLibro.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaResultadoLibroUsuario.png">
      </p>
  
  - ***Pantalla Revistas***
      
      Similar a la pantalla anterior de libros pero en este caso es con las diferentes revistas de nuestra biblioteca.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaBuscadorRevista.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaResultadoBuscadorRevista.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaResultadoRevistaUsuario.png">
      </p>
      
  - ***Pantalla Salas Trabajo Grupo***
  
      En esta pantalla podremos ver todas las Salas Trabajo Grupo que estan disponibles. Podremos ver de cada una su ubicacion, su capacidad y si es compartida o no. En caso de que seamos usuarios de BookTeca, podremos realizar la reserva de la sala.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaSalasTrabajoGrupo.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaSTGUsuario.png">
      </p>
 
 - ***Pantalla Equipos Informaticos***
 
      Similar a la pantalla anterior, pero en este caso podremos ver los equipos informaticos de nuestra coleccion. Al igual que antes, podremos realizar la reserva del equipo desde esta pagina.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaEquipoInformatico.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaEquipoUsuario.png">
      </p>
 - ***Pantalla Mi Perfil***
 
      En esta pantalla, un usuario podra ver el desglose de todas las reservas a su nombre o acceder a la zona donde podra editar la informacion relacionada con su perfil.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaMiPerfilUsuario.png">
      </p>
      
 - ***Pantalla Editar Perfil***
 
      En esta pantalla, se podra editar la informacion relacionada con el perfil (nombre completo y contraseña).
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaEditarPerfilUsuario.png">
      </p>
      
 - ***Pantalla Inicio Sesion***
 
      Esta es una pantalla de inicio de sesion clasica, donde el usuario debera introducir su email y la contraseña asociada a su cuenta. En caso de que el usuario sea administrador, al iniciar sesion se te redigira a una zona especial de administracion.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaIniciarSesion.png">
      </p>
      
  - ***Pantalla Registro***
  
       En esta pantalla, un usuario podra crearse una cuenta para poder acceder a todos los recursos de la BookTeca.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaRegistro.png">
      </p>
        
  - ***Pantalla Inicio Admin***
  
      En caso de que tu perfil sea de administrador, al realizar el inicio de sesion, se te redigira a la pantalla de inicio de administrador, donde se podra ver entera la coleccion de recursos que tiene la biblioteca.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaInicioAdmin.png">
      </p>
      
  - ***Pantalla Mi Perfil Admin***
      
      Siendo administrador de la pagina, podras entrar en tu perfil y aqui podras gestionar tanto los recursos de BookTeca como gestionar los diferentes usuarios, convirtiendolos en administrador.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaPerfilAdmin.png">
      </p>
      
  - ***Pantalla Gestion Recursos Admin***
      
      En esta parte se organizan las cuatro pantallas correspondientes a añadir recursos a la BookTeca. Desde aqui podremos añadir libros, revistas, salas y equipos informaticos, tan sencillo como incluyendo la informacion requerida para cada caso.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaAñadirEquipo.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaAñadirLibro.png">
         <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaAñadirRevista.png">
          <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaAñadirSala.png">
      </p>
      
 - ***Pantalla Gestion Usuarios Admin***
 
      Desde esta parte, podremos realizar una busqueda de usuarios en funcion de su correo. Posteriormente se mostrara el resultado y podriamos cambiar el rol de ese usuario, en caso de que este no sea administrador.
      <p align="center">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaAdministracionUsuarios.png">
        <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaResultadoAdministracionUsuarios.png">
      </p>
      
#### Diagrama Navegacion <a name="id9"></a>
Para la parte del diagrama de navegacion, hemos utilizado la aplicacion de prototipos "Marvel App". En esta pagina podras ir navegando y simulando la experiencia de nuestra web.
<p align="center">
  
   [PROTOTIPO DE NAVEGACION ENTRE PANTALLAS](https://marvelapp.com/4c45eaf)
          
</p>

En la parte del diagrama de navegacion podemos ver dos partes distinguidas claramente. Una zona de color verde, que es la que el acceso esta condicionado de que seas un usuario administrador y la otra zona azul que es la relacionada con un usuario basico de la pagina web.
Como aclaracion, las relaciones entre pantallas son bidireccionales, es decir, desde una pantalla podemos ir a la otra y posteriormente podremos volver a la pantalla anterior.

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/diagramaNavegacion.png">
</p>

#### Modelo Datos<a name="id10"></a>
En cuanto al modelo de datos, el diagrama relacionado con las diferentes clases de nuestro proyecto, es el siguiente:
<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/imagenesWeb/diagrama_clases_BookTeca.png">
</p>

### Tercera Fase<a name="id11"></a>

#### Diagrama Navegacion<a name="id12"></a>
En esta parte de la practica, hemos incluido seguridad y autenticacion a nuestra pagina, con el fin de actualizar y crear una pagina completamente segura.
En este diagrama se puede observar la organizacion que tendra nuestra aplicacion:
 - Una zona de color azul solido, la cual representa una zona totalmente publica
 - Una zona de color naranja solido, la cual representa una zona totalmente privada
 - Una zona de color degradado naranja-azul, donde se añaden funcionalidades en funcion de si estamos o no logeados en la           pagina 
<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/diagramaNavegacionSeguridad.png">
</p>

Como aclaracion, hemos añadido una pantalla de error para el caso de que salga error tanto en el ***servicio interno*** como en la ***pagina web***, desde donde podremos volver a la pantalla de inicio de la pagina web.

#### Virtualizacion<a name="id13"></a>
Como primera aclaracion, esta parte ha sido realizada en una maquina con sistema operativo macOS Mojave x64.
Primeramente, descargamos tanto ***VirtualBox*** como ***Vagrant*** en los siguientes link:
 - <https://www.virtualbox.org/wiki/Downloads>
 - <https://www.vagrantup.com/downloads.html>

Una vez descargado lo anterior, abrimos un terminal en la maquina local, y crearemos la maquina virtual. Iremos a la carpeta ***Vagrant*** donde se encuentran las maquinas virtual y creamos nuestra nueva maquina virtual:

`mkdir -p ~/vagrant/bookteca`

 `cd ~/vagrant/bookteca`

Creamos la maquina virtual Linux de 64 bits

`vagrant init ubuntu/trusty64`

Una vez creada, se creara automaticamente un fichero ***Vagrantfile*** donde tendremos que descomentar la siguiente linea:

`config.vm.network "private_network", ip: “192.168.33.10"`

Ademas de esto, dentro del mismo ***Vagrantfile***, añadimos las siguientes lineas de codigo, con el fin de darle a la maquina virtual creada 2GB de memoria RAM.

`config.vm.provider "virtualbox" do |v|
      v.memory = 2048
      v.cpus = 2
 end`
 
 Antes de arrancar la maquina virtual debemos tener copiados en esa carpeta ***bookteca*** de Vagrant, el siguiente material:
 - booktecaBBDD.sql
 - ServicioInterno-0.0.1-SNAPSHOT.jar
 - BookTeca-0.0.1-SNAPSHOT.jar
 
Primeramente, el fichero con extension ***.sql*** se consigue exportando desde el MySQLWorkBench el esquema de nuestra base datos creada en local, para posteriormente incluirla en nuestra maquina virtual.
En nuestro caos, debimos cambiar una linea del fichero ***.sql*** debido a fallos en la codificacion UTF-8 especifica, cambiando la linea:

`ENGINE=InnoDB DEFAULT CHARSET=utf8-especial....;`
 
 por la siguiente linea:
 
 `ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;`

Para crear ambos ficheros ***.jar***, desde dentro del STS, click derecho en cada proyecto y Run As>Maven build...>Goals: clean package. Posteriormente, dentro de la carpeta ***target*** de cada proyecto, tendremos los diferentes ficheros.

Una vez creada la maquina virtual y teniendo todos los ficheros necesarios copiados en la carpeta ***bookteca*** de Vagrant, arrancamos la maquina virtual:

`vagrant up`

Y nos conectamos a ella mediante:

`vagrant ssh`

Una vez dentro de la maquina virtual (lo sabremos viendo el terminal), necesitaremos instalar java, introduciendo:

`sudo apt-get update`

`sudo apt-get install default-jdk`

Nos movemos a la carpeta ***vagrant*** de la maquina y aqui veremos que tenemos los ficheros compartidos entre la maquina fisica y la virtual.

Seguidamente, instalamos mysql-server:

`sudo apt-get install mysql-server`

Una vez instalado el server de mysql, necesitamos crear nuestra base de datos, por lo tanto, accedemos a consola propia de mysql:

`mysql -u root -p`

Introducimos la contraseña y accederemos a la consola especifica de mysql. Aqui dentro:

`CREATE DATABASE bookteca;`

Ya hemos creado la base de datos para nuestra aplicacion, salimos e introducimos:

`mysql -u root -p bookteca < booktecaBBDD.sql`

De esta manera, volcaremos nuestro fichero ***.sql*** en la base de datos virtualizada, quedandose creadas las diferentes tablas y el esquema igual al que teniamos anteriormente en local.

Por ultimo, para evitar fallos de seguridad en el servicio interno, ya que se debe conectar pos SSL, introducimos:

`sudo apt-get install -y ca-certificates-java`

En caso de que nos salga error, porque ya estan presentes esos certificados, introducimos:

`sudo mkdir /etc/ssl/certs/java/`

Si falla, es porque ya los tenemos, entonces solo debemos actualizar esos certificados:

`sudo update-ca-certificates -f`

Finalmente, ejecutamos nuestros ficheros ***.jar*** :

`java -jar ServicioInterno-0.0.1-SNAPSHOT.jar & java -jar BookTeca-0.0.1-SNAPSHOT.jar`

Para comprobar el correcto funcionamiento de la app, nos vamos al navegador de nuestra maquina local y en la barra de busqueda ponemos lo siguiente:

`https://192.168.33.10:8443`

Si todo sale correcto, deberia aparecernos nuestra pagina principal de la pagina web.

#### Servicio Interno<a name="id14"></a>

El servicio interno de esta pagina web consiste en el envio automatico de correos a la hora de realizar cada una de las siguientes acciones:

 - Reserva/devolucion de un libro
 - Reserva/devolucion de una revista
 - Reserva/devolucion de una sala de trabajo en grupo
 - Reserva/devolucion de un equipo informatico
 
 Adicionalemente, tambien se realizara un envio de correo electronico cuando un administrador de nuestra pagina, haga administrador a ese usuario en cuestion.
 
<p align="center">
        <img width="700" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/CorreoBooktecaUsuario.PNG">
        <img width="200" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWeb/capturaReservaLibro.jpeg">
      </p>

### Cuarta Fase<a name="id15"></a>
#### Caches<a name="id16"></a>
En nuestra practica hemos activado tanto las caches de consultas como la cache de sesion para que el rendimiento sea mayor en las diferentes consultas de la base de datos. Se quedan cacheadas las siguientes consultas:
 - Libros
 - Usuarios
 - Revistas
 - Salas
 - Equipos Informaticos

Ademas de esto, se activa la cache de sesion entre los dos nodos del servicio web, por lo que en caso de que se produzca una caida en algun servicio, no se cierre la sesion, sino que puedas reanudar tu sesion, evitando asi, tener que iniciar sesion otra vez en la pagina.

#### Docker<a name="id17"></a>
Como anotacion para este apartado, tendremos en cuenta que la instalacion se ha realizado en un ordenador con sistema operativo MacOs Catalina.

Primeramente, instalamos el ***Docker Desktop*** del siguiente link:

 - <https://docs.docker.com/docker-for-mac/install/>
 
Una vez lo tengamos instalado, nos aparecera un menu como el siguiente, donde podremos ver que en realidad esta funcionando correctamente.

<p align="center">
  <img width="400" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaDockerDesktop.png">
</p>

Como nota muy importante a tener en cuenta, a la hora de descargarte el ***Docker Desktop***, viene incluido tanto el propio Docker como la funcionalidad de ***Docker Compose***, la cual nos sera muy util mas adelante.
Primeramente mostrar la estructura que tendra nuestro proyecto, el cual se encontrara formado por:

 - Un contenedor proxy que balanceara las peticiones a la web
 - Dos contenedores web
 - Un contenedor proxy que balanceara las peticiones al servicio interno
 - Dos contenedores servicio interno
 - Un contenedor base de datos BookTeca
 
 A continuacion podremos ver una representacion grafica de esta estructura:

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/esquemaGraficoProyecto.png">
</p>

Tras la explicacion de la estructura, nos pondremos con las instrucciones de docker. Primeramente, deberemos generar los ***.jar*** tanto de la web como del servicio interno, como fue explicado en la fase anterior, ademas de tener disponible el fichero ***.sql*** de la base de datos

Una vez tengamos esto, tendremos que generar los Dockerfile de la web como del servicio interno, con el fin de posteriormente generar una imagen basada en ese Dockerfile. Seguidamente tras realizar los Dockerfile de cada uno, abriremos una terminal y nos iremos a la direccion donde este el Dockerfile (web o servicio interno). Despues de esto, realizaremos la creacion de la imagen docker especifica tanto de la web como del servicio interno con:

`docker build -t imagen_web .` (web) 
`docker build -t imagen_sinterno .` (servicio interno)

Para los demas contenedores no realizaremos ninguna imagen, ya que usaremos las imagenes oficiales que aparecen en DockerHub (<https://hub.docker.com>), siendo necesario primero para acceder a ella, crear una cuenta en esta.
Tras esto, deberemos crear un fichero de configuracion del balanceador de carga (tanto del balanceador del servicio web como del balanceador del servicio interno), los cuales se pueden ver en la carpeta ***proxyWeb*** y ***proxyServicioInterno***

En el fichero haproxy.cfg relacionado con el balanceador del servicio interno no tenemos mucha complicacion, ya que solo tenemos que controlar el flujo entre los dos contenedores del servicio interno pero en el haproxy.cfg del balanceador de la web tenemos que elegir el tipo de seguridad que hay, siendo nuestro caso totalmente seguro desde la entrada al balanceador hasta la salida a la app (SSL PassThrough), utilizando un tutorial de una pagina web (<https://virtuallyhyper.com/2013/05/configure-haproxy-to-load-balance-sites-with-ssl/>)

Debido a este metodo de seguridad, el balanceador no necesita ningun certificado ***.pem*** especial, sino que se utiliza el de la aplicacion, dejando seguro todo el tramo balanceador-app.

Una vez tengamos las imagenes creadas (podemos verlo haciendo `docker images`), realizaremos la construccion y la ejecucion de todos los contenedores que necesitamos:

 - Contenedor balanceador de web: `docker run -p 8443:8443 --name contenedor_proxy_web -d haproxy:1.7`
 - Contenedor web 1: `docker run -e SPRING.DATASOURCE.URL="jdbc:mysql://172.17.0.2/bookteca?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" -e SPRING.DATASOURCE.PASSWORD="booktecan" -e SERVER.PORT=8444 -p 8444:8444 --name contenedor_web1 -d  imagen_web`
 - Contenedor web 2: `docker run -e SPRING.DATASOURCE.URL="jdbc:mysql://172.17.0.2/bookteca?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" -e SPRING.DATASOURCE.PASSWORD="booktecan" -e SERVER.PORT=8445 -p 8445:8445 --name contenedor_web2 -d  imagen_web`
 - Contenedor balanceador de servicio interno: `docker run -p 7000:7000 --name contenedor_proxy_sinterno -d haproxy:1.7`
 - Contenedor servicio interno 1: `docker run -e SPRING.DATASOURCE.URL="jdbc:mysql://172.17.0.2/bookteca?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" -e SPRING.DATASOURCE.PASSWORD="booktecan" -p 8070:8070  -d imagen_servicio_interno`
 - Contenedor servicio interno 2: `docker run -e SPRING.DATASOURCE.URL="jdbc:mysql://172.17.0.2/bookteca?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" -e SPRING.DATASOURCE.PASSWORD="booktecan" -p 8070:8070  -d imagen_servicio_interno`
 - Contendor base datos bookteca: `docker run -e  -p 33306:3306  -e MYSQL_ROOT_PASSWORD=booktecan -e MYSQL_DATABASE=bookteca -e MYSQL_USER=root -e MYSQL_PASSWORD=booktecan -d mysql:latest`
 
 Tras la ejecucion de esto y si todo ha ido bien, al realizar `docker ps -a` nos deberia mostrar todos los contenedores que tenemos activos, llevando todos la etiqueta de estado ***UP***. Posteriormente podemos acceder a cada uno con la siguiente instruccion:
 
 `docker exec -it nombreContenedor comandoAEjecutar` 
 (comando mas normal seria `bash` para acceder a la terminal del contenedor)
 
Gracias a esto, podremos configurar correctamente nuestra base de datos, como se explica en la fase anterior.

Tras la realizacion de todos estos pasos nuestra app deberia estar funcionando correctamente, pero teniendo en cuenta el orden que hemos seguido y la tecnica de Docker que hemos utilizado, cada cambio que se realice en codigo de app como de servicio interno, como en cada haproxy.cfg, es necesario:

 - Parar contenedor
 - Borrar contenedor
 - Borrar imagen
 - Crear imagen
 - Crear contenedor
 
Por esto, utilizaremos la tecnica de generar un fichero ***Docker Compose*** para poder automatizar el proceso aun mas.

#### Docker Compose<a name="id18"></a>

Como hemos comentado anteriormente, al tener el instalado el ***Docker Desktop***, tenemos instalado tambien la parte funcional de Docker relativa a Docker Compose. En caso de no tenerlo, seguir la documentacion oficial de Docker (<https://docs.docker.com/compose/install/>)

Docker Compose nos servira para automatizar el lanzamiento de todos los contenedores que formen parte de nuestro proyecto, sin tener que realizar borrados de contenedores ni de imagenes.

Para la creacion del fichero ***docker-compose.yml*** te puedes fijar en el nuestro que se llama igual, alojado en la raiz del proyecto.

Tras la creacion de este fichero y su implementacion, realizaremos su ejecucion. Primero abriremos una terminal y nos moveremos hasta el directo donde se encuentre el ficheoro ***.yml*** que queremos ejecutar. Seguidamente realizaremos:

`docker-compose up --build`

Es recomendable si es la primera vez que lo ejecutas, que le añadas el `--build`, para que regenere todo. Si no, simplemente escribe en la terminal `docker-compose up` y tendras todos tus contenedores que forman la app corriendo (puedes comprobar el estado ***UP*** de todos ellos con `docker ps -a` en otra terminal).

Tras esto, abrimos nuestro navegador y accediendo a la direccion _https://127.0.0.1:8443_ tendremos nuestra aplicacion corriendo perfectamente

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaInicioWeb.png">
</p>

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaConsultaBD.png">
</p>

Ademas de esto, podemos acceder a los diferentes balanceadores, con el fin de poder revisar las estadisticas que nos devuelven de los nodos balanceados:

 - Estadisticas balanceador web: _http://127.0.0.1:7777/admin?stats_
 - Estadisticas balanceador servicio interno: _http://127.0.0.1:9999/admin?stats_

Como nota, antes de entrar te pedira usuario y contraseña. Insertando en ambas ***bookteca*** podremos acceder a la pantalla de estadisticas.

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaWebFuncionando.png">
</p>

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaSInternoFuncionando.png">
</p>

Una vez visto que funciona, debemos de comprobar si el funcionamiento del balanceador es correcto. 
Como tenemos replicados los nodos de web y de servicio interno, esto nos proporcionara una tolerancia a fallos muy potente, permitiendo que nuestro proyecto entero siga funcionando en caso de que se pare alguno de los nodos de web o de servicio interno.

Para realizar esta prueba, tras correr correctamente el ***docker-compose.yml***, abrimos una terminal y tras listar los contenedores que estan activos, realizamos una parada de alguno, con:

`docker stop nombreContenedorParar`

Tras esto, si teniamos el navegador abierto con nuestra app, podremos ver como su funcionamiento es el mismo. Donde podremos notar el cambio es en la pagina de estadisticas de cada balanceador, donde nos señalara que nodo que balancea, esta caido.

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaTirandoNodoWeb.png">
</p>

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaTirandoNodoSInterno.png">
</p>

A pesar de esto, nuestra app seguira funcionando correctamente, al igual que el servicio interno, por lo que tendremos nuestra app con una alta tolerancia a fallos

<p align="center">
  <img width="800" height="500" src="https://github.com/sergiohd47/BookTeca/blob/master/capturasWebDocker/capturaFuncionandoSInterno.png">
</p>

 
