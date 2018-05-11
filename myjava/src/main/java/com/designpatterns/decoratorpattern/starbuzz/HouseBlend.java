package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 */

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .99;
    }


}
