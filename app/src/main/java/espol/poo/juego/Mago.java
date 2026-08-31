package espol.poo.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mago extends Personaje {

    public Mago(String nombre, int vida, int ataque, int defensa) {
        super(nombre, vida, ataque, defensa);
    }

    // Implementación obligatoria del método abstracto de la clase padre
    @Override
    public void usarEstrategia() {
        System.out.println(this.nombre + " necesita una lista de compañeros para usar su estrategia.");
    }

    // Sobrecarga del método para cumplir con el requerimiento de recibir la lista
    public void usarEstrategia(List<Personaje> equipo) {
        if (!this.estaVivo()) {
            return; // Si el mago está muerto, no hace nada
        }

        if (equipo == null || equipo.isEmpty()) {
            System.out.println(this.nombre + " no tiene compañeros a los que curar.");
            return;
        }

        // Filtrar compañeros que estén vivos (excluyéndose a sí mismo, si está en la lista)
        List<Personaje> companerosVivos = new ArrayList<>();
        for (Personaje p : equipo) {
            if (p.estaVivo() && p != this) {
                companerosVivos.add(p);
            }
        }

        if (companerosVivos.isEmpty()) {
            System.out.println(this.nombre + " intentó curar, pero no hay compañeros vivos.");
            return;
        }

        // Elegir un compañero vivo aleatoriamente
        Random random = new Random();
        Personaje objetivo = companerosVivos.get(random.nextInt(companerosVivos.size()));

        // Calcular la curación: 25% de la vida actual del propio Mago
        int curacion = (int) (this.vida * 0.25);

        // Aumentar la vida del objetivo
        objetivo.setVida(objetivo.getVida() + curacion);

        System.out.println(this.nombre + " ha usado su estrategia mágica. Curó a "
                + objetivo.getNombre() + " por " + curacion + " puntos de vida.");
    }
}
