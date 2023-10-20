package com.mjc.school.service.validator;

import com.mjc.school.service.dto.author.AuthorDTOReq;
import com.mjc.school.service.dto.comment.CommentDTOReq;
import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.tag.TagDTOReq;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configurable
public class ValidatorAspect {
    @Autowired
    ApplicationContext applicationContext;

    @Before("validate() && args(arg)")
    public void validateReq(Object arg) {
        var argClass = arg.getClass();
        boolean success = false;
        if (argClass.equals(NewsDTOReq.class)) {
            var validator = applicationContext.getBean(NewsValidator.class);
            success = validator.validate((NewsDTOReq) arg);
        }
        else if (argClass.equals(AuthorDTOReq.class)) {
            var validator = applicationContext.getBean(AuthorValidator.class);
            success = validator.validate((AuthorDTOReq) arg);
        }
        else if (argClass.equals(TagDTOReq.class))
        {
            var validator = applicationContext.getBean(TagValidator.class);
            success = validator.validate((TagDTOReq) arg);
        }
        else if (argClass.equals(CommentDTOReq.class)) {
            return;
        } else
            throw new RuntimeException("Validator unable to process class: " + argClass);
        if (!success)
            throw new RuntimeException("Validation failed");
    }

    @Pointcut("execution(public * *(.., @com.mjc.school.service.validator.Validate (*), ..))")
    private void validate() {}

}
