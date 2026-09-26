package com.pruebas.proyecto.nivell3.model;

import java.util.Objects;

public class Seat {
    private final int row;
    private final int seat;
    private String personName;


    public Seat(String personName, int seat, int row) {
        this.personName = personName;
        this.seat = seat;
        this.row = row;
    }

    public int getRow() { return row; }
    public int getSeat() { return seat; }
    public String getPersonName() { return personName; }
    public void setPersonName(String personName) { this.personName = personName; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat1 = (Seat) o;
        return row == seat1.row && seat == seat1.seat;
    }

    @Override
    public int hashCode() { return Objects.hash(row, seat); }

    @Override
    public String toString() {
        return "\nSEAT\n" +
                "---------\n" +
                "Row: " + row + "\n" +
                "Seat: " + seat + "\n" +
                "Client: " + personName + "\n";
    }
}
