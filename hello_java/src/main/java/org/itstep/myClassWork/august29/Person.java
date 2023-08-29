package org.itstep.myClassWork.august29;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Person implements Serializable
{
    private String name;
    private int age;
    private boolean isMary;


    @JsonCreator
    public Person(
            @JsonProperty("name")
            String name,
            @JsonProperty("age")
            int age
    ) {
        this.name = name;
        this.age = age;
    }
}
