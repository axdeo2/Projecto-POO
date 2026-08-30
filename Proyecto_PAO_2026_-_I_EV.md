**Proyecto PAO 2026 I – 2EV**

**Propuesta de Proyecto: Juego de Roles por Turnos con Interfaz Gráfica
(GUI)**

**Objetivo del Proyecto**

- Desarrollar un programa que simule un juego de roles por turnos entre
  dos equipos conformados por diferentes tipos de personajes (Guerrero,
  Mago, Místico). El sistema debe implementar estrategias específicas
  para cada personaje, permitiendo aplicar conceptos avanzados de
  programación orientada a objetos, como herencia, abstracción,
  polimorfismo y generación de números aleatorios.

- Desarrollar un juego de roles por turnos con interfaz gráfica en
  Android Studio. Los estudiantes aprenderán a implementar programación
  de interfaces gráficas (GUI) y manejo de eventos, utilizando controles
  como botones, textos dinámicos, y notificaciones interactivas para
  visualizar y gestionar el juego.

**Alcance de Proyecto**

1.  **Clases Principales:**

- **Personaje (abstracta):** Define los atributos y comportamientos
  comunes a todos los personajes, como vida, ataque y defensa.

- **Guerrero, Mago y Místico:** Clases concretas que implementan
  estrategias únicas en el método usarEstrategia().

- **Equipo:** Representa un equipo de personajes y coordina sus
  acciones.

- **Main:** Controla el flujo principal del programa y las rondas de
  combate.

2.  **Características del Sistema:**

Cada equipo está compuesto por:

> 2 guerreros.
>
> 1 mago.
>
> 1 místico.

Estrategias únicas para cada tipo de personaje:

> Guerrero: Duplica su ataque en cada turno.
>
> Mago: Aumenta la vida de un compañero de equipo en un 25% de su propia
> vida.
>
> Místico: Genera un valor aleatorio entre 1 y 6; si el jugador acierta,
> suma los daños reales de sus compañeros a su ataque.

Combate cíclico:

> Los personajes atacan en el orden definido por su equipo.
>
> Las estrategias se activan antes de cada ataque.

El combate finaliza cuando:

> Un equipo es completamente derrotado.
>
> Se alcanzan 15 rondas y el ganador se decide por la suma total de la
> vida restante de los personajes.

3.  **Funciones Claves:**

- usarEstrategia(): Aplica modificaciones especiales a cada tipo de
  personaje

- realizarAtaque(): Ejecuta un ataque sobre un personaje contrario.

- recibirAtaque(): Calcula y aplica el daño, ajustando la vida del
  personaje.

- atacarOtroEquipo(): Simula ataques entre los personajes de dos
  equipos.

**Etapas del Proyecto**

1.  **Diseño de Clases**

<!-- -->

1.  **Clase Personaje**

- Atributos:

  - **nombre:** Nombre del personaje.

  - **vida:** Puntos de vida del personaje.

  - **ataque:** Poder de ataque del personaje.

  - **defensa:** Poder de defensa del personaje.

- Métodos:

  - Constructor: Inicializa los atributos.

  - usarEstrategia(): Abstracto, implementado en subclases.

  - realizarAtaque(Personaje oponente): Ejecuta el ataque sobre un
    oponente.

  - recibirAtaque(int dano): Calcula el daño neto y reduce la vida del
    personaje.

  - Métodos auxiliares para obtener el estado del personaje.

2.  **Clase Guerrero**

- Hereda de Personaje.

- **usarEstrategia():** Duplica el valor del ataque durante ese turno.

3.  **Clase Mago**

- Hereda de Personaje.

- **usarEstrategia():** Selecciona aleatoriamente a un compañero de
  equipo y aumenta su vida en un 25% de la vida del Mago.

4.  **Clase Místico**

- Hereda de Personaje.

