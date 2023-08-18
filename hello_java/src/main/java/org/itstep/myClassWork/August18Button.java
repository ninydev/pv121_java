package org.itstep.myClassWork;

import org.itstep.buttons.Button;
import org.itstep.buttons.IClickable;
import org.itstep.buttons.IDoubleClickable;
import org.itstep.entities.academy.Group;
import org.itstep.entities.academy.Student;
import org.itstep.exceptions.validations.NameLengthValidationMax;
import org.itstep.exceptions.validations.NameLengthValidationMin;

public class August18Button implements Runnable
{
    @Override
    public void run() {
        textEx();
    }

    private void textEx() {

        int r;
        try {
            r= (int) 10 / 0;
        } catch (Exception e){
            System.out.println(e.getMessage());
            r = 10;
        }

        System.out.println(r);
    }


    public void btnWork() {
        System.out.println(" Button work ");

        String someVar = "Test";

        Button btn = new Button(new IClickable() {
            @Override
            public void click() throws Exception {
                System.out.println("Click: " + someVar);
                throw new Exception("Test Exception");
            }
        });

        try {
            btn.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        btn.doubleClick();

        Button btn2 = new Button(new IDoubleClickable() {
            @Override
            public void doubleClick() {
                System.out.println("Double Click: " + someVar);
            }

            @Override
            public void click() {
                System.out.println("Click: " + someVar);
            }
        });

        try {
            btn2.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        btn2.doubleClick();


    }


}
