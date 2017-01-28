package com.shoponline.aspect;


import com.shoponline.controller.UserController;
import com.shoponline.model.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by Damian on 2017-01-24.
 */
@Component
@Aspect
public class UserAspect extends BaseAspect{

    private final Logger logger = getLogger(UserController.class);

    @Before("execution(* com.shoponline.controller.UserController.createUser(..))")
    public void createUserLog(JoinPoint joinPoint) {

        StringBuilder sb = new StringBuilder();

        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg: signatureArgs) {
            sb.append(signatureArg.toString());
        }

        logger.info(LOG + "Someone is trying create user: " + sb.toString());
    }
}
