# DataCar 

La finalidad de esta aplicación es ser usada internamente por los usuarios y trabajadores de un taller de vehículos.
Habrá 3 tipos de usuarios, el administrador, el trabajador del taller y el cliente del taller.

A continuación se explica la funcionalidad que puede realizar cada uno de los usuarios.
 
También se ha añadido una prueba en src/test llamada anyadirCoche.feature que realiza un test de añadir el coche.

# ------------ FUNCIONALIDAD COMUN A TODOS LOS USUARIOS 

### Página de inicio 
Es la página que aparece por defecto cuando entras. Contiene información sobre el funcionamiento de la misma. Además esta página puede ser modificada por los usuarios administradores y permite el uso MarkDown.

### Página de perfil de usuario
Se puede acceder a ella estando loggeado como cliente y como trabajador. Se accede pulsando en el icono de arriba a la derecha. Esta página varía en función del rol y contiene información básica sobre la persona registrada en ese momento.

# ------------ FUNCIONALIDADES DEL ADMINISTRADOR 

### Página del panel de administración
Desde esta página, el administrador podrá observar y modificar los datos de los usuarios, tanto de los trabajadores (rol empleado) como de los los clientes (rol cliente). También podrá eliminar cualquiera de ellos si se desea.

### Página de editar pantalla de inicio
Desde esta página el administrador podrá modificar la información que se muestra nada más lanzar la aplicacion, es decir la informacion que todo usuario (hasta los no registrados) puede visualizar. Además los campos de texto soportan MarkDOwn gracias a la librería https://github.com/commonmark/commonmark-java. 
El cambio de la imágen de perfil todavía no funciona (lo conseguiremos en los próximos días :) ).


# ------------ FUNCIONALIDADES DEL USUARIO CLIENTE 

El usuario cliente tiene una única vista desde donde realizará todas las operaciones. Hemos creído que era lo más conveniente para facilitar la experiencia de usuario. Se debe pulsar en el link del nav para acceder, ya que inicialmente sale la página del perfil de usuario.

![Screenshot 2022-04-26 at 21 32 01](https://user-images.githubusercontent.com/46989089/165377833-c27a2db8-b138-4762-bf7d-9244f46b859c.png)


En la parte superior, donde la Lista de Vehículos, aparecen los vehículos que el usuario ya añadió, además puede añadir nuevos vehículos pulsando en el botón verde del +.
Desde esta tabla también puede observar en detalle un vehículo, editar sus atributos, eliminarlo y solicitar una reparación. 
Al solicitar una reparación debe poner una fecha en la que puede dejar el vehículo en el taller e indicar brevemente qué le ocurre.

En la parte inferior el cliente puede ver el estado de las reparaciones solicitadas, junto con los servicios que se le están realizando. También puede iniciar un chat con el mecánico responsable de esa reparación. Hay un chat por reparación, no por mecánico ya que nos parece más oportuno, por lo que tambien se muestra información específica de esta reparacion, asi como una imagen del vehículo.

![Screenshot 2022-04-27 at 06 45 33](https://user-images.githubusercontent.com/46989089/165442491-dc547924-eaba-4fd9-92c4-ebd3918996af.png)

Si el usario no se encuentra en el chat, se le comunicara el mensaje via toast

Abajo a la izq esta el toast.

![Screenshot 2022-04-27 at 06 46 16](https://user-images.githubusercontent.com/46989089/165442564-7970807d-7847-403c-9718-e848d4913431.png)

Además cuando un servicio se marca como completado, se le emite una notificación vía toast al cliente (solo si el cliente se encuentra fuera del chat).

![Screenshot 2022-04-26 at 21 37 23](https://user-images.githubusercontent.com/46989089/165378768-41427b40-1bea-46d7-991e-45c217c5c2b7.png)


Tambien cuando una reparacion es finalizada , se le emite una notificación vía toast al cliente (solo si el cliente se encuentra fuera del chat).

![Screenshot 2022-04-26 at 21 37 41](https://user-images.githubusercontent.com/46989089/165378815-9914d4ac-e6c6-428a-b263-3d5d0031862e.png)


Para probar el chat y las notificaciones, recomendamos abrir 2 navegadores distintos, uno con el rol de cliente y otro con el rol de empleado vía los botones de debug.

# ------------ FUNCIONALIDADES DEL USUARIO EMPLEADO 


El usuario empleado tiene una única vista desde donde realizará todas las operaciones. Hemos creído que era lo más conveniente para facilitar el uso de la web por los empleados del taller. Se debe pulsar en el link del nav para acceder, ya que inicialmente sale la página del perfil de usuario.

![Screenshot 2022-04-26 at 21 38 34](https://user-images.githubusercontent.com/46989089/165378967-552e96eb-cf16-434b-812f-9ed66fc91e7d.png)


En la parte superior están las reparaciones que ese empleado ha aceptado y está llevando a cabo. A estas reparaciones se le pueden añadir servicios, marcalos como completados (con la notificación al usuario correspondiente) y marcar como finalizada la reparación (con la notificación al usuario correspondiente). También se puede iniciar un chat con el cliente en caso de ser necesario.


En la parte inferior aparecen todas las reparaciones que han llegado al taller, pero que no han sido aceptadas por ningún mecánico. 
Una vez que un mecánico las acepta, pasan a la tabla superior y se le asignan a ese mecánico. 
También se puede rechazar una reparación, por ser inviable y no le aparecerá a ningún mecánico.  Tenemos pensado seguir añadiendo funcionalidad a esta vista, haciendo que cuando una reparación es rechazada, se le indique al cliente el porqué de ese rechazo.

# TESTS

Para finalizar, destacar que se ha realizado una prueba externa con Karate. Se prueba a añadir un nuevo vehiculo como usuario registrado CLIENTE y se valida posteriormente que los datos del vehiculo añadido son los correctos.



