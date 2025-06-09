# Gestion de Eventos

Este proyecto es una aplicación de gestión de eventos desarrollada en Java utilizando Swing para la interfaz gráfica. Permite a los usuarios crear, editar y gestionar eventos, así como inscribir asistentes y gestionar recursos.

## Estructura del Proyecto

El proyecto está organizado en las siguientes carpetas:

- **controller**: Contiene las clases que gestionan la lógica de negocio y las operaciones relacionadas con los eventos.
  - `GestorEventos.java`: Maneja las operaciones relacionadas con eventos (agregar, editar, listar).
  - `GestorInformes.java`: Genera informes sobre la participación en eventos y los exporta a archivos .txt.

- **model**: Contiene las clases que representan los datos del sistema.
  - `Asistente.java`: Representa a un asistente con propiedades como DNI, nombre y correo electrónico.
  - `Evento.java`: Representa un evento con propiedades como ID, nombre, fecha, ubicación y descripción, así como listas de asistentes y recursos.
  - `Recurso.java`: Representa un recurso con propiedades de tipo y descripción.

- **view**: Contiene las clases que gestionan la interfaz gráfica del usuario.
  - `DetalleEvento.java`: Muestra información detallada sobre un evento seleccionado y permite la inscripción de asistentes.
  - `FormularioEvento.java`: Proporciona un formulario para agregar y editar detalles de eventos.
  - `VentanaPrincipal.java`: Ventana principal de la aplicación que muestra la lista de eventos y proporciona botones para agregar y editar eventos.

- **lib**: Contiene bibliotecas externas necesarias para el funcionamiento del proyecto.
  - `jcalendar.jar`: Biblioteca externa requerida para la funcionalidad del JCalendar.

- **data**: Contiene archivos de texto para la persistencia de datos.
  - `eventos.txt`: Almacena datos de eventos en formato de texto.
  - `asistentes.txt`: Almacena datos de asistentes en formato de texto.
  - `recursos.txt`: Almacena datos de recursos en formato de texto.
## Uso

- La aplicación permite gestionar eventos, inscribir asistentes y gestionar recursos.
- Utiliza el calendario para seleccionar fechas de eventos.
- Genera informes de participación que se exportan a archivos .txt.

## Notas

- La funcionalidad de notificaciones por correo electrónico está preparada para futura configuración de un servidor SMTP.

# Detalles del Diagrama de Clases y Vistas (UML)

Este proyecto gestiona eventos, asistentes y recursos, permitiendo crear, editar, visualizar y almacenar información relevante de forma organizada. A continuación se describe el diagrama UML que representa la estructura del sistema.

---

## Clases Principales

### `Evento`
- Clase central del sistema.
- Atributos:
  - `id`: int
  - `nombre`: String
  - `fecha`: Date
  - `ubicacion`: String
  - `descripcion`: String
  - `asistentes`: List<Asistente>
  - `recursos`: List<Recurso>
- Métodos principales:
  - Getters para todos los atributos
  - `agregarAsistente(asistente: Asistente)`
  - `agregarRecurso(recurso: Recurso)`
  - `toFileString()`, `toString()`

### `Asistente`
- Representa una persona que asiste a un evento.
- Atributos:
  - `dni`: String
  - `nombre`: String
  - `email`: String
- Métodos principales:
  - Getters para todos los atributos
  - `toFileString()`, `toString()`

### `Recurso`
- Representa un recurso necesario para un evento (proyector, sala, etc.).
- Atributos:
  - `tipo`: String
  - `descripcion`: String
- Métodos principales:
  - Getters
  - `toFileString()`, `toString()`

---

## Clases de Lógica y Gestión

### `GestorEventos`
- Administra la lista de eventos.
- Atributo:
  - `eventos`: List<Evento>
- Métodos:
  - `agregarEvento(evento)`
  - `editarEvento(id, eventoActualizado)`
  - `eliminarEvento(id)`
  - `buscarEvento(id)`
  - `listarEventos()`
  - Métodos para cargar/guardar eventos, recursos y asistentes desde/hacia archivos

### `GestorInformes`
- Genera informes de participación a partir de una lista de eventos.
- Método:
  - `generarInformeParticipacion(eventos, rutaArchivo)`

---

##  Clases de Interfaz

### `VentanaPrincipal`
- Muestra la lista de eventos.
- Permite:
  - `mostrarEventos()`
  - `agregarEvento()`
  - `editarEvento()`

### `FormularioEvento`
- Permite editar o crear un evento.
- Métodos:
  - `mostrarFormulario()`
  - `guardarEvento()`

### `DetalleEvento`
- Muestra la información de un evento en detalle.
- Permite:
  - `mostrarDetalle()`
  - `inscribirAsistente()`

---

## Relaciones entre Clases

- `GestorEventos` **gestiona** la lista de `Evento`.
- `Evento` **tiene**:
  - 0 o más `Asistente`
  - 0 o más `Recurso`
- `GestorInformes` **lee** los eventos.
- `DetalleEvento` **muestra** un `Evento`.
- `FormularioEvento` **edita** un `Evento`.
- `VentanaPrincipal` **lista** y permite acciones sobre `Evento`.