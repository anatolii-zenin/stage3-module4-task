package com.mjc.school.controller.command.implementation.tagCommands;

import com.mjc.school.controller.TagController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.TagDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateTag implements Command {
    @Autowired
    private TagController tagController;
    private final String name = "Update Tag";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter tag ID:");
        Long tagId = Long.parseLong(in.nextLine());
        System.out.println("Enter name:");
        var name = in.nextLine();
        var req = new TagDTOReq();
        req.setId(tagId);
        req.setName(name);
        var updatedTag = tagController.update(req);
        System.out.println(updatedTag.toString());
    }
}
