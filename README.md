# 📚 ForoHub - Challenge Alura Latam

![forohub](./img/forohub.png)

## 📋 Descripción del Proyecto
**ForoHub** es una API REST robusta desarrollada en Java con Spring Boot para la gestión de un foro comunitario. El sistema permite a los usuarios autenticados crear tópicos de discusión, interactuar mediante respuestas y gestionar el ciclo de vida de las dudas o sugerencias planteadas.

El proyecto implementa seguridad basada en **JWT (JSON Web Tokens)**, persistencia en **PostgreSQL** y una arquitectura modular que separa claramente las responsabilidades de negocio, presentación y acceso a datos.

## 📋 Metodología de Trabajo

Se utilizó metodología Kanban para la gestión del proyecto.

🗂 Herramienta utilizada:

Trello: https://trello.com/b/9DeAlIsq/foro-hub-challenge-back-end

Columnas empleadas:
- Backlog
- En Desarrollo
- Pausado
- Concluido
  
---

## 🧠 Modelo de Datos - Diagrama ER

![diagrama](./img/diagrama.png)

## 🛠 Stack Tecnológico
| Tecnología | Versión / Detalle |
| :--- | :--- |
| **Java** | 21 (LTS) |
| **Spring Boot** | 3.5.11 |
| **Spring Security** | Implementación de Seguridad Stateless |
| **Auth0 Java JWT** | 4.5.0 |
| **Spring Data JPA** | Gestión de persistencia |
| **PostgreSQL** | Base de datos relacional |
| **Lombok** | Optimización de código (Boilerplate) |
| **Gradle** | Gestor de dependencias y construcción |

---

## 🏗 Arquitectura del Sistema
El sistema sigue un diseño desacoplado en capas, garantizando escalabilidad y facilidad de mantenimiento:

| Capa | Responsabilidad |
| :--- | :--- |
| **Controller** | Expone los endpoints RESTful y gestiona las peticiones HTTP. |
| **Service** | Contiene la lógica de negocio y validaciones complejas. |
| **Repository** | Interfaz de comunicación con la base de datos mediante JPA. |
| **Domain** | Define las entidades JPA (`UserEntity`, `Topico`, `Respuesta`) y las reglas del modelo. |
| **DTO** | Objetos de transferencia de datos para entrada (Request) y salida (Response). |
| **Infrastructure** | Configuraciones de seguridad, filtros JWT y manejo global de excepciones. |

---
## 🏗 Estructura de Carpetas 

![carpetas](./img/img.png)
---
## 🔒 Seguridad y Autenticación
La seguridad se maneja de forma **Stateless**. Solo los endpoints de `/auth/login` y `/auth/register` están abiertos al público. El resto de la API requiere un token Bearer válido en el encabezado `Authorization`.

### Ejemplo de Registro de Usuario (JSON)
**POST** `/auth/register`

![register](./img/register.png)

### Login de Usuario
Genera el token JWT necesario para las demás operaciones.

**POST** `/auth/login`
![login](./img/login.png)

### Crear nuevo Tópico
**POST** `/topico`

![posttop](./img/posttop.png)

### Ver todos los Tópicos
**GET** `/topico`

![gettop](./img/gettop.png)

### Ver Tópicos por ID
**GET** `/topico/{id}`

![getidtop](./img/getidtop.png)

### Eliminar Tópicos por ID
**DELETE** `/topico/{id}`

![deletetop](./img/deletetop.png)

### Actualizar la pregunta del Tópico por ID
**PATCH** `/topico/{id}`

![patchtop](./img/patchtop.png)


### Crear nueva Respuesta
**POST** `/respuesta`

![postres](./img/postres.png)

### Ver todas las Respuestas
**GET** `/respuesta`

![getres](./img/getres.png)

### Ver Respuesta por ID
**GET** `/respuesta/{id}`

![getidres](./img/getidres.png)

### Eliminar Respuesta por ID
**DELETE** `/respuesta/{id}`

![deleteres](./img/deleteres.png)

### Actualizar parcialmente la Respuesta por ID
**PATCH** `/respuesta/{id}`

![patchres](./img/patchres.png)
