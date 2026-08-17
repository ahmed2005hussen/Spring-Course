package com.ahmed.ex8;


import com.ahmed.ex8.Beans.Person;
import com.ahmed.ex8.Beans.Vehicle;
import com.ahmed.ex8.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example8 {
    static void main() {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class  );

        var v = context.getBean(Vehicle.class);
        var p = context.getBean("person" , Person.class);
        var p1 = context.getBean("person1" , Person.class);
        System.out.println(v);
        System.out.println(p);
        System.out.println(p1);

    }
}
