package com.lewisBrennanLearning.perpetualProject.UnitTesting.ControllerTest;

import com.lewisBrennanLearning.perpetualProject.Controller.ProjectController;
import com.lewisBrennanLearning.perpetualProject.Model.ParsedPayload;
import com.lewisBrennanLearning.perpetualProject.Services.PayloadService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Starts a minimal Spring MVC context
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
    void generateAndWriteInitialData_UnitTest() throws Exception {
        Map stubPayload = Map.of("key", "value");

        when(restTemplateLocalDeclaration.getForObject(anyString(), eq(Map.class)))
                .thenReturn(stubPayload);

        mockMvc.perform(post("/createAndWriteInitialData"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.key").value("value"));

        verify(payloadService).saveInitialPayload(eq(stubPayload));
    }

//    returns200_andDelegatesToService

    @Test
    void parseInitialDataMethodOneJsonNode_UnitTest() throws Exception {
        ParsedPayload stubPayload = new ParsedPayload();
        stubPayload.setId(new ObjectId("TEST_ID"));
        stubPayload.setFullName("TEST_NAME");

        when(payloadService.parseMethodOneJsonNode()).thenReturn(stubPayload);

        mockMvc.perform(post("/parseInitialDataMethodOneJsonNode"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.fullName").value("TEST_NAME"));

        verify(payloadService, times(1)).parseMethodOneJsonNode();
        verifyNoMoreInteractions(payloadService);
    }
}