package com.mjc.school.main;

import com.mjc.school.controller.command.invoker.Invoker;
import com.mjc.school.controller.command.invoker.implementation.InvokerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(MainSpringConfig.class);
        Invoker invoker = context.getBean(InvokerImpl.class);
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            try {
                invoker.commandList();
                int operationChoice = Integer.parseInt(in.nextLine());
                if (operationChoice == -1)
                    exit = true;
                invoker.runCommand(operationChoice);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
