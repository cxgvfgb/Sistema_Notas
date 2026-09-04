# Plan de Implementación: Sistema de Notas

Este plan detalla el desarrollo de la aplicación "Sistema de Notas" en Kotlin + XML, siguiendo los requisitos académicos y las reglas de Git especificadas.

## User Review Required

> [!IMPORTANT]
> Se ha detectado que `git` no está en el PATH del sistema. Utilizaré la ruta completa `C:\Program Files\Git\bin\git.exe` para todas las operaciones de Git.
> Se procederá a inicializar el repositorio y configurar el remoto: `https://github.com/cxgvfgb/Sistema_Notas.git`.

## Proposed Changes

### [Recursos de Diseño]

#### [MODIFY] [colors.xml](file:///C:/Users/USUARIO/AndroidStudioProjects/Sistemas_Notas/app/src/main/res/values/colors.xml)
Definir colores profesionales (primario, secundario, fondo, botones).

#### [MODIFY] [strings.xml](file:///C:/Users/USUARIO/AndroidStudioProjects/Sistemas_Notas/app/src/main/res/values/strings.xml)
Agregar todos los textos de la interfaz (títulos, etiquetas, botones, mensajes de aviso).

### [Interfaz de Usuario]

#### [MODIFY] [activity_main.xml](file:///C:/Users/USUARIO/AndroidStudioProjects/Sistemas_Notas/app/src/main/res/layout/activity_main.xml)
Diseñar la interfaz con:
- Títulos y subtítulos.
- `EditText` para Nombre y Nota.
- `Button` para Guardar y Mostrar (con bordes redondeados).
- `TextView` para mostrar la lista de estudiantes.

### [Lógica de la Aplicación]

#### [MODIFY] [MainActivity.kt](file:///C:/Users/USUARIO/AndroidStudioProjects/Sistemas_Notas/app/src/main/java/com/example/sistemas_notas/MainActivity.kt)
Implementar:
- `TreeMap<String, Int>` para almacenar datos ordenados.
- Lógica del botón **GUARDAR**: validaciones (vacío, rango 0-20, numérico) y `AlertDialog`.
- Lógica del botón **MOSTRAR**: iteración del `TreeMap` y clasificación de notas (Aprobado, Sustitutorio, Desaprobado).

### [Control de Versiones]

#### [GIT OPERATIONS]
1. `git init` (si es necesario).
2. `git remote add origin https://github.com/cxgvfgb/Sistema_Notas.git`.
3. Commits por etapas:
   - "Crear interfaz del sistema de notas"
   - "Agregar TreeMap y registro de estudiantes"
   - "Agregar validaciones y clasificación de notas"
   - "Finalizar sistema de notas"

## Verification Plan

### Automated Tests
- Compilación del proyecto mediante `gradle build`.

### Manual Verification
1. Ingresar datos vacíos y verificar el `AlertDialog` "AVISO - Por favor ingresa datos".
2. Ingresar notas fuera de rango (ej. 25 o -5) y verificar validación.
3. Ingresar varios estudiantes y verificar que se muestren ordenados alfabéticamente al presionar "MOSTRAR".
4. Verificar la clasificación de notas (15 -> Aprobado, 11 -> Sustitutorio, 8 -> Desaprobado).
5. Verificar que los cambios se reflejen en el repositorio de GitHub.
