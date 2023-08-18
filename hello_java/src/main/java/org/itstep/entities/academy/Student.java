package org.itstep.entities.academy;

import lombok.Data;
import org.itstep.exceptions.validations.NameLengthValidationMax;
import org.itstep.exceptions.validations.NameLengthValidationMin;

@Data
public class Student implements Cloneable
{
    private String name;
    private Group group;

    public Student(String name, Group g) throws NameLengthValidationMin, NameLengthValidationMax {
        this.setName(name);
        this.setGroup(g);
    }


    public void setName(String name) throws NameLengthValidationMin, NameLengthValidationMax {
        System.out.println("Set Name " + name);
        if (name.length() < 3) {
            System.out.println("Имя не может быть меньще трех символов");
            throw new NameLengthValidationMin("Имя не может быть меньще трех символов");
        }
        if (name.length() > 10) {
            throw new NameLengthValidationMax("Имя не может быть длинее 10");
        }
        this.name = name;
    }

    @Override
    public Student clone() {
        try {
            Student clone = (Student) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
