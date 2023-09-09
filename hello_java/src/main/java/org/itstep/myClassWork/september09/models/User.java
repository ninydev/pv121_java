package org.itstep.myClassWork.september09.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{
    private String name;

    private UUID user_id; // = UUID.randomUUID();
    private UUID customer_id;
}
