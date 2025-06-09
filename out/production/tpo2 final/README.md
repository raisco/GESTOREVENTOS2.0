# Gestion de Eventos

Este proyecto es una aplicación de gestión de eventos desarrollada en Java utilizando Swing para la interfaz gráfica. Permite a los usuarios crear, editar y gestionar eventos, así como inscribir asistentes y gestionar recursos.

## Estructura del Proyecto

El proyecto está organizado en las siguientes carpetas:

- **controller**: Contiene las clases que gestionan la lógica de negocio y las operaciones relacionadas con los eventos.
  - `GestorEventos.java`: Maneja las operaciones relacionadas con eventos (agregar, editar, listar).
  - `GestorInformes.java`: Genera informes sobre la participación en eventos y los exporta a archivos .txt.
  - `GestorNotificaciones.java`: Maneja las notificaciones por correo electrónico, preparado para futura configuración de SMTP.

- **model**: Contiene las clases que representan los datos del sistema.
  - `Asistente.java`: Representa a un asistente con propiedades como DNI, nombre y correo electrónico.
  - `Evento.java`: Representa un evento con propiedades como ID, nombre, fecha, ubicación y descripción, así como listas de asistentes y recursos.
  - `Recurso.java`: Representa un recurso con propiedades de tipo y descripción.

- **view**: Contiene las clases que gestionan la interfaz gráfica del usuario.
  - `CalendarioEventos.java`: Proporciona una interfaz de calendario visual para seleccionar fechas de eventos utilizando la biblioteca JCalendar.
  - `DetalleEvento.java`: Muestra información detallada sobre un evento seleccionado y permite la inscripción de asistentes.
  - `FormularioEvento.java`: Proporciona un formulario para agregar y editar detalles de eventos.
  - `VentanaPrincipal.java`: Ventana principal de la aplicación que muestra la lista de eventos y proporciona botones para agregar y editar eventos.

- **lib**: Contiene bibliotecas externas necesarias para el funcionamiento del proyecto.
  - `jcalendar.jar`: Biblioteca externa requerida para la funcionalidad del JCalendar.

- **data**: Contiene archivos de texto para la persistencia de datos.
  - `eventos.txt`: Almacena datos de eventos en formato de texto.
  - `asistentes.txt`: Almacena datos de asistentes en formato de texto.
  - `recursos.txt`: Almacena datos de recursos en formato de texto.

## Instrucciones de Configuración

1. **Requisitos Previos**:
   - Asegúrate de tener instalado Java Development Kit (JDK) en tu máquina.
   - Descarga la biblioteca JCalendar y colócala en la carpeta `lib`.

2. **Compilación**:
   - Compila los archivos `.java` en las carpetas `controller`, `model` y `view`.

3. **Ejecución**:
   - Ejecuta la clase `VentanaPrincipal` para iniciar la aplicación.

## Uso

- La aplicación permite gestionar eventos, inscribir asistentes y gestionar recursos.
- Utiliza el calendario para seleccionar fechas de eventos.
- Genera informes de participación que se exportan a archivos .txt.

## Notas

- La funcionalidad de notificaciones por correo electrónico está preparada para futura configuración de un servidor SMTP.
- Se recomienda revisar el código y realizar pruebas para asegurar el correcto funcionamiento de todas las funcionalidades.