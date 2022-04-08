# DataCar --> Entrega 3

Esta aplicación será usada internamente por un taller para gestionar las reparaciones de sus clientes.
Habrá 3 tipos de usuarios, el admin, el trabajador del taller y el cliente.
Cada cliente tendrá acceso a una página donde verá sus vehículos y podrá chatear con el taller. Así mismo también podrá ver el estado de sus reparaciones.
Los trabajadores podrán chatear con los clientes y marcar los servicios de una reparación como completados.
También se ha añadido una prueba en src/test llamada anyadirCoche.feature que realiza un test de añadir el coche.


------------------ TODOS LOS USUARIOS ---------------------

### [inicio](http://localhost:8080/) 
Es la página que aparece por defecto cuando entras. Contiene información sobre el funcionamiento de la misma.

### [login](http://localhost:8080/login)
Un usuario de cualquier tipo (administrador, cliente o trabajador del taller) iniciará sesión introduciendo su correo y su contraseña.

### [Formulario de registro](http://localhost:8080/registro)
Un usuario de cualquier tipo (administrador, cliente o trabajador del taller) se registrará introduciendo su correo y una contraseña cualquiera.

### [Perfil de usuario](http://localhost:8080/profile)
En esta página se podrán ver y modificar los datos propios de cada usuario. En caso de ser un cliente, podrá ver un listado de sus coches en propiedad.


------------------ ADMINISTRADOR ---------------------

### [admin](http://localhost:8080/admin/)           
En esta página el administrador podrá gestionar a los trabajadores y a los clientes, añadiendo, eliminando o editando datos de estos manualmente cuando sea necesario.

### [editarInicio](http://localhost:8080/admin/editarInicio)
En esta página el adimistrador puede editar la pantalla de incio del taller.De momento sólo deja cambiar una fras de momento.

### [Gestión del taller](http://localhost:8080/taller)
En esta vista se va a poder gestionar todo lo relacionado con los servicios del taller, notificar al usuario de servicios finalizados, asignar trabajadores, iniciar chats con usuarios.


------------------ CLIENTE ---------------------

### [Mis vehículos](http://localhost:8080/misVehiculos)
En esta página el usuario podrá ver que vehículos tiene registrados en la página, podrá seleccionar cualquiera de ellos para ver su información relevante (redirige a vehiculoDetallado).

### [Vehículo detallado](http://localhost:8080/vehiculoDetallado)
En esta página se muestra toda la información relevante acerca del vehículo seleccionado, como la marca, el modelo, ITV, año de fabricación, matrícula, foto del vehículo y el historial de reparaciones.

### [Formulario de reparación](http://localhost:8080/reparaciones)
En esta página se van a rellenar los formularios de la reparación asignando los campos necesarios. Después se mostrará tanto en la vista taller como en la del propio usuario que reparaciones se están y se han realizado.

### [Reparaciones en curso](http://localhost:8080/reparacionesEnCursoCliente)
En esta página se van a ver todas las reparaciones asignadas al cliente.Esta vista se modificará llevándola a la página de reparaciones.



------------------ EMPLEADO ---------------------

### [Gestionar reparaciones](http://localhost:8080/user/gestionarReparaciones)
El mecánico dispone de un listado de todas las reparaciones que están disponibles, y él puede decidir si aceptar la reparación, por el contrario, rechazarla, si por ejemplo no dispone de tiempo suficiente para llevar a cabo la reparación antes de la fecha establecida por el cliente.


------------------ CLIENTE Y EMPLEADO ---------------------

### [chat](http://localhost:8080/chat)          
En esta página podrán chatear 2 usuarios entre sí. Por ejemplo, un trabajador del taller con un cliente. Próximamente aparecerá la imágen de cada usuario, así como su nombre de perfil. También se verá si un mensaje ha sido leído o no.



### -------------- NUEVOS CAMBIOS -------------------------


-------------------- EMPLEADO ----------------------------

Vista gestionarReparaciones.html:
    - Si se acepta o rechaza una reparación, y se quiere deshacer esta acción, ahora se puede ya que se ha añadido un botón para volver a poner la reparación en estado pendiente.
    - Al aceptar una reparación, ahora aparece un formulario que permite añadir todos los servicios que se deseen (con un botón hecho con ajax).

