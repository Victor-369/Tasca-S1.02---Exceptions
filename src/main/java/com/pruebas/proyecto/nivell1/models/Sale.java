package com.pruebas.proyecto.nivell1.models;

import com.pruebas.proyecto.nivell1.exceptions.EmptySaleException;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<Product> products;
    private float totalPrice;

    public Sale() {
        this.products = new ArrayList<>();
        this.totalPrice = 0f;
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotal() throws EmptySaleException {
        if (this.products.isEmpty()) throw new EmptySaleException("To make a sale, you must first add products.");

        for (Product product : products) this.totalPrice += product.getPrice();
    }
}
