package com.example.unit.tests;

import com.example.unit.tests.models.Person;
import com.example.unit.tests.services.PeopleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeopleService peopleService;

    @Test
    public void createNewPerson() throws Exception {
        Person mockPerson = new Person((long) 1, "Fulanao", 28);

        when(peopleService.create(any(Person.class))).thenReturn(mockPerson);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String mockPersonJson = ow.writeValueAsString(mockPerson);

        this.mockMvc.perform(post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockPersonJson))
                .andExpect(status().isOk()).andExpect(content().json(mockPersonJson));

        verify(peopleService).create(any(Person.class));
    }

    @Test
    public void findAll() throws Exception {
        Person fulano = new Person((long) 1, "Fulanao", 28);
        List<Person> mockPeople = Arrays.asList(fulano);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String mockPeopleJson = ow.writeValueAsString(mockPeople);

        when(peopleService.findAll()).thenReturn(mockPeople);

        this.mockMvc.perform(get("/people").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(mockPeopleJson));
    }

    @Test
    public void deletePerson() throws Exception {
       this.mockMvc.perform(delete("/people" + "/{id}", 1L))
                .andExpect(status().is(200));
    }

    @Test
    public void createNewPersonAndFail() throws Exception {
        Person fulano = new Person((long) 1, "", 28);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String mockPersonJson = ow.writeValueAsString(fulano);

        when(peopleService.create(any(Person.class))).thenReturn(fulano);
        this.mockMvc.perform(post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(mockPersonJson))
                .andExpect(status().isBadRequest());
    }


}
