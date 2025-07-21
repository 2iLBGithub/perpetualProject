package com.lewisBrennanLearning.yearTwoProject.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.DataTransferObject.PayloadDataTransferObject;
import com.lewisBrennanLearning.yearTwoProject.Helper.HelperFunction;
import com.lewisBrennanLearning.yearTwoProject.Interpreter.PayloadInterpreter;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Model.ParsedPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import com.lewisBrennanLearning.yearTwoProject.Repository.ParsedPayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayloadService {

    private final InitialPayloadRepository initialPayloadRepository;
    private final ParsedPayloadRepository parsedPayloadRepository;
    private final PayloadInterpreter payloadInterpreter;
    @Autowired
    private final ObjectMapper objectMapperJSON;

    public PayloadService(InitialPayloadRepository initialPayloadRepository, ParsedPayloadRepository parsedPayloadRepository, PayloadInterpreter payloadInterpreter) {
        this.initialPayloadRepository = initialPayloadRepository;
        this.parsedPayloadRepository = parsedPayloadRepository;
        this.payloadInterpreter = payloadInterpreter;
        this.objectMapperJSON = new ObjectMapper();
    }

    public InitialPayload saveInitialPayload(Map<String, Object> initialPayloadMap) throws JsonProcessingException {
        String jsonAsString = objectMapperJSON.writeValueAsString(initialPayloadMap);
        InitialPayload initialPayload = new InitialPayload();
        initialPayload.setInitialPayloadJsonString(jsonAsString);
        return initialPayloadRepository.save(initialPayload);
    }

//    JsonNode
    public ParsedPayload parseMethodOne() throws JsonProcessingException {
        List<InitialPayload> allInitialPayloads = payloadInterpreter.findAllInitialPayloads();
        InitialPayload selectedPayload = allInitialPayloads.get(0);
        JsonNode jsonNodeSelectedPayload = payloadInterpreter.jsonNodeInitialPayloadString(selectedPayload);
        ParsedPayload parsedPayload = new ParsedPayload();
        parsedPayload = parsedPayload.assignValuesJsonNode(jsonNodeSelectedPayload);
        return parsedPayloadRepository.save(parsedPayload);
    }

//    POJO - DTO
    public ParsedPayload parseMethodTwo() throws JsonProcessingException {
        InitialPayload selectedPayload = payloadInterpreter.selectFirstPayload();
        PayloadDataTransferObject payloadDataTransferObject = payloadInterpreter.payloadDataTransferConversion(selectedPayload);
        ParsedPayload parsedPayload = new ParsedPayload();
        parsedPayload = parsedPayload.assignValuesDTO(payloadDataTransferObject);
        return parsedPayloadRepository.save(parsedPayload);
    }
}
