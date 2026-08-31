package espol.poo.juego;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Pantalla de combate. Reproduce el mismo comportamiento que Equipo.atacarOtroEquipo()
 * (revertir el ataque temporal tras cada golpe, daño acumulado por equipo para el Místico,
 * 15 rondas máximo), pero repartido en clics del jugador en vez de un bucle bloqueante —
 * y sin usar Scanner para el Místico, que en Android no se puede leer así: se resuelve
 * con el AlertDialog de más abajo, aplicando el mismo efecto (this.ataque += daño
 * acumulado si acierta) directamente con los getters/setters públicos de Personaje.
 */
public class CombateActivity extends AppCompatActivity {

    private static final int MAX_RONDAS = 15;

    private Equipo equipoA;
    private Equipo equipoB;
    private final Map<Personaje, Integer> vidaMaxima = new HashMap<>();
    private PersonajeAdapter adapterA;
    private PersonajeAdapter adapterB;

    private Equipo equipoEnTurno;
    private int indicePersonajeEnTurno;
    private int ronda = 1;
    private int danoAcumuladoA = 0;
    private int danoAcumuladoB = 0;
    private int ataqueOriginalTurnoActual;
    private boolean estrategiaUsada = false;

    private TextView textTurnoActual;
    private TextView textLog;
    private ScrollView scrollLog;
    private Button botonUsarEstrategia;
    private Button botonAtacar;
    private final StringBuilder log = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_combate);

        View root = findViewById(R.id.root_combate);
        int paddingBase = root.getPaddingLeft(); // el padding de 16dp que ya tenía el layout
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(paddingBase + systemBars.left, paddingBase + systemBars.top,
                    paddingBase + systemBars.right, paddingBase + systemBars.bottom);
            return insets;
        });

        equipoA = getEquipoExtra("equipoA");
        equipoB = getEquipoExtra("equipoB");

        for (Personaje p : equipoA.getPersonajes()) vidaMaxima.put(p, p.getVida());
        for (Personaje p : equipoB.getPersonajes()) vidaMaxima.put(p, p.getVida());

        GridView gridEquipoA = findViewById(R.id.grid_equipo_a);
        GridView gridEquipoB = findViewById(R.id.grid_equipo_b);
        adapterA = new PersonajeAdapter(this, equipoA.getPersonajes(), vidaMaxima);
        adapterB = new PersonajeAdapter(this, equipoB.getPersonajes(), vidaMaxima);
        gridEquipoA.setAdapter(adapterA);
        gridEquipoB.setAdapter(adapterB);

        textTurnoActual = findViewById(R.id.text_turno_actual);
        textLog = findViewById(R.id.text_log);
        scrollLog = findViewById(R.id.scroll_log);
        botonUsarEstrategia = findViewById(R.id.boton_usar_estrategia);
        botonAtacar = findViewById(R.id.boton_atacar);

        equipoEnTurno = equipoA;
        indicePersonajeEnTurno = siguienteIndiceVivo(equipoEnTurno, 0);

        botonUsarEstrategia.setOnClickListener(v -> onUsarEstrategia());
        botonAtacar.setOnClickListener(v -> onAtacar());

        botonUsarEstrategia.setEnabled(false);
        botonAtacar.setEnabled(false);
        mostrarTurnoActual();
        mostrarAnimacionVersus();
    }

    /** Los paneles de cada equipo entran deslizándose desde los bordes hasta el centro, con "VS" en el medio; al terminar, "VS" desaparece y empieza la partida. */
    private void mostrarAnimacionVersus() {
        View panelEquipoA = findViewById(R.id.panel_equipo_a);
        View panelEquipoB = findViewById(R.id.panel_equipo_b);
        TextView textVsCombate = findViewById(R.id.text_vs_combate);

        float distancia = getResources().getDisplayMetrics().widthPixels;
        panelEquipoA.setTranslationX(-distancia);
        panelEquipoB.setTranslationX(distancia);

        panelEquipoA.animate()
                .translationX(0f)
                .setDuration(700)
                .setInterpolator(new android.view.animation.OvershootInterpolator())
                .start();

        panelEquipoB.animate()
                .translationX(0f)
                .setDuration(700)
                .setInterpolator(new android.view.animation.OvershootInterpolator())
                .withEndAction(() -> textVsCombate.animate()
                        .alpha(0f)
                        .setStartDelay(500)
                        .setDuration(300)
                        .withEndAction(() -> {
                            textVsCombate.setVisibility(View.GONE);
                            actualizarBotones();
                        })
                        .start())
                .start();
    }

    private void onUsarEstrategia() {
        Personaje actual = equipoEnTurno.getPersonajes().get(indicePersonajeEnTurno);
        ataqueOriginalTurnoActual = actual.getAtaque();

        if (actual instanceof Mago) {
            ((Mago) actual).usarEstrategia(equipoEnTurno.getPersonajes());
            agregarLog(actual.getNombre() + " (Mago) usa su estrategia: cura a un compañero vivo en 90% de su propia vida.");
            estrategiaUsada = true;
            refrescarAdapters();
            actualizarBotones();
        } else if (actual instanceof Mistico) {
            mostrarDialogoMistico((Mistico) actual);
        } else {
            actual.usarEstrategia();
            agregarLog(actual.getNombre() + " usa su estrategia: duplica su ataque a " + actual.getAtaque() + ".");
            estrategiaUsada = true;
            actualizarBotones();
        }
    }

    private void mostrarDialogoMistico(Mistico mistico) {
        String[] opciones = {"1", "2", "3", "4", "5", "6"};
        new AlertDialog.Builder(this)
                .setTitle(mistico.getNombre() + " — " + getString(R.string.mensaje_prediccion_mistico))
                .setItems(opciones, (dialog, indiceElegido) -> {
                    int prediccion = indiceElegido + 1;
                    int real = new Random().nextInt(6) + 1;
                    boolean acierto = prediccion == real;
                    int danoAcumuladoDelEquipo = (equipoEnTurno == equipoA) ? danoAcumuladoA : danoAcumuladoB;

                    if (acierto) {
                        mistico.setAtaque(mistico.getAtaque() + danoAcumuladoDelEquipo);
                    }

                    agregarLog(mistico.getNombre() + " predice " + prediccion + ", salió " + real
                            + (acierto
                            ? " -> ¡Acierto! Absorbe el daño acumulado de su equipo (" + danoAcumuladoDelEquipo + ")."
                            : " -> Falló, sin bono."));
                    estrategiaUsada = true;
                    actualizarBotones();
                })
                .setCancelable(false)
                .show();
    }

    private void onAtacar() {
        if (!estrategiaUsada) {
            Toast.makeText(this, "Usa la estrategia antes de atacar.", Toast.LENGTH_SHORT).show();
            return;
        }

        Personaje atacante = equipoEnTurno.getPersonajes().get(indicePersonajeEnTurno);
        Equipo equipoRival = (equipoEnTurno == equipoA) ? equipoB : equipoA;

        List<Personaje> personajesRival = equipoRival.getPersonajes();
        Personaje objetivo = null;
        List<Integer> indicesVivos = new java.util.ArrayList<>();
        for (int i = 0; i < personajesRival.size(); i++) {
            if (personajesRival.get(i).estaVivo()) indicesVivos.add(i);
        }
        if (!indicesVivos.isEmpty()) {
            objetivo = personajesRival.get(indicesVivos.get(new Random().nextInt(indicesVivos.size())));
        }
        if (objetivo == null) return;

        int vidaAntes = objetivo.getVida();
        atacante.realizarAtaque(objetivo);
        int danoInfligido = vidaAntes - objetivo.getVida();

        if (danoInfligido > 0) {
            if (equipoEnTurno == equipoA) danoAcumuladoA += danoInfligido;
            else danoAcumuladoB += danoInfligido;
        }

        agregarLog(equipoEnTurno.getNombre() + " ataca.\n"
                + atacante.getClass().getSimpleName() + " " + atacante.getNombre()
                + " inflige " + danoInfligido + " puntos de daño a "
                + objetivo.getClass().getSimpleName() + " " + objetivo.getNombre() + ".\n"
                + objetivo.getNombre() + " tiene " + objetivo.getVida() + " puntos de vida restantes.");

        atacante.setAtaque(ataqueOriginalTurnoActual);
        refrescarAdapters();
        estrategiaUsada = false;
        actualizarBotones();

        if (equipoRival.estaDerrotado()) {
            finDePartida(equipoEnTurno.getNombre() + " gana (equipo rival derrotado).");
            return;
        }

        avanzarTurno();
        if (ronda > MAX_RONDAS) {
            declararGanadorPorVida();
            return;
        }
        mostrarTurnoActual();
    }

    private void avanzarTurno() {
        int siguiente = siguienteIndiceVivo(equipoEnTurno, indicePersonajeEnTurno + 1);
        if (siguiente != -1) {
            indicePersonajeEnTurno = siguiente;
            return;
        }
        if (equipoEnTurno == equipoA) {
            equipoEnTurno = equipoB;
        } else {
            equipoEnTurno = equipoA;
            ronda++;
        }
        indicePersonajeEnTurno = siguienteIndiceVivo(equipoEnTurno, 0);
    }

    private int siguienteIndiceVivo(Equipo equipo, int desde) {
        List<Personaje> personajes = equipo.getPersonajes();
        for (int i = desde; i < personajes.size(); i++) {
            if (personajes.get(i).estaVivo()) return i;
        }
        return -1;
    }

    private void declararGanadorPorVida() {
        int vidaA = equipoA.vidaTotal();
        int vidaB = equipoB.vidaTotal();
        String resultado;
        if (vidaA > vidaB) resultado = equipoA.getNombre() + " gana por vida total (" + vidaA + " vs " + vidaB + ").";
        else if (vidaB > vidaA) resultado = equipoB.getNombre() + " gana por vida total (" + vidaB + " vs " + vidaA + ").";
        else resultado = "Empate (" + vidaA + " de vida cada equipo).";
        finDePartida(resultado);
    }

    private void finDePartida(String mensaje) {
        agregarLog("--- " + mensaje + " ---");
        botonUsarEstrategia.setEnabled(false);
        botonAtacar.setEnabled(false);
        new AlertDialog.Builder(this)
                .setTitle("Fin de la partida")
                .setMessage(mensaje)
                .setPositiveButton("Jugar de nuevo", (d, w) -> volverASeleccionDeEquipos())
                .setCancelable(false)
                .show();
    }

    /**
     * Vuelve a MainActivity como pantalla nueva (no solo finish()) para que la
     * selección de equipos arranque en blanco, sin arrastrar los equipos ya
     * confirmados de la partida anterior.
     */
    private void volverASeleccionDeEquipos() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void actualizarBotones() {
        botonUsarEstrategia.setEnabled(!estrategiaUsada);
        botonAtacar.setEnabled(estrategiaUsada);
    }

    private void refrescarAdapters() {
        adapterA.notifyDataSetChanged();
        adapterB.notifyDataSetChanged();
    }

    private void mostrarTurnoActual() {
        Personaje actual = equipoEnTurno.getPersonajes().get(indicePersonajeEnTurno);
        textTurnoActual.setText("Turno de: " + actual.getNombre() + " (" + equipoEnTurno.getNombre()
                + ") — Ronda " + ronda + "/" + MAX_RONDAS);
    }

    private void agregarLog(String texto) {
        log.append(texto).append("\n\n");
        textLog.setText(log.toString());
        scrollLog.post(() -> scrollLog.fullScroll(View.FOCUS_DOWN));
    }

    @SuppressWarnings("deprecation")
    private Equipo getEquipoExtra(String clave) {
        if (Build.VERSION.SDK_INT >= 33) {
            return getIntent().getSerializableExtra(clave, Equipo.class);
        }
        return (Equipo) getIntent().getSerializableExtra(clave);
    }
}
