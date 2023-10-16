package com.mjc.school.service.validator;

public interface Validator<T> {
    boolean validate(T obj);
}
