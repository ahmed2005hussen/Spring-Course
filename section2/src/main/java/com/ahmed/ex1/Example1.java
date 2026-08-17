package com.ahmed.ex1;

import com.ahmed.ex1.Beans.Vehicle;
import com.ahmed.ex1.config.ProjectConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    static void main() {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        // NoUniqueBeanDefinitionException, because we have 3 functions of type Vehicle
        // System.out.println(context.getBean(Vehicle.class));

        // find by name and data type
        Vehicle v = context.getBean("vehicle" , Vehicle.class);

        System.out.println(v);

    }
}
