package com.u2.submit.aspect;

import com.u2.submit.annotation.U2RepeatSubmit;
import com.u2.submit.enums.IdentityLocation;
import com.u2.submit.properties.RepeatSubmitProperties;
import com.u2.submit.service.impl.RepeatSubmitServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/14:00
 * @Description:
 */
@Aspect
public class SubmitAspect{

    HttpServletRequest httpServletRequest;

    RepeatSubmitProperties repeatSubmitProperties;

    RepeatSubmitServiceImpl repeatSubmitServiceImpl;


    @Around("@annotation(u2RepeatSubmit)")
    public Object repeatSubmit(ProceedingJoinPoint joinPoint , U2RepeatSubmit u2RepeatSubmit) throws Throwable {
        String identification = getIdentity();
        String key = prefix() + ":" + identification;
        repeatSubmitServiceImpl.hasKey(key, u2RepeatSubmit);
        repeatSubmitServiceImpl.submit(key, u2RepeatSubmit);
        return joinPoint.proceed();
    }

    public String getIdentity(){
        String identity = repeatSubmitProperties.getIdentity();
        String identityLocation = repeatSubmitProperties.getIdentityLocation();
        String identification = null;
        if (identityLocation.equals(IdentityLocation.HEADER.getIdentityName())){
            identification = httpServletRequest.getHeader(identity);
        }
        if (identityLocation.equals(IdentityLocation.PARAM.getIdentityName())){
            identification = httpServletRequest.getParameter(identity);
        }
        if (identityLocation.equals(IdentityLocation.SESSION.getIdentityName())){
            identification = httpServletRequest.getSession().getAttribute(identity).toString();
        }
        return identification;
    }

    public String prefix(){
        return httpServletRequest.getRequestURL().toString();
    }

    public SubmitAspect(HttpServletRequest httpServletRequest, RepeatSubmitProperties repeatSubmitProperties, RepeatSubmitServiceImpl repeatSubmitServiceImpl) {
        this.httpServletRequest = httpServletRequest;
        this.repeatSubmitProperties = repeatSubmitProperties;
        this.repeatSubmitServiceImpl = repeatSubmitServiceImpl;
    }
}
