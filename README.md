# Instrucciones

1.a Clone estos proyectos en Netbeans

1.b Ejecute Clean & Build sobre el proyecto raíz: s2-parcial1-heroes para descragar las dependencias del proyecto. 

2. En Netbeans Vaya a Services, Databases, JavaDB y cree una base de datos que 
se llame heroeDB (los demás campos déjelos en blanco)

3. Cada vez que complete un paso haga un commit 

4. Al finalizar ingrese a github y genere un release con el nombre Entrega_Parcial

## Contexto

Esta aplicación permite gestionar los heroes de nuestra ciudad. El recurso presente en la aplicación es Heroe, el cual tiene un nombre (name), una habilidad (ambas String) y un id (Long) que es la llave primaria. 
En la aplicación usted encontrará que las funcionalidades de crear (POST) y solicitar (GET) ya están implementadas.

Se nos ha solicitado completar los siguientes requerimientos.

## Punto 1 (60%): Villanos
Se desea que el sistema permita gestionar ahora el listado de villanos que han sido capturados por el heroe.
De cada villano se conoce su nombre (name) (String), si está preso actualmente (preso) (Boolean), la fecha del último arresto (fechaArresto) (Date) y se tiene un campo 
id (Long) que representa la llave primaria del villano. 

Ud. debe extender su programa para que cuando ejecute 

```POST localhost:8080/heroes-web/api/heroes```

con el json:

```json 
{ 
  "name": "Batman",
  "habilidad": "Soy Rico",
  "villanos": [{
               "name": "El dos caras",
			   "preso": true,
			   "fechaArresto": "2018-02-22T20:38:54.973-05:00"
            }]
}
```

Se cree el heroe con la información de un villano o villanos. 

Para esto Ud. debe:

1. (15%) Crear las clases VillanoDTO y VillanoEntity que modelan al Villano.  

En la clase VillanoDTO, además de tener un constructor sin parámetros, 
defina un constructor para convertir un VillanoEntity en un VillanoDTO:
```java
public VillanoDTO(VillanoEntity heroe) {
        this.id = heroe.getId();
        this.name = heroe.getName();
		...
    }
```

Para convertir un VillanoDTO en un VillanoEntity defina el siguiente método:
```java
public VillanoEntity toEntity() {
        VillanoEntity entity = new VillanoEntity();
        entity.setId(this.id);
        entity.setName(this.name);   
        ...		
        return entity;
    }
```
2. (10%) Defina en HeroeEntity la relación con villano (unidireccional) e implemente sus set/get. Esta es una relación de **composición** de uno de muchos (OneToMany). 

3. (15%) Defina un atributo nuevo en HeroeDetailDTO que representa el listado de villanos que han sido capturados por el villano (villanos).
Defina set/get y actualice el método constructor que recibe un HeroeEntity al igual que el método toEntity, para que también hagan la conversión del listado de villanos. 

4. (15%) Modifique el método createHeroe de la clase HeroeLogic para que tenga en cuenta las siguientes reglas de negocio. 
- No debe existir un heroe con el mismo nombre 
- Todos los villanos deben estar presos
- La fecha de captura de todos los villanos debe ser menor a la fecha actual (para esto puede usar los métodos before o after de la clase Date, o el método compareTo),

Si las reglas de negocio se cumplen, se debe llamar la persistencia para que 
el objeto sea persistido, de lo contrario debe lanzar una excepción 
BussinessLogicException con un mensaje donde se especifique cual regla no se cumplió.

5. (15%) Modifique la prueba de crear heroe en HeroePersistenceTest para que ahora también valide si el listado de villanos es correcto en su longitud y en cada elemento almacenado.

6. Ejecute su prueba unitaria

7. Ejecute la siguiente prueba la cual debe arrojar el código 200

`POST localhost:8080/heroes-web/api/heroes`

* json body

```json 
{ 
  "name": "Superman",
  "habilidad": "Volar",
  "villanos": [{
               "name": "Zod",
			   "preso": true,
			   "fechaArresto": "2018-02-22T20:38:54.973-05:00"
            }]
}
```

* Fijese en el id que retornó el POST y Ejecute 

`GET localhost:8080/heroes-web/api/heroes/:id`

8. Ejecute las siguientes pruebas que deben arrojar un código 412

```json 
{ 
  "name": "Batman",
  "habilidad": "Soy Rico",
  "villanos": [{
               "name": "El dos caras",
			   "preso": false,
			   "fechaArresto": "2018-02-22T20:38:54.973-05:00"
            }]
}
```

```json 
{ 
  "name": "Batman",
  "habilidad": "Soy Rico",
  "villanos": [{
               "name": "El dos caras",
			   "preso": true,
			   "fechaArresto": "2018-02-22T20:38:54.973-05:00"
            },{
               "name": "El Joker :D",
			   "preso": true,
			   "fechaArresto": "2018-03-22T20:38:54.973-05:00"
            }]
}
```

## Punto 2 (40%): Eliminar
Se desea que el sistema permita eliminar un heroe y todos sus villanos.
Para ello usted de cumplir con los siguientes pasos.

1. (10%) Cree un método con el nombre delete en la clase HeroePersistent el cual recibe el id del heroe y elimina el heroe.

2. (10%) Cree un método de prueba en la clase HeroePersistentTest el cual valida que efectivamente se este borrando el heroe.

3. (10%) Cree un método con el nombre deleteHeroe en la clase HeroeLogic en el cual se debe validar la siguiente regla de negocio:
- Solo se puede eliminar un heroe que tenga villanos, en el caso que no tenga el método debe lanzar una excepción BusinessLogicException con la información del error, de lo contrario se llama a la persistencia y se procede a eliminar el heroe.

4. (10%) Cree el método deleteHeroe en la clase HeroeResource el cual verifica la existencia del recurso; en el caso que no exista lanza una excepción WebApplicationException con el mensaje correspondiente, de lo contrario llama a la lógica para validar reglas de negocio.

5. Verifique el funcionamiento ejecutando

`DELETE localhost:8080/heroes-web/api/heroes/:id`
