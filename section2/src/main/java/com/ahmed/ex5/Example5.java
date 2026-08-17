package com.ahmed.ex5;


import com.ahmed.ex5.Beans.Vehicle;
import com.ahmed.ex5.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example5 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class  );

        var c = context.getBean(Vehicle.class);

        System.out.println(c.getName()); // null because we don't set value
        c.hello();

    }
}
