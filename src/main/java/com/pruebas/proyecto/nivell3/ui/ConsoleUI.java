package com.pruebas.proyecto.nivell3.ui;

import com.pruebas.proyecto.nivell3.exception.InvalidPersonNameException;
import com.pruebas.proyecto.nivell3.exception.InvalidSeatException;
import com.pruebas.proyecto.nivell3.exception.SeatAlreadyEmptyException;
import com.pruebas.proyecto.nivell3.exception.SeatAlreadyTakenException;
import com.pruebas.proyecto.nivell3.model.Seat;
import com.pruebas.proyecto.nivell3.service.ReservationService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private ReservationService reservationService;


    public ConsoleUI(ReservationService reservationService, Scanner scanner) {
        this.scanner = scanner;
        this.reservationService = reservationService;
    }

    public void start() {
        int option;

        do {
            printMenu();
            option = readInt("Option: ");

            try {
                switch (option) {
                    case 1 -> displayAllReservedSeats();
                    case 2 -> displaySeatsByPerson();
                    case 3 -> reserveSeat();
                    case 4 -> cancelSeat();
                    case 5 -> cancelAllByPerson();
                    case 0 -> System.out.println("Bye!");
                    default -> System.out.println("Please choose a valid option from the menu.");
                }
            } catch (SeatAlreadyTakenException | SeatAlreadyEmptyException
                     | InvalidSeatException | InvalidPersonNameException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("\n\nMenu");
        System.out.println("-------------");
        System.out.println("1. Display all reserved seats.");
        System.out.println("2. Display the seats reserved by a person.");
        System.out.println("3. Reserve a seat.");
        System.out.println("4. Cancel a seat reservation.");
        System.out.println("5. Cancel all reservations made by a person.");
        System.out.println("0. Exit.");
    }

    private void displayAllReservedSeats() {
        List<Seat> reservedSeats = reservationService.getAllSeats();

        if (reservedSeats.isEmpty()) {
            System.out.println("There are no reserved seats yet.");

            return;
        }

        for (Seat seat : reservedSeats) System.out.println(seat);
    }

    private void displaySeatsByPerson() {
        String clientName = readLine("\n Write client's name: ");
        List<Seat> reservedSeatsByPerson = reservationService.getSeatsByPerson(clientName);

        if (reservedSeatsByPerson.isEmpty()) {
            System.out.println(clientName + " has no reserved seats.");

            return;
        }

        for (Seat seat : reservedSeatsByPerson) System.out.println(seat);
    }

    private void reserveSeat() {
        //String clientName = readLine("\n Write client's name: ");
        String clientName = readValidName("\n Write client's name: ");
        int row = readInt("\n Write row: ");
        int seatNumber = readInt("\n Write seat number: ");

        reservationService.reserveSeat(row, seatNumber, clientName);
        System.out.println("Seat reserved successfully.");
    }

    private void cancelSeat() {
        int row = readInt("\n Write row: ");
        int seatNumber = readInt("\n Write seat number: ");

        reservationService.cancelSeat(row, seatNumber);
        System.out.println("Reservation cancelled successfully.");
    }

    private void cancelAllByPerson() {
        String clientName = readLine("\n Write client's name: ");

        reservationService.cancelAllByPerson(clientName);
        System.out.println("All reservations for " + clientName + " have been cancelled.");
    }

    private String readLine(String prompt) {
        System.out.print(prompt);

        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();

                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                scanner.nextLine();
            }
        }
    }

    private String readValidName(String prompt) {
        while (true) {
            String name = readLine(prompt);
            try {
                reservationService.validatePersonName(name);

                return name;
            } catch (InvalidPersonNameException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
