package com.ahmed.ex10.config;

import com.ahmed.ex10.Beans.Bike;
import com.ahmed.ex10.Beans.Engine;
import com.ahmed.ex10.Beans.Vehicle;
import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

import java.util.Random;

public class MyBeanRegister implements BeanRegistrar {
    @Override
    public void register(BeanRegistry registry, Environment env) {
        int num = new Random().nextInt(100);
        System.out.println("Number is " + num);

        if (num % 2 == 0) {

            System.out.println("Even , register vehicle and engine");

            registry.registerBean("engine", Engine.class,

                    spec -> spec.supplier(

                            supplierContext -> {
                                Engine engine = new Engine();
                                engine.setName("Engiiiiiiine");
                                return engine;
                            }

                    )

            );


            registry.registerBean("vehicle", Vehicle.class,
                    spec -> spec.supplier(
                            context -> {
                                Vehicle vehicle = new Vehicle(context.bean(Engine.class));
                                vehicle.setName("Hello");
                                return vehicle;
                            }
                    )

            );

        } else {
            System.out.println("Odd , register bike");

            registry.registerBean("bike", Bike.class,

                    spec -> spec.supplier(
                            context -> {
                                Bike bike = new Bike();
                                bike.setName("bikeee");
                                return bike;
                            }
                    )
            );

        }
    }
}
