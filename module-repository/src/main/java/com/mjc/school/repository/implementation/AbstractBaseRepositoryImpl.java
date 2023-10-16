package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public abstract class AbstractBaseRepositoryImpl<T extends BaseEntity<Long>> implements BaseRepository<T, Long> {
    @Override
    public List<T> readAll() {
        var findAll = getEntityManager().createQuery("SELECT a FROM " + getTableName() + " a", getEntityClass());
        return findAll.getResultList();
    }

    @Override
    public Optional<T> readById(Long id) {
        return Optional.ofNullable(getEntityManager().find(getEntityClass(), id));
    }

    @Override
    public T create(T entity) {
        var mergedEntity = getEntityManager().merge(entity);
        return  getEntityManager().find(getEntityClass(), mergedEntity.getId());
    }

    @Override
    public abstract T update(T entity);

    @Override
    public boolean deleteById(Long id) {
        var obj = readById(id);
        if (obj.isPresent())
            getEntityManager().remove(obj.get());
        else
            return false;
        return readById(id).isEmpty();
    }

    @Override
    public boolean existById(Long id) {
        Optional<T> itemById = readById(id);
        return itemById.isPresent();
    }

    protected abstract Class<T> getEntityClass();
    protected abstract EntityManager getEntityManager();
    protected abstract String getTableName();
}
