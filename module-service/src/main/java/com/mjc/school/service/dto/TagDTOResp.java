package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagDTOResp {
    private Long id;
    private String name;
    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
