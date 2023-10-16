package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.NewsDTOResp;
import com.mjc.school.service.dto.TagDTOResp;

import java.util.List;

public interface NewsService extends BaseService<NewsDTOReq, NewsDTOResp, Long>{
    List<TagDTOResp> readTagsByNewsId(Long newsId);
    AuthorDTOResp readAuthorByNewsId(Long newsId);
    List<NewsDTOResp> readByCriteria(NewsDTOReq req);
}
