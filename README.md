# DataCar

Esta aplicación será usada internamente por un taller para gestionar las reparaciones de sus clientes.
Habrá 3 tipos de usuarios, el admin, el trabajador del taller y el cliente.
Cada cliente tendrá acceso a una página donde verá sus vehículos y podrá chatear con el taller. Así mismo también podrá ver el estado de sus reparaciones.
Los trabajadores podrán chatear con los clientes y marcar los servicios de una reparación como completados.


### [admin](http://localhost:8080/admin/)           
En esta página el administrador podrá gestionar a los trabajadores y a los clientes, añadiendo, eliminando o editando datos de estos manualmente cuando sea necesario.

### [chat](http://localhost:8080/chat)          
En esta página podrán chatear 2 usuarios entre sí. Por ejemplo, un trabajador del taller con un cliente. Próximamente aparecerá la imágen de cada usuario, así como su nombre de perfil. También se verá si un mensaje ha sido leído o no.

### [inicio](http://localhost:8080/) 
Es la página que aparece por defecto cuando entras. Contiene información sobre el funcionamiento de la misma.

### [login](http://localhost:8080/login)
Un usuario de cualquier tipo (administrador, cliente o trabajador del taller) iniciará sesión introduciendo su correo y su contraseña.

### [Mis vehículos](http://localhost:8080/misVehiculos)
En esta página el usuario podrá ver que vehículos tiene registrados en la página, podrá seleccionar cualquiera de ellos para ver su información relevante (redirige a vehiculoDetallado).

### [Perfil de usuario](http://localhost:8080/profile)
En esta página se podrán ver y modificar los datos propios de cada usuario. En caso de ser un cliente, podrá ver un listado de sus coches en propiedad.

### [Formulario de registro](http://localhost:8080/registro)
Un usuario de cualquier tipo (administrador, cliente o trabajador del taller) se registrará introduciendo su correo y una contraseña cualquiera.

### [Formulario de reparación](http://localhost:8080/reparaciones)
En esta página se van a rellenar los formularios de la reparación asignando los campos necesarios. Después se mostrará tanto en la vista taller como en la del propio usuario que reparaciones se están y se han realizado.

### [Gestión del taller](http://localhost:8080/taller)
En esta vista se va a poder gestionar todo lo relacionado con los servicios del taller, notificar al usuario de servicios finalizados, asignar trabajadores, iniciar chats con usuarios.

### [Vehículo detallado](http://localhost:8080/vehiculoDetallado)
En esta página se muestra toda la información relevante acerca del vehículo seleccionado, como la marca, el modelo, ITV, año de fabricación, matrícula, foto del vehículo y el historial de reparaciones.
