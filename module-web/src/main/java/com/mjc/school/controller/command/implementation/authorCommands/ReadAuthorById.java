package com.mjc.school.controller.command.implementation.authorCommands;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReadAuthorById implements Command {
    @Autowired
    private AuthorController authorController;
    private final String name = "Read Author by ID";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = authorController.readById(id);
        if (result != null)
            System.out.println(result);
        else
            System.out.println("Author with ID " + id + " does not exist.");
    }
}
