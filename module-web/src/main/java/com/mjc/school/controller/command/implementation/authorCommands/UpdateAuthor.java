package com.mjc.school.controller.command.implementation.authorCommands;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.AuthorDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateAuthor implements Command {
    @Autowired
    private AuthorController authorController;
    private final String name = "Update Author";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter author ID:");
        Long authorID = Long.parseLong(in.nextLine());
        System.out.println("Enter Name:");
        var name = in.nextLine();
        var req = new AuthorDTOReq();
        req.setId(authorID);
        req.setName(name);
        var updatedAuthor = authorController.update(req);
        System.out.println(updatedAuthor.toString());
    }
}
