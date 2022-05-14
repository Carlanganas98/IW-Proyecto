package external;

import com.intuit.karate.junit5.Karate;

class ExternalRunner {
    
  @Karate.Test
  Karate testLogin() {
   return Karate.run("login").relativeTo(getClass());
  }

  // // NO FUNCIONA
  // @Karate.Test
  // Karate testWs() {
  //     return Karate.run("ws").relativeTo(getClass());
  // }  

  @Karate.Test
  Karate tesAddCar() {
      return Karate.run("anyadirCoche").relativeTo(getClass());
  }

  // @Karate.Test
  // Karate tesReparaciones() {
  //     return Karate.run("operacionesReparacion").relativeTo(getClass());
  // }

}
