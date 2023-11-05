# AgoDemo
Proyecto de demostración de la implementación:

 - Solicitudes HTTP *Request*.
 - Respuestas HTTP *Respose*.
 - Manejo de Exceptiones.
 

## Solicitudes HTTP (Request)🚀

Al diseñar una **solicitud http (request)**, es importante poder abstraer la funcionalidad de su servicio de tal manera que todas las operaciones puedan representarse realizando operaciones **CRUD** (Create, Read, Update, Delete) para los diferentes recursos (entidades). Las acciones (**verbos HTTP** ó **métodos HTTP**) que se realizan nunca deben ser parte del endpoint.

El enfoque más utilizado es exponer diferentes colecciones de recursos relacionados. Por ejemplo, si su servicio contiene información sobre diferentes empresas y usuarios, podría tener una colección llamada **empresas** y otra colección llamada **usuarios**:

 - /empresa
 - /empresa/{idEmpresa}
 - /empresa/{idEmpresa}/usuario
 - /empresa/{idEmpresa}/usuario/{idUsuario}
 - /usuarios
 - /usuarios/{idUsuario}

Por lo tanto, si queremos  obtener la lista de empresas debemos solicitar el recurso [/empresa], si queremos obtener la información  de los usuarios de una empresa debemos solicitar el recurso [/empresa/{idEmpresa}/usuario].

Los clientes solicitan distintos recursos con la ayuda de del **protocolo http**, el cual regula como ha de formular las peticiones y como se a responder a la solicitud. Algunos de los métodos más utilizados son los siguientes:

 - GET.- Se utiliza para solicitar información o recurso en concreto indicado en la UR, no debería incluir un body.
 - POST.- Se utilizar para enviar información al servidor; como imagenes o datos de un formulario realizando un cambio en el servidor.
 - DELETE.- Se utiliza para eliminar información indicado en la UR, no deberia incluir un body.
 - PUT.- Se utiliza para reemplazar información, indicado en el body de la solicitud<sup>(1)</sup>.
 - PATCH.- Se utiliza para realizar cambios parciales a la información, indicado en el body de la solicitud.

 <sup>(1)</sup> Se puede utilizar también como el método PATCH para realizar cambios parciales.

Para el recuerdo usuario se podria ejemplicar de la siguientes manera:

|Solicitud | Método | Acción |
|--|--|--|
|GET|/usuarios|Listado de usuarios|
|GET|/usuarios/{idUsuario}|Información de un usuario|
|POST|/usuarios|Registra un nuevo usuario|
|DELETE|/usuarios/{idUsuario}|Elimina un usuario|
|PUT|/usuarios|Actualiza un usuario|
|PATCH|/usuarios/{idUsuario}|Actualiza el nombre de un usuario|

## Respuestas HTTP (Respose)🚀

Al enviar una solicitud (request), el servidor nos responderá (response) con un código de la solicitud, opcionalmente puede incluir el contenido de la respuesta y una cabecera "content-type" que en este caso será del tipo "application/json", es decir del tipo Json.

Los códigos de las repuestas están formados por tres números enteros que se pueden agrupar en 5 tipos:

 - 1xx Respuesta informativa.
 - 2xx Respuesta exitosa.
 - 3xx Redirección.
 - 4xx Error en el cliente.
 - 5xx Error en el servidor

De las cuales de los grupos 1 y 3 (1xx y 3xx) son las menos utilizadas.

Dentro de los grupos 2, 4 y 5 (2xx, 4xx y 5xx) podemos mencionar los siguientes:

- 200 OK
- 201 Creado
- 204 Sin contenido
- 400 Petición Incorrecta
- 404 No encontrado
- 500 Error interno de servidor 

Tomando el ejemplo de la sección anterior se podría regresar las siguientes respuestas
|Solicitud | Metodo | Accion | Respuesta |
|--|--|--|--|
|GET|/usuarios|Listado de usuarios|200(Ok): Lista de Usuarios|
|GET|/usuarios/{idUsuario}|Información de un usuario|200(Ok):Usuario solicitado|
|POST|/usuarios|Registra un nuevo usuario|201(Created): Vacio|
|DELETE|/usuarios/{idUsuario}|Elimina un usuario|204(No Content): Vacio|
|PUT|/usuarios|Actualiza un usuario|204(No Content): Vacio|
|PATCH|/usuarios/{idUsuario}|Actualiza el nombre de un usuario|204(No Content): Vacio|


