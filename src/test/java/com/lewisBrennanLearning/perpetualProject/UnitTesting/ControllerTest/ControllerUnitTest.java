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
import java.util.UUID;

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
        stubPayload.setId(new ObjectId("507f1f77bcf86cd799439011"));
        stubPayload.setUuid(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        stubPayload.setFullName("TEST_NAME");
        stubPayload.setGender("TEST_GENDER");
        stubPayload.setEmail("TEST_EMAIL");
        stubPayload.setPictureUrl("TEST_PICTURE-URL");
        stubPayload.setDob("TEST_DOB");
        stubPayload.setNationality("TEST_NATIONALITY");

        when(payloadService.parseMethodOneJsonNode()).thenReturn(stubPayload);

        mockMvc.perform(post("/parseInitialDataMethodOneJsonNode"))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        mockMvc.perform(post("/parseInitialDataMethodOneJsonNode"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.id.timestamp").isNumber())
                .andExpect(jsonPath("$.id.date").isString())
                .andExpect(jsonPath("$.uuid").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.fullName").value("TEST_NAME"))
                .andExpect(jsonPath("$.gender").value("TEST_GENDER"))
                .andExpect(jsonPath("$.email").value("TEST_EMAIL"))
                .andExpect(jsonPath("$.pictureUrl").value("TEST_PICTURE-URL"))
                .andExpect(jsonPath("$.dob").value("TEST_DOB"))
                .andExpect(jsonPath("$.nationality").value("TEST_NATIONALITY"));

        verify(payloadService, times(1)).parseMethodOneJsonNode();
        verifyNoMoreInteractions(payloadService);
    }
}