package com.mjc.school.repository;

import com.mjc.school.repository.model.implementation.NewsEntity;

import java.util.List;

public interface NewsRepository extends BaseRepository<NewsEntity, Long>{
    List<NewsEntity> readNewsByCriteria(NewsEntity req);
}
