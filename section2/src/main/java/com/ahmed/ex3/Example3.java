package com.ahmed.ex3;

import com.ahmed.ex3.Beans.Vehicle;
import com.ahmed.ex3.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example3 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // will use the primary bean
        var v = context.getBean(Vehicle.class);
        var v1 = context.getBean(Vehicle.class);

        System.out.println(v.getName());
        System.out.println(v1.getName());

    }
}
