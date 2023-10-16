package com.mjc.school.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.mjc.school.main", "com.mjc.school.controller"})
public class MainSpringConfig {
}
