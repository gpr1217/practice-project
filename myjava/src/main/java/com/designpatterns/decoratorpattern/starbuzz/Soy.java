package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 */

public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        double cost = beverage.cost();
        if (getSize() == Size.TALL){
            cost += .10;
        } else if (getSize() == Size.GRANDE) {
            cost += .15;
        }  else if (getSize() == Size.VENTI) {
            cost += .20;
        }
        return cost;
    }

    public Size getSize(){
        return beverage.getSize();
    }

}
