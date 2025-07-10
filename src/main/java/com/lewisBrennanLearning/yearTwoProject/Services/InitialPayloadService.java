package com.lewisBrennanLearning.yearTwoProject.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InitialPayloadService {

    private final InitialPayloadRepository initialPayloadRepository;
    private final ObjectMapper objectMapperJSON;

    public InitialPayloadService(InitialPayloadRepository initialPayloadRepository) {
        this.initialPayloadRepository = initialPayloadRepository;
        this.objectMapperJSON = new ObjectMapper();
    }

    public InitialPayload saveInitialPayload(Map<String, Object> initialPayloadMap) throws JsonProcessingException {
        String jsonAsString = objectMapperJSON.writeValueAsString(initialPayloadMap);
        InitialPayload initialPayload = new InitialPayload();
        initialPayload.setInitialPayloadJsonString(jsonAsString);
        return initialPayloadRepository.save(initialPayload);
    }
}
