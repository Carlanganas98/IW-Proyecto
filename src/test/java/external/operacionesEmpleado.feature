Feature: operaciones sobre las reparaciones

# Este test consiste en que el usuario con rol 'empleado' realice distintas operaciones
# sobre las reparaciones que solicitan los clientes

Background: acceder a la pagina 'Gestionar reparaciones'
    # login del empleado:
    * call read('login.feature@login_d')
    # una vez logueado, voy a la página donde se encuentran las reparaciones:
    * click("a[id=repEmpleado]")
    * delay(2000)

# @aceptarReparacion
# Scenario: aceptar reparacion
#     * click("button[id=accept2]")
#     * delay(2000)
#     * click("button[id=modalAccept2]")
#     * delay(2000)

@anyadirServicio
Scenario: anyadir un servicio a una reparacion
    * click("button[id=addServices2]")
    * delay(2000)
    * click("input[id=newService2]")
    * delay(2000)
    * input('[name=info]', 'Cambiar parabrisas')
    * input('[name=precio]', '100')
    * delay(2000)
    * click("button[id=modalAddServices2]")
    * delay(2000)
