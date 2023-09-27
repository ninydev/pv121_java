package com.itstep.hello_spring.services.bar.interfaces;

import com.itstep.hello_spring.models.bar.Coffee;

public class SemService implements SemParentInterface
{
    @Override
    public Coffee getCoffee() {
        Coffee c = new Coffee();
        c.setName("Coffee");
        return c;
    }
}
