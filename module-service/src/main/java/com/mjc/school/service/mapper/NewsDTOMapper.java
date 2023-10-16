package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {TagDTOMapper.class, AuthorDTOMapper.class})
@Component
public abstract class NewsDTOMapper {

    @Mapping(target = "author", source = "newsEntity.author")
    public abstract NewsDTOResp newsToDto(NewsEntity newsEntity);

    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    public abstract NewsEntity newsReqToEntity(NewsDTOReq newsDTOReq);

}