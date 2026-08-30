**Rúbrica de Evaluación Proyecto Android Studio IA**

1.  **Esquema de Calificación y Ponderación**

| **Componente**                            | **Ponderación** | **Descripción**                                                                                               |
|-------------------------------------------|-----------------|---------------------------------------------------------------------------------------------------------------|
| **Lógica POO y Reglas de Negocio**        | **25%**         | Jerarquía de clases (Personaje, Guerrero, Mago, Místico, Equipo), polimorfismo y flujo de 15 rondas.          |
| **Interfaz Gráfica y Eventos en Android** | **25%**         | Integración de Spinner, ProgressBar, AlertDialog, ScrollView y actualización dinámica de vistas.              |
| **Vibe Coding y Proceso con IA**          | **25%**         | Calidad de prompts, refinamiento iterativo, detección y corrección de alucinaciones o errores del modelo.     |
| **Sustentación Oral y "Live Mod"**        | **25%**         | Capacidad del estudiante para explicar el código generado y realizar una modificación en vivo sin asistencia. |

2.  **Rúbrica Analítica de Evaluación**

| **Criterio**                   | **Excelente (100% - 90%)**                                                                                                                                                                        | **Aceptable (89% - 70%)**                                                                           | **Insuficiente (\< 70%)**                                                                  |
|--------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| **Arquitectura POO**           | Implementa correctamente abstracción, herencia, polimorfismo, uso de interfaces y concurrencia. Código modular y desacoplado.                                                                     | Clases implementadas, pero con acoplamiento innecesario o mala delegación de responsabilidades.     | Lógica concentrada en la interfaz; no aplica conceptos de POO solicitados.                 |
| **GUI Android y Eventos**      | Interfaz fluida y reactiva; ProgressBar y logs se actualizan en tiempo real; AlertDialog maneja entradas de forma robusta. Uso de otros controles gráficos y eventos para el manejo del programa. | Interfaz funcional, pero con inconsistencias visuales, bloqueos leves o mal manejo de estados.      | Controles gráficos faltantes o errores que provocan caídas (*crashes*) durante el combate. |
| **Ingeniería de Prompts e IA** | Prompts estructurados con contexto, restricciones y pasos iterativos. Bitácora clara de depuración asistida.                                                                                      | Prompts genéricos; copia de código directo sin refinamiento sistemático de errores.                 | Uso pasivo de IA ("caja negra"), sin control del flujo ni registro de interacciones.       |
| **Comprensión y Live Mod**     | Explica detalladamente cualquier fragmento del código y realiza ajustes solicitados en vivo con soltura.                                                                                          | Explica la lógica general, pero duda en estructuras específicas o requiere pistas para modificarlo. | Desconoce la lógica del código entregado; incapaz de hacer ajustes menores en vivo.        |

3.  Entregables Requeridos del Proyecto

- **Repositorio de Código Fuente:** Proyecto funcional en Android Studio
  con arquitectura limpia y separación clara entre modelo y vista.

- **Bitácora de Vibe Coding (PROMPTS.md):** Registro cronológico de los
  prompts utilizados, errores arrojados por el compilador/runtime y cómo
  se instruyó a la IA para resolverlos. (Chats Referente al Proyecto)

- **Video Demostrativo Breve (opcional/asíncrono):** Demostración de 4
  minutos mostrando la configuración de equipos y un combate completo
  hasta la pantalla de fin de juego.

**NOTA**: Se adjunta archivo de plantilla en formato Markdown
(PROMPTS.md). Pueden utilizar este texto directamente para que lo usen
como base en sus repositorios.
