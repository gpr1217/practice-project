package com.designpatterns.factorypattern.pizzafranchise;

/**
 * Created by Pratyu on 1/8/2018.
 *
 * Concrete Product Class
 *
 */

public class NYCheesePizza extends Pizza {

    public NYCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Jalapeno");
        toppings.add("Pineapple pieces");
    }
}
