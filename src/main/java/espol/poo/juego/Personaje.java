public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int defensa;

    public Personaje(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public abstract void usarEstrategia();

    public void realizarAtaque(Personaje oponente) {
        if (this.estaVivo()) {
            oponente.recibirAtaque(this.ataque);
        }
    }

    public void recibirAtaque(int dano) {
        int danoNeto = dano - this.defensa;
        
        if (danoNeto > 0) {
            this.vida -= danoNeto;
        }
        
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}