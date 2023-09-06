package org.itstep.myClassWork.september06.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable
{

    private UUID customer_id; // = UUID.randomUUID();
    private UUID user_id;

    private String name;
}
