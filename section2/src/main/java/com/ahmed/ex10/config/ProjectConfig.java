package com.ahmed.ex10.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyBeanRegister.class})
public class ProjectConfig {

}
