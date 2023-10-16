package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.AuthorDTOResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class AuthorDTOMapper {
    @Autowired
    protected AuthorService authorService;

    public abstract AuthorDTOResp authorToDtoResp(AuthorEntity authorEntity);
    public abstract AuthorDTOReq authorToDtoReq(AuthorEntity authorEntity);

    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(ignore = true, target = "news")
    public abstract AuthorEntity authorReqToEntity(AuthorDTOReq authorDTOReq);

    public AuthorEntity authorIdToEntity(Long id) {
        var author = new AuthorEntity();
        author.setId(id);
        return author;
    }
}