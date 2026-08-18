package com.ahmed.ex11;

import com.ahmed.ex11.Beans.MyService;
import com.ahmed.ex11.Beans.UserSession;
import com.ahmed.ex11.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example11 {
    static void main() {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        MyService m1 = context.getBean(MyService.class);
        MyService m2 = context.getBean(MyService.class);

        System.out.println(m1 == m2); // true

        UserSession s1 = context.getBean(UserSession.class);
        UserSession s2 = context.getBean(UserSession.class);

        System.out.println(s1 == s2);
    }
}