- **usarEstrategia():**

  - Genera un valor aleatorio entre 1 y 6.

  - Solicita al usuario ingresar un número entre 1 y 6:

    - Si coincide, el ataque se incrementa temporalmente por la suma de
      los daños reales realizados por los compañeros.

    - Si no, el ataque permanece normal.

5.  **Clase Equipo**

- Atributos:

  - nombre: Nombre del equipo.

  - personajes: Lista de personajes del equipo.

- Métodos:

  - agregarPersonaje(Personaje p): Agrega un personaje al equipo.

  - atacarOtroEquipo(Equipo otroEquipo): Simula un turno de ataque.

  - estaDerrotado(): Determina si todos los personajes están derrotados.

  - vidaTotal(): Suma la vida de todos los personajes restantes.

6.  **Clase Main**

- Controla la simulación del juego:

  - Crea los equipos y asigna los personajes.

  - Ejecuta las rondas de combate.

  - Muestra detalles de cada turno:

    - Equipo atacante.

    - Personaje atacante, estrategia usada y daño realizado.

    - Estado del personaje defensor.

- Declara al equipo ganador, considerando:

  - Derrota completa de un equipo.

  - Comparación de la vida total restante tras 15 rondas.

  - Empate si la vida total es igual.

2.  **Implementación**

<!-- -->

1.  Clases base (Personaje, Guerrero, Mago).

2.  Manejo de equipos (Equipo) con listas de personajes.

3.  Simulación de combate en Main.

<!-- -->

3.  **Ejecución**

<!-- -->

1.  Crear dos equipos con personajes predefinidos.

2.  Iniciar las rondas de combate, alternando entre equipos.

3.  Registrar resultados en la consola, mostrando daño y vida restante.

4.  Declarar el equipo ganador al finalizar la simulación.

<!-- -->

4.  **Pantalla de Salida**

Por cada ronda, se debe mostrar:

1.  Nombre del equipo atacante.

2.  Estrategia usada por el atacante.

3.  Daño realizado y vida restante del defensor.

Ejemplo:

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><p>Equipo A ataca.</p>
<p>Guerrero A usa su estrategia (ataque duplicado).</p>
<p>Guerrero A inflige 40 puntos de daño a Mago B.</p>
<p>Mago B tiene 60 puntos de vida restantes.</p></td>
</tr>
</tbody>
</table>

**Ganador**

1.  Si un equipo es derrotado, el otro gana automáticamente.

2.  Si hay empate tras 15 rondas, la vida total define al ganador o
    declara un empate.

**  
**

**Implementación de Interfaz Gráfica (GUI) Android Studio**

**Descripción General del Juego**

El proyecto consiste en un combate por turnos entre dos equipos de
personajes (Guerreros, Magos, Místicos), donde los estudiantes
diseñarán:

1.  **Interfaz gráfica interactiva:**

    - Visualizar equipos, personajes, y atributos como vida y ataque.

    - Botones para activar estrategias y gestionar turnos.

    - Resultados dinámicos de cada ronda.

2.  **Manejo de eventos:**

    - Eventos de clic en botones para ejecutar ataques y estrategias.

    - Notificaciones emergentes (diálogos) para interacciones del
      jugador, como elegir números para el Místico.

**Diseño de la Interfaz**

**Pantalla Principal: Selección de Equipos**

- Propósito: Permitir al jugador personalizar cada equipo antes del
  inicio del combate.

- Diseño:

  - Dropdown (Spinner): Selección de personajes (Guerrero, Mago,
    Místico) para cada equipo.

  - TextFields: Ingreso de nombres de los personajes.

  - Botón "Confirmar Equipo": Valida y guarda la configuración del
    equipo.

  - Ejemplo de distribución:

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><p>------------------------------------</p>
<p>Selecciona los personajes del equipo</p>
<p>------------------------------------</p>
<p>Equipo A:</p>
<p>[Dropdown: Guerrero/Mago/Místico] [TextField: Nombre]</p>
<p>[Dropdown: Guerrero/Mago/Místico] [TextField: Nombre]</p>
<p>...</p>
<p>[Botón: Confirmar Equipo A]</p>
<p>Equipo B:</p>
<p>[Dropdown: Guerrero/Mago/Místico] [TextField: Nombre]</p>
<p>[Dropdown: Guerrero/Mago/Místico] [TextField: Nombre]</p>
<p>...</p>
<p>[Botón: Confirmar Equipo B]</p></td>
</tr>
</tbody>
</table>

