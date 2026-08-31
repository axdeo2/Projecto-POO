package espol.poo.juego;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        
        // Creación del Equipo A
        Equipo equipoA = new Equipo("Equipo A");
        equipoA.agregarPersonaje(new Guerrero("Arturo", 100, 25, 10));
        equipoA.agregarPersonaje(new Guerrero("Leónidas", 110, 22, 12));
        equipoA.agregarPersonaje(new Mago("Merlín", 70, 15, 5));
        equipoA.agregarPersonaje(new Mistico("Rasputín", 85, 18, 8));

        // Creación del Equipo B
        Equipo equipoB = new Equipo("Equipo B");
        equipoB.agregarPersonaje(new Guerrero("Gunnar", 105, 24, 11));
        equipoB.agregarPersonaje(new Guerrero("Ragnar", 115, 21, 13));
        equipoB.agregarPersonaje(new Mago("Gandalf", 75, 14, 6));
        equipoB.agregarPersonaje(new Mistico("Oráculo", 80, 19, 7));

        System.out.println("¡Los equipos están listos para la batalla!");
        mostrarInfoEquipo(equipoA);
        mostrarInfoEquipo(equipoB);
        
        System.out.println("\n================ ¡COMIENZA EL COMBATE! ================");

        int ronda = 1;
        int danoAcumuladoA = 0; // Para el Místico del equipo A
        int danoAcumuladoB = 0; // Para el Místico del equipo B
        
        // Bucle principal de combate (máximo 15 rondas)
        while (ronda <= 15 && !equipoA.estaDerrotado() && !equipoB.estaDerrotado()) {
            System.out.println("\n================ RONDA " + ronda + " ================");
            
            // Turno del Equipo A
            danoAcumuladoA += ejecutarTurno(equipoA, equipoB, danoAcumuladoA);
            
            // Comprobar si el Equipo B fue derrotado antes de su turno
            if (equipoB.estaDerrotado()) {
                break;
            }
            
            // Turno del Equipo B
            danoAcumuladoB += ejecutarTurno(equipoB, equipoA, danoAcumuladoB);
            
            ronda++;
        }

        // ==========================================
        // RESOLUCIÓN Y RESULTADO FINAL DEL COMBATE
        // ==========================================
        System.out.println("\n================ RESULTADO FINAL ================");
        
        if (equipoA.estaDerrotado() && equipoB.estaDerrotado()) {
            // Caso extremo poco probable pero buena práctica cubrirlo
            System.out.println("¡Es un empate! Ambos equipos han caído en batalla simultáneamente.");
        } else if (equipoB.estaDerrotado()) {
            System.out.println("¡El " + equipoB.getNombre() + " ha sido derrotado! El ganador automático es el " + equipoA.getNombre() + ".");
        } else if (equipoA.estaDerrotado()) {
            System.out.println("¡El " + equipoA.getNombre() + " ha sido derrotado! El ganador automático es el " + equipoB.getNombre() + ".");
        } else {
            // Se llegó a las 15 rondas sin un derrotado total
            System.out.println("Se ha alcanzado el límite de 15 rondas. ¡Decisión por puntos de vida total!");
            
            int vidaA = equipoA.vidaTotal();
            int vidaB = equipoB.vidaTotal();
            
            System.out.println(equipoA.getNombre() + " - Vida Total Restante: " + vidaA);
            System.out.println(equipoB.getNombre() + " - Vida Total Restante: " + vidaB);
            
            if (vidaA > vidaB) {
                System.out.println("¡El ganador es el " + equipoA.getNombre() + " por tener mayor vida restante!");
            } else if (vidaB > vidaA) {
                System.out.println("¡El ganador es el " + equipoB.getNombre() + " por tener mayor vida restante!");
            } else {
                System.out.println("¡Increíble! Ambos equipos tienen exactamente la misma vida restante. ¡Es un EMPATE!");
            }
        }
    }

    // Método que ejecuta el turno de todos los miembros de un equipo
    private static int ejecutarTurno(Equipo atacante, Equipo defensor, int danoAcumulado) {
        Random random = new Random();
        int danoTotalTurno = 0;

        for (Personaje pAtacante : atacante.getPersonajes()) {
            if (!pAtacante.estaVivo()) continue;

            List<Personaje> enemigosVivos = new ArrayList<>();
            for (Personaje p : defensor.getPersonajes()) {
                if (p.estaVivo()) {
                    enemigosVivos.add(p);
                }
            }

            if (enemigosVivos.isEmpty()) {
                break;
            }

            Personaje pDefensor = enemigosVivos.get(random.nextInt(enemigosVivos.size()));

            int ataqueOriginal = pAtacante.getAtaque();
            int vidaEnemigaAntes = pDefensor.getVida();
            String nombreEstrategia = "";
            
            System.out.println("--------------------------------------------------");
            
            if (pAtacante instanceof Guerrero) {
                nombreEstrategia = "ataque duplicado";
                pAtacante.usarEstrategia();
            } else if (pAtacante instanceof Mago) {
                nombreEstrategia = "curación a un compañero";
                ((Mago) pAtacante).usarEstrategia(atacante.getPersonajes());
            } else if (pAtacante instanceof Mistico) {
                nombreEstrategia = "absorción de daño acumulado";
                ((Mistico) pAtacante).usarEstrategia(danoAcumulado);
            }

            pAtacante.realizarAtaque(pDefensor);

            int danoInfligido = vidaEnemigaAntes - pDefensor.getVida();
            danoTotalTurno += danoInfligido;

            String atacanteTexto = pAtacante.getClass().getSimpleName() + " " + pAtacante.getNombre();
            String defensorTexto = pDefensor.getClass().getSimpleName() + " " + pDefensor.getNombre();

            System.out.println(atacante.getNombre() + " ataca. " + 
                               atacanteTexto + " usa su estrategia (" + nombreEstrategia + "). " + 
                               atacanteTexto + " inflige " + danoInfligido + " puntos de daño a " + 
                               defensorTexto + ". " + 
                               defensorTexto + " tiene " + pDefensor.getVida() + " puntos de vida restantes.");

            pAtacante.setAtaque(ataqueOriginal);
        }
        
        return danoTotalTurno;
    }

    private static void mostrarInfoEquipo(Equipo equipo) {
        System.out.println("\n================ " + equipo.getNombre() + " ================");
        for (Personaje p : equipo.getPersonajes()) {
            System.out.println(p.getClass().getSimpleName() + " -> " + p.getNombre() + 
                               " [Vida: " + p.getVida() + 
                               " | Ataque: " + p.getAtaque() + 
                               " | Defensa: " + p.getDefensa() + "]");
        }
    }
}