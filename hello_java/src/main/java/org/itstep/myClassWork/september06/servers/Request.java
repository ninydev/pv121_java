package org.itstep.myClassWork.september06.servers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable
{
    private RequestCommands command;
    private Object body;
}
