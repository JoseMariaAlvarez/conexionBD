# Ejemplo de uso de Hibernate con XML en Java
Ejemplo de uso de Hibernate con XML en Java.


El ejemplo está hecho mediante un proyecto Maven. En el archivo pom.xml están las dos dependencias del proyecto, hibernate y el driver para el manejo de la base de datos Oracle MySQL.

Está estructurado siguiendo la arquitectura MVC. Por cada una de las tres funciones principales, añadir un nuevo jugador, borrar un jugador y adscribir un jugador a un equipo o darlo de baja de un equipo, hay una clase que implementa el interfaz usando Swing y una clase controladora que atiende las ocurrencias de eventos. Las clases controladoras se encargan de modificar los objetos del modelo y actualizar coherentemente el contenido de la base de datos a través de una clase dedicada.

Las clases del modelo son dos arraylists, uno de equipos, con sus jugadores, y otro de jugadores que no están adscritos a ningún equipo.

Las clases que se encargan de comunicarse con la base de datos están implementadas siguiendo el patrón singular. Ofrecen un método por cada operación que puedan necesitar los requisitos funcionales. Una usa JDBC para abrir una conexión. En sus métodos se usan Statements y PreparedStatements para realizar dichas operaciones. La otra usa Hibernate con un intefaz similar. De hecho, hay definida una clase abstracta que especializan las dos.

No se ha usado ni el patrón Observador ni el Publicador/Suscriptor, que pueden mejorar la comunicación entre las clases de la vista y las de modelo.


En el archivo hibernate.cfg.xml se indica la configuración de la conexión a la base de datos (los campos de propiedades del tipo de base de datos, el driver del conector, el punto de conexión de la base de datos, la base de datos, el usuario y la contraseña) y cuáles son las clases que estarán bajo control de Hibernate. Por cada clase, se añade una línea con el archivo que contiene la configuración de la aplicación entre clases y tablas. El nombre del archivo es el de la clase con la extensión .hbm.xml.En ese archivo se indica la clase y la tabla relacionada, cuál es el atributo que hace de clave primeria y cuáles son el resto de atributos que se almacenarán en la base de datos y a qué columna se aplican.