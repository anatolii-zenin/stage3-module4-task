package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewsDTOReq {
    private Long id;
    private String title;
    private String content;
    private AuthorDTOReq author = new AuthorDTOReq();
    private List<TagDTOReq> tags = new ArrayList<>();
}
