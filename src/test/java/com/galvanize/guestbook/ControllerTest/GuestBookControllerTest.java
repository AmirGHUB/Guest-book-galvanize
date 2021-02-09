package com.galvanize.guestbook.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.Model.GuestBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GuestBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllEntries() throws Exception {

        mockMvc
                .perform(get("/api/visitors"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addEntry() throws Exception {

        GuestBook guest1 = new GuestBook("sam","very nice");


        mockMvc
                .perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(guest1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("sam"))
                .andExpect(jsonPath("comment").value("very nice"))
                .andReturn();
    }
}
