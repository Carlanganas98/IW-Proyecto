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

  # login incorrecto, mostrando mensaje de error
  Scenario: login malo en plantilla
    Given driver baseUrl + '/login'
    And input('#username', 'dummy')
    And input('#password', 'world')
    * delay(1000)
    When submit().click(".form-signin button")
    Then match html('.error') contains 'Error en nombre de usuario o contraseña'
    * delay(2000)

  # login como cliente
  @login_b
  Scenario: login correcto como b
    Given driver baseUrl + '/login'
    And input('#username', 'b')
    And input('#password', 'aa')
    * delay(1000)
    When submit().click(".form-signin button")
    Then waitForUrl(baseUrl + '/cliente/2')
    * delay(2000)

  # login como empleado
  @login_d
  Scenario: login correcto como d
    Given driver baseUrl + '/login'
    And input('#username', 'd')
    And input('#password', 'aa')
    * delay(1000)
    When submit().click(".form-signin button")
    Then waitForUrl(baseUrl + '/empleado/4')
    * delay(2000)

  # login como admin
  @login_a
  Scenario: login correcto como a
    Given driver baseUrl + '/login'
    And input('#username', 'a')
    And input('#password', 'aa')
    * delay(1000)
    When submit().click(".form-signin button")
    Then waitForUrl(baseUrl + '/admin')
    * delay(2000)

  # login como admin, y después hacemos logout
  Scenario: logout after login
    Given driver baseUrl + '/login'
    And input('#username', 'a')
    And input('#password', 'aa')
    * delay(1000)
    When submit().click(".form-signin button")
    Then waitForUrl(baseUrl + '/admin')
    * delay(2000)
    When submit().click("{button}logout")
    Then waitForUrl(baseUrl + '/login')
    * delay(2000)
