# Guía de Flujo de Trabajo — Proyecto Juego de Roles (Grupal)

Esta guía es para ti si te asignaron una issue en https://github.com/axdeo2/Projecto-POO/issues y nunca has trabajado con Git/GitHub en equipo. Sigue los pasos en orden — no necesitas saber nada más de Git para esto.

## Antes de empezar, necesitas:

- Una cuenta de GitHub, y que Axel ya te haya agregado como colaborador del repositorio.
- Git instalado. Si ya tienes Android Studio instalado, ya tienes Git (viene incluido) — puedes usar la terminal normal de tu sistema.

## Paso 1 — Clonar el repositorio

Abre una terminal donde quieras guardar el proyecto y corre:

```bash
git clone https://github.com/axdeo2/Projecto-POO.git
cd Projecto-POO
```

## Paso 2 — Crea tu rama

Nunca trabajes directamente sobre `main`. Antes de tocar nada, crea tu propia rama con el formato `feature/<numero-de-issue>-<nombre-corto>`:

- Si te asignaron la **issue #1** (clases del modelo: Personaje, Guerrero, Mago, Místico, Equipo):

  ```bash
  git checkout -b feature/1-clases-poo
  ```

- Si te asignaron la **issue #2** (Main, simulación de 15 rondas, salida por consola):

  ```bash
  git checkout -b feature/2-main-consola
  ```

## Paso 3 — Ve a tu issue y usa el prompt con tu IA

1. Entra a https://github.com/axdeo2/Projecto-POO/issues y abre la issue que te asignaron.
2. Copia el bloque **"Prompt sugerido para tu IA"** que ya viene escrito ahí.
3. Pégalo en la IA que uses (ChatGPT, Claude, Gemini, Copilot, etc.) y genera el código.
4. **Toma captura de pantalla del resultado** — la vas a necesitar en el paso 5.

No te quedes con la primera respuesta si algo no cuadra con lo que pide la issue (por ejemplo, revisa que el 25% de vida del Mago se calcule sobre su propia vida, no la de otro personaje) — pídele a la IA que lo corrija y vuelve a probar.

## Paso 4 — Guarda el código en el proyecto

Coloca los archivos `.java` que te dé la IA dentro de `src/main/java/espol/poo/juego/` (ya existe en el repo). Si tu IA usó un nombre de paquete distinto en el código, ajústalo a `package espol.poo.juego;` para que coincida con esa carpeta — así todos usamos el mismo paquete.

Para probar que compila y corre antes de subirlo:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out espol.poo.juego.Main
```

(el segundo comando solo funciona una vez que exista `Main.java` — issue #2. Si solo hiciste las clases del modelo, con que el primer comando compile sin errores ya está bien).

## Paso 5 — Registra tu proceso en tu bitácora

Abre (o crea) tu archivo `PROMPTS <TU NOMBRE>.md` en la raíz del repo — puedes usar `PROMPTS AXEL.md` como plantilla de formato — y agrega una nueva "Iteración" con:

- El prompt exacto que usaste.
- La captura de pantalla del paso 3.
- Si el código funcionó a la primera.
- Qué errores o alucinaciones detectaste.
- Qué corregiste tú a mano.

Esto es el 25% de la nota que evalúa "Vibe Coding y Proceso con IA" — no lo dejes para el final.

## Paso 6 — Sube tus cambios

```bash
git add .
git commit -m "Agrega <describe brevemente lo que hiciste>"
git push -u origin feature/1-clases-poo
```

(cambia `feature/1-clases-poo` por el nombre real de tu rama del paso 2).

## Paso 7 — Abre el Pull Request (PR)

1. Ve a https://github.com/axdeo2/Projecto-POO — GitHub te va a mostrar un botón amarillo **"Compare & pull request"** para la rama que acabas de subir. Haz clic ahí.
   - Si no lo ves, ve a la pestaña **"Pull requests"** → **"New pull request"**, y elige tu rama en "compare" y `main` en "base".
2. Como título, usa algo como: `Clases del modelo (issue #1)` o `Main y simulación de combate (issue #2)`.
3. En la descripción, escribe algo como "Relacionado a la issue #1" — **sin usar la palabra "Closes"**, porque en este proyecto las issues no se abren/cierran como seguimiento, solo se usan para asignar y documentar el trabajo.
4. Dale clic a **"Create pull request"**.

## ¿Qué pasa después?

Axel revisa tu PR. Puede dejarte comentarios o pedirte cambios — si te pide algo, lo corriges en la misma rama, subes de nuevo con `git push`, y el PR se actualiza solo (no hace falta abrir uno nuevo). Cuando esté todo bien, Axel lo integra a `main`. Tu rama y tu issue quedan como el registro de ese trabajo, junto con tu bitácora.
