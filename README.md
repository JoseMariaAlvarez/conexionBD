# EjemploJDBC
Ejemplo de uso de JDBC con Java.

Se ha usado un proyecto Maven para gestionar más fácilmente las dependencias. En el archivo pom.xml están las dos dependencias del proyecto, hibernate y el driver para el manejo de la base de datos Oracle MySQL.

Está estructurado siguiendo la arquitectura MVC. Por cada una de las tres funciones principales, añadir un nuevo jugador, borrar un jugador y adscribir un jugador a un equipo o darlo de baja de un equipo, hay una clase que implementa el interfaz usando Swing y una clase controladora que atiende las ocurrencias de eventos. Las clases controladoras se encargan de modificar los objetos del modelo y actualizar coherentemente el contenido de la base de datos a través de una clase dedicada.

Las clases del modelo son dos arraylists, uno de equipos, con sus jugadores, y otro de jugadores que no están adscritos a ningún equipo.

Las clases que se encargan de comunicarse con la base de datos están implementadas siguiendo el patrón singular. Ofrecen un método por cada operación que puedan necesitar los requisitos funcionales. Una usa JDBC para abrir una conexión. En sus métodos se usan Statements y PreparedStatements para realizar dichas operaciones. La otra usa Hibernate con un intefaz similar. De hecho, hay definida una clase abstracta que especializan las dos.

No se ha usado ni el patrón Observador ni el Publicador/Suscriptor, que pueden mejorar la comunicación entre las clases de la vista y las de modelo.

JDBC es la solución de nivel más bajo para gestionar la relación entre objetos y tablas de la base de datos. En este caso, cada vez que un objeto es creado, modificado o borrado en la aplicación se llama al correspondiente método para que ejecute una sentencia sobre la base de datos para actualizar las tablas.
