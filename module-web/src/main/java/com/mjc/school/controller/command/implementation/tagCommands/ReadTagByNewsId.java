package com.mjc.school.controller.command.implementation.tagCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReadTagByNewsId implements Command {
    @Autowired
    private NewsController newsController;
    private final String name = "Read Tag by news ID";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter news ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = newsController.readTagsByNewsId(id);
        if (result != null)
            if (!result.isEmpty())
                for (var tag : result)
                    System.out.println(tag.toString());
            else
                System.out.println("No tags found for news with ID " + id);
        else
            System.out.println("News with ID " + id + " does not exist.");
    }
}
