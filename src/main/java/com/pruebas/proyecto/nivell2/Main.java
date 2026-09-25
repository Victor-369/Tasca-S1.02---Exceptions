package com.pruebas.proyecto.nivell2;

import com.pruebas.proyecto.nivell2.utils.ConsoleReader;

public class Main {
    public static void main(String[] args) {

        byte age = ConsoleReader.readByte("Enter your age: ");
        System.out.println("Age recorded: " + age);

        int quantity = ConsoleReader.readInt("Enter a quantity: ");
        System.out.println("Quantity recorded: " + quantity);

        float price = ConsoleReader.readFloat("Enter a price: ");
        System.out.println("Price recorded: " + price);

        double balance = ConsoleReader.readDouble("Enter your account balance: ");
        System.out.println("Balance recorded: " + balance);

        char initial = ConsoleReader.readChar("Enter the initial of your name: ");
        System.out.println("Initial recorded: " + initial);

        String code = ConsoleReader.readString("Enter a short code (max 3 characters): ");
        System.out.println("Code recorded: " + code);

        boolean likesJava = ConsoleReader.readYesNo("Do you like Java? (y/n): ");
        System.out.println("Answer recorded: " + likesJava);

        System.out.println("\nAll data was read successfully. The program has finished.");
    }
}
