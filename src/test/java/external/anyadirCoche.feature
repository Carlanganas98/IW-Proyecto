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
    # login completado, ahora tengo que ir a la página donde se encuentra el menú principal
    Then click("a[id=rep]")
    And match html('title') contains 'IW: Menú principal'
    * delay(2000)

  @add_car
  Scenario: anyadir coche
    * click("button[id=AnyVehi]")
    * delay(2000)
    And input('#matricula', '3456W')
    Then click("input[id=coche]")
    And input('#modelo', 'Opel corsa')
    * delay(2000)
    Then click("button[id=anyadirVehiculo]")
    * delay(2000)
    Then match html('#Modelo3456W') contains 'Opel corsa'
    * delay(2000)
    Then click("button[id=AnyVehi]")
    * delay(2000)
    Then click("button[id=closeAnyadir]")
    And driver.screenshot()
    * delay(2000)
