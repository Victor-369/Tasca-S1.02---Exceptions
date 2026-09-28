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
    private final ReservationService reservationService;
    private final Scanner scanner;
    private static final String ASK_CLIENTS_NAME = "\n Write client's name: ";


    public ConsoleUI(ReservationService reservationService, Scanner scanner) {
        this.reservationService = reservationService;
        this.scanner = scanner;
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
                    case 0 -> {
                        closeScanner();
                        System.out.println("Bye!");
                    }
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
        String clientName = readValidName(ASK_CLIENTS_NAME);
        List<Seat> reservedSeatsByPerson = reservationService.getSeatsByPerson(clientName);

        if (reservedSeatsByPerson.isEmpty()) {
            System.out.println(clientName + " has no reserved seats.");

            return;
        }

        for (Seat seat : reservedSeatsByPerson) System.out.println(seat);
    }

    private void reserveSeat() {
        String clientName = readValidName(ASK_CLIENTS_NAME);
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
        String clientName = readValidName(ASK_CLIENTS_NAME);
        boolean isAllReservationsCancelled = reservationService.cancelAllByPerson(clientName);

        if (isAllReservationsCancelled) {
            System.out.println("All reservations for " + clientName + " have been cancelled.");
        } else {
            System.out.println("Client " + clientName + " has no reservations to cancel.");
        }
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
            System.out.print(prompt);
            String name = scanner.nextLine();
            name = name.trim();

            try {
                reservationService.validatePersonName(name);

                return name;
            } catch (InvalidPersonNameException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void closeScanner() {
        scanner.close();
    }
}
