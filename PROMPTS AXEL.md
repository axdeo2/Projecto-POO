# Bitácora de Vibe Coding - Proyecto PAO 2025 II EV

**Nombre del Estudiante:** Axel Vásquez
**Fecha de Inicio:** 2026-08-29
**Herramientas de IA Utilizadas:** Claude Code (Claude Sonnet 5)

---

## 1. Estrategia General
*Describe brevemente cómo planeas abordar el desarrollo del juego de roles usando IA. ¿Por dónde empezarás? (Ej: "Primero generaré la estructura de clases POO sin interfaz gráfica para probar la lógica de las 15 rondas en consola, luego integraré los componentes de Android Studio").*

[Tu respuesta aquí]
La estrategia es pedir a la IA que nos ayude describiendo el trabajo detalladamente en issues de github, repartiendo el trabajo en 3 bloques; Clases, Main y Android. De esta manera creamos primero la esctructura d clases sin la interfaz gráfica para intenterar sobre ella para luego pasar a la parte de Android.
---

## 2. Registro de Interacciones (Iteraciones)

*(Copia y pega este bloque por cada problema importante o funcionalidad que hayas desarrollado asistido por IA)*

*Nota: las siguientes iteraciones cubren toda la conversación con Claude Code sobre la organización del proyecto grupal, desde el primer mensaje (aún no había generación de código de las clases del juego, sino de la infraestructura de trabajo: CLAUDE.md, repositorio, issues, guía para compañeros y estructura de carpetas).*

### Iteración 1: Generar el CLAUDE.md inicial del proyecto (comando `/init`)
* **Objetivo:** Tener un archivo de guía para que Claude Code entienda el proyecto al trabajar en este directorio — en ese momento la carpeta solo tenía la plantilla de esta bitácora.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> /init (comando propio de Claude Code que le pide analizar el repositorio y generar un CLAUDE.md)

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí. La IA detectó que la carpeta estaba casi vacía (solo esta bitácora) y documentó el estado real del proyecto en vez de inventar una arquitectura que todavía no existía.
* **Errores o Alucinaciones detectadas:** Ninguna — al no haber código todavía, no tuvo margen para inventar detalles técnicos.
* **Modificaciones manuales realizadas:** Ninguna en este punto.

---

### Iteración 2: Inicializar el repositorio Git y conectarlo a GitHub
* **Objetivo:** Crear el repositorio del proyecto en esta carpeta y conectarlo al repo vacío que ya había creado en GitHub, para después poder agregar a mis compañeros.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Te pongo en contexto: es un proyecto grupal. Te dejo el link del repositorio de GitHub que acabo de crear vacío — necesito que lo inicialices en esta carpeta, porque no sé cómo hacerlo desde mi computadora. La idea es que luego yo use un skill para crear issues y asignarles tareas a mis compañeros, donde las tareas están en un archivo .md del proyecto y la rúbrica de evaluación en otro archivo .md. Por el momento necesito inicializar el repositorio para poder agregar a mis compañeros y luego empezar a hacer las issues.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí en cuanto a resultado técnico (`git init`, primer commit y `push` quedaron bien hechos, remoto conectado), pero con un problema de comportamiento: la IA escribió que me iba a dejar el comando para que yo lo corriera, y en el mismo mensaje lo ejecutó ella misma.
* **Errores o Alucinaciones detectadas:** No en el código/configuración de Git en sí. El error fue de instrucciones: dijo una cosa e hizo otra en el mismo mensaje — el primero de varios episodios parecidos en esta sesión (ver Iteraciones 5, 6 y 7).
* **Modificaciones manuales realizadas:** Ninguna; no llegué a correr ningún comando porque la IA se adelantó.

---

