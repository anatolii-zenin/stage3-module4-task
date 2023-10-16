package com.mjc.school.controller.command.implementation.newsCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadAllNews implements Command {
    @Autowired
    private NewsController newsController;
    private final String name = "Read All News";

     @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        for (var newsDTO : newsController.readAll()) {
            System.out.println(newsDTO.toString());
        }
    }
}
