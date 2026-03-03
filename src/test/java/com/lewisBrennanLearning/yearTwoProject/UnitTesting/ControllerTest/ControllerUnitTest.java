package com.lewisBrennanLearning.yearTwoProject.UnitTesting.ControllerTest;

import com.lewisBrennanLearning.yearTwoProject.Controller.ProjectController;
import com.lewisBrennanLearning.yearTwoProject.Services.PayloadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@TestPropertySource(properties = {
        "project.randomDataApi.get=http://example.test/random"
})
public class ControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestTemplate restTemplateLocalDeclaration;

    @MockitoBean
    private PayloadService payloadService;

//    GenerateAndWriteInitialDataTest
//    Purpose - Checks endpoint exists and wired correctly
//    Checks it returns 200 and JSON payload and calls repository to save

    @Test
    void generateAndWriteInitialDataTest() throws Exception {
        Map stubPayload = Map.of("key", "value");

        when(restTemplateLocalDeclaration.getForObject(anyString(), eq(Map.class)))
                .thenReturn(stubPayload);

        mockMvc.perform(post("/createAndWriteInitialData"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.key").value("value"));

        verify(payloadService).saveInitialPayload(eq(stubPayload));
    }
}