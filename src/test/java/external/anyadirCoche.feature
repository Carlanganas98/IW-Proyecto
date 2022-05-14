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

  @login_b
  Scenario: login correcto como b
    Given driver baseUrl + '/login'
    And input('#username', 'b')
    And input('#password', 'aa')
    When submit().click(".form-signin button")
    Then waitForUrl(baseUrl + '/cliente/2')
    Then click("a[id=rep]")
    And match html('title') contains 'IW: Menú principal'
    * delay(1000)
    Then click("button[id=AnyVehi]")
    * delay(1000)
    And input('#matricula', '3456W')
    * delay(1000)
    Then click("input[id=coche]")
    * delay(1000)
    And input('#modelo', 'Opel corsa')
    * delay(1000)
    Then click("button[id=anyadirVehiculo]")
    * delay(1000)
    Then match html('#Modelo3456W') contains 'Opel corsa'
    * delay(1000)
    Then click("button[id=AnyVehi]")
    * delay(1000)
    Then click("button[id=closeAnyadir]")
    * delay(1000)
    And driver.screenshot()
    * delay(1000)
