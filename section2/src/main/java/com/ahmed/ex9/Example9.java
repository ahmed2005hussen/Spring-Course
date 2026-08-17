package com.ahmed.ex9;


import com.ahmed.ex9.Beans.Car;
import com.ahmed.ex9.Beans.Engine;
import com.ahmed.ex9.Beans.Person;
import com.ahmed.ex9.Beans.Vehicle;
import com.ahmed.ex9.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example9 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class  );

        var v = context.getBean(Car.class);
        var p = context.getBean(Engine.class);
        System.out.println(v);
        System.out.println(p);
    }
}
