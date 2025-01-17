package com.mjc.school.service.validator;

import com.mjc.school.service.dto.author.AuthorDTOReq;
import com.mjc.school.service.dto.comment.CommentDTOReq;
import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.tag.TagDTOReq;
import com.mjc.school.service.exception.BadRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configurable
public class ValidatorAspect {
    @Autowired
    Validator<NewsDTOReq> newsValidator;
    @Autowired
    Validator<AuthorDTOReq> authorValidator;
    @Autowired
    Validator<TagDTOReq> tagValidator;
    @Autowired
    Validator<CommentDTOReq> commentValidator;

    @Before("validate() && args(arg)")
    public void validateCreateReq(Object arg) {
        validateReq(arg, "create");
    }

    @Before("validateUpdate() && args(arg)")
    public void validateUpdateReq(Object arg) {
        validateReq(arg, "update");
    }


    @Pointcut("execution(public * *(.., @com.mjc.school.service.validator.annotations.Validate (*), ..))")
    private void validate() {}

    @Pointcut("execution(public * *(.., @com.mjc.school.service.validator.annotations.ValidateUpdate (*), ..))")
    private void validateUpdate() {}

    private void validateReq(Object arg, String reqType) {
        var argClass = arg.getClass();
        if (argClass.equals(NewsDTOReq.class)) {
            newsValidator.validate((NewsDTOReq) arg, reqType);
        } else if (argClass.equals(AuthorDTOReq.class)) {
            authorValidator.validate((AuthorDTOReq) arg, reqType);
        } else if (argClass.equals(TagDTOReq.class)) {
            tagValidator.validate((TagDTOReq) arg, reqType);
        } else if (argClass.equals(CommentDTOReq.class)) {
            commentValidator.validate((CommentDTOReq) arg, reqType);
        } else
            throw new BadRequestException("Validator unable to process class: " + argClass);
    }
}
