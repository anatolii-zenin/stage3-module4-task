package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.AuthorDTOResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class AuthorDTOMapper {

    public abstract AuthorDTOResp authorToDtoResp(AuthorEntity authorEntity);

    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(ignore = true, target = "news")
    public abstract AuthorEntity authorReqToEntity(AuthorDTOReq authorDTOReq);
}