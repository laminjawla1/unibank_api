# UniBank database bootstrap (tables + users)

UniBank creates tables automatically on startup using Hibernate (`spring.jpa.hibernate.ddl-auto=update`).

On every startup the `DatabaseSeeder` runs and ensures:
- roles are present
- default users exist (admin/teller/finance/compliance)
- account types and transaction types exist

## Default users (fresh database)

All default users use the same password:
- Password: `Pass@123`

Usernames:
- `admin` (ROLE_ADMIN)
- `teller` (ROLE_TELLER)
- `finance` (ROLE_FINANCE)
- `compliance` (ROLE_COMPLIANCE)

## One command to bootstrap ANY new database URL

### Option A: run the built JAR

From `unibank_api/` after you have built the jar:

`java -jar target/unibank_api-0.0.1-SNAPSHOT.jar --spring.profiles.active=mysql --spring.datasource.url="jdbc:mysql://HOST:3306/DB_NAME?allowPublicKeyRetrieval=true&useSSL=false" --spring.datasource.username="USER" --spring.datasource.password="PASS"`

### Option B: use Maven wrapper (no need to pre-build)

From `unibank_api/`:

Windows:

`mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:mysql://HOST:3306/DB_NAME?allowPublicKeyRetrieval=true&useSSL=false --spring.datasource.username=USER --spring.datasource.password=PASS"`

macOS/Linux:

`./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:mysql://HOST:3306/DB_NAME?allowPublicKeyRetrieval=true&useSSL=false --spring.datasource.username=USER --spring.datasource.password=PASS"`

Notes:
- You can also override via env vars: `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`.
- On a brand-new DB, the first startup creates the tables *and* seeds the users.
- Change these default passwords after first login.
