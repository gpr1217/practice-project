package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 *
 * Concrete Component - Object we are going to dynamically add new behavior to it and extends Component(Beverage)
 */

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark roast coffee";
    }

    @Override
    public double cost() {
        return .99;
    }


}
