package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.service.dto.author.AuthorDTOReq;
import com.mjc.school.service.dto.author.AuthorDTOResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public abstract class AuthorDTOMapper {

    public abstract AuthorDTOResp authorToDtoResp(AuthorEntity authorEntity);
    public abstract List<AuthorDTOResp> authorsToDtoResp(List<AuthorEntity> authorEntities);

    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(ignore = true, target = "news")
    public abstract AuthorEntity authorReqToEntity(AuthorDTOReq authorDTOReq);
}