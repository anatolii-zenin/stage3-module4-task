package com.mjc.school.controller.implementation;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("singleton")
public class AuthorControllerImpl implements AuthorController {
    @Autowired
    AuthorService service;

    @Override
    public List<AuthorDTOResp> readAll() {
        return service.readAll();
    }

    @Override
    public AuthorDTOResp readById(Long id) {
        return service.readById(id);
    }

    @Override
    public AuthorDTOResp create(AuthorDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    public AuthorDTOResp update(AuthorDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
