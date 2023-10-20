package com.mjc.school.controller;

import java.util.List;

public interface RestController<T, R, K> {

    List<R> readAll(int page, int size);

    R readById(K id);

    R create(T createRequest);

    R update(T updateRequest);

    boolean deleteById(K id);
}
