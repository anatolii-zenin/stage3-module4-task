package com.mjc.school.controller.command.implementation.utilCommands;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.util.DemoDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDemoData implements Command {
    @Autowired
    DemoDataLoader demoDataLoader;

    private String name = "Load Demo Data";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        demoDataLoader.populateAuthors();
        demoDataLoader.populateTags();
        demoDataLoader.populateNews();
        demoDataLoader.populateComments();
    }
}
