package com.mjc.school.service.implementation;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagDTOReq;
import com.mjc.school.service.dto.TagDTOResp;
import com.mjc.school.service.mapper.TagDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
@Transactional
public class TagServiceImpl extends BaseServiceImpl<TagDTOReq, TagDTOResp, TagEntity, TagRepository>
        implements TagService {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagDTOMapper mapper;
    @Override
    protected TagEntity dtoToEntity(TagDTOReq tagDTOReq) {
        return mapper.reqToEntity(tagDTOReq);
    }

    @Override
    protected TagDTOResp entityToDto(TagEntity tagEntity) {
        return mapper.entityToResp(tagEntity);
    }

    @Override
    protected TagRepository getRepo() {
        return tagRepository;
    }
}
