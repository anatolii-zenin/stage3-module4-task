package com.mjc.school.controller.command.implementation.newsCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.NewsDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateNews implements Command {
    @Autowired
    private NewsController newsController;
    private final String name = "Update News";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter news ID:");
        Long newsID = Long.parseLong(in.nextLine());
        System.out.println("Enter title:");
        var title = in.nextLine();
        System.out.println("Enter content:");
        var content = in.nextLine();
        System.out.println("Enter author ID:");
        Long authorID = Long.parseLong(in.nextLine());
        var req = new NewsDTOReq();
        req.setId(newsID);
        req.setTitle(title);
        req.setContent(content);
        req.getAuthor().setId(authorID);
        var updatedNews = newsController.update(req);
        System.out.println(updatedNews.toString());
    }
}
