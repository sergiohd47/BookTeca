# BookTeca
<p align="center">
  <img width="400" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/imagenesWeb/logo.png">
</p>

## Indice
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
