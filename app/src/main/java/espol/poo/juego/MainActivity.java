package espol.poo.juego;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner[] spinnersA;
    private EditText[] nombresA;
    private Spinner[] spinnersB;
    private EditText[] nombresB;
    private Button botonConfirmarA;
    private Button botonConfirmarB;

    private Equipo equipoA;
    private Equipo equipoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnersA = new Spinner[]{
                findViewById(R.id.spinner_a1), findViewById(R.id.spinner_a2),
                findViewById(R.id.spinner_a3), findViewById(R.id.spinner_a4)};
        nombresA = new EditText[]{
                findViewById(R.id.nombre_a1), findViewById(R.id.nombre_a2),
                findViewById(R.id.nombre_a3), findViewById(R.id.nombre_a4)};
        spinnersB = new Spinner[]{
                findViewById(R.id.spinner_b1), findViewById(R.id.spinner_b2),
                findViewById(R.id.spinner_b3), findViewById(R.id.spinner_b4)};
        nombresB = new EditText[]{
                findViewById(R.id.nombre_b1), findViewById(R.id.nombre_b2),
                findViewById(R.id.nombre_b3), findViewById(R.id.nombre_b4)};
        botonConfirmarA = findViewById(R.id.boton_confirmar_a);
        botonConfirmarB = findViewById(R.id.boton_confirmar_b);

        // Composición requerida por la especificación: 2 Guerreros, 1 Mago, 1 Místico.
        int[] tipoPorDefecto = {0, 0, 1, 2};
        for (int i = 0; i < 4; i++) {
            spinnersA[i].setSelection(tipoPorDefecto[i]);
            spinnersB[i].setSelection(tipoPorDefecto[i]);
        }

        botonConfirmarA.setOnClickListener(v -> {
            equipoA = construirEquipo("Equipo A", spinnersA, nombresA);
            botonConfirmarA.setEnabled(false);
            setEquipoHabilitado(spinnersA, nombresA, false);
            Toast.makeText(this, "Equipo A confirmado", Toast.LENGTH_SHORT).show();
            iniciarCombateSiListo();
        });

        botonConfirmarB.setOnClickListener(v -> {
            equipoB = construirEquipo("Equipo B", spinnersB, nombresB);
            botonConfirmarB.setEnabled(false);
            setEquipoHabilitado(spinnersB, nombresB, false);
            Toast.makeText(this, "Equipo B confirmado", Toast.LENGTH_SHORT).show();
            iniciarCombateSiListo();
        });
    }

    private void setEquipoHabilitado(Spinner[] spinners, EditText[] nombres, boolean habilitado) {
        for (int i = 0; i < spinners.length; i++) {
            spinners[i].setEnabled(habilitado);
            nombres[i].setEnabled(habilitado);
        }
    }

    private void iniciarCombateSiListo() {
        if (equipoA == null || equipoB == null) {
            return; // Falta que se confirme el otro equipo.
        }
        Intent intent = new Intent(this, CombateActivity.class);
        intent.putExtra("equipoA", equipoA);
        intent.putExtra("equipoB", equipoB);
        startActivity(intent);
    }

    private Equipo construirEquipo(String nombreEquipo, Spinner[] spinners, EditText[] nombres) {
        Equipo equipo = new Equipo(nombreEquipo);
        for (int i = 0; i < spinners.length; i++) {
            String tipo = (String) spinners[i].getSelectedItem();
            String nombre = nombres[i].getText().toString();
            if (TextUtils.isEmpty(nombre)) {
                nombre = tipo + " " + (i + 1);
            }
            equipo.agregarPersonaje(crearPersonaje(tipo, nombre));
        }
        return equipo;
    }

    private Personaje crearPersonaje(String tipo, String nombre) {
        switch (tipo) {
            case "Mago":
                return new Mago(nombre, 75, 15, 5);
            case "Místico":
                return new Mistico(nombre, 80, 18, 7);
            case "Guerrero":
            default:
                return new Guerrero(nombre, 105, 22, 10);
        }
    }
}
