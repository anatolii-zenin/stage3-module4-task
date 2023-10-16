package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.NewsDTOResp;
import com.mjc.school.service.dto.TagDTOResp;

import java.util.List;

public interface NewsController extends BaseController<NewsDTOReq, NewsDTOResp, Long> {
    List<TagDTOResp> readTagsByNewsId(Long id);
    AuthorDTOResp readAuthorByNewsId(Long id);
    List<NewsDTOResp> readByCriteria(NewsDTOReq req);
}
