package com.mjc.school.controller.command.implementation;

import com.mjc.school.service.dto.TagDTOReq;

import java.util.ArrayList;
import java.util.List;

public class CommandHelperFunctions {
    public static List<TagDTOReq> parseTags(String tagString) {
        List<TagDTOReq> tags = new ArrayList<>();
        if (tagString.isBlank())
            return tags;
        var split = tagString.split(" ");
        if (split.length > 0 && split[0].matches("\\d+")) {
            for (var tagId : split) {
                var id = Long.parseLong(tagId);
                var tag = new TagDTOReq();
                tag.setId(id);
                tags.add(tag);
            }
        }
        else
            for (var tagName : split) {
                var tag = new TagDTOReq();
                tag.setName(tagName);
                tags.add(tag);
            }
        return tags;
    }
}
