package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.CommentEntity;
import com.mjc.school.service.dto.comment.CommentDTOReq;
import com.mjc.school.service.dto.comment.CommentDTOResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommentDTOMapper {
    public abstract CommentDTOResp commentEntityToDto(CommentEntity commentEntity);
    public abstract List<CommentDTOResp> commentEntitiesToDto(List<CommentEntity> commentEntities);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(target = "news.id", source = "req.newsId")
    public abstract CommentEntity dtoToCommentEntity(CommentDTOReq req);
}
