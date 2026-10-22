package com.example.projectfinal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cart  {
    ArrayList<Products>cart = new ArrayList<>();

    public Cart(){
    }


    public ArrayList<Products> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Products> cart) {
        this.cart = cart;
    }

    public void clearCart() {
        cart.clear();
    }
    public double calculateTotal() {
        double total = 0;
        for (Products p : cart) {
            total += p.getPrice();
        }
        return total;
    }
    public void addProduct(Products product) {
        cart.add(product);
    }

    public List<Products> getProducts() {
        return cart ;
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(Products::getPrice).sum();
    }
}


