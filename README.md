# Demicon

Go to src/main/resource/db, then you run in terminal:
### `docker build -t demicon-db-image ./`
### `docker run -d --name demicon-db-container -p 5432:5432 demicon-db-image`

With this, you just created a dump db name `demicondb` in your machine

## Liquibase script
`./gradlew liquibase dropAll` => drop all tables
`./gradlew liquibase update` => loading initial changeset which in this case create a demicon_user table (`./gradlew bootrun` will do this job as well)

Port: 8080
Endpoint: `http://localhost:8080/users/import-users` to import users from open api (initial will import 20 users)
Endpoint: `http://localhost:8080/users/getUsers` to get all users
