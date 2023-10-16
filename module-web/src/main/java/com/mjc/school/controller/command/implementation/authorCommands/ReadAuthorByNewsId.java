package com.mjc.school.controller.command.implementation.authorCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReadAuthorByNewsId implements Command {
    @Autowired
    private NewsController newsController;
    private final String name = "Read Author by news ID";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter news ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = newsController.readAuthorByNewsId(id);
        System.out.println(result);
    }
}
