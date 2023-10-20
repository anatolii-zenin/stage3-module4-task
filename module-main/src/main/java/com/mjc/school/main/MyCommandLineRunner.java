package com.mjc.school.main;

import com.mjc.school.controller.command.implementation.utilCommands.LoadDemoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    LoadDemoData loadDemoData;
    @Override
    public void run(String... args) throws Exception {
//        loadDemoData.execute();
    }
}
