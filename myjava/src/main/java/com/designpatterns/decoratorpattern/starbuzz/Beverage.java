package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 *
 * Component - Each component can be used on its own or wrapped by a decorator
 *
 * how come we didn’t use an interface instead of an abstract class for the Beverage class?
 * Traditionally the Decorator Pattern does specify an abstract component, but in Java, obviously, we could use an interface.
 * But we always try to avoid altering existing code, so don’t “ﬁx” it if the abstract class will work just ﬁne.
 */

public abstract class Beverage {

    public enum Size {TALL, GRANDE, VENTI};

    Size size = Size.TALL;

    String description = "Unknown Beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
