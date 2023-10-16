package com.mjc.school.controller.implementation;

import com.mjc.school.controller.TagController;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagDTOReq;
import com.mjc.school.service.dto.TagDTOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@Scope("singleton")
public class TagControllerImpl implements TagController {
    @Autowired
    TagService service;

    @Override
    public List<TagDTOResp> readAll() {
        return service.readAll();
    }

    @Override
    public TagDTOResp readById(Long id) {
        return service.readById(id);
    }

    @Override
    public TagDTOResp create(TagDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    public TagDTOResp update(TagDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
