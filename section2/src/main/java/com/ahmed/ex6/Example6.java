package com.ahmed.ex6;


import com.ahmed.ex6.Beans.Vehicle;
import com.ahmed.ex6.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example6 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class  );

        var c = context.getBean(Vehicle.class);

        System.out.println(c.getName()); // null because we don't set value
        c.hello();

        context.close();
    }
}
