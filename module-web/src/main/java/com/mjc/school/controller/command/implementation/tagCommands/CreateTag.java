package com.mjc.school.controller.command.implementation.tagCommands;

import com.mjc.school.controller.TagController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.TagDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateTag implements Command {
    @Autowired
    private TagController tagController;
    private final String name = "Create Tag";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new tag name:");
        var tagName = in.nextLine();
        var req = new TagDTOReq();
        req.setName(tagName);
        var newTag = tagController.create(req);
        System.out.println(newTag.toString());
    }
}
