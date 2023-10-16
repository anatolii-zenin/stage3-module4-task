package com.mjc.school.service.validator;

import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidator implements Validator<AuthorDTOReq> {
    @Autowired
    private AuthorService authorService;
    private final int nameLengthFrom = 3;
    private final int nameLengthTo = 25;
    @Override
    public boolean validate(AuthorDTOReq req) {
        StringBuilder errors = new StringBuilder();
        if (!validateRange(req.getName().length(),nameLengthFrom, nameLengthTo))
            errors.append("Name length should be between " + nameLengthFrom + " and " +
                    nameLengthTo + " characters. Provided length: " + req.getName().length() + ".\n");
        if (req.getId() != null && authorService.readById(req.getId()) == null)
            errors.append("Author with id " + req.getId() + " does not exist.\n");
        if (errors.length() > 0)
            throw new RuntimeException(errors.toString());
        return true;
    }

    private boolean validateRange(int value, int from, int to) {
        return value > from && value < to;
    }
}
