package com.lewisBrennanLearning.perpetualProject.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lewisBrennanLearning.perpetualProject.Model.ParsedPayload;
import com.lewisBrennanLearning.perpetualProject.Services.PayloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@RestController
public class ProjectController {

    private final RestTemplate restTemplateLocalDeclaration;
    private final PayloadService payloadService;

    public ProjectController(RestTemplate restTemplateFromConfig, PayloadService payloadService) {
        this.restTemplateLocalDeclaration = restTemplateFromConfig;
        this.payloadService = payloadService;
    }

    @Value("${project.randomDataApi.get}")
    private String randomDataApiGet;

    @PostMapping("/createAndWriteInitialData")
    @SuppressWarnings({"unchecked"})
    public Map<String,Object> generateAndWriteRandomUser() throws JsonProcessingException {
        Map<String,Object> responseMap = restTemplateLocalDeclaration.getForObject(randomDataApiGet, Map.class);
        payloadService.saveInitialPayload(responseMap);
        return responseMap;
    }

    @PostMapping("/parseInitialDataMethodOneJsonNode")
    public ParsedPayload parseMethodOneJsonNode() throws JsonProcessingException {
        return payloadService.parseMethodOneJsonNode();
    }

    @PostMapping("/parseInitialDataMethodTwoDtoPojo")
    public ParsedPayload parseMethodTwoDtoPojo() throws JsonProcessingException {
        return payloadService.parseMethodTwoDtoPojo();
    }

    @PostMapping("/parseInitialDataMethodThreeStream")
    public ParsedPayload parseMethodThreeStream() throws IOException {
        return payloadService.parseMethodThreeStream();
    }

}
