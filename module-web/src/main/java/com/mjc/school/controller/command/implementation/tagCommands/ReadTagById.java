package com.mjc.school.controller.command.implementation.tagCommands;

import com.mjc.school.controller.TagController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReadTagById implements Command {
    @Autowired
    private TagController tagController;
    private final String name = "Read Tag by ID";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter tag ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = tagController.readById(id);
        if (result != null)
            System.out.println(result);
        else
            System.out.println("Tag with ID " + id + " does not exist.");
    }
}
