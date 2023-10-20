package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.news.NewsDTOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsControllerImpl implements NewsController {
    @Autowired
    NewsService service;

    @Override
    @GetMapping(value = "")
    public List<NewsDTOResp> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "1") int size
    ) {
        return service.readAll();
    }

    @Override
    @GetMapping(value = "{id:\\d+}")
    public NewsDTOResp readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NewsDTOResp create(@RequestBody NewsDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PatchMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NewsDTOResp update(@RequestBody NewsDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "{id:\\d+}")
    public boolean deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Override
    @GetMapping(value = "read-by-criteria", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<NewsDTOResp> readByCriteria(@RequestBody NewsDTOReq req) {
        return service.readByCriteria(req);
    }
}
