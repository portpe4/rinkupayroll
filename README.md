# Rinku Payroll

API RESTful nomina Rinku

# Version

![version](https://img.shields.io/badge/version-1.0.0-blue.svg)


### Pre-requisitos

* Instalar Java 8
* Instalar Maven
* Instalar Lombok
* Instalar Postgresql 12
* Instalar IntelliJ IDEA IDE

### Desarrollo
* Arquitectura: Api RESTful
* Estandar: Openapi 3
* Framework: Spring Boot
* IDE: IntelliJ IDEA 

###Configuración
Crear el esquema de base de datos rinku
```
CREATE DATABASE db_rinku
```
### Instalación

Proyecto generado y compilado con maven

```
mavn clean install
```

### Pruebas unitarias

Las pruebas se realizaron con mockito y junit



      @Test
      public void metodoTest() {
        Mockito.when(mock).thenReturn(resultadoEsperado);
        Assert.assertNotNull(metodoa probar);
      }

## Construido con

* Spring boot 2.5.0
* Java 1.8
* Maven
* Intellij IDEA
## Documentacion (OpenAPI 3)

|Proyecto|URL|
|--------|---|
|Rinku|[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
## Licencia 📄

Este proyecto está bajo la Licencia MIT License - mira el archivo [LICENSE.md](LICENSE) para detalles

