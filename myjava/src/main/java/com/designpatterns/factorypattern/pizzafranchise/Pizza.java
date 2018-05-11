package com.designpatterns.factorypattern.pizzafranchise;

import java.util.ArrayList;

/**
 * Created by Pratyu on 1/5/2018.
 *
 * Product class - Factories produce products.
 */

public abstract class Pizza {

    String name;
    String sauce;
    String dough;
    ArrayList<String> toppings = new ArrayList<>();

    public String getName(){
        return name;
    }

    public void prepare(){
        System.out.println("Prepare " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
            System.out.println("   " + topping);
        }
    }

    public void bake(){
        System.out.println("Baking...");
    }

    public void cut(){
        System.out.println("Cutting...");
    }

    public void box(){
        System.out.println("Boxing...");
    }
}
