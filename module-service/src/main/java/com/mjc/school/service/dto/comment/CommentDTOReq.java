package com.mjc.school.service.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTOReq {
    private Long id;
    private String content;
    private Long newsId;
}