Se recomiendan los siguientes concejos a la hora de implementar las repuestas de los recursos generados:

 1. #### Pagina tus resultados.
Implementar la paginación en todos los recursos que devolverá demasiados datos. Reducirá el tiempo de respuesta y evitará comportamientos no deseados en el cliente.

2. #### Responder solo lo que se está solicitando.
Si está solicitando un recurso, devuelva su representación o una lista de ellos; Evite responder con algo diferente. Por ejemplo, solicitamos un listado de usuarios y se devuelve la siguiente respuesta:
```
{
	"usuario":[
			{
				"idUsuario":  1,
				"nombre":  "Edgar",
				"edad":  10
			},
			{
				"idUsuario":  2,
				"nombre":  "Jerry",
				"edad":  14
			}
		]
}
```
Esta respuesta en realidad es incorrecta debido a que obtiene un objeto con una lista de representaciones de usuarios. La respuesta debería haber obtenido solo una lista de representaciones de usuarios:
```
[
	{
		"idUsuario":  1,
		"nombre":  "Edgar",
		"edad":  10
	},
	{
		"idUsuario":  2,
		"nombre":  "Jerry",
		"edad":  14
	}
]
```

3. #### Devolver tipos de datos correctos.
Devolver los tipos de datos correctos y aprovechar los objetos nulos en caso de que no tenga esa información.

Por ejemplo, al devolver información del usuario. Los campos que tenemos de los usuarios son idUsuario, nombre y edad:
```
{
	"idUsuario":  <int>,
	"nombre":  <string>,
	"edad":  <int>
}
```
Si la edad no fuera obligatoria, podría ser nula. Evite usar edad como cadena y devuelva una cadena vacía (“”), devuelva nulo cuando sea desconocido:
```
{
	"idUsuario":  1,
	"nombre":  "Edgar",
	"edad":  10
}
```
La integridad del modelo podría verse afectada y evitará conversiones de tipos de datos inútiles.

4. #### Devolver tipos de datos correctos.
Se debe mantener el mismo formato de error, esto simplificará la integración de nuevos recursos. Por ejemplo, se puede definir una estructura como sigue y tiene cualquier excepción que se detecte se devuelva una respuesta como:
```
{
"exception":  "NotFoundException",
"message":  "Not Found Exception (404). Se encontro ningun usuario con id: 3",
"path":  "/usuarios"
}
```
```
{
	"exception":  "BadRequest",
	"message":  Bad Request (400). Solo se permiten valores enteros en la url,
	"path":  "/usuarios/1as"
}
```
Adicionalmente nunca se deben devolver el seguimiento de la pila de errores.

## Manejo de Exceptiones🚀

Si la petición no es satisfactoria, se provoca una excepción. El manejador de excepciones captura la excepción y se la envia la cliente, el mensaje de erro en el cuerpo y con la respuesta http correspondiente:

![image](https://github.com/gramska/demo/assets/43713784/85d9e498-3829-45a2-8a8b-a4b0bdfeda36)

En el proyecto se maneja el siguiente paquete para el manejo de excepciones:

![image](https://github.com/gramska/demo/assets/43713784/00572a88-b16c-42ce-9824-e144670b0fba)

[ErrorMenssage.java](https://github.com/gramska/demo/blob/main/src/main/java/com/ago/demo/exception/ErrorMessage.java): Contiene la estructura del mensaje se mostrar cuando el curso muestre un error.

[ApiExceptionHandler.java](https://github.com/gramska/demo/blob/main/src/main/java/com/ago/demo/exception/ApiExceptionHandler.java): Es controlador que gestionara el tipo de error que lanze el recuerdo cuando se presente.

[NotFoundException.java](https://github.com/gramska/demo/blob/main/src/main/java/com/ago/demo/exception/NotFoundException.java)<sup>(2)</sup>: Es la excepcion que se lazanra cuando se presente una excepcion no cuenta con un recurso (404) extendida de la clase  ``RunTimeException`` lo cual no nos obliga utlizar en entre bloques ``try/catch``.

*<sup>(2)</sup> El resto de clases con terminación "Exception" contienen la misma estructura y son para gestionar los tipos de excepciones que se pueden presentar*

## Despliegue 📦

_Se despliega con el servidor embebido de spring boot_

 1. Click derecho en la carpeta inicial de proyecto.
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
