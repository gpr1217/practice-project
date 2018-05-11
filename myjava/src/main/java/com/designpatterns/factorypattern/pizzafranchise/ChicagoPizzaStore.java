package com.designpatterns.factorypattern.pizzafranchise;

/**
 * Created by Pratyu on 1/8/2018.
 */

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")){
            return new ChicagoCheesePizza();
        }else if(type.equals("veggie")){
            return new ChicagoVeggiePizza();
        }else if(type.equals("pepperoni")){
            return new ChicagoPepperoniPizza();
        }else {
            return null;
        }
    }
}
