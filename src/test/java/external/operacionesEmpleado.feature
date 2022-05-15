Feature: operaciones sobre las reparaciones

# Este test consiste en que el usuario con rol 'empleado' realice distintas operaciones
# sobre las reparaciones que solicitan los clientes

Background: acceder a la pagina 'Gestionar reparaciones'
    # login del empleado:
    * call read('login.feature@login_d')
    # una vez logueado, voy a la página donde se encuentran las reparaciones, pulsando el link 'Gestionar reparaciones' que se encuentra en la cabecera
    * click("a[id=repEmpleado]")
    * delay(2000)

# este test solo se puede probar una vez, ya que solo se puede aceptar una reparación una única vez
@aceptarReparacion
Scenario: aceptar reparacion
    # en el listado 'Listado de reparaciones disponibles en el taller', pulso el botón verde con el 'tick'
    * click("button[id=accept2]")
    * delay(2000)
    * click("button[id=modalAccept2]")
    * delay(2000)

@anyadirServicio
Scenario: anyadir un servicio a una reparacion
    # en el listado 'Listado de tus reparaciones', pulso el botón '+'
    * click("button[id=addServices2]")
    * delay(2000)
    # una vez abierto el modal, pulso el botón 'Introducir nuevo servicio'
    * click("input[id=newService2]")
    * delay(2000)
    * input('[name=info]', 'Cambiar parabrisas')
    * input('[name=precio]', '100')
    * delay(2000)
    # pulso el botón 'Aceptar'
    * click("button[id=modalAddServices2]")
    * delay(2000)

@mensajeCliente
Scenario: comunicacion con cliente a traves del chat
    # en el listado 'Listado de tus reparaciones', pulso en el icono del mensaje (columna 'Chat con el cliente')
    * click("a[id=chat1]")
    * waitForUrl(baseUrl + '/chat/1')
    * delay(2000)
    * input("#messageBar", "Buenos días, puedes venir mañana ya a recoger el coche")
    * delay(1000)
    # pulso el botón 'Enviar'
    * click("button[id=sendMessage]")
    * delay(2000)
