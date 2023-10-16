package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.implementation.AuthorServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.test.util.AssertionErrors.assertEquals;


public class AuthorServiceTests {
    private AuthorService authorService;

    private static AnnotationConfigApplicationContext context;
    @BeforeEach
    public void setUp() {
        context = new AnnotationConfigApplicationContext(ServiceSpringConfig.class);
    }
    @AfterEach
    public void tearDown() {
        context.close();
    }

    @Test
    public void CreateReadAuthorTest() {
        authorService = context.getBean(AuthorServiceImpl.class);

        var testEntries = 5;
        for (int i = 0; i < testEntries; i++) {
            String authorName = "testAuthor" + i;

            var authorId = createAuthor(authorName);
            var author = authorService.readById(authorId);

            assertEquals("Entry id is not as expected", authorId, author.getId());
            assertEquals("Entry name is not as expected", authorName, author.getName());
        }

        var allEntries = authorService.readAll();
        assertEquals("Incorrect number of entries:", testEntries, allEntries.size());
    }

    @Test
    public void UpdateAuthorTest() {
        authorService = context.getBean(AuthorServiceImpl.class);

        String authorName = "testAuthor";
        String authorNameUpdated = "testAuthorUpdated";

        var authorReq = new AuthorDTOReq();
        authorReq.setName(authorName);
        var id = authorService.create(authorReq).getId();
        var entry = authorService.readById(id);
        assertEquals("Created entry is not as expected", authorName, entry.getName());

        authorReq = new AuthorDTOReq();
        authorReq.setName(authorNameUpdated);
        authorReq.setId(id);
        authorService.update(authorReq);
        entry = authorService.readById(id);
        assertEquals("Updated entry is not as expected", authorNameUpdated, entry.getName());
    }

    @Test
    public void DeleteAuthorTest() {
        authorService = context.getBean(AuthorServiceImpl.class);

        String authorName = "testAuthor";

        var authorReq = new AuthorDTOReq();
        authorReq.setName(authorName);
        var id = authorService.create(authorReq).getId();
        var entry = authorService.readById(id);
        assertEquals("Created entry is not as expected", authorName, entry.getName());

        authorService.deleteById(id);
        entry = authorService.readById(id);
        assertEquals("Entry is not properly deleted", null, entry);
    }

    private Long createAuthor(String name) {
        var authorReq = new AuthorDTOReq();
        authorReq.setName(name);
        return authorService.create(authorReq).getId();
    }
}
