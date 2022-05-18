package external;

import com.intuit.karate.junit5.Karate;

class ExternalRunner {
  
  // NO FUNCIONA
  // @Karate.Test
  // Karate testWs() {
  //     return Karate.run("ws").relativeTo(getClass());
  // }  

  @Karate.Test
  Karate testLogin() {
   return Karate.run("login").relativeTo(getClass());
  }

  @Karate.Test
  Karate testCliente() {
      return Karate.run("operacionesCliente").relativeTo(getClass());
  }

  @Karate.Test
  Karate testEmpleado() {
      return Karate.run("operacionesEmpleado").relativeTo(getClass());
  }

}
