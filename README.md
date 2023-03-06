# star-wars-api

A Kotlin facade for [SWAPI (The Star Wars API)](https://swapi.dev/).

## Technologies

- Embedded:
  - [Detekt 1.22](https://detekt.dev/)
  - [Fuel 2.3](https://fuel.gitbook.io/documentation/)
  - [Gradle 7.6](https://gradle.org/)
  - [Jackson 2.14](https://github.com/FasterXML/jackson)
  - [JaCoCo 0.8](https://www.eclemma.org/jacoco/)
  - [JDK 17](https://openjdk.org/projects/jdk/17/)
  - [Kotest 5.5](https://kotest.io/)
  - [Kotlin 1.8](https://kotlinlang.org/docs/whatsnew18.html)
  - [Ktor/Netty 2.2](https://ktor.io/)
  - [Logback 1.4](https://logback.qos.ch/)
  - [MongoDB 6.0](https://www.mongodb.com/)
  - [Spring Framemork 6.0](https://spring.io/projects/spring-framework)
  - [Testcontainers 1.17](https://www.testcontainers.org/)
  - [WireMock 2.31](https://wiremock.org/)
- External:
  - [Docker Compose 2.16](https://docs.docker.com/compose/)
  - [Docker Engine 23.0](https://docs.docker.com/engine/)

**Note:** <u>the external technologies are prerequisites and must be previously installed at your host to run the application</u>.

## Running the application

### Tests
```sh
docker compose -f compose-test.yml up
```

### Application
```sh
docker compose up
```

- **Important:** the first execution will be slow, as Docker (and Gradle) will need to download all the necessary dependencies to build the images.
- The command above will trigger the execution of two containers: MongoDB and the application.
- Application logs will be available at directory `./app-data/star-wars-api/log` of the host. If you prefer another base directory instead of `/app-data`, set the environment variable `HOST_APP_DATA_DIR`.

## Endpoints

- **URL:** `http://localhost:8080`

### REST

- ***POST*** `/v1.0/planets` - given an id, load a planet from swapi and persist it in the application.
  <details>
    <summary><b>Request</b></summary><p>

  ```sh
  curl -i -s -X POST 'http://localhost:8080/v1.0/planets' -H 'Content-Type: application/json' -d '{ "id": 9 }'
  ```
  </p>
  </details><br/>

  <details>
    <summary><b>Response Body (HTTP 201)</b></summary><p>

  ```json
  {
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "films": [
      {
        "title": "Return of the Jedi",
        "director": "Richard Marquand",
        "releaseDate": "1983-05-25"
      },
      {
        "title": "The Phantom Menace",
        "director": "George Lucas",
        "releaseDate": "1999-05-19"
      },
      {
        "title": "Attack of the Clones",
        "director": "George Lucas",
        "releaseDate": "2002-05-16"
      },
      {
        "title": "Revenge of the Sith",
        "director": "George Lucas",
        "releaseDate": "2005-05-19"
      }
    ]
  }
  ```
  </p>
  </details><br/>

    <details>
      <summary><b>Other responses: HTTP 400, HTTP 422</b></summary><p>
    </details><br/>

- ***GET*** `/v1.0/planets` - list all planets saved in the application.
    <details>
      <summary><b>Request</b></summary><p>

    ```sh
    curl -i -s -X GET 'http://localhost:8080/v1.0/planets'
    ```
    </p>
    </details><br/>

    <details>
      <summary><b>Response Body (HTTP 200)</b></summary><p>

  ```json
    [
      {
        "id": 9,
        "name": "Coruscant",
        "climate": "temperate",
        "terrain": "cityscape, mountains",
        "films": [
          {
            "title": "Return of the Jedi",
            "director": "Richard Marquand",
            "releaseDate": "1983-05-25"
          },
          {
            "title": "The Phantom Menace",
            "director": "George Lucas",
            "releaseDate": "1999-05-19"
          },
          {
            "title": "Attack of the Clones",
            "director": "George Lucas",
            "releaseDate": "2002-05-16"
          },
          {
            "title": "Revenge of the Sith",
            "director": "George Lucas",
            "releaseDate": "2005-05-19"
          }
        ]
      }
    ]
  ```
  </p>
  </details><br/>

    <details>
      <summary><b>Other responses:</b></summary><p>
    </details><br/>

- ***GET*** `/v1.0/planets/?name={name}` - find a planet by name.
  - query parameters:
    - required:
      - `name`: **string**

  <details>
    <summary><b>Request</b></summary><p>

  ```sh
  curl -i -s -X GET 'http://localhost:8080/v1.0/planets/?name=Coruscant'
  ```
  </p>
  </details><br/>

  <details>
    <summary><b>Response Body (HTTP 200)</b></summary><p>

  ```json
  {
    "id": 9,
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "films": [
      {
        "title": "Return of the Jedi",
        "director": "Richard Marquand",
        "releaseDate": "1983-05-25"
      },
      {
        "title": "The Phantom Menace",
        "director": "George Lucas",
        "releaseDate": "1999-05-19"
      },
      {
        "title": "Attack of the Clones",
        "director": "George Lucas",
        "releaseDate": "2002-05-16"
      },
      {
        "title": "Revenge of the Sith",
        "director": "George Lucas",
        "releaseDate": "2005-05-19"
      }
    ]
  }
    ```
  </p>
  </details><br/>

    <details>
      <summary><b>Other responses: HTTP 404</b></summary><p>
    </details><br/>

- ***GET*** `/v1.0/planets/{id}` - find a planet by id.
  - path parameters:
    - required:
      - `id`: **int**

  <details>
    <summary><b>Request</b></summary><p>

  ```sh
  curl -i -s -X GET 'http://localhost:8080/v1.0/planets/9'
  ```
  </p>
  </details><br/>

  <details>
    <summary><b>Response Body (HTTP 200)</b></summary><p>

  ```json
  {
    "id": 9,
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "films": [
      {
        "title": "Return of the Jedi",
        "director": "Richard Marquand",
        "releaseDate": "1983-05-25"
      },
      {
        "title": "The Phantom Menace",
        "director": "George Lucas",
        "releaseDate": "1999-05-19"
      },
      {
        "title": "Attack of the Clones",
        "director": "George Lucas",
        "releaseDate": "2002-05-16"
      },
      {
        "title": "Revenge of the Sith",
        "director": "George Lucas",
        "releaseDate": "2005-05-19"
      }
    ]
  }
    ```
  </p>
  </details><br/>

    <details>
      <summary><b>Other responses: HTTP 400, HTTP 404</b></summary><p>
    </details><br/>

- ***DELETE*** `/v1.0/planets/{id}` - delete a planet.
  - path parameters:
    - required:
      - `id`: **int**

  <details>
    <summary><b>Request</b></summary><p>

  ```sh
  curl -i -s -X DELETE 'http://localhost:8080/v1.0/planets/9'
  ```
  </p>
  </details><br/>

  <details>
    <summary><b>Response Body (HTTP 204)</b></summary><p>

  ```json
  ```
  </p>
  </details><br/>

    <details>
      <summary><b>Other responses: HTTP 400, HTTP 404</b></summary><p>
    </details><br/>

- ### Postman
  - A [Postman](https://www.postman.com/) [collection](./docs/postman/star-wars-api.postman_collection.json) (and the respective [environment](./docs/postman/Star-Wars-API-Development-Env.postman_environment.json)) is available at [/docs/postman/](./docs/postman/) for all endpoints. Import both files to your local Postman installation and start making requests.

## Further discussions

### Disclaimers:

- MongoDB credentials were stored as plain text at configuration files. Naturally, in a Production system, such credentials would not be disclosed (typically they would be configured in a Secrets Manager).
- Through a Docker volume (in [compose-mongo.yml](./compose-mongo.yml)), the state of MongoDB is maintained between executions.

### Possible improvements:

- Implement a retry logic for Fuel calls to swapi at [HttpClient](./integration/src/main/kotlin/org/rmleme/starwarsapi/integration/http/HttpClient.kt).
- Document the endpoints with [OpenAPI](https://www.openapis.org/) (in addition to README + Postman).
- Fine-tune the exclusion rules of JaCoCo plugin in order to discard meaningless classes from code coverage report (*e.g.* configuration classes, DTOs, etc).
- Use the reactive version of MongoRepository (instead of triggering new coroutine contexts at [PlanetRepositoryImpl](./persistence/src/main/kotlin/org/rmleme/starwarsapi/persistence/repository/PlanetRepositoryImpl.kt)). 