### Iteración 3: Aclarar qué skill usar y pedir tickets de trabajo no tan pequeños
* **Objetivo:** Usar un skill instalado localmente para generar los tickets de trabajo del equipo, evitando que quedaran demasiado pequeños.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Me refería al skill `setup-matt-pocock-skills`, para que analice nuestro proyecto y cree los tickets que necesitamos para completar la tarea, repartidos por cada persona del equipo — yo voy a revisar los PRs de ellos para poder continuar. Quisiera que los tickets no sean tan pequeños: como el proyecto se divide en la parte que no es gráfica, que una persona se encargue de la mayor parte de la creación de las clases, y otra persona del Main y de la ejecución con la pantalla de salida. Los demás tickets, de la parte de Android Studio, déjalos para mí.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Parcialmente. El skill tiene bloqueada la invocación automática por diseño, así que la IA no pudo correrlo directamente y me indicó que lo invocara yo mismo con `/setup-matt-pocock-skills`.
* **Errores o Alucinaciones detectadas:** La IA intentó invocar el skill directamente y el propio sistema se lo bloqueó, diciéndole explícitamente que no intentara replicar ese flujo por otros medios — la IA respetó esa restricción.
* **Modificaciones manuales realizadas:** Ninguna; corrí yo el comando en el siguiente paso.

---

### Iteración 4: Configurar el skill de ingeniería (`setup-matt-pocock-skills`)
* **Objetivo:** Dejar configurado dónde viven los issues del proyecto (tracker) y el vocabulario de etiquetas.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> /setup-matt-pocock-skills (comando que explora el repo y pregunta cómo configurar el tracker de issues)

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí. La IA detectó que el remoto ya apuntaba a GitHub y propuso usarlo como tracker, y preguntó si quería mantener las etiquetas de triage por defecto — acepté ambas recomendaciones. Generó `docs/agents/issue-tracker.md`, `docs/agents/domain.md`, `docs/agents/triage-labels.md`, y una sección "Agent skills" en `CLAUDE.md`.
* **Errores o Alucinaciones detectadas:** Ninguna en el contenido generado en ese momento (algunos de estos archivos quedaron desactualizados más adelante por un cambio mío de enfoque, no por un error de la IA — ver Iteración 6).
* **Modificaciones manuales realizadas:** Ninguna.

---

### Iteración 5: Definir el reparto de trabajo en issues de GitHub con un prompt sugerido para copiar
* **Objetivo:** Repartir el proyecto en 3 bloques de trabajo (clases del modelo, Main/consola, Android) sin que mis compañeros necesitaran saber usar Pull Requests, dejando en cada issue de GitHub un prompt ejemplo de guia para que usen en su propia IA.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Vamos a cambiar el enfoque del flujo de trabajo, ya que mis compañeros no saben usar GitHub con PRs. Para hacer más rápido el flujo, lo único que vamos a hacer es crear una issue por bloque: la primera con todo el concepto de las clases de Java, la siguiente con todo lo del Main, y la última con la de Android. Súbelo tú y crea todo lo que se pide en la issue de GitHub, por favor ayúdame con eso. Quisiera que mis compañeros puedan copiar y pegar lo que sale en la issue en la IA que ellos usen, ya que el proyecto trata de cómo usamos la IA para generar código — necesitan hacer un prompt en base a lo que se les pide y tomar captura de eso para la bitácora.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Parcialmente. Las 3 issues se crearon con contenido correcto (contexto, requisitos, restricciones y un prompt combinado por issue), pero el enfoque de flujo (issues sin PR) lo cambié yo mismo en la Iteración 6 al darme cuenta de que sí quería usar ramas y PR.
* **Errores o Alucinaciones detectadas:** Ninguna en el contenido de las issues. Sí se repitió el problema de la Iteración 2: la IA dijo que me dejaba el comando de `git commit`/`push` para que yo lo corriera, y lo ejecutó ella misma en el mismo mensaje.
* **Modificaciones manuales realizadas:** Ninguna sobre el contenido de las issues en este punto; el ajuste real vino como una nueva instrucción mía en la siguiente iteración.

