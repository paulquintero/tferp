#Proyect tferp
this is a crud proyect created with the library Quick

# Quick

Quick is a library that allows you to generate the base classes and files for a CRUD operation in a Spring project.

This means it will generate the necessary classes to have a basic path that includes a Controller, Service, Repository, and its corresponding Entity.

## Deployment

To run this project, you need Java 8 or Java 21, depending on the version downloaded. The develop branch works with Java 8 and the feature/migracion/java21 branch works with Java 21.


## Usage/Examples

### Initial Library Configuration

To use the library, you must add the following properties to either your .properties or yml file. These properties are responsible for scanning packages to read and generate the necessary classes. The required properties are listed below:

```properties
quick.api.parent.basePackage //corresponds to the project's base package name
quick.api.package.controller //name of the package where the controllers are located
quick.api.package.entity //name of the package where the entities are located
quick.api.package.service //name of the package where the services are located
quick.api.package.repository //name of the package where the repositories are located
```

### example
```properties
quick.api.parent.basePackage=com.project.crud
quick.api.package.controller=controllers
quick.api.package.entity=entityies
quick.api.package.service=services
quick.api.package.repository=repositoryies

```

To use the library, you must import it and create a controller that specifies which files you want to generate. The methods and controller are listed below as examples of what can be included.

The controller is currently located in the crud-repository project, with the name TemplateController.

## Implementation

Import the library scan. First, indicate the project's base path, followed by the library "com.quick.rest.*".

### Aplicaction
```java
@SpringBootApplication(scanBasePackages = {"com.project.crud.*","com.quick.rest.*"})
public class ProjectCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCrudApplication.class, args);
	}

}
```

### Controller

```java
package com.project.crud.controllers;

import com.quick.rest.models.request.*;
import com.quick.rest.models.response.QuickApiResponse;
import com.quick.rest.services.QuickApiGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class serves as the main controller for automatically generating files.
 * Be careful! Do not leave this endpoint exposed in deployed environments. Only use it in your local environment or as part of your coding process.
 * @author Paul Quintero
 */
@RestController
@RequestMapping("quick/template")
public class TemplateController {

	@Autowired
	private QuickApiGeneratorService quickApiGeneratorService;

    /**
     * This method allows you to generate the entity exclusively.
     * @param entityTemplateDTO It contains the name of the entity and its columns.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
	@PostMapping(value = "entity", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateEntity(@RequestBody EntityTemplateDTO entityTemplateDTO){
	  QuickApiResponse quickResponse = new QuickApiResponse();
	  if(this.quickApiGeneratorService.generateEntity(entityTemplateDTO)) {
	    quickResponse.setMessageResponse("Success");
	  } else {
	    quickResponse.setMessageResponse("Fail");
	  }
	  return ResponseEntity.ok(quickResponse);
	}

    /**
     * It allows to generate its repository interface and its corresponding entity.
     * @param repositoryTemplateDTO It contains the name of the entity and its columns, and the name of repository.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
	@PostMapping(value = "repository", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateRepository(@RequestBody RepositoryTemplateDTO repositoryTemplateDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateRepository(repositoryTemplateDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}

    /**
     * It allows to generate its service, repository interface and its corresponding entity.
     * @param businessTemplateDTO It contains the name of the entity and its columns, the name of repository and the name of service.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
	@PostMapping(value = "service", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateService(@RequestBody BusinessTemplateDTO businessTemplateDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateService(businessTemplateDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}

    /**
     * It allows the full path including controller, service, repository, entity
     * @param fullBodyDTO It contains the name of the endpoint, the entity and its columns, the name of repository and the name of service.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
	@PostMapping(value = "full-path", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateFull(@RequestBody FullBodyDTO fullBodyDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateFull(fullBodyDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}
}

```

## API Reference

#### Generate an entity

```http
  POST quick/template/entity
```

```json
{
  "entityName" : "book", //string type, name of the entity
  "columns": [{
      "columnName" : "id", //Name of the column
      "dataType": "INT", //Data type of the column, data types can be INT("Integer"), INTEGER("Integer"), LONG("Long"), STRING("String"), BOOLEAN("Boolean"), FLOAT("Float"), DOUBLE("Double");
      "primaryKey": "true" //indicates if this will be the primary key column
  },
  {
      "columnName" : "name",
      "dataType": "STRING",
      "primaryKey": "false"
  }
  ]
}
```

#### Generate a repository by relating it to an existing entity.

```http
  POST quick/template/repository
```

```json
{
  "repositoryName": "string", //name of the repository
  "entityName": "string", // name of the existing entity
  "dataTypeId": "INT" //data type used as the primary key in the entity
}
```


#### Generate a service and its implementation with an existing repository.

```http
  POST quick/template/service
```

```json
{
  "serviceName": "string", //name of the service
  "repositoryName": "string", //name of the existing repository
  "entityName": "string", //name of the existing entity
  "idDataType": "INT" //data type used as the primary key in the entity
}
```

#### Generate a complete CRUD for an entity, for example a book, with its respective controller, service, repository, and entity

```http
  POST quick/template/full-path
```

```json
{
  "entityName": "string", //name of the existing entity
  "columns": [
      "columns": [{
        "columnName" : "id", //Name of the column
        "dataType": "INT", //Data type of the column, data types can be INT("Integer"), INTEGER("Integer"), LONG("Long"), STRING("String"), BOOLEAN("Boolean"), FLOAT("Float"), DOUBLE("Double");
        "primaryKey": "true" //indicates if this will be the primary key column
    },
  ],
  "pathMapping": "string" //name of the endpoint, for example book
}
```

## Swagger

The current version has Swagger implemented, so you can make requests within the same tool, as well as view the endpoints for generating each file.
```bash
http://localhost:{port}/api/v1/swagger-ui/index.html#/template-controller
```

