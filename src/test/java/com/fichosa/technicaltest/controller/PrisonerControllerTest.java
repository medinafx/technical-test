package com.fichosa.technicaltest.controller;

import com.fichosa.technicaltest.model.Stats;
import com.fichosa.technicaltest.repository.ScapeResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrisonerControllerTest.class)
class PrisonerControllerTest {

    private MockMvc mockMvc;
    private ScapeResultRepository repositoryMock;

    @BeforeEach
    public void setup() {
        this.repositoryMock = mock(ScapeResultRepository.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PrisonerController(this.repositoryMock)).build();
    }

    @Test
    void whenPrisonerCanScape_thenReturnHttpStatusCodeOk() throws Exception {
        String jsonContent = "{\"prison\": [\"|||||||S||\",\"|P ||   |\",\"|| " +
                " | | |\",\"|v| | < |\",\"| |   | |\",\"|   |   |\",\"|||||||||\"]}";

        RequestBuilder request = MockMvcRequestBuilders.post("/prisoner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenPrisonerCantScape_thenReturnHttpStatusCodeForbidden() throws Exception {
        String jsonContent = "{\"prison\": [\"||||||>S||\",\"|P ||   |\",\"|| " +
                " | | |\",\"|v| | < |\",\"| |   | |\",\"|   |   |\",\"|||||||||\"]}";

        RequestBuilder request = MockMvcRequestBuilders.post("/prisoner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent);

        this.mockMvc.perform(request)
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void whenPrisonIsNotProvided_thenReturnHttpStatusCodeBadRequest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/prisoner")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void givenSuccesIs40AndUnsuccesIs100_thenRatioIs4() throws Exception {

        when(repositoryMock.getStats()).thenReturn(new Stats(40, 100));

        RequestBuilder request = MockMvcRequestBuilders.get("/stats")
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(content().json("{\"count_successful_escape\": 40"
                        + ", \"count_unsuccessful_escape\": 100, \"ratio\": 0.4}"))
                .andReturn();
    }
}