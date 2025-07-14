package com.lewisBrennanLearning.yearTwoProject.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HelperFunction {

    private final InitialPayloadRepository initialPayloadRepository;
    private final ObjectMapper objectMapperJSON = new ObjectMapper();

    public HelperFunction(InitialPayloadRepository initialPayloadRepository) {
        this.initialPayloadRepository = initialPayloadRepository;
    }

    public List<InitialPayload> findAllInitialPayloads() {
        return initialPayloadRepository.findAll();
    }

    public Map<String,Object> reMapInitialPayloadStrings(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readValue(firstInitialPayload.getInitialPayloadJsonString(), new TypeReference<Map<String,Object>>() {});
    }
}
