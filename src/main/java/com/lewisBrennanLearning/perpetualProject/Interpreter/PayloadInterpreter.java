package com.lewisBrennanLearning.perpetualProject.Interpreter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.perpetualProject.DataTransferObject.PayloadDataTransferObject;
import com.lewisBrennanLearning.perpetualProject.Model.InitialPayload;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PayloadInterpreter {

    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapperJSON = new ObjectMapper();

    public PayloadInterpreter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<InitialPayload> findAllInitialPayloads() {
        return mongoTemplate.findAll(InitialPayload.class, "initial_payloads");
    }

    public InitialPayload selectRandomPayload() {
        List<InitialPayload> allInitialPayloads = findAllInitialPayloads();
        int allInitialPayloadsListSize = allInitialPayloads.size() - 1;
        int randomEntryInitialPayloadsList = ThreadLocalRandom.current().nextInt(allInitialPayloadsListSize);
        return allInitialPayloads.get(randomEntryInitialPayloadsList);
    }

    public Map<String,Object> reMapInitialPayloadString(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readValue(firstInitialPayload.getInitialPayloadJsonString(), new TypeReference<Map<String,Object>>() {});
    }

    public JsonNode jsonNodeInitialPayloadString(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readTree(firstInitialPayload.getInitialPayloadJsonString());
    }

    public PayloadDataTransferObject payloadDataTransferConversion (InitialPayload selectedPayload) throws JsonProcessingException {
        Map<String,Object> mappedInitialPayload = reMapInitialPayloadString(selectedPayload);
        List<Map<String,Object>> listedInitialPayloadResults = (List<Map<String,Object>>) mappedInitialPayload.get("results");
        Map<String,Object> mappedInitialPayloadResults = listedInitialPayloadResults.get(0);
        PayloadDataTransferObject payloadDataTransferObject = new PayloadDataTransferObject();
        return payloadDataTransferObject = objectMapperJSON.convertValue(mappedInitialPayloadResults, PayloadDataTransferObject.class);
    }

    public Optional<Map<String, Object>> getResultsMapFromInitialPayloadString(InitialPayload selectedPayload) throws JsonProcessingException {
        Map<String,Object> remappedInitialPayload = reMapInitialPayloadString(selectedPayload);
        List<Map<String,Object>> remappedInitialPayloadResults = (List<Map<String,Object>>) remappedInitialPayload.get("results");
        return remappedInitialPayloadResults.stream().findFirst();
    }

}
