Feature: operaciones mas importantes sobre los clientes

# Este test consiste en que el usuario con rol 'cliente' realice distintas operaciones
# sobre sus vehículos y sobre las reparaciones que ha solicitado

  Background: 
    # login del cliente 'b'
    * call read('login.feature@login_b')
    # login completado, ahora pulso el link del header 'Menu Principal - REPARACIONES' para ir a la página donde se encuentra el menú principal
    Then click("a[id=rep]")
    # And match html('title') contains 'IW: Menú principal'
    * delay(2000)

  @anyadirCoche
  Scenario: anyadir coche
    # Pulso el botón verde con un '+' que se encuentra en 'Lista de vehiculos'
    * click("button[id=AnyVehi]")
    * delay(2000)
    # Completo los campos
    And input('#matricula', '3456W')
    Then click("input[id=coche]")
    And input('#modelo', 'Opel corsa')
    * delay(2000)
    # Pulso el botón 'Añadir'
    Then click("button[id=anyadirVehiculo]")
    * delay(2000)
    Then match html('#Modelo3456W') contains 'Opel corsa'
    And driver.screenshot()
    * delay(2000)

  @solicitarReparacion
  Scenario: solicitar una reparacion
      # Pulso el botón 'Solicitar Reparacion', de la 'Lista de vehiculos'
      * click("button[id=solicitar1]")
      * delay(1000)
      # Introduzco los campos necesarios
      * input('#fechaFin1', '20/05/2022')
      * input('#descripcion1', 'Me he quedado sin retrovisor')
      * delay(2000)
      # Envío la solicitud pulsando el botón 'Guardar'
      * click("button[id=enviarSolicitud1]")
      * delay(2000)
      # Hago scroll hacia abajo para ver que se ha añadido correctamente la reparación a la lista
      * scroll('#tablaListaReparaciones')
      * delay(2000)
