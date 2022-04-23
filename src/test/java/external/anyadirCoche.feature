Feature: login en servidor

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
    And match html('title') contains 'IW: Mis Vehiculos'
    Then click("button[id=AnyVehi]")
    * delay(1000)
    And input('#matricula', '3456W')
    * delay(1000)
    Then click("input[id=coche]")
    And input('#modelo', 'Opel corsa')
    * delay(1000)
    Then click("button[id=anyadirVehiculo]")
    * delay(1000)
    Then click("button[id=closeAnyadir]")
    * delay(1000)
    Then match html('#Modelo3456W') contains 'Opel corsa'
    And driver.screenshot()




  Scenario: logout after login
    Given driver baseUrl + '/login'
    And input('#username', 'a')
    And input('#password', 'aa')
    When submit().click(".form-signin button")
    Then waitForUrl(baseUrl + '/admin')
    When submit().click("{button}logout")
    Then waitForUrl(baseUrl + '/login')
