package com.mjc.school.controller.command.implementation.newsCommands;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.command.implementation.CommandHelperFunctions;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.NewsDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReadNewsByCriteria implements Command {
    @Autowired
    private NewsController newsController;
    private final String name = "Read News by Criteria";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        var criteria = new NewsDTOReq();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter author ID or name:");
        var authorInput = in.nextLine();
        System.out.println("Enter a part of title:");
        var title = in.nextLine();
        System.out.println("Enter a part of content:");
        var content = in.nextLine();
        System.out.println("Enter tags or tag IDs:");
        var tagInput = in.nextLine();

        var author = parseAuthorInput(authorInput);
        criteria.setAuthor(author);

        if (!title.isBlank())
            criteria.setTitle(title);
        if (!content.isBlank())
            criteria.setContent(content);

        var tags = CommandHelperFunctions.parseTags(tagInput);
        if (tags.size() > 0)
            criteria.setTags(tags);

        for (var newsDTO : newsController.readByCriteria(criteria)) {
            System.out.println(newsDTO.toString());
        }
    }

    private AuthorDTOReq parseAuthorInput(String input) {
        var author = new AuthorDTOReq();
        if (input.isBlank())
            return author;
        if (input.matches("\\d+"))
            author.setId(Long.parseLong(input));
        else
            author.setName(input);
        return author;
    }
}
