# AgoDemo
Proyecto de demostración de la implementación:

 - Solicitudes HTTP *Request*.
 - Respuestas HTTP *Respose*.
 - Manejo de Exceptiones.
 

## Solicitudes HTTP (Request)🚀

Al diseñar una **solicitud http (request)**, es importante poder abstraer la funcionalidad de su servicio de tal manera que todas las operaciones puedan representarse realizando operaciones **CRUD** (Create, Read, Update, Delete) para los diferentes recursos (entidades). Las acciones (**verbos HTTP** ó **métodos HTTP**) que se realizan nunca deben ser parte del endpoint.

El enfoque más utilizado es exponer diferentes colecciones de recursos relacionados. Por ejemplo, si su servicio contiene información sobre diferentes empresas y usuarios, podría tener una colección llamada **empresas** y otra colección llamada **empleados**:

 - /empresa
 - /empresa/{idEmpresa}
 - /empresa/{idEmpresa}/usuario
 - /empresa/{idEmpresa}/usuario/{idUsuario}
 - /usuarios
 - /usuarios/{idUsuario}

Por lo tanto si quieremos obtener la lista de empresas debemos solicitar el recurso [/empresa], si queremos obtener la informacion de los usuarios de una empresea debemos solicitar el recurso [/empresa/{idEmpresa}/usuario].

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

## Respuestas HTTP (Respose)🚀

Al  enviar una solicitud (request), el servidor nos respondera (response) con un codigo de la solicitud, opcionalmente puede incluir el contenido de la respuesta y una cabecera "content-type" que en este caso sera del tipo "application/json", es decir del tipo Json.

Los codigos de repuesta esta formado por tres numeros enteros que se pueden agrupar en 5 tipos:

 - 1xx Respuesta informativa.
 - 2xx Respuesta exitosa.
 - 3xx Redirección.
 - 4xx Error en el cliente.
 - 5xx Error en el servidor

De las cuales de los grupos 1 y 3 (1xx y 3xx) son las que comunmente son las menos utlizadas. 

Dentro de los grupos 2, 4 y 5 (2xx, 4xx y 5xx) podemos mencionar los siguientes:

- 200 OK
- 201 Creado
- 204 Sin contenido
- 400 Petición Incorrecta
- 404 No encontrado
- 500 Error interno de servidor 

Tomando el ejemplo de la seccion anterior se podria regresar las siguientes respuestas:
|Solicitud | Metodo | Accion | Respuesta |
|--|--|--|--|
|GET|/usuarios|Listado de usuarios|200(Ok): Lista de Usuarios|
|GET|/usuarios/{idUsuario}|Información de un usuario|200(Ok):Usuario solicitado|
|POST|/usuarios|Registra un nuevo usuario|201(Created): Vacio|
|DELETE|/usuarios/{idUsuario}|Elimina un usuario|204(Created): No Content|
|PUT|/usuarios|Actualiza un usuario|204(Created): No Content|
|PATCH|/usuarios/{idUsuario}|Actualiza el nombre de un usuario|204(Created): No Content|


Recomiendan los siguientes concejos a la hora de implementar las repuestas de los recursos generados:

 1. #### Pagina tus resultados.
Implementar la paginación en todos los recursos que devolverá demasiados datos. Reducirás el tiempo de respuesta y evitar comportamientos no deseados en el cliente.

2. #### Responder solo lo que se esta solicitando.
Si está solicitando un recurso, devuelva su representación o una lista de ellos; Evite responder con algo diferente. Por ejemplo solicitamos un listado de usuarios y se devulve la siguiente respuesta:

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
Esta respuesta en realidad es incorrecta debido a que obtiene un objeto con una lista de representaciones de usuarios. La respuesta deberia haber obtenido solo una lista de representaciones de usuarios:
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

2. #### ## Devolver tipos de datos correctos.
Devolver los tipods de datos correctos y aproveche los objetos nulos en caso de que no tenga esa información.

Por ejemplo, al devolver información del usuario. Los campos que tenemos de los usuarios son idUsuario, nombre,y edad:
```
{
	"idUsuario":  <int>,
	"nombre":  <string>,
	"edad":  <int>
}
```
Si la edad no fuera obligatoria, podría ser nula. En ese caso, evite usar age como cadena y devuelva una cadena vacía (“”), devuelva nulo cuando sea desconocido:
```
{
	"idUsuario":  1,
	"nombre":  "Edgar",
	"edad":  10
}
```
La integridad del modelo podría verse afectada y evitará conversiones de tipos de datos inútiles.

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
