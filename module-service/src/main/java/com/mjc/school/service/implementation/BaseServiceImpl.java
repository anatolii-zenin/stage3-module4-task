package com.mjc.school.service.implementation;

import com.mjc.school.repository.PaginationCapableRepository;
import com.mjc.school.repository.model.BaseEntity;
import com.mjc.school.service.PaginationCapableService;
import com.mjc.school.service.validator.Validate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<Req, Resp, Entity extends BaseEntity<Long>,
        Repository extends PaginationCapableRepository<Entity, Long>>
        implements PaginationCapableService<Req, Resp, Long> {

    @Override
    public List<Resp> readAll() {
        return entitiesToDtos(getRepo().readAll());
    }

    @Override
    public List<Resp> readAll(int page, int size) {
        return entitiesToDtos(getRepo().readAll(page, size));
    }

    @Override
    public Resp readById(Long id) {
        var item = getRepo().readById(id);
        return item.map(this::entityToDto).orElse(null);
    }

    @Override
    public Resp create(@Validate Req createRequest) {
        return entityToDto(
                getRepo().create(dtoToEntity(createRequest))
        );
    }

    @Override
    public Resp update(@Validate Req updateRequest) {
        return entityToDto(
                getRepo().update(dtoToEntity(updateRequest))
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return getRepo().deleteById(id);
    }

    protected abstract Entity dtoToEntity(Req dto);

    protected abstract List<Resp> entitiesToDtos(List<Entity> entities);

    protected abstract Resp entityToDto(Entity entity);
    
    protected abstract Repository getRepo();
}
