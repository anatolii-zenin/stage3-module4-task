package com.mjc.school.service;

import com.mjc.school.service.dto.author.AuthorDTOReq;
import com.mjc.school.service.dto.news.NewsDTOReq;
import com.mjc.school.service.dto.tag.TagDTOReq;
import com.mjc.school.service.implementation.AuthorServiceImpl;
import com.mjc.school.service.implementation.NewsServiceImpl;
import com.mjc.school.service.implementation.TagServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;


public class TagServiceTests {
    private AuthorService authorService;
    private NewsService newsService;
    private TagService tagService;

    private static AnnotationConfigApplicationContext context;
    @BeforeEach
    public void setUp() {
        context = new AnnotationConfigApplicationContext(ServiceSpringConfig.class);
        prepareServices();
    }
    @AfterEach
    public void tearDown() {
        context.close();
    }

    @Test
    public void CreateReadTagsTest() {
        String authorName = "testAuthor";
        var authorId = createAuthor(authorName);

        var testEntries = 5;
        for (int i = 0; i < testEntries; i++) {

            String tagName = "tag" + i;
            String newsTitle = "newsTitle" + i;
            String newsContent = "newsContent" + i;

            var tagId = createTag(tagName);
            var tag = tagService.readById(tagId);
            assertEquals("Entry id is not as expected", tagId, tag.getId());

            List<TagDTOReq> tags = new ArrayList<>();
            addTagById(tags, tagId);
            var newsId = createNews(authorId, newsTitle, newsContent, tags);

            var newsEntry = newsService.readById(newsId);
            var tagEntries = tagService.readByNewsId(newsId);

            assertEquals("Entry id is not as expected", newsId, newsEntry.getId());
            assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
            assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
            assertEquals("Tag is not tied to the news entry", newsId, tagEntries.get(0).getId());
        }

        var allEntries = tagService.readAll();
        assertEquals("Incorrect number of entries:", testEntries, allEntries.size());
    }

    @Test
    public void multipleTagsTest() {
        String authorName = "testAuthor";
        var authorId = createAuthor(authorName);

        List<TagDTOReq> tags = new ArrayList<>();
        var tagNum = 3;
        var tagNameBase = "multiTag";

        Long[] tagIds = new Long[tagNum];
        for (int i = 0; i < tagNum; i++) {
            tagIds[i] = createTag(tagNameBase + i);
            addTagById(tags, tagIds[i]);
        }

        var newsTitle = "multiTagNews";
        var newsContent = "multiTagNews";
        var newsId = createNews(authorId, newsTitle, newsContent, tags);

        var tagsFromNews = tagService.readByNewsId(newsId);

        for (int i = 0; i < tagNum; i++) {
            assertEquals("Tag is not as expected", tagIds[i], tagsFromNews.get(i).getId());
            assertEquals("Tag is not as expected", tagNameBase + i, tagsFromNews.get(i).getName());
        }

        var allNewsEntries = newsService.readAll();
        assertEquals("Incorrect number of entries:", 1, allNewsEntries.size());
    }

    @Test
    public void updateTagsTest() {
        String authorName = "testAuthor";
        String tagName = "tagTitle";
        String tagNameUpdated = "tagTitleUpd";
        String newsTitle = "newsTitle";
        String newsContent = "testContent";

        var authorId = createAuthor(authorName);
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        var tagId = createTag(tagName);
        List<TagDTOReq> tags = new ArrayList<>();
        addTagById(tags, tagId);

        var newsId = createNews(authorId, newsTitle, newsContent, tags);
        var newsEntry = newsService.readById(newsId);
        var tagsFromNews = tagService.readByNewsId(newsId);

        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry tag is not as expected", tagName, tagsFromNews.get(0).getName());
        assertEquals("Entry tag is not as expected", tagId, tagsFromNews.get(0).getId());

        tagId = updateTag(tagId, tagNameUpdated);

        tagsFromNews = tagService.readByNewsId(newsId);

        assertEquals("Updated tag id is not as expected", tagId, tagsFromNews.get(0).getId());
        assertEquals("Updated tag is not as expected", tagNameUpdated, tagsFromNews.get(0).getName());
    }

    @Test
    public void deleteTagsTest() {
        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsContent = "testContent";
        String tagName = "tagg";

        var authorId = createAuthor(authorName);
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        var tagId = createTag(tagName);
        List<TagDTOReq> tags = new ArrayList<>();
        addTagById(tags, tagId);

        var newsId = createNews(authorId, newsTitle, newsContent, tags);
        var newsEntry = newsService.readById(newsId);
        var tagsFromNews = tagService.readByNewsId(newsId);
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry tag is not as expected", tagName, tagsFromNews.get(0).getName());

        updateNews(newsId, authorId, newsTitle, newsContent, new ArrayList<>());
        tagService.deleteById(tagId);

        var tag = tagService.readById(tagId);

        tagsFromNews = tagService.readByNewsId(newsId);
        assertEquals("Tag entry is not properly deleted", true, tagsFromNews.isEmpty());
        assertEquals("Tag entry is not properly deleted", null, tag);
    }

    private Long createAuthor(String name) {
        var authorReq = new AuthorDTOReq();
        authorReq.setName(name);
        return authorService.create(authorReq).getId();
    }

    private Long createNews(Long authorId, String newsTitle, String newsContent, List<TagDTOReq> tags) {
        var newsReq = new NewsDTOReq();
        newsReq.getAuthor().setId(authorId);
        newsReq.setContent(newsContent);
        newsReq.setTitle(newsTitle);
        newsReq.setTags(tags);
        return newsService.create(newsReq).getId();
    }

    private Long updateNews(Long newsId, Long authorId, String newsTitle, String newsContent, List<TagDTOReq> tags) {
        var newsReq = new NewsDTOReq();
        newsReq.setId(newsId);
        newsReq.getAuthor().setId(authorId);
        newsReq.setContent(newsContent);
        newsReq.setTitle(newsTitle);
        newsReq.setTags(tags);
        return newsService.update(newsReq).getId();
    }

    private Long createTag(String name) {
        var tagReq = new TagDTOReq();
        tagReq.setName(name);
        return tagService.create(tagReq).getId();
    }

    private Long updateTag(Long id, String name) {
        var tagReq = new TagDTOReq();
        tagReq.setName(name);
        tagReq.setId(id);
        return tagService.update(tagReq).getId();
    }

    private void addTagById(List<TagDTOReq> tags, Long id) {
        var newTag = tagService.readById(id);
        if (newTag != null) {
            var tagDto = new TagDTOReq();
            tagDto.setId(id);
            tagDto.setName(newTag.getName());
            tags.add(tagDto);
        }
    }

    private void prepareServices() {
        authorService = context.getBean(AuthorServiceImpl.class);
        newsService = context.getBean(NewsServiceImpl.class);
        tagService = context.getBean(TagServiceImpl.class);
    }
}
