import java.util.Random;
import java.util.Scanner;

public class Místico extends Personaje {

    public Místico(String nombre, int vida, int ataque, int defensa) {
        super(nombre, vida, ataque, defensa);
    }

    // Implementación obligatoria del método abstracto de la clase padre
    @Override
    public void usarEstrategia() {
        System.out.println(this.nombre + " necesita conocer el daño acumulado por su equipo para usar su estrategia.");
    }

    // Sobrecarga del método para recibir la información necesaria
    public void usarEstrategia(int danoAcumuladoEquipo) {
        if (!this.estaVivo()) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int numeroSecreto = random.nextInt(6) + 1; // Valor entre 1 y 6
        int eleccionJugador = 0;

        System.out.println(this.nombre + " está canalizando las energías... ¡Adivina el número del 1 al 6!");
        System.out.print("Introduce tu elección: ");
        
        // Validación básica para evitar errores de entrada
        if (scanner.hasNextInt()) {
            eleccionJugador = scanner.nextInt();
        } else {
            scanner.next(); // Limpiar entrada inválida
        }

        if (eleccionJugador == numeroSecreto) {
            this.ataque += danoAcumuladoEquipo;
            System.out.println("¡Sintonía perfecta! El número era " + numeroSecreto + ".");
            System.out.println(this.nombre + " ha absorbido el daño infligido por su equipo (" + danoAcumuladoEquipo + ").");
            System.out.println("¡Su ataque ha incrementado temporalmente a " + this.ataque + "!");
            
            // Nota: Al igual que en Guerrero, como Personaje no maneja la duración de turnos,
            // la lógica principal del juego (main) deberá encargarse de restar este 'danoAcumuladoEquipo'
            // del ataque del Místico una vez que finalice su turno para que sea estrictamente temporal.
        } else {
            System.out.println("Visión nublada. El número era " + numeroSecreto + " y elegiste " + eleccionJugador + ".");
            System.out.println("La estrategia de " + this.nombre + " ha fallado.");
        }
    }
}

