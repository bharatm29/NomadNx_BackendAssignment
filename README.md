## Backend Assessment for NomadNx internship

---

### How to run

### Natively

To run on your machine, you have to export two environment variables:

`$FIREBASE_PRIVATE_KEY`: `/path/to/private-key.json`

`$FIREBASE_WEB_KEY`: Your firebase web API key

Then run the following command

```shell
mvn spring-boot:run
```

### Using Docker

1. Place your private key JSON file obtained from firebase to the root of the application. It should be of
   name `private-key.json`
2. Populate the `firebase.env` file with the web API key
3. Run the following commands

```shell
docker compose up
```

---

### Usage

The Application runs on port `4000` by default. If you are running with docker compose, the 4000 port of the container
is also mapped to 4000 port on the host machine.

#### Endpoints

Access the following endpoints with the prefix `localhost:4000`

- [Swagger docs](http://localhost:4000/swagger-ui.html) - `/swagger-ui.html`

- Unsecure user action endpoints - `/api/v1/users/create`, `/api/v1/users/login`

- Secure data endpoints - `/api/v1/data/names`

---