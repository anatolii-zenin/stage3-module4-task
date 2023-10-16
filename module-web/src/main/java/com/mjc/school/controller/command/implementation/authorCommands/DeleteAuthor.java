package com.mjc.school.controller.command.implementation.authorCommands;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteAuthor implements Command {
    @Autowired
    private AuthorController authorController;
    private final String name = "Delete Author";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter author ID:");
        Long id = Long.parseLong(in.nextLine());
        var result = authorController.deleteById(id);
        if (result)
            System.out.println("Successfully deleted");
    }
}
