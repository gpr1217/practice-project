package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 */

public class StarbuzzCoffee {

    public static void main(String args[]){

        Beverage beverage = new Espresso();
        beverage.setSize(Beverage.Size.TALL);
        System.out.println(beverage.getSize() + " - " +beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage1 = new DarkRoast();
        beverage1.setSize(Beverage.Size.VENTI);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        System.out.println(beverage1.getSize() + " - " + beverage1.getDescription() + " $" + beverage1.cost());

        Beverage beverage2 = new HouseBlend(); //.99
        beverage2.setSize(Beverage.Size.GRANDE);
        beverage2 = new Soy(beverage2); // .15
        beverage2 = new Mocha(beverage2); // .25
        beverage2 = new Whip(beverage2); // .12
        System.out.println(beverage2.getSize() + " - " + beverage2.getDescription() + " $" + Math.round((beverage2.cost()) * 100.0)/100.0 );

        Beverage beverage3 = new HouseBlend(); // .99
        beverage3.setSize(Beverage.Size.TALL);
        beverage3 = new Soy(beverage3); // .15
        beverage3 = new Mocha(beverage3); // .20
        beverage3 = new Mocha(beverage3); // .20
        beverage3 = new Whip(beverage3); // .10
        System.out.println(beverage3.getSize() + " - " + "Double mocha soy lotte with whip $" + beverage3.cost() );

    }
}