**Pantalla de Juego: Combate por Turnos**

- Propósito: Visualizar los equipos, administrar turnos y estrategias.

- Diseño:

  - Panel de Equipos:

    - Cada equipo se muestra en un GridView con:

      - Nombre del personaje.

      - Barra de vida (ProgressBar).

      - Estadísticas de ataque y defensa.

  - Botón "Usar Estrategia":

    - Desencadena el comportamiento específico del personaje en turno.

  - Botón "Atacar":

    - Realiza el ataque del personaje seleccionado al enemigo.

  - Log de Combate (ScrollView):

    - Muestra eventos de cada turno:

      - Ejemplo: "Guerrero A duplicó su ataque y causó 40 de daño a
        Místico B."

  - Ejemplo de distribución:

<table>
<colgroup>
<col style="width: 100%" />
</colgroup>
<tbody>
<tr class="odd">
<td><p>------------------------------------</p>
<p>Equipo A | Equipo B</p>
<p>------------------------------------</p>
<p>Guerrero A [Barra Vida] Guerrero B [Barra Vida]</p>
<p>Mago A [Barra Vida] Mago B [Barra Vida]</p>
<p>Místico A [Barra Vida] Místico B [Barra Vida]</p>
<p>------------------------------------</p>
<p>[Botón: Usar Estrategia] [Botón: Atacar]</p>
<p>------------------------------------</p>
<p>Log de Combate:</p>
<p>--------------------------------</p>
<p>- Turno 1: Guerrero A atacó...</p>
<p>--------------------------------</p></td>
</tr>
</tbody>
</table>

**Uso de Controles Gráficos**

1.  TextView:

    - Para mostrar nombres y estadísticas de los personajes.

2.  ProgressBar:

    - Representa visualmente la vida restante de los personajes.

3.  Spinner:

    - Permite al jugador seleccionar los tipos de personajes al
      configurar equipos.

4.  Buttons:

    - Ejecutan estrategias y ataques durante el combate.

5.  ScrollView:

    - Muestra un historial de eventos de combate.

6.  AlertDialog:

    - Interacciones dinámicas, como el ingreso del número para el
      Místico.

**Interacción Dinámica**

**Eventos y Acciones:**

1.  Usar Estrategia:

    - Al hacer clic, se ejecuta el método usarEstrategia() del personaje
      en turno y actualiza la interfaz.

2.  Atacar:

    - Aplica el daño al personaje del equipo contrario, actualizando la
      barra de vida.

3.  Dialogo para el Místico:

    - Muestra un diálogo emergente donde el jugador ingresa un número
      entre 1 y 6.

    - Muestra el resultado de la predicción y ajusta el ataque del
      Místico en función si acertó o no.

**Flujo de Trabajo**

1.  Configuración Inicial:

    - Los jugadores personalizan sus equipos.

    - La configuración se guarda y pasa a la pantalla de combate.

2.  Rondas de Combate:

    - Los personajes atacan de manera cíclica.

    - Las estrategias se activan antes del ataque.

    - Los resultados se reflejan en la barra de vida y el log de
      combate.

3.  Determinación del Ganador:

    - El combate termina cuando un equipo es derrotado o se alcanzan 15
      rondas.

    - El equipo con mayor vida restante gana, o se declara empate.

Guia de Controles Gráficos:
<https://www.tutorialesprogramacionya.com/javaya/androidya/androidstudioya/>
