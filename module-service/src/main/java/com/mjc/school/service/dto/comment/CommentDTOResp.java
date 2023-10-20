package com.mjc.school.service.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CommentDTOResp {
    private Long id;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private static final String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public String toString() {
        return "[" + id + "]. created: " +
                createDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". modified: " +
                lastUpdateDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) +
                ". " + content;
    }
}
