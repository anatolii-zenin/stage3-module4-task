package com.mjc.school.service.validator;

import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagValidator implements Validator<TagDTOReq> {
    @Autowired
    private TagService tagService;
    private final int nameLengthFrom = 3;
    private final int nameLengthTo = 15;
    @Override
    public boolean validate(TagDTOReq req) {
        StringBuilder errors = new StringBuilder();
        if (!validateRange(req.getName().length(),nameLengthFrom, nameLengthTo))
            errors.append("Tag length should be between " + nameLengthFrom + " and " +
                    nameLengthTo + " characters. Provided length: " + req.getName().length() + ".\n");
        if (req.getId() != null && tagService.readById(req.getId()) == null)
            errors.append("Author with id " + req.getId() + " does not exist.\n");
        if (errors.length() > 0)
            throw new RuntimeException(errors.toString());
        return true;
    }

    private boolean validateRange(int value, int from, int to) {
        return value > from && value < to;
    }
}
