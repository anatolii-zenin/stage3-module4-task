package com.mjc.school.controller.command.implementation.tagCommands;

import com.mjc.school.controller.TagController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteTag implements Command {
    @Autowired
    private TagController tagController;
    private String name = "Delete Tag";

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter tag ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = tagController.deleteById(id);
        if (result)
            System.out.println("Successfully deleted");
    }
}
