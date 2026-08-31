package espol.poo.juego;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/** Adaptador para mostrar los 4 personajes de un equipo en un GridView. */
public class PersonajeAdapter extends BaseAdapter {

    private final Context context;
    private final List<Personaje> personajes;
    private final Map<Personaje, Integer> vidaMaxima;

    public PersonajeAdapter(Context context, List<Personaje> personajes, Map<Personaje, Integer> vidaMaxima) {
        this.context = context;
        this.personajes = personajes;
        this.vidaMaxima = vidaMaxima;
    }

    @Override
    public int getCount() {
        return personajes.size();
    }

    @Override
    public Object getItem(int position) {
        return personajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_personaje, parent, false);
        }

        Personaje p = personajes.get(position);
        String tipo = p.getClass().getSimpleName();

        TextView textNombreTipo = view.findViewById(R.id.text_nombre_tipo);
        ProgressBar progressVida = view.findViewById(R.id.progress_vida);
        TextView textAtaque = view.findViewById(R.id.text_ataque);
        TextView textDefensa = view.findViewById(R.id.text_defensa);

        textNombreTipo.setText(p.getNombre() + " (" + tipo + ")" + (p.estaVivo() ? "" : " [derrotado]"));
        int max = vidaMaxima.containsKey(p) ? vidaMaxima.get(p) : p.getVida();
        progressVida.setMax(max);
        progressVida.setProgress(p.getVida());
        textAtaque.setText("Ataque: " + p.getAtaque());
        textDefensa.setText("Defensa: " + p.getDefensa());
        view.setAlpha(p.estaVivo() ? 1f : 0.4f);

        return view;
    }
}
