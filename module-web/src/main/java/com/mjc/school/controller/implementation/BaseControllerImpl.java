package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;

import java.util.List;

public class BaseControllerImpl<Req, Resp, ServiceClass extends BaseService<Req, Resp, Long>>
        implements BaseController<Req, Resp, Long> {
    protected ServiceClass service;
    @Override
    public List<Resp> readAll() {
        return service.readAll();
    }

    @Override
    public Resp readById(Long id) {
        return service.readById(id);
    }

    @Override
    public Resp create(Req createRequest) {
        return service.create(createRequest);
    }

    @Override
    public Resp update(Req updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
