import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Equipo {
    private String nombre;
    private List<Personaje> personajes;
    private int danoAcumuladoEquipo; // Para la estrategia del Místico

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
        this.danoAcumuladoEquipo = 0;
    }

    // Método para agregar personajes (2 Guerreros, 1 Mago, 1 Místico, etc.)
    public void agregarPersonaje(Personaje p) {
        if (p != null) {
            this.personajes.add(p);
        }
    }

    // Simula un turno de ataque de todos los personajes vivos de este equipo
    public void atacarOtroEquipo(Equipo otroEquipo) {
        System.out.println("\n--- Turno de ataque del equipo: " + this.nombre + " ---");
        Random random = new Random();

        for (Personaje atacante : this.personajes) {
            // Si el atacante está muerto, no puede atacar
            if (!atacante.estaVivo()) {
                continue;
            }

            // Obtener enemigos vivos
            List<Personaje> enemigosVivos = otroEquipo.obtenerPersonajesVivos();
            if (enemigosVivos.isEmpty()) {
                System.out.println("El equipo " + otroEquipo.getNombre() + " ya está completamente derrotado.");
                break;
            }

            // Elegir un enemigo al azar
            Personaje objetivo = enemigosVivos.get(random.nextInt(enemigosVivos.size()));

            // 1. Guardar el ataque original para revertir los bufos temporales después del turno
            int ataqueOriginal = atacante.getAtaque();
            int vidaEnemigaAntes = objetivo.getVida();

            // 2. Usar estrategia según el tipo de personaje
            System.out.println("\nTurno de " + atacante.getNombre() + ":");
            if (atacante instanceof Mago) {
                ((Mago) atacante).usarEstrategia(this.personajes);
            } else if (atacante instanceof Mistico) {
                ((Mistico) atacante).usarEstrategia(this.danoAcumuladoEquipo);
            } else {
                atacante.usarEstrategia(); // Guerrero u otros
            }

            // 3. Realizar el ataque
            System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre() + "!");
            atacante.realizarAtaque(objetivo);

            // 4. Calcular el daño real infligido y sumarlo al acumulado (para el Místico)
            int danoInfligido = vidaEnemigaAntes - objetivo.getVida();
            if (danoInfligido > 0) {
                this.danoAcumuladoEquipo += danoInfligido;
                System.out.println(objetivo.getNombre() + " ha recibido " + danoInfligido + " puntos de daño.");
            } else {
                System.out.println("El ataque fue ineficaz (la defensa de " + objetivo.getNombre() + " bloqueó el daño).");
            }

            // 5. Revertir el ataque al estado original (hace que las estrategias de Guerrero y Místico sean temporales)
            atacante.setAtaque(ataqueOriginal);
        }
    }

    // Retorna true si todos los personajes del equipo están derrotados (vida 0)
    public boolean estaDerrotado() {
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                return false; // Si al menos uno está vivo, el equipo no está derrotado
            }
        }
        return true;
    }

    // Suma y retorna la vida actual de todos los personajes vivos del equipo
    public int vidaTotal() {
        int vidaTotal = 0;
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                vidaTotal += p.getVida();
            }
        }
        return vidaTotal;
    }

    // Método auxiliar privado para obtener la lista de personajes vivos del equipo
    private List<Personaje> obtenerPersonajesVivos() {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje p : this.personajes) {
            if (p.estaVivo()) {
                vivos.add(p);
            }
        }
        return vivos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}



