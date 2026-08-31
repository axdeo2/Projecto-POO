package espol.poo.juego;
public class Guerrero extends Personaje {

    public Guerrero(String nombre, int vida, int ataque, int defensa) {
        super(nombre, vida, ataque, defensa);
    }

    @Override
    public void usarEstrategia() {
        this.ataque *= 2;
        System.out.println(this.nombre + " ha usado su estrategia. ¡Su ataque se ha duplicado a " + this.ataque + "!");
        
        // Nota: Como la clase Personaje no maneja estados de turnos temporales, 
        // este aumento será permanente a menos que la lógica principal (main) 
        // lo divida entre 2 al finalizar el turno.
    }
}
