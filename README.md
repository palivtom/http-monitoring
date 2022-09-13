# http-monitoring

App can monitor http[-s] GET endpoints without security in given interval

### Running the app through the IDE
__Requirements: Running MySQL or using H2 one__

1. Compile the app - In this app is used OpenApi code generator, without the generated classes is application unable to build and run
   ```shell
   ./mvnw compile
   ```

2. Run the app

### Running the app through the docker
1. Build the app
    ```shell
    ./mvnw package
    ```

2. Run the docker compose (database is packed)
    ```shell
    docker compose up --build
    ```

### How to use this app
After running the docker compose, you can see endpoints in a file living at `/.openapi/http-monitoring.yml,` or you can check the endpoints (by default) at address `localhost:8081/actuator/swagger-ui`.

**Default user access**
Add this header to your requests: `accessToken: dcb20f8a-5657-4f1b-9f7f-ce65739b359e`.

If any user is needed, put it to the database/create a flyway migration script.