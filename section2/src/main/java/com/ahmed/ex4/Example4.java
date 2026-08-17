package com.ahmed.ex4;


import com.ahmed.ex4.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example4 {
    static void main() {

        var context = new AnnotationConfigApplicationContext
                (ProjectConfig.class /*, AnotherConfig.class*/ );
        // or using import -> recommended

        System.out.println(context.getBean("helloWorld"));



    }
}
