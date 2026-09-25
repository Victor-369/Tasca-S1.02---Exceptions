# Level 2 — Safe Keyboard Reading

This part of the project is a small utility class, `ConsoleReader`, that
reads different types of data from the keyboard safely. If the user
types something invalid, the program shows an error message and asks
for the value again instead of crashing.

## What it does

- Reads `byte`, `int`, `float` and `double` values, catching
  `InputMismatchException` when the input is not a number.
- Reads a single `char`, a `String` and a yes/no answer (`s` / `n`),
  using three custom checked exceptions for the cases that
  `InputMismatchException` does not cover:
    - `UniqueCharacterException` — thrown when more (or less) than one
      character is entered where only one is expected.
    - `StringTooLongException` — thrown when the text is longer than
      allowed.
    - `YesNoAnswerException` — thrown when the answer is not `y` or `n`.
- Every error is logged with Log4j2 and printed to the console, and
  the same message is shown again so the user can try once more.

## Folder structure

```
Tasca S1.02 - Exceptions/
├── pom.xml                         # Maven dependencies (log4j-core, log4j-api)
└── src/
    └── main/
        ├── java/
        │   └── com/pruebas/proyecto/
        │       └── nivell2/
        │           ├── Main.java                       # Calls every ConsoleReader method
        │           ├── exceptions/
        │           │   ├── StringTooLongException.java
        │           │   ├── UniqueCharacterException.java
        │           │   └── YesNoAnswerException.java
        │           └── utils/
        │               └── ConsoleReader.java           # The utility class itself
        └── resources/
            └── log4j2.xml           # Log4j2 configuration (console output)
```

## Notes

- `readString()` currently rejects anything longer than 3 characters.
  This is intentional, just to test that `StringTooLongException` is
  thrown and caught correctly — it is not meant to be a realistic
  limit for a real name or sentence.
- All classes share a single `Scanner` object, so there is only one
  connection to the keyboard for the whole program.