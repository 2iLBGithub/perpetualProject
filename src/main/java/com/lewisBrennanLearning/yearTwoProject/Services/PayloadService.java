package com.lewisBrennanLearning.yearTwoProject.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.Helper.HelperFunction;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Model.ParsedPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import com.lewisBrennanLearning.yearTwoProject.Repository.ParsedPayloadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayloadService {

    private final InitialPayloadRepository initialPayloadRepository;
    private final ParsedPayloadRepository parsedPayloadRepository;
    private HelperFunction helperFunction;
    private final ObjectMapper objectMapperJSON;

    public PayloadService(InitialPayloadRepository initialPayloadRepository, ParsedPayloadRepository parsedPayloadRepository, HelperFunction helperFunction) {
        this.initialPayloadRepository = initialPayloadRepository;
        this.parsedPayloadRepository = parsedPayloadRepository;
        this.helperFunction = helperFunction;
        this.objectMapperJSON = new ObjectMapper();
    }

    public InitialPayload saveInitialPayload(Map<String, Object> initialPayloadMap) throws JsonProcessingException {
        String jsonAsString = objectMapperJSON.writeValueAsString(initialPayloadMap);
        InitialPayload initialPayload = new InitialPayload();
        initialPayload.setInitialPayloadJsonString(jsonAsString);
        return initialPayloadRepository.save(initialPayload);
    }

    public ParsedPayload parseMethodOne() throws JsonProcessingException {
        List<InitialPayload> allInitialPayloads = helperFunction.findAllInitialPayloads();
        InitialPayload selectedPayload = allInitialPayloads.get(0);
        JsonNode jsonNodeSelectedPayload = helperFunction.jsonNodeInitialPayloadString(selectedPayload);
        ParsedPayload parsedPayload = helperFunction.assignValues(jsonNodeSelectedPayload);
        return parsedPayloadRepository.save(parsedPayload);
    }
}
