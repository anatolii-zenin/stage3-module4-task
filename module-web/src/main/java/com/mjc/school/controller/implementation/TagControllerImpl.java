package com.mjc.school.controller.implementation;

import com.mjc.school.controller.TagController;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.tag.TagDTOReq;
import com.mjc.school.service.dto.tag.TagDTOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class TagControllerImpl implements TagController {
    @Autowired
    TagService service;

    @Override
    @GetMapping(value = "tags")
    public List<TagDTOResp> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "1") int size
    ) {
        return service.readAll();
    }

    @Override
    @GetMapping(value = "tags/{id:\\d+}")
    public TagDTOResp readById(Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping(value = "tags/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TagDTOResp create(TagDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PatchMapping(value = "tags/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TagDTOResp update(TagDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "tags/{id:\\d+}")
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }

    @Override
    @GetMapping(value = "news/{id:\\d+}/tags")
    public List<TagDTOResp> readByNewsId(@PathVariable Long id) {
        return service.readByNewsId(id);
    }
}
