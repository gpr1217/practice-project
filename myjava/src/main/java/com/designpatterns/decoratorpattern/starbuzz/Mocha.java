package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 *
 * Concrete Decorator - has an instance variable for the thing it decorates(the component the Decorator wraps)
 * Decorators can add new methods;however, new behavior is typically added by doing computation before or after an existing method
 * in the component.
 *
 * Decorators can extend the state of the component
 *
 */

public class Mocha extends CondimentDecorator {

    // An instance variable to hold the beverage we are wrapping
    Beverage beverage;

    // A way to set the instance variable to the object we are wrapping
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        double cost = beverage.cost();
        if (getSize() == Size.TALL){
            cost += .15;
        } else if (getSize() == Size.GRANDE) {
            cost += .25;
        }  else if (getSize() == Size.VENTI) {
            cost += .30;
        }
        return cost;
    }

    public Size getSize(){
        return beverage.getSize();
    }
}
