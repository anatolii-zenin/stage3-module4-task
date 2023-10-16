package com.mjc.school.controller.command.implementation.newsCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.command.implementation.CommandHelperFunctions;
import com.mjc.school.service.dto.NewsDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateNews implements Command {
    @Autowired
    private NewsController newsController;
    private final String name = "Create News";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title:");
        var title = in.nextLine();
        System.out.println("Enter content:");
        var content = in.nextLine();
        System.out.println("Enter author ID:");
        Long authorID = Long.parseLong(in.nextLine());
        System.out.println("Enter tag IDs: ");
        var tagIds = in.nextLine();
        var tags = CommandHelperFunctions.parseTags(tagIds);
        var req = new NewsDTOReq();
        req.setTitle(title);
        req.setContent(content);
        req.getAuthor().setId(authorID);
        req.setTags(tags);
        var newNews = newsController.create(req);
        System.out.println(newNews.toString());
    }
}
