package com.lewisBrennanLearning.yearTwoProject.Interpreter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PayloadInterpreter {

    private final InitialPayloadRepository initialPayloadRepository;
    private final ObjectMapper objectMapperJSON = new ObjectMapper();

    public PayloadInterpreter(InitialPayloadRepository initialPayloadRepository) {
        this.initialPayloadRepository = initialPayloadRepository;
    }

    public List<InitialPayload> findAllInitialPayloads() {
        return initialPayloadRepository.findAll();
    }


    //    Currently unused but useful for debug
    public Map<String,Object> reMapInitialPayloadString(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readValue(firstInitialPayload.getInitialPayloadJsonString(), new TypeReference<Map<String,Object>>() {});
    }

    public JsonNode jsonNodeInitialPayloadString(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readTree(firstInitialPayload.getInitialPayloadJsonString());
    }
}
