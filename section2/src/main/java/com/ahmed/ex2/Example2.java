package com.ahmed.ex2;

import com.ahmed.ex2.Beans.Vehicle;
import com.ahmed.ex2.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example2 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // NoSuchBeanDefinitionException, because we change the name
        // Vehicle v = context.getBean("vehicle" , Vehicle.class);

        Vehicle v = context.getBean("v1", Vehicle.class);
        Vehicle v1 = context.getBean("v2", Vehicle.class);
        Vehicle v2 = context.getBean("v3", Vehicle.class);
        Vehicle v3 = context.getBean("v4", Vehicle.class);
        Vehicle v4 = context.getBean("vv", Vehicle.class);

        System.out.println(v.getName());
        System.out.println(v1.getName());
        System.out.println(v2.getName());
        System.out.println(v3.getName());
        System.out.println(v4.getName());

    }
}
