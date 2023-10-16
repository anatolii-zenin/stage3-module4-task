package com.mjc.school.service.mapper;


import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = NewsDTOMapper.class)
@Component
public abstract class TagDTOMapper {
    public abstract TagDTOResp entityToResp(TagEntity tagEntity);

    @Mapping(ignore = true, target = "news")
    public abstract TagEntity reqToEntity(TagDTOReq tagDTOReq);
    public abstract List<TagEntity> reqsToEntities(List<TagDTOReq> tagReqs);
    public abstract List<TagDTOResp> entitiesToResps(List<TagEntity> tagEntities);

}