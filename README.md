# BookTeca
<p align="center">
  <img width="460" height="300" src="https://github.com/sergiohd47/BookTeca/blob/master/imagenesWeb/logoBookTeca.png">
</p>

## Indice
- [Introduccion](#id1)
- [Primera fase](#id2) 
    - [Segmentacion del acceso](#id3) 
    - [Entidades principales](#id4)
    - [Funcionalidad servicio interno](#id5) 
    - [Integrantes proyecto](#id6)
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
