package com.u2.zklock.generator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/05/21:56
 * @Description: 锁唯一标识生成
 */
public class LockKeyGenerator {

    public static String lockKey(ProceedingJoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < parameterTypes.length; i++) {
            params.append(parameterTypes[i].getName());
            if (i != parameterTypes.length -1){
                params.append(",");
            }
        }
        return "/"+clazz.getName()+"."+method.getName()+"["+params+"]";
    }

    public static String requestKey(){
        byte[] mac;
        StringBuffer sb = new StringBuffer("");
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            mac = NetworkInterface.getByInetAddress(localHost).getHardwareAddress();
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }
}
