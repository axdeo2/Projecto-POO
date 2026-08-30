# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Estado actual del proyecto

Este directorio contiene únicamente documentación ([PROMPTS.md](PROMPTS.md), [Proyecto_PAO_2026_-_I_EV.md](Proyecto_PAO_2026_-_I_EV.md), [RUBRICA_DE_EVALUACION.md](RUBRICA_DE_EVALUACION.md)): aún **no existe** proyecto de Android Studio, código fuente, `build.gradle` ni ningún otro archivo de configuración. Es el punto de partida de la evaluación, antes de generar cualquier código. Es además un **proyecto grupal**, versionado en GitHub (`axdeo2/Projecto-POO`) con issues asignadas por compañero.

No hay comandos de build/lint/test que documentar todavía. En cuanto se cree el proyecto Android (Gradle), actualiza esta sección con los comandos reales (p. ej. `./gradlew assembleDebug`, `./gradlew test`, `./gradlew lint`) y añade una sección de arquitectura describiendo los paquetes y clases del juego.

## Qué es este proyecto

Es la entrega "Proyecto PAO 2026 I – 2EV" del curso de Programación Orientada a Objetos (POO) de ESPOL, detallada en [Proyecto_PAO_2026_-_I_EV.md](Proyecto_PAO_2026_-_I_EV.md): una app Android que implementa un **juego de roles por turnos** entre dos equipos (2 Guerreros, 1 Mago, 1 Místico cada uno), con una clase abstracta `Personaje` y subclases concretas que implementan `usarEstrategia()`:

- **Guerrero:** duplica su ataque en el turno.
- **Mago:** aumenta la vida de un compañero aleatorio en un 25% de **la vida del propio Mago** (no de la del compañero).
- **Místico:** genera un valor 1–6; si el jugador lo adivina, suma al ataque los daños reales ya infligidos por sus compañeros de equipo.

El combate es cíclico y termina cuando un equipo queda derrotado o se cumplen 15 rondas (en cuyo caso gana quien tenga más vida total sumada; empate si es igual). La GUI en Android Studio usa `Spinner` para elegir tipo de personaje, `ProgressBar` para la vida, `ScrollView` como log de combate y `AlertDialog` para la predicción del Místico — ver el detalle completo de pantallas en `Proyecto_PAO_2026_-_I_EV.md`.

El desarrollo se hace mediante "vibe coding" (asistido por IA) y debe quedar documentado en [PROMPTS.md](PROMPTS.md), que es una bitácora obligatoria y evaluable (25% de la nota según `RUBRICA_DE_EVALUACION.md`), no un archivo auxiliar descartable.

## Trabajando con PROMPTS.md

`PROMPTS.md` sigue una plantilla fija con tres secciones que se repiten/completan a medida que avanza el desarrollo:
1. **Estrategia General** — enfoque de alto nivel (p. ej. probar la lógica de rondas en consola antes de integrar la UI de Android).
2. **Registro de Interacciones** — un bloque por cada prompt relevante enviado a la IA, con el prompt exacto, si el código funcionó a la primera, errores/alucinaciones detectadas y qué se corrigió a mano.
3. **Resolución de Errores (Debugging)** — errores de compilación o crashes (Logcat) resueltos con ayuda de IA.

Cuando ayudes a implementar algo en este proyecto, añade el registro correspondiente en `PROMPTS.md` (no lo sobrescribas ni omitas secciones existentes) siguiendo ese mismo formato.

## Notas para trabajar en este directorio

- Este proyecto tiene su **propio repositorio git**, independiente del monorepo personal de `~/Projects` en el que vive físicamente esta carpeta (esa carpeta padre está excluida vía `.gitignore`/no rastreada). No mezclar operaciones de git entre ambos repos.
- Es un proyecto grupal: la autoría de commits/push la decide Axel (dueño del repo `axdeo2/Projecto-POO`); no asumas permiso para agregar colaboradores o modificar configuración del repo de GitHub sin confirmación explícita.
- Estrategia sugerida por la propia plantilla: implementar primero la lógica POO pura (jerarquía de `Personaje`, bucle de combate de 15 rondas) en Java plano/consola, y solo después integrar los componentes de Android Studio (Activities, layouts, `Spinner`).
- Cuidado ya señalado en la rúbrica/plantilla como error típico de IA: los efectos basados en porcentaje (p. ej. el 25% de vida que aporta el Mago) deben calcularse sobre **la vida del propio Mago**, y al aplicar daño se debe afectar la **vida actual**, no la vida máxima.