---

### Iteración 6: Corregir el flujo a "sí usar PR" y pedir una guía para compañeros sin experiencia en Git
* **Objetivo:** Mantener las issues solo como asignación de trabajo (no como seguimiento de estado abrir/cerrar), pero que mis compañeros sí trabajen con rama propia y Pull Request para que yo lo revise. Necesitaba un tutorial que se les pudiera mandar directamente.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Perdóname, sí vamos a usar el flujo de crear una issue: mis amigos crean su rama y suben un PR para que yo lo revise. Solo que la issue va a quedar solo para asignar el trabajo, no para abrirla y cerrarla como seguimiento — es solo para tener el registro de esos cambios en el repositorio. Mis compañeros van a seguir usando la issue para complementarse con la IA que ellos usen. Necesito que crees un archivo que pueda mandarles a mis compañeros: un tutorial de cómo debe ser su flujo de trabajo con su IA, donde puedan entender cómo se debe llamar su rama y cómo deben subirla a GitHub por PR.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí. Generó `GUIA_FLUJO_GIT.md` con convención de nombres de rama (`feature/<numero-issue>-<slug>`), pasos de clone/branch/commit/push, y cómo abrir el PR desde la web de GitHub sin usar la palabra "Closes" (para que la issue no se cierre sola).
* **Errores o Alucinaciones detectadas:** No en la guía nueva. Sí quedaron desactualizados `docs/agents/issue-tracker.md` y `CLAUDE.md` (todavía decían "sin flujo de PRs" de la Iteración 5) — lo detecté yo y la IA lo corrigió en la misma sesión. Y otra vez el mismo problema de comportamiento: dijo "yo no los subo, te dejo el comando" y los subió ella misma.
* **Modificaciones manuales realizadas:** Ninguna directa sobre el archivo; la corrección fue pedirle a la IA que actualizara los documentos desactualizados.

---

### Iteración 7: Estructurar la carpeta `src/` de Java para que mi compañero pudiera crear su rama y trabajar
* **Objetivo:** Tener ya lista una estructura de carpetas en el repo para que mis compañeros pudieran clonar, crear su rama, y saber exactamente dónde poner sus archivos `.java`.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Necesito que mi carpeta del proyecto esté adecuada para los archivos Java y todo lo que necesitamos, ya estructurada para que mis compañeros puedan entrar y comenzar a trabajar en la parte de Java.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí. Se creó `src/main/java/espol/poo/juego/package-info.java` (un archivo Java real, no un truco tipo `.gitkeep`, para que la carpeta quedara rastreada por Git) y se documentaron los comandos de `javac`/`java` para compilar y correr sin Maven/Gradle, ya que el proyecto Android todavía no existe.
* **Errores o Alucinaciones detectadas:** Ninguna en la estructura en sí. Tercera y cuarta vez en la conversación que la IA ejecutó `git commit`/`push` por su cuenta pese a decir que no lo haría — se lo señalé explícitamente y a partir de ahí dejó de pasar.
* **Modificaciones manuales realizadas:** Ninguna sobre el código; solo confirmé que la carpeta ya estuviera en GitHub antes de avisarle a mi compañero que podía clonar.

---

### Iteración 8: Aclarar qué archivos no son entregable final y confirmar que la rama ya se puede crear
* **Objetivo:** Aclarar que la guía de flujo no cuenta como entregable del curso, y confirmar que mi compañero ya podía clonar y crear su rama para trabajar en Java.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Aún no he subido nada, ningún commit. En realidad, esos puntos del archivo de guía de flujo no tienen que ir en el entregable final — quizás el CLAUDE.md sí se queda. Pero lo que necesito ahora es que mi compañero ya pueda hacer una nueva rama basada en esta carpeta, donde ya pueda trabajar con Java. Avísame cuando ya se pueda.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí. Esta vez la IA sí dejó el comando de `git add`/`commit`/`push` solo como texto, sin ejecutarlo ella misma — recién ahí se corrigió el comportamiento de las iteraciones 2, 5 y 6.
* **Errores o Alucinaciones detectadas:** Ninguna nueva.
* **Modificaciones manuales realizadas:** Ninguna todavía; quedó pendiente que yo corriera el comando.

