package com.mjc.school.controller.command.implementation.tagCommands;

import com.mjc.school.controller.TagController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadAllTags implements Command {
    @Autowired
    private TagController tagController;
    private final String name = "Read All Tags";

     @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        for (var tagDTO : tagController.readAll()) {
            System.out.println(tagDTO.toString());
        }
    }
}
