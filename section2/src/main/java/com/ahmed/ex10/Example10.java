package com.ahmed.ex10;

import com.ahmed.ex10.Beans.Bike;
import com.ahmed.ex10.Beans.Engine;
import com.ahmed.ex10.Beans.Vehicle;
import com.ahmed.ex10.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example10 {
    static void main() {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        System.out.println("Available beans");

        if (context.containsBean("engine")) {
            Engine engine = context.getBean(Engine.class);
            System.out.println(engine);
        }

        if (context.containsBean("vehicle")) {
            Vehicle vehicle = context.getBean(Vehicle.class);
            System.out.println(vehicle);
        }

        if (context.containsBean("bike")) {
            Bike bike = context.getBean(Bike.class);
            System.out.println(bike);
        }

    }
}
