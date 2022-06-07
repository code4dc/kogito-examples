# Kogito Serverless Workflow - Greeting Example

## Description

This example contains a simple greeting workflow services that use gRPC. 
The service is described using both JSON  format as defined in the 
[CNCF Serverless Workflow specification](https://github.com/cncf/wg-serverless/tree/main/workflow/spec).

The workflow expects as JSON input containing the name of the person to greet, and the language in 
which to greet them in
(see details in the [Submit a request](#Submit-a-request) section).

The workflow invokes a gRPC service that returns the greet message in the proper language

The flow then prints out the greeting to the console.

## Installing and Running

### Prerequisites
 
You will need:
  - Java 11+ installed
  - Environment variable JAVA_HOME set accordingly
  - Maven 3.8.1+ installed

When using native image compilation, you will also need: 
  - [GraalVm](https://www.graalvm.org/downloads/) 19.3.1+ installed
  - Environment variable GRAALVM_HOME set accordingly
  - Note that GraalVM native image compilation typically requires other packages (glibc-devel, zlib-devel and gcc) to be installed too.  You also need 'native-image' installed in GraalVM (using 'gu install native-image'). Please refer to [GraalVM installation documentation](https://www.graalvm.org/docs/reference-manual/aot-compilation/#prerequisites) for more details.

### Compile and Run in Local Dev Mode

```sh
mvn clean package quarkus:dev
```

### Compile and Run in JVM mode

```sh
mvn clean package 
java -jar target/quarkus-app/quarkus-run.jar
```

or on windows

```sh
mvn clean package
java -jar target\quarkus-app\quarkus-run.jar
```

### Compile and Run using Local Native Image
Note that this requires GRAALVM_HOME to point to a valid GraalVM installation

```sh
mvn clean package -Pnative
```
  
To run the generated native executable, generated in `target/`, execute

```sh
./target/sw-quarkus-greeting-{version}-runner
```

### Submit a request

The service based on the JSON workflow definition can be accessed by sending a request to http://localhost:8080/jsongreet
with following content 

```json
{
  "workflowdata": {
   "name" : "John",
   "language": "English"
  }
}
```

Complete curl command can be found below:

```sh
curl -X POST -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"workflowdata" : {"name": "John", "language": "English"}}' http://localhost:8080/jsongreet
```

Log after curl executed:

```json
{"id":"541a5363-1667-4f6d-a8b4-1299eba81eac","workflowdata":{"name":"John","language":"English","greeting":"Hello from JSON Workflow, "}}
```

In Quarkus you should see the log message printed:

```text
Hello from JSON Workflow, John
```

If you would like to greet the person in Spanish, we need to pass the following data on workflow start:

```json
{
  "workflowdata": {
   "name" : "John",
   "language": "Spanish"
  }
}
```

Complete curl command can be found below:

```sh
curl -X POST -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"workflowdata" : {"name": "John", "language": "Spanish"}}' http://localhost:8080/jsongreet
```

In Quarkus you should now see the log message printed: 

```text
Saludos desde JSON Workflow, John
```

Similarly, the service based on the YAML workflow definition can be access by sending a request to http://localhost:8080/yamlgreet'
using the same content:

```json
{
  "workflowdata": {
   "name" : "John",
   "language": "English"
  }
}
``` 

Complete curl command can be found below:

```sh
curl -X POST -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"workflowdata" : {"name": "John", "language": "English"}}' http://localhost:8080/yamlgreet
```
 
In Quarkus you should see the log message:

```text
Hello from YAML Workflow, John
```

You can also change the language parameter value to "Spanish" to get the greeting in Spanish.