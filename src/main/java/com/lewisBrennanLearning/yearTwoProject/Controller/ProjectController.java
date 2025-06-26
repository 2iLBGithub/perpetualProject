package com.lewisBrennanLearning.yearTwoProject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lewisBrennanLearning.yearTwoProject.Services.InitialPayloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ProjectController {

    private final RestTemplate restTemplateLocalDeclaration;
    private final InitialPayloadService initialPayloadService;
    public ProjectController(RestTemplate restTemplateFromConfig, InitialPayloadService initialPayloadService) {
        this.restTemplateLocalDeclaration = restTemplateFromConfig;
        this.initialPayloadService = initialPayloadService;
    }

    @Value("${project.randomDataApi.get}")
    private String randomDataApiGet;

    @GetMapping("/get")
    @SuppressWarnings({"unchecked"})
    public Map<String,Object> getRandomUser() throws JsonProcessingException {
        Map<String,Object> responseMap = restTemplateLocalDeclaration.getForObject(randomDataApiGet, Map.class);
        initialPayloadService.saveInitialPayload(responseMap);
        return responseMap;
    }
}
