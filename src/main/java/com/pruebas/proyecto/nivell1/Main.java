package com.pruebas.proyecto.nivell1;

import com.pruebas.proyecto.nivell1.exceptions.EmptySaleException;
import com.pruebas.proyecto.nivell1.models.Product;
import com.pruebas.proyecto.nivell1.models.Sale;

import java.util.List;

public class Main {
    public static void main() {
        Sale salesWithProducts = new Sale();

        salesWithProducts.getProducts().addAll(List.of(
                new Product("TV", 100),
                new Product("PC", 2000),
                new Product("Tablet", 150)
        ));

        try {
            salesWithProducts.calculateTotal();
            System.out.println("Tota price is: " + salesWithProducts.getTotalPrice());
        } catch (EmptySaleException e) {
            System.out.println("Error 1: " + e.getMessage());
        }

        Sale emptySales = new Sale();
        try {
            emptySales.calculateTotal();
        } catch (EmptySaleException e) {
            System.out.println("Error 2: " + e.getMessage());
        }

        try {
            System.out.println("Product: " + salesWithProducts.getProducts().get(44));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error IndexOutOfBoundsException: " + e.getMessage());
        }
    }
}
