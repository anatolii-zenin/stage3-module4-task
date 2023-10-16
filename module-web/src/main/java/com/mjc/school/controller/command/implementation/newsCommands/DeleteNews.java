package com.mjc.school.controller.command.implementation.newsCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteNews implements Command {
    @Autowired
    private NewsController newsController;
    private String name = "Delete News";

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter news ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = newsController.deleteById(id);
        if (result)
            System.out.println("Successfully deleted");
    }
}
