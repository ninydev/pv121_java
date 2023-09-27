package com.itstep.hello_spring.services.bar;

import com.itstep.hello_spring.models.bar.Watter;
import com.itstep.hello_spring.services.bar.interfaces.IdenChildInterface;
import com.itstep.hello_spring.services.bar.SemService;
import org.springframework.stereotype.Service;

@Service
public class IdenService
        extends SemService
        implements IdenChildInterface
{
    @Override
    public Watter getWatter() {
        Watter w = new Watter();
        w.setName("Watter");
        return w;
    }
}
