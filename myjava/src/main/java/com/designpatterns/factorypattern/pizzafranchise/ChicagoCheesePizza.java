package com.designpatterns.factorypattern.pizzafranchise;

/**
 * Created by Pratyu on 1/8/2018.
 */

public class ChicagoCheesePizza extends Pizza {

    public ChicagoCheesePizza() {
        name = "Chicago style cheese pizza";
        dough = "Extra thick crust dough";
        sauce = "Tomato sauce";

        toppings.add("Shredded mozzarella cheese");
    }
}
