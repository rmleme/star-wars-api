# star-wars-api

A Kotlin facade for [SWAPI (The Star Wars API)](https://swapi.dev/).

## Technologies

- [Detekt 1.22](https://detekt.dev/)
- [Docker Compose 2.16](https://docs.docker.com/compose/)
- [Docker Engine 23.0](https://docs.docker.com/engine/)
- [Fuel 2.3](https://fuel.gitbook.io/documentation/)
- [Gradle 7.6](https://gradle.org/)
- [Jackson 2.14](https://github.com/FasterXML/jackson)
- [JaCoCo 0.8](https://www.eclemma.org/jacoco/)
- [JDK 17](https://openjdk.org/projects/jdk/17/)
- [Kotest 5.5](https://kotest.io/)
- [Kotlin 1.8](https://kotlinlang.org/docs/whatsnew18.html)
- [Ktor/Netty 2.2](https://ktor.io/)
- [Logback 1.4](https://logback.qos.ch/)
- [Spring Framemork 6.0](https://spring.io/projects/spring-framework)
- [WireMock 2.31](https://wiremock.org/)

## Running the application

### Tests
```sh
docker compose -f compose-test.yml up
```

### Application
```sh
docker compose up
```

- **Note:** the first execution will be slow, as Docker (and Gradle) will need to download all the necessary dependencies to build the images. 

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
  </details></br>

  <details>
    <summary><b>Response Body</b></summary><p>

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
  </details></br>

- ***GET*** `/v1.0/planets` - list all planets saved in the application.
    <details>
      <summary><b>Request</b></summary><p>

    ```sh
    curl -i -s -X GET 'http://localhost:8080/v1.0/planets'
    ```
    </p>
    </details></br>

    <details>
      <summary><b>Response Body</b></summary><p>

  ```json
    [
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
    ]
  ```
  </p>
  </details></br>

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
  </details></br>

  <details>
    <summary><b>Response Body</b></summary><p>

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
  </details></br>

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
  </details></br>

  <details>
    <summary><b>Response Body</b></summary><p>

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
  </details></br>

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
  </details></br>

  <details>
    <summary><b>Response Body</b></summary><p>

  ```json
    ```
  </p>
  </details></br>

- ### Postman
  - A [Postman](https://www.postman.com/) [collection](./docs/postman/star-wars-api.postman_collection.json) (and the respective [environment](./docs/postman/Star-Wars-API-Development-Env.postman_environment.json)) is available for all endpoints. Import both files to your local Postman installation and start making requests.
