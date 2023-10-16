package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Setter
@Getter
public class NewsDTOResp {
    private Long id;
    private String title;
    private String content;
    private AuthorDTOResp author;
    private List<TagDTOResp> tags;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private static final String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public String toString() {
        return "[" + id + "] " + title + ". created: " +
                createDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". modified: " +
                lastUpdateDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". author: " +
                getAuthor().getName() + ". " + " tags: " + tagsToString() + ". "  + content;
    }

    private String tagsToString() {
        var tagString = new StringBuilder();
        for (var tag : tags)
            tagString.append(tag.toString() + " ");
        return tagString.toString().trim();
    }
}
