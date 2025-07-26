package com.lewisBrennanLearning.yearTwoProject.Services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.DataTransferObject.PayloadDataTransferObject;
import com.lewisBrennanLearning.yearTwoProject.Interpreter.PayloadInterpreter;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Model.ParsedPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PayloadService {

    private final MongoTemplate mongoTemplate;
    private final PayloadInterpreter payloadInterpreter;
    @Autowired
    private final ObjectMapper objectMapperJSON;

    public PayloadService(MongoTemplate mongoTemplate, PayloadInterpreter payloadInterpreter, ObjectMapper objectMapperJson) {
        this.mongoTemplate = mongoTemplate;
        this.payloadInterpreter = payloadInterpreter;
        this.objectMapperJSON = objectMapperJson;
    }

    public InitialPayload saveInitialPayload(Map<String, Object> initialPayloadMap) throws JsonProcessingException {
        String jsonAsString = objectMapperJSON.writeValueAsString(initialPayloadMap);
        InitialPayload initialPayload = new InitialPayload();
        initialPayload.setInitialPayloadJsonString(jsonAsString);
        return mongoTemplate.save(initialPayload, "initial_payloads");
    }

    //    JsonNode
    public ParsedPayload parseMethodOneJsonNode() throws JsonProcessingException {
        InitialPayload selectedPayload = payloadInterpreter.selectRandomPayload();
        JsonNode jsonNodeSelectedPayload = payloadInterpreter.jsonNodeInitialPayloadString(selectedPayload);
        ParsedPayload parsedPayload = new ParsedPayload();
        parsedPayload = parsedPayload.assignValuesJsonNode(jsonNodeSelectedPayload);
        return mongoTemplate.save(parsedPayload, "methodOneJsonNode_collection");
    }

    //    POJO - DTO
    public ParsedPayload parseMethodTwoDtoPojo() throws JsonProcessingException {
        InitialPayload selectedPayload = payloadInterpreter.selectRandomPayload();
        PayloadDataTransferObject payloadDataTransferObject = payloadInterpreter.payloadDataTransferConversion(selectedPayload);
        ParsedPayload parsedPayload = new ParsedPayload();
        parsedPayload = parsedPayload.assignValuesDTO(payloadDataTransferObject);
        return mongoTemplate.save(parsedPayload, "methodTwoPojoDto_collection");
    }

    //    Java Stream
    public ParsedPayload parseMethodThreeStream() throws IOException {
        InitialPayload selectedPayload = payloadInterpreter.selectRandomPayload();
        Optional<Map<String, Object>> mappedInitialPayload = payloadInterpreter.getResultsMapFromInitialPayloadString(selectedPayload);
        ParsedPayload parsedPayload = new ParsedPayload();
        parsedPayload = parsedPayload.assignValuesStream(mappedInitialPayload);
        return mongoTemplate.save(parsedPayload, "methodThreeStream_collection");
    }

}
