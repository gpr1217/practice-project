package com.designpatterns.decoratorpattern.starbuzz;

/**
 * Created by Pratyu on 1/3/2018.
 *
 * Decorator - implement the same interface or abstract class as the component they are going to decorate.
 *
 *  It’s vital that the decorators have the same type as the objects they are going to decorate.
 *  So here we’re using inheritance to achieve the type matching, but we aren’t using inheritance to get behavior.
 *
 *   we need to be interchangeable with a Beverage, so we extend the Beverage class.
 */

public abstract class CondimentDecorator extends Beverage{
    public Beverage beverage;
    public abstract String getDescription();

    @Override
    public Size getSize() {
        return beverage.getSize();
    }
}
