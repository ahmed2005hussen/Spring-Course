package com.ahmed;

import com.ahmed.Beans.Vehicle;
import com.ahmed.config.ProjectConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {

//        BeanFactory context = new AnnotationConfigApplicationContext(ProjectConfig.class);
//        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // by class data type
        System.out.println(context.getBean(Integer.class));
        System.out.println(context.getBean(Vehicle.class));
        System.out.println(context.getBean(String.class));

        // by function name
        System.out.println(context.getBean("hello"));
        System.out.println(context.getBean("luckyNumber"));
        System.out.println(context.getBean("vehicle"));


        // String y = context.getBean("hello"); does not work
        Object x = context.getBean("hello");
        String z = (String) context.getBean("hello");

        // we made casting, because the context does not know the data type when use the name

    }
}
