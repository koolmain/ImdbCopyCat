package com.koolmain.imdb;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "docs/snippets")
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema-h2.sql", } )
@Sql(scripts = {"/testdata/names.sql", "/testdata/titles.sql","/testdata/crew.sql","/testdata/ratings.sql", "/testdata/principals.sql"} )
@Transactional
class ImdbTitleControllerTest {

    @Autowired
    private MockMvc mockMvc; 

    @DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url", () -> "jdbc:h2:mem:testdb");
		registry.add("spring.datasource.password", () ->"sa");
		registry.add("spring.datasource.username", ()-> "");
		registry.add("spring.datasource.driver-class-name", ()-> "org.h2.Driver");
		registry.add("spring.jpa.properties.hibernate.dialect", ()-> "org.hibernate.dialect.H2Dialect");
	}
    
    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void GenreSearchForDrama() throws Exception {
		this.mockMvc.perform(get("/title/toprated/genre/Drama")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$.[0].genres", containsString("Drama")))
        .andExpect(jsonPath("$.[0].tconst", equalTo("tt0119896")))
        .andExpect(jsonPath("$.[0].primaryTitle", equalTo("Picture Perfect")))
        .andDo(document("toprated",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void GenreSearchForDramaIgnoreCase() throws Exception {
		this.mockMvc.perform(get("/title/toprated/genre/drama")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$.[0].genres", containsString("Drama")))
        .andExpect(jsonPath("$.[0].tconst", equalTo("tt0119896")))
        .andExpect(jsonPath("$.[0].primaryTitle", equalTo("Picture Perfect")))
        .andDo(document("topratedIgnorecase",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void GenreSearchForPartialWord() throws Exception {
		this.mockMvc.perform(get("/title/toprated/genre/ama")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$.[0].genres", containsString("Drama")))
        .andExpect(jsonPath("$.[0].tconst", equalTo("tt0119896")))
        .andExpect(jsonPath("$.[0].primaryTitle", equalTo("Picture Perfect")))
        .andDo(document("topratedPartial",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void GenreSearchForAction() throws Exception {
		this.mockMvc.perform(get("/title/toprated/genre/Action")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$.[0].genres", containsString("Action")))
        .andExpect(jsonPath("$.[0].tconst", equalTo("tt0129167")))
        .andExpect(jsonPath("$.[0].primaryTitle", equalTo("The Iron Giant")))
        .andDo(document("topratedAction",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void GenreSearchForNotExisting() throws Exception {
		this.mockMvc.perform(get("/title/toprated/genre/NOTEXISTING")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(0)))
        .andDo(document("topratedNotExisting",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {})
	void GenreSearchForDramaWithNoRole() throws Exception {
		this.mockMvc.perform(get("/title/toprated/genre/Drama")).andDo(print())
            .andExpect(status().is4xxClientError())
            .andDo(document("topratedWithoutRole"));

	}

    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void NameSearchForIron() throws Exception {
		this.mockMvc.perform(get("/title/name/Iron")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$.[0].titleType",  equalTo("movie")))
        .andExpect(jsonPath("$.[0].tconst", equalTo("tt0129167")))
        .andExpect(jsonPath("$.[0].primaryTitle", equalTo("The Iron Giant")))
        .andDo(document("namesearchIron",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {"TITLE"})
	void NameSearchForPartialPerfect() throws Exception {
		this.mockMvc.perform(get("/title/name/erfec")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$.[0].tconst", equalTo("tt0119896")))
        .andExpect(jsonPath("$.[0].primaryTitle", equalTo("Picture Perfect")))
        .andDo(document("namesearchPartial",preprocessRequest(modifyHeaders().remove("Host").add("Role", "TITLE")), preprocessResponse(prettyPrint())));
	}

    @Test
    @WithMockUser(username = "user",roles = {})
	void NameSearchForIronWithNoRole() throws Exception {
		this.mockMvc.perform(get("/title/name/Iron")).andDo(print())
            .andExpect(status().is4xxClientError())
            .andDo(document("namesearchWithoutRole"));
            
	}

}
