package com.galvanize.guestbook.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.Model.GuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class GuestBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllEntries() throws Exception {

        GuestBook book = new GuestBook("Jon", "nice");
        mockMvc
                .perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());


        mockMvc
                .perform(get("/api/visitors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Jon"))
                .andExpect(jsonPath("[0].comment").value("nice"))
                .andDo(document("GuestBooks", responseFields(
                        fieldWithPath("[0].name").description("This is the name of the commenter"),
                        fieldWithPath("[0].comment").description("This is the comment in the Guest Book")
                )));
    }

    @Test
    public void addEntry() throws Exception {

        GuestBook guest1 = new GuestBook("Jon","good");


        mockMvc
                .perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(guest1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("Jon"))
                .andExpect(jsonPath("comment").value("good"))
                .andDo(document("AddGuestBook", responseFields(
                        fieldWithPath("name").description("This is the name of the commenter"),
                        fieldWithPath("comment").description("This is the comment in the Guest Book")
                )));
    }
}
