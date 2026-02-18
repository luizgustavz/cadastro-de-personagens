package com.luizgustavz.cadastrodepersonagens.integration.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizgustavz.cadastrodepersonagens.application.controller.PersonController;
import com.luizgustavz.cadastrodepersonagens.application.dto.request.PersonRequest;
import com.luizgustavz.cadastrodepersonagens.application.mapper.PersonMapper;
import com.luizgustavz.cadastrodepersonagens.domain.entities.Person;
import com.luizgustavz.cadastrodepersonagens.domain.enums.Rank;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.PersonServicesImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@Import(PersonMapper.class)
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonServicesImpl services;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deve_retornar_status_CREATED() throws Exception {

        Person saved = new Person();
        saved.assignName("kakarott");
        saved.assignUrl("https://imgs.search.brave.com/o7oPkZ9g_u432qZpsPCGjsNvNZ_4Qr2H3azJYPkZQ2U/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/");
        saved.assignRank(Rank.A);
        saved.assignAge(48);

        UUID id = UUID.randomUUID();
        ReflectionTestUtils.setField(saved, "id", id);

        when(services.createEntity(any(Person.class))).thenReturn(saved);

        PersonRequest request = new PersonRequest(
                "kakarott",
                "https://imgs.search.brave.com/o7oPkZ9g_u432qZpsPCGjsNvNZ_4Qr2H3azJYPkZQ2U/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/",
                Rank.A,
                48
        );

        mockMvc
                .perform(post("/api/v1/person")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value(saved.getName()))
                .andExpect(jsonPath("$.imageUrl").value(saved.getImageUrl()))
                .andExpect(jsonPath("$.rank").value(saved.getRank().toString()))
                .andExpect(jsonPath("$.age").value(saved.getAge()))
                .andDo(print());
    }



}
