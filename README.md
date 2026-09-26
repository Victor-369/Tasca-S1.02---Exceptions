# Tasca S1.02 — Exceptions

A small Maven project made up of three independent console exercises, each one practising custom (unchecked or checked) Java exceptions and safe handling of user input.

## Levels

| Level | Topic | Details |
|-------|-------|---------|
| [Level 1](src/main/java/com/pruebas/proyecto/nivell1/README.md) | Customised exceptions and error control | Checked vs unchecked exceptions, sales with an `EmptySaleException`. |
| [Level 2](src/main/java/com/pruebas/proyecto/nivell2/README.md) | Safe keyboard reading | A `ConsoleReader` utility that reads numbers, characters and yes/no answers without crashing on bad input. |
| [Level 3](src/main/java/com/pruebas/proyecto/nivell3/README.md) | Cinema seat reservations | A menu-driven app to reserve, cancel and look up cinema seat bookings. |

## Folder structure

```
Tasca S1.02 - Exceptions/
├── pom.xml                                  # Maven dependencies (log4j-core, log4j-api)
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/pruebas/proyecto/
    │   │       ├── nivell1/                 # Level 1 — see its own README
    │   │       ├── nivell2/                 # Level 2 — see its own README
    │   │       └── nivell3/                 # Level 3 — see its own README
    │   └── resources/
    │       └── log4j2.xml                   # Log4j2 configuration (console output)
```

## Requirements

- Java 25 (see `maven.compiler.source` / `maven.compiler.target` in `pom.xml`).
- Maven, to resolve the Log4j2 dependencies used by levels 1 and 2.

## Running a level

Each level has its own `Main.java` with a `main()` method, so any of them can be run independently, for example:

```
mvn compile
mvn exec:java -Dexec.mainClass="com.pruebas.proyecto.nivell3.Main"
```

(replace the class name with `nivell1.Main` or `nivell2.Main` to run a different level).