/*
 * Created by Vologda Developer
 * Date: 31.03.2020
 * Time: 15:29
 */


package ru.belyaev.shop.aop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppLogger.class);

    @Before("execution(* loadUserByUsername(String))")
    public void beforeLogIn(JoinPoint jointPoint){
        Object[] args = jointPoint.getArgs();
        for (Object arg: args) {
            LOGGER.info("-->> User {} Log in", arg);
        }
    }

    @Before("execution(public void sessionCreated(*))")
    public void beforeSession(){
           LOGGER.info("-->> Session created");
    }
}
