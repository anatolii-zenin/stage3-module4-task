package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.news.NewsDTOResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TagDTOMapper.class, AuthorDTOMapper.class})
@Component
public abstract class NewsDTOMapper {

//    @Mapping(target = "author", source = "newsEntity.author")
    public abstract NewsDTOResp newsToDto(NewsEntity newsEntity);

    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(ignore = true, target = "comments")
    public abstract NewsEntity newsReqToEntity(NewsDTOReq newsDTOReq);

    public abstract List<NewsDTOResp> newsEntitiesToDto(List<NewsEntity> newsEntities);

}