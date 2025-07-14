package com.lewisBrennanLearning.yearTwoProject.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.Helper.HelperFunction;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayloadService {

    private final InitialPayloadRepository initialPayloadRepository;
    private HelperFunction helperFunction;
    private final ObjectMapper objectMapperJSON;

    public PayloadService(InitialPayloadRepository initialPayloadRepository, HelperFunction helperFunction) {
        this.initialPayloadRepository = initialPayloadRepository;
        this.helperFunction = helperFunction;
        this.objectMapperJSON = new ObjectMapper();
    }

    public InitialPayload saveInitialPayload(Map<String, Object> initialPayloadMap) throws JsonProcessingException {
        String jsonAsString = objectMapperJSON.writeValueAsString(initialPayloadMap);
        InitialPayload initialPayload = new InitialPayload();
        initialPayload.setInitialPayloadJsonString(jsonAsString);
        return initialPayloadRepository.save(initialPayload);
    }

    public Map<String, Object> parseMethodOne() throws JsonProcessingException {
        List<InitialPayload> allInitialPayloads = helperFunction.findAllInitialPayloads();
        InitialPayload firstInitialPayload = allInitialPayloads.get(0);
        Map<String, Object> reMappedFirstInitialPayload = helperFunction.reMapInitialPayloadStrings(firstInitialPayload);
        return reMappedFirstInitialPayload;
    }
}