---

### Iteración 9: Confirmar que los cambios ya estaban en `main`
* **Objetivo:** Asegurarme de que todo quedara subido a la rama principal del repositorio.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Por favor, pasa estos cambios al main.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** No hizo falta ninguna acción: la IA revisó (`git status`, comparación con `origin/main`, PRs abiertos) y confirmó que todo ya estaba subido desde el paso anterior — no había nada pendiente.
* **Errores o Alucinaciones detectadas:** Ninguna; en este caso el chequeo evitó que se hiciera una acción innecesaria.
* **Modificaciones manuales realizadas:** Ninguna.

---

### Iteración 10: Dividir el "prompt sugerido" de cada issue en varios prompts en vez de uno solo
* **Objetivo:** Que cada clase (o pantalla, en el caso de Android) tuviera su propio prompt independiente, en vez de un solo prompt gigante por issue.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Listo, ya mi compañero tiene el repositorio actualizado. Ahora necesito que modifiques las issues en la parte de "Prompt sugerido para IA": quiero que tenga varios prompts, no solo uno. En el caso de la issue de clases, un prompt para la clase Personaje, otro para Guerrero, otro para Mago, otro para Místico, y otro para Equipo. Lo mismo para las otras issues.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** Sí para la issue de clases (5 prompts, uno por clase, tal como pedí). Para las otras dos issues no había una división 1:1 tan clara (Main y Android no se dividen en 5 piezas independientes), así que la IA usó su propio criterio para agrupar en 3 prompts coherentes cada una en vez de forzar una división artificial — me pareció razonable y no pedí cambios.
* **Errores o Alucinaciones detectadas:** Ninguna.
* **Modificaciones manuales realizadas:** Ninguna; validé el criterio de agrupación que propuso la IA en vez de corregirlo.

---

### Iteración 11: Quitar los placeholders de "pegar código" al asumir que se trabaja en el mismo chat
* **Objetivo:** Revisar el contenido final antes de mandárselo a mis compañeros.
* **Herramienta:** Claude Code (Claude Sonnet 5)

**Prompt Utilizado:**
> Acabo de leer lo que le vas a pasar a mi compañero, por ejemplo en la issue de clases, y veo que pones algo como "ya tengo esta clase abstracta (te la pego)" y sigues con el prompt. Esas partes donde dice "pega aquí el código" no me parecen convenientes, porque vamos a estar trabajando en el mismo chat y la IA ya va a tener el recordatorio de cómo se ve el código anterior.

**Resultado y Análisis Crítico:**
* **¿El código funcionó a la primera?** No en la primera versión (Iteración 10) — ahí fue donde detecté el defecto. La IA lo corrigió bien a la primera una vez se lo señalé.
* **Errores o Alucinaciones detectadas:** Al redactar los prompts para que mis compañeros los usaran, la IA no consideró que, si se trabaja en un mismo chat, no hace falta pedir que se vuelva a pegar el código ya generado — generó instrucciones redundantes que un compañero sin experiencia podría malinterpretar.
* **Modificaciones manuales realizadas:** Ninguna directa en el archivo (la corrección la hizo la IA en las 3 issues), pero salió de mi propia revisión crítica del contenido antes de mandarlo, no de un error que la IA detectara sola.

---


## 3. Resolución de Errores (Debugging)
*(Usa esta sección cuando te hayas encontrado con un error de compilación o un crash en la aplicación (Logcat) y usaste la IA para resolverlo)*

* **Error obtenido (Logcat/Consola):** 
  ```text
  [Pega aquí el error, Ej: NullPointerException en MainActivity.java:45]