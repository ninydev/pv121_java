package org.itstep.myClassWork;

import org.itstep.buttons.Button;
import org.itstep.buttons.IClickable;
import org.itstep.entities.academy.Group;
import org.itstep.entities.academy.Student;
import org.itstep.exceptions.validations.NameLengthValidationMax;
import org.itstep.exceptions.validations.NameLengthValidationMin;

public class August18Button implements Runnable
{
    @Override
    public void run() {
        System.out.println(" Button work ");

        String someVar = "Test";

        Button btn = new Button(new IClickable() {
            @Override
            public void click() {
                System.out.println("Click: " + someVar);
            }
        });

        btn.click();
        btn.click();
        btn.click();
        btn.click();

    }

}
