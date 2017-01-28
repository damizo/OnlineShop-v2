package com.shoponline.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by Damian on 2017-01-24.
 */
@Component
public abstract class BaseAspect {

    protected static final String LOG = "********LOG******** ";

    Logger getLogger(Class clazz){
        return Logger.getLogger(clazz.getName());
    }
}
