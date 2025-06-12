# 🏢 Employee API - Spring Boot 3.4.2

Esta es API REST desarrollada con Spring Boot 3.4.2 que permite gestionar empleados mediante operaciones CRUD.
Esta API está diseñada bajo un esquema modular para que pueda ser escalable y fácil de mantener.
Incluye documentación con Swagger, pruebas unitarias con JUnit 5 y Mockito, y un sistema de logs y bitácora de eventos para auditoría.

---

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.4.2**
- **Microsoft Azure SQL Database**
- **Spring Data JPA (SQL Server)**
- **Lombok**
- **Java Bean Validation (Hibernate)**
- **ModelMapper**
- **DTO Pattern**
- **Swagger (OpenAPI)**
- **JUnit 5 / Mockito**
- **Slf4j para manejo de logs**
- **Postman Collection**

---

## 📌 Instalación y Configuración

### 👅 1️⃣ Clonar el repositorio
```sh
git clone https://github.com/PabloAraujo90/Employees-API.git
cd employees-api
```

### 👅 2️⃣ Configurar la Base de Datos (SQL Server)
Modifica el archivo `src/main/resources/application.properties` con los datos de conexión:

```properties
datasource:
    url: jdbc:sqlserver://hypertech.database.windows.net;databaseName=demodbInvx;
    username: 
    password: 
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### 👅 3️⃣ Ejecutar la aplicación
```sh
mvn clean spring-boot:run
```

### 👅 4️⃣ Acceder a la API
- 📝 **Swagger UI**: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)  
- 📝 **Documentación OpenAPI**: [`http://localhost:8080/api-docs`](http://localhost:8080/api-docs)  


### 👅 5️⃣ Collection en POSTMAN
- 📝 **Documentación cURL**: [`https://hypertech-api-team.postman.co/workspace/Hypertech-API-Team-Workspace~7dd7c2e4-6785-44e2-ab8d-78f6138843aa/api/45e6f27b-8d15-4e4a-b92c-359eeb76f99f?action=share&creator=21414695`]
---

## 📌 Endpoints REST

| Método | URL | Descripción |
|--------|-----|------------|
| `GET` | `/api/v1/employees` | Obtiene todos los empleados |
| `GET` | `/api/v1/employees/{id}` | Obtiene un empleado por ID |
| `POST` | `/api/v1/employees` | Crea un nuevo empleado |
| `POST` | `/api/v1/employees/batch` | Crea uno o varios empleados por lotes |
| `PUT` | `/api/v1/employees/{id}` | Actualiza un empleado |
| `PATCH` | `/api/v1/employees/{id}` | Actualiza una propiedad de un empleado |
| `DELETE` | `/api/v1/employees/{id}` | Elimina un empleado |

---

## 📌 Peticiones `curl`

### 📍 **Obtener todos los empleados**
```sh
curl --location --request GET 'http://localhost:8080/api/v1/employees' \
--header 'Accept: application/json'
```

### 📍 **Obtener un empleado por ID**
```sh
curl --location --request GET 'http://localhost:8080/api/v1/employees/1' \
--header 'Accept: application/json'
```

### 📍 **Crear un nuevo empleado**
```sh
curl --location --request POST 'http://localhost:8080/api/v1/employees' \
--header 'Content-Type: application/json' \
--data '{
  "firstName": "Pablo",
  "middleName": "Andrés",
  "lastName": "Mendoza",
  "secondLastName": "Araujo",
  "gender": "Male",
  "birthDate": "28-12-1990",
  "position": "Software Architect",
  "age": 34
}'
```

### 📍 **Actualizar un empleado**
```sh
curl --location --request PUT 'http://localhost:8080/api/v1/employees/1' \
--header 'Content-Type: application/json' \
--data '{
  "id": 1,
  "firstName": "Pablo",
  "middleName": "Andrés",
  "lastName": "Mendoza",
  "secondLastName": "Araujo",
  "gender": "Male",
  "birthDate": "28-12-1990",
  "position": "Software Architect",
  "age": 34
}'
```

### 📍 **Eliminar un empleado**
```sh
curl --location --request DELETE 'http://localhost:8080/api/v1/employees/1'
```

### 📍 **Actualizar una propiedad de un empleado por ID**
```sh
curl --location --request PATCH 'http://localhost:8080/api/v1/employees/2' \
--header 'Content-Type: application/json' \
--data ' {
        
        "age": 39
        
    }'
```
---

## 📌 📝 Logs y Bitácora de Eventos

Esta API registra **todos los headers de las solicitudes** y eventos importantes.

📍 **Ejemplo de Log al recibir una petición**
```plaintext
📌 Request Method: POST | Path: /api/v1/employees
📌 Header: Content-Type = application/json
📌 EVENT: Empleado creado con ID 1
```

📍 **Ejemplo de log de actualización**
```plaintext
📌 Request Method: PUT | Path: /api/v1/employees/1
📌 EVENT: Empleado con ID 1 actualizado
```

---

## 🚀 Contribuciones y Contacto
Si deseas contribuir a este proyecto, por favor envía un **pull request** o contacta al desarrollador:

📍 **Autor:** Pablo Mendoza  
📍 **Correo:** blopa.araujo11@gmail.com  
📍 **GitHub:** [https://github.com/PabloAraujo90](https://github.com/PabloAraujo90)  

---

## 📌 Licencia
**N/A**.  
```

