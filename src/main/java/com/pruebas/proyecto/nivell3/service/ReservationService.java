package com.pruebas.proyecto.nivell3.service;

import com.pruebas.proyecto.nivell3.exception.InvalidPersonNameException;
import com.pruebas.proyecto.nivell3.exception.InvalidSeatException;
import com.pruebas.proyecto.nivell3.exception.SeatAlreadyEmptyException;
import com.pruebas.proyecto.nivell3.exception.SeatAlreadyTakenException;
import com.pruebas.proyecto.nivell3.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final int totalRows;
    private final int seatsPerRow;
    private final List<Seat> seats;

    public ReservationService(int totalRows, int seatsPerRow) {
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new ArrayList<>();
    }

    public List<Seat> getAllSeats() { return List.copyOf(seats); }

    public List<Seat> getSeatsByPerson(String name) {
        validatePersonName(name);

        List<Seat> personSeats = seats.stream()
                .filter(s -> s.getPersonName().equalsIgnoreCase(name))
                .toList();

        return List.copyOf(personSeats);
    }

    public void reserveSeat(int row, int seat, String name) {
        validateSeatPosition(row, seat);
        validatePersonName(name);

        boolean alreadyTaken = seats.stream()
                .anyMatch(s -> s.getRow() == row && s.getSeat() == seat);

        if (alreadyTaken) {
            throw new SeatAlreadyTakenException(
                    "Seat " + seat + " in row " + row + " is already reserved.");
        }

        seats.add(new Seat(name, seat, row));
    }

    public void cancelSeat(int row, int seat) {
        validateSeatPosition(row, seat);

        Seat reservedSeat = seats.stream()
                .filter(s -> s.getRow() == row && s.getSeat() == seat)
                .findFirst()
                .orElseThrow(() -> new SeatAlreadyEmptyException(
                        "Seat " + seat + " in row " + row + " is not reserved."));

        seats.remove(reservedSeat);
    }

    public void cancelAllByPerson(String name) {
        validatePersonName(name);
        seats.removeIf(s -> s.getPersonName().equalsIgnoreCase(name));
    }

    private void validateSeatPosition(int row, int seat) {
        if (row < 1 || row > totalRows || seat < 1 || seat > seatsPerRow) {
            throw new InvalidSeatException(
                    "Row " + row + ", seat " + seat + " does not exist. "
                            + "Valid rows: 1-" + totalRows + ", valid seats: 1-" + seatsPerRow + ".");
        }
    }

    public void validatePersonName(String name) {
        if (name == null || name.isBlank()) throw new InvalidPersonNameException("The name cannot be empty.");
        if (name.matches(".*\\d.*"))  throw new InvalidPersonNameException("The name cannot contain numbers.");
    }
}
