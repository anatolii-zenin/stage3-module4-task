package com.mjc.school.controller.implementation;

import com.mjc.school.controller.CommentController;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.dto.comment.CommentDTOReq;
import com.mjc.school.service.dto.comment.CommentDTOResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentControllerImpl implements CommentController {
    @Autowired
    CommentService service;
    @Override
    @GetMapping(value = "comments")
    public List<CommentDTOResp> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "1") int size
    ) {
        return service.readAll(page, size);
    }

    @Override
    @GetMapping(value = "/comments/{id:\\d+}")
    public CommentDTOResp readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping(value = "/comments/create")
    public CommentDTOResp create(CommentDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PatchMapping(value = "/comments/update")
    public CommentDTOResp update(CommentDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping(value = "/comments/{id:\\d+}")
    public boolean deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Override
    @GetMapping(value = "/news/{id:\\d+}/comments")
    public List<CommentDTOResp> readByNewsId(@PathVariable Long id) {
        return service.readCommentsByNewsId(id);
    }
}
