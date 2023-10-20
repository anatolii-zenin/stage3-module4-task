package com.mjc.school.controller.util;

import com.mjc.school.service.AuthorService;
import com.mjc.school.service.CommentService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.author.AuthorDTOReq;
import com.mjc.school.service.dto.comment.CommentDTOReq;
import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.tag.TagDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoDataLoader {
    @Autowired
    AuthorService authorService;
    @Autowired
    NewsService newsService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;

    public void populateAuthors() {
        var authorLines = readAuthorLines();
        for (String authorLine : authorLines) {
            var author = new AuthorDTOReq();
            author.setName(authorLine);

            authorService.create(author);
        }
    }

    public void populateTags() {
        var tagNumber = 30;
        var baseName = "tag#";
        for (int i = 0; i < tagNumber; i++) {
            var tag = new TagDTOReq();
            tag.setName(baseName + (i + 1));
            tagService.create(tag);
        }
    }

    public void populateNews() {
        var titleLines = readTitleLines();
        var contentLines = readContentLines();
        for (int i=0; i < titleLines.size(); i++) {
            var news = new NewsDTOReq();
            var tags = chooseTags(i);
            news.setTitle(titleLines.get(i));
            news.setContent(contentLines.get(i));
            news.getAuthor().setId((long) i+1);
            news.setTags(tags);
            var id = newsService.create(news);
            System.out.println(id);
        }
    }

    public void populateComments() {
        var commentNumber = 60;
        var baseName = "commentContent#";
        for (int i = 0; i < commentNumber; i++) {
            var comment = new CommentDTOReq();
            comment.setContent(baseName + (i+1));
            comment.setNewsId((long) i%30 + 1);
            commentService.create(comment);
        }
    }

    private String titleFileName = "news";
    private String contentFileName = "content";
    private String authorFileName = "authors";

    private List<String> readFile(String filename) {
        List<String> lines;
        try(var inputStream = this.getClass().getResourceAsStream("/" + filename)) {
            assert inputStream != null;
            lines = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    private List<String> readTitleLines() {
        return readFile(titleFileName);
    }

    private List<String> readContentLines() {
        return readFile(contentFileName);
    }

    private List<String> readAuthorLines() {
        return readFile(authorFileName);
    }

    private List<TagDTOReq> chooseTags(int i) {
        var tags = new ArrayList<TagDTOReq>();
        var tag1 = new TagDTOReq();
        var tag2 = new TagDTOReq();
        Long id1 = i > 2L ? i - (long) i%2L : 1L;
        Long id2 = (long) i + 1;
        tag1.setId(id1);
        tag2.setId(id2);
        tags.add(tag1);
        tags.add(tag2);
        return tags;
    }
}
