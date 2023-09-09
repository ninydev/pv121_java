package org.itstep.myClassWork.september09.models.interfaces;

import org.itstep.myClassWork.september09.models.Customer;

public interface DeserializableFromBytes <ObjectType> {
    public ObjectType fromBytes(byte[] bytes);
}
