package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.TagDTOReq;
import com.mjc.school.service.implementation.AuthorServiceImpl;
import com.mjc.school.service.implementation.NewsServiceImpl;
import com.mjc.school.service.implementation.TagServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;


public class NewsServiceTests {
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
    public void CreateReadNewsTest() {
        var testEntries = 5;
        for (int i = 0; i < testEntries; i++) {
            String authorName = "testAuthor" + i;
            String tagName = "tag" + i;
            String newsTitle = "newsTitle" + i;
            String newsContent = "newsContent" + i;

            var authorId = createAuthor(authorName);
            var tagId = createTag(tagName);
            List<TagDTOReq> tags = new ArrayList<>();
            addTagById(tags, tagId);
            var newsId = createNews(authorId, newsTitle, newsContent, tags);

            var newsEntry = newsService.readById(newsId);
            var tagEntries = newsService.readTagsByNewsId(newsId);

            assertEquals("Entry id is not as expected", newsId, newsEntry.getId());
            assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
            assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
            assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthor().getId());
            assertEquals("Entry tag list is empty", false, newsEntry.getTags().isEmpty());
            assertEquals("Entry tag is not as expected", tagId, newsEntry.getTags().get(0).getId());
            assertEquals("Tag is not tied to the news entry", newsId, tagEntries.get(0).getId());
        }

        var allEntries = newsService.readAll();
        assertEquals("Incorrect number of entries:", testEntries, allEntries.size());
    }

    @Test
    public void updateNewsTest() {
        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsTitleUpdated = "testTitleUpdated";
        String newsContent = "testContent";
        String newsContentUpdated = "testContentUpdated";

        var authorId = createAuthor(authorName);
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        var newsId = createNews(authorId, newsTitle, newsContent);
        var newsEntry = newsService.readById(newsId);

        assertEquals("Entry id is not as expected", newsId, newsEntry.getAuthor().getId());
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthor().getId());

        var newsReq = new NewsDTOReq();
        newsReq.setTitle(newsTitleUpdated);
        newsReq.setContent(newsContentUpdated);
        newsReq.setId(newsId);
        newsReq.getAuthor().setId(authorId);
        newsService.update(newsReq);
        newsEntry = newsService.readById(newsId);

        assertEquals("Updated id is not as expected", newsId, newsEntry.getAuthor().getId());
        assertEquals("Updated title is not as expected", newsTitleUpdated, newsEntry.getTitle());
        assertEquals("Updated content is not as expected", newsContentUpdated, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthor().getId());
    }

    @Test
    public void deleteNewsTest() {
        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsContent = "testContent";

        var authorId = createAuthor(authorName);
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        var newsId = createNews(authorId, newsTitle, newsContent);
        var newsEntry = newsService.readById(newsId);
        assertEquals("Entry id is not as expected", newsId, newsEntry.getAuthor().getId());
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthor().getId());

        newsService.deleteById(newsId);
        newsEntry = newsService.readById(newsId);
        assertEquals("News entry is not properly deleted", null, newsEntry);

        authorEntry = authorService.readById(authorId);
        assertEquals("Author is not persisted after deleting news", authorName, authorEntry.getName());
    }

    @Test
    public void deleteNewsByDeletingAuthorTest() {
        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsContent = "testContent";

        var authorId = createAuthor(authorName);
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        var newsId = createNews(authorId, newsTitle, newsContent);
        var newsEntry = newsService.readById(newsId);
        assertEquals("Entry id is not as expected", newsId, newsEntry.getAuthor().getId());
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthor().getId());

        authorService.deleteById(authorId);
        authorEntry = authorService.readById(authorId);
        assertEquals("Author is not deleted", null, authorEntry);
        newsEntry = newsService.readById(newsId);
        assertEquals("News entry is not properly deleted after deleting author", null, newsEntry);
    }

    @Test
    public void selectNewsByCriteriaTest() {
        String authorName1 = "testAuthor1";
        String authorName2 = "testAuthor1";
        var authorId1 = createAuthor(authorName1);
        var authorId2 = createAuthor(authorName2);

        List<TagDTOReq> tags = new ArrayList<>();
        var tagNum = 3;
        var tagNameBase = "tag#";

        Long[] tagIds = new Long[tagNum];
        for (int i = 0; i < tagNum; i++) {
            tagIds[i] = createTag(tagNameBase + i);
            addTagById(tags, tagIds[i]);
        }

        var newsTitle = "taggedNews";
        var newsContent = "taggedNews";
        createNews(authorId1, newsTitle, newsContent, tags);
        createNews(authorId2, newsTitle + "2", newsContent + "2", tags);
        createNews(authorId1, "DoNotRead", "DoNotRead", new ArrayList<>());

        ArrayList<TagDTOReq> tagsById = new ArrayList<>();
        var tag1 = new TagDTOReq();
        tag1.setId(tagIds[0]);
        tagsById.add(tag1);

        var criteriaTagId = new NewsDTOReq();
        criteriaTagId.setTags(tagsById);

        var readByTagId = new NewsDTOReq();
        readByTagId.setTags(tagsById);

        var newsByCriteria = newsService.readByCriteria(readByTagId);

        assertEquals("The amount of entries is not as expected", 2, newsByCriteria.size());
        assertEquals("Expected tag ID is not found", tagIds[0],
                newsByCriteria.get(0)
                        .getTags()
                        .stream()
                        .filter(x -> x.getId().equals(tagIds[0])).toList()
                        .get(0)
                        .getId()
        );

        var criteriaAuthorId = new AuthorDTOReq();
        criteriaAuthorId.setId(authorId1);

        var readByAuthorId = new NewsDTOReq();
        readByAuthorId.setAuthor(criteriaAuthorId);

        newsByCriteria = newsService.readByCriteria(readByAuthorId);
        assertEquals("The amount of entries is not as expected", 2, newsByCriteria.size());
        assertEquals("Expected author ID is not found", authorId1,
                newsByCriteria.get(0).getAuthor().getId()
        );
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

    private Long createNews(Long authorId, String newsTitle, String newsContent) {
        var newsReq = new NewsDTOReq();
        newsReq.getAuthor().setId(authorId);
        newsReq.setContent(newsContent);
        newsReq.setTitle(newsTitle);
        return newsService.create(newsReq).getId();
    }

    private Long createTag(String name) {
        var tagReq = new TagDTOReq();
        tagReq.setName(name);
        return tagService.create(tagReq).getId();
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
