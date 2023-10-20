package com.mjc.school.service.dto.news;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class NewsDTOResp {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private static final String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public String toString() {
        return "[" + id + "] " + title + ". created: " +
                createDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". modified: " +
                lastUpdateDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". " + content;
    }
}
