package com.mjc.school.controller;

import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.news.NewsDTOResp;

import java.util.List;

public interface NewsController extends RestController<NewsDTOReq, NewsDTOResp, Long> {
    List<NewsDTOResp> readByCriteria(NewsDTOReq req);
}
