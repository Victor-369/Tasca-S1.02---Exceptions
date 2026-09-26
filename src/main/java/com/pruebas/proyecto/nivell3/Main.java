package com.pruebas.proyecto.nivell3;

import com.pruebas.proyecto.nivell3.service.ReservationService;
import com.pruebas.proyecto.nivell3.ui.ConsoleUI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        int rows = readPositiveInt(scanner, "How many rows are there in the theatre?: ");
        int seatsPerRow = readPositiveInt(scanner, "How many seats are there in each row?: ");

        ReservationService reservationService = new ReservationService(rows, seatsPerRow);
        new ConsoleUI(reservationService, scanner).start();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();

                if (value > 0) return value;
                System.out.println("Please enter a number greater than zero.");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                scanner.nextLine();
            }
        }
    }
}
