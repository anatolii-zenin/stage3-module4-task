package com.mjc.school.service.validator;

import com.mjc.school.service.AuthorService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.NewsDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsValidator implements Validator<NewsDTOReq> {
    @Autowired
    private NewsService newsService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;
    private final int titleLengthFrom = 3;
    private final int titleLengthTo = 50;
    private final int contentLengthFrom = 3;
    private final int contentLengthTo = 355;
    @Override
    public boolean validate(NewsDTOReq req) {
        StringBuilder errors = new StringBuilder();
        if (req.getId() != null && newsService.readById(req.getId()) == null)
            errors.append("News with id " + req.getId() + " does not exist.\n");
        if (!validateRange(req.getTitle().length(),titleLengthFrom, titleLengthTo))
            errors.append("Title length should be between " + titleLengthFrom + " and " +
                    titleLengthTo + " characters. Provided length: " + req.getTitle().length() + ".\n");
        if (!validateRange(req.getContent().length(), contentLengthFrom, contentLengthTo))
            errors.append("Title length should be between " + contentLengthFrom + " and " +
                    contentLengthTo + " characters. Provided length: " + req.getContent().length() + ".\n");
        if (authorService.readById(req.getAuthor().getId()) == null)
            errors.append("Author with id " + req.getAuthor().getId() + " does not exist.\n");
        for (var tag : req.getTags())
            if (tagService.readById(tag.getId()) == null)
                errors.append("Tag with id " + tag.getId() + " does not exist.\n");
        if (errors.length() > 0)
            throw new RuntimeException(errors.toString());
        return true;
    }

    private boolean validateRange(int value, int from, int to) {
        return value > from && value < to;
    }
}
