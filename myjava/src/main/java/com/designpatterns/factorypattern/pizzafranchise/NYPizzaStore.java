package com.designpatterns.factorypattern.pizzafranchise;

/**
 * Created by Pratyu on 1/8/2018.
 *
 * Concrete Creators
 *
 * createPizza - factory method, produces products (classes that produce products are called concrete creators)
 */

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")){
            return new NYCheesePizza();
        }else if(type.equals("veggie")){
            return new NYVeggiePizza();
        }else if(type.equals("pepperoni")){
            return new NYVeggiePizza();
        }else {
            return null;
        }
    }
}
