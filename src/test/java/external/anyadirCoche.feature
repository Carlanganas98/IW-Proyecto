Feature: anyadir coche

#
#  Este test funciona, pero no es de buena educación martillear una API externa
#
#  Scenario: login malo en github
#    Given driver 'https://github.com/login'
#    And input('#login_field', 'dummy')
#    And input('#password', 'world')
#    When submit().click("input[name=commit]")
#    Then match html('.flash-error') contains 'Incorrect username or password.'
#

  Background: 
    # login del cliente 'b'
    * call read('login.feature@login_b')
    # login completado, ahora pulso el link del header 'Menu Principal - REPARACIONES' para ir a la página donde se encuentra el menú principal
    Then click("a[id=rep]")
    And match html('title') contains 'IW: Menú principal'
    * delay(2000)

  @add_car
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
