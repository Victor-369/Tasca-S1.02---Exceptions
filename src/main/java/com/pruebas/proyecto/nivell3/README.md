# Level 3 — Cinema Seat Reservations

This part of the project is a small console application that manages seat reservations for a single cinema room. It is used to reserve a seat for a person, cancel a reservation, and look up who has booked what — all without the program ever crashing on bad input.

## What it does

- Asks for the number of rows and seats per row when it starts, then shows a menu with six options:
    1. Display all reserved seats.
    2. Display the seats reserved by a person.
    3. Reserve a seat.
    4. Cancel a seat reservation.
    5. Cancel all reservations made by a person.
    0. Exit.
- Every seat, row and menu choice typed by the user is validated before it reaches the business logic, catching `InputMismatchException` so a wrong keystroke never breaks the program.
- Business rules are enforced with four custom unchecked exceptions:
    - `InvalidSeatException` — the row or seat number does not exist in this room.
    - `InvalidPersonNameException` — the name is blank or contains digits.
    - `SeatAlreadyTakenException` — the seat is already reserved by someone else.
    - `SeatAlreadyEmptyException` — trying to cancel a seat that is not currently reserved.
- Two seats are considered equal when they share the same row and seat number (`equals()` / `hashCode()` in `Seat`), regardless of who booked them.

## Folder structure

```
Tasca S1.02 - Exceptions/
└── src/
    └── main/
        └── java/
            └── com/pruebas/proyecto/
                └── nivell3/
                    ├── Main.java                              # Entry point: asks for room size, starts the UI
                    ├── model/
                    │   └── Seat.java                          # A single seat reservation (row, seat, person)
                    ├── service/
                    │   └── ReservationService.java            # Business logic and validation
                    ├── ui/
                    │   └── ConsoleUI.java                     # Menu, console input/output
                    └── exception/
                        ├── InvalidPersonNameException.java
                        ├── InvalidSeatException.java
                        ├── SeatAlreadyEmptyException.java
                        └── SeatAlreadyTakenException.java
```

## Notes

- `ReservationService` never reads from or writes to the console: it only throws exceptions, and `ConsoleUI` is the only class that catches them and shows a message to the user.
- The `seats` list inside `ReservationService` only ever holds seats that are **currently reserved** — an empty seat has no entry at all, rather than an entry with a blank name. This matches the description of `Seat` as "a seat reservation", not "a seat".
- `Main` and `ConsoleUI` share a single `Scanner` object, so there is only one connection to the keyboard for the whole program.