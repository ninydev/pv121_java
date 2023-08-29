package org.itstep.myClassWork.august29;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable
{
    private String name;
    private int age;
}
