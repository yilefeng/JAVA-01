package com.yilefeng.dynamic_src.aop;

import com.yilefeng.dynamic_src.annotation.DataSource;
import com.yilefeng.dynamic_src.datasource.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by yilefeng on 2021/3/7.
 */
@Component
@Aspect
@Order(1)
public class DataSourceAspect {

    /**
     * @within匹配类上的注解
     * @annotation匹配方法上的注解
     */
    @Pointcut(value = "@within(com.yilefeng.dynamic_src.annotation.DataSource)||@annotation(com.yilefeng.dynamic_src.annotation.DataSource)")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        try {
            //获取类上@DataSource注解
            DataSource annotationOfClass = point.getTarget().getClass().getAnnotation(DataSource.class);

            //获取方法上@DataSource注解
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);
            DataSource methodAnnotation = method.getAnnotation(DataSource.class);

            methodAnnotation = methodAnnotation == null ? annotationOfClass : methodAnnotation;
            String dsName = "";
            if (methodAnnotation != null) {
                System.out.println("使用注解上标记的数据源:" + dsName);
                dsName = methodAnnotation.value();
            } else {
                System.out.println("没有注解指定数据源，随机使用一个数据源");
                dsName = DataSourceContext.randomDataSource();
            }
            DataSourceContext.setDataSource(dsName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @After(value = "pointcut()")
    public void after(JoinPoint point) {
        DataSourceContext.clearDataSource();
    }
}
