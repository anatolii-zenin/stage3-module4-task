package com.mjc.school.controller.command.invoker.implementation;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.command.implementation.authorCommands.*;
import com.mjc.school.controller.command.implementation.newsCommands.*;
import com.mjc.school.controller.command.implementation.tagCommands.*;
import com.mjc.school.controller.command.implementation.utilCommands.LoadDemoData;
import com.mjc.school.controller.command.invoker.Invoker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class InvokerImpl implements Invoker {
    private List<Command> commandList = new ArrayList<>();
    @Override
    public void runCommand(int id) {
        commandList.get(id).execute();
    }

    @Override
    public void commandList() {
        StringBuilder commandText = new StringBuilder();
        commandText.append("Select operation:\n");
        for (var command : commandList)
            commandText.append(commandList.indexOf(command) + ". " + command.getName() + "\n");
        System.out.println(commandText);
    }

    InvokerImpl(
            CreateAuthor createAuthor,
            CreateNews createNews,
            CreateTag createTag,

            DeleteAuthor deleteAuthor,
            DeleteNews deleteNews,
            DeleteTag deleteTag,

            ReadAllAuthors readAllAuthors,
            ReadAllNews readAllNews,
            ReadAllTags readAllTags,

            ReadAuthorById readAuthorById,
            ReadTagById readTagById,
            ReadNewsById readNewsById,

            ReadAuthorByNewsId readAuthorByNewsId,
            ReadTagByNewsId readTagByNewsId,

            UpdateAuthor updateAuthor,
            UpdateNews updateNews,
            UpdateTag updateTag,

            ReadNewsByCriteria readNewsByCriteria,

            LoadDemoData loadDemoData
    ) {
        commandList.add(createAuthor);
        commandList.add(createNews);
        commandList.add(createTag);

        commandList.add(deleteAuthor);
        commandList.add(deleteNews);
        commandList.add(deleteTag);

        commandList.add(readAllAuthors);
        commandList.add(readAllNews);
        commandList.add(readAllTags);

        commandList.add(readAuthorById);
        commandList.add(readNewsById);
        commandList.add(readTagById);

        commandList.add(readAuthorByNewsId);
        commandList.add(readTagByNewsId);

        commandList.add(updateAuthor);
        commandList.add(updateNews);
        commandList.add(updateTag);

        commandList.add(readNewsByCriteria);

        commandList.add(loadDemoData);
    }
}
