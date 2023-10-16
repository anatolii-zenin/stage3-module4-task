package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.NewsDTOResp;
import com.mjc.school.service.dto.TagDTOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@Scope("singleton")
public class NewsControllerImpl implements NewsController {
    @Autowired
    NewsService service;

    @Override
    public List<NewsDTOResp> readAll() {
        return service.readAll();
    }

    @Override
    public NewsDTOResp readById(Long id) {
        return service.readById(id);
    }

    @Override
    public NewsDTOResp create(NewsDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    public NewsDTOResp update(NewsDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }

    @Override
    public List<TagDTOResp> readTagsByNewsId(Long id) {
        return service.readTagsByNewsId(id);
    }

    @Override
    public AuthorDTOResp readAuthorByNewsId(Long id) {
        return service.readAuthorByNewsId(id);
    }

    @Override
    public List<NewsDTOResp> readByCriteria(NewsDTOReq req) {
        return service.readByCriteria(req);
    }
}
