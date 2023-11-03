
# AgoDemo
Proyecto de demostración de la implementación:

 - Solicitudes HTTP.
 - Implementación de los codigos de estados de respuestas.
 - Manejo de Exceptiones.
 

## Solicitudes HTTP🚀

Al diseñar una **solicitud http (request)**, es importante poder abstraer la funcionalidad de su servicio de tal manera que todas las operaciones puedan representarse realizando operaciones **CRUD** (Create, Read, Update, Delete) para los diferentes recursos (entidades). Las acciones (**verbos HTTP** ó **métodos HTTP**) que se realizan nunca deben ser parte del endpoint.

El enfoque más utilizado es exponer diferentes colecciones de recursos relacionados. Por ejemplo, si su servicio contiene información sobre diferentes empresas y usuarios, podría tener una colección llamada **empresas** y otra colección llamada **empleados**:

 - /empresa
 - /empresa/{idEmpresa}
 - /empresa/{idEmpresa}/usuario
 - /empresa/{idEmpresa}/usuario/{idUsuario}
 - /usuarios
 - /usuarios/{idUsuario}

Por lo tanto si quieremos obtener la lista de empresas debemos solicitar el recurso [/empresa], si queremos obtener la informacion de una prese debemos solicitar el recurso [/empresa/{idEmpresa}/usuario].

Los clientes solicitan distintos recursos con la ayuda de del **protocolo http**, el cual regula como ha de formular las peticiones y como se a responder a la solicitud. Algunos de los metodos mas utulizados son los siguientes:

 - GET.- Se utiliza para solicitar informacion o recurso en concreto  indicado en la UR, no deberia incluir un body.
 - POST.- Se utilizar para enviar informacion al servidor; como imagenes o datos de un formulario realizando un cambio en el servidor.
 - DELETE.- Se utiliza para eliminar informacion indicado en la UR, no deberia incluir un body.
 - PUT.- Se utiliza para reemplazar informacion, indicado en el body de la solicitud<sup>(1)</sup>.
 - PATCH.- Se utiliza para realizar cambios parciales a la informacion, indicado en el body de la solicitud.

 <sup>(1)</sup> Se puede utilizar tambien como el metodo PATCH para realizar cambios particales.

Para el recuerdo usuario se podria ejemplicar de la siguientes manera:

|Solicitud | Metodo | Accion |
|--|--|--|
|GET|/usuarios|Listado de usuarios|
|GET|/usuarios/{idUsuario}|Información de un usuario|
|POST|/usuarios|Registra un nuevo usuario|
|DELETE|/usuarios/{idUsuario}|Elimina un usuario|
|PUT|/usuarios|Actualiza un usuario|
|PATCH|/usuarios/{idUsuario}|Actualiza el nombre de un usuario|



### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas_

```
Da un ejemplo
```

```
Da un ejemplo
```

## Despliegue 📦

_Se despliega con el servidor embebido de spring boot_

 1. Click derecho en la carpeta inicial de prouecto.
 2. Run As.
 3. Spring Boot App.

![image](https://github.com/gramska/demo/assets/43713784/bae2c867-3caa-41b5-a502-e7b15d593867)

## Construido con 🛠️

_Herramientas necesarias_

* [SpringBoot](https://start.spring.io/) - Inicio del proyecto
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Java JDK](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) - Java JDK
* [IDE Eclipse](https://www.eclipse.org/downloads/packages/release/2023-06/r) - Eclipse 2023-06 (4.28.0)

## Autores ✒️

* **Gabino Ramos** - *Trabajo Inicial* - [gramska](https://github.com/gramska)
---
⌨️ 2023 | [AGO Consultores](https://www.agoconsultores.com.mx/) © 
