package com.lewisBrennanLearning.yearTwoProject.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import com.lewisBrennanLearning.yearTwoProject.Model.ParsedPayload;
import com.lewisBrennanLearning.yearTwoProject.Repository.InitialPayloadRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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

//    Currently unused but useful for debug
    public Map<String,Object> reMapInitialPayloadString(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readValue(firstInitialPayload.getInitialPayloadJsonString(), new TypeReference<Map<String,Object>>() {});
    }

    public JsonNode jsonNodeInitialPayloadString(InitialPayload firstInitialPayload) throws JsonProcessingException {
        return objectMapperJSON.readTree(firstInitialPayload.getInitialPayloadJsonString());
    }

    public ParsedPayload assignValues(JsonNode jsonNodeSelectedPayload) {
        ParsedPayload assignedPayload = new ParsedPayload();
        assignedPayload.setUuid(UUID.fromString(jsonNodeSelectedPayload.path("results").get(0).path("login").get("uuid").asText()));
        assignedPayload.setFullName(createFullName(jsonNodeSelectedPayload));
        assignedPayload.setGender(jsonNodeSelectedPayload.path("results").get(0).get("gender").asText());
        assignedPayload.setEmail(jsonNodeSelectedPayload.path("results").get(0).get("email").asText());
        assignedPayload.setEmail(jsonNodeSelectedPayload.path("results").get(0).get("email").asText());
        assignedPayload.setPictureUrl(jsonNodeSelectedPayload.path("results").get(0).get("picture").get("large").asText());
        assignedPayload.setDob(jsonNodeSelectedPayload.path("results").get(0).get("dob").get("date").asText().substring(0,10));
        assignedPayload.setNationality(translateIsoCode(jsonNodeSelectedPayload));
        return assignedPayload;
    }

    public String createFullName(JsonNode jsonNodeSelectedPayload) {
        JsonNode nameNode = jsonNodeSelectedPayload.path("results").get(0).path("name");
        String title = nameNode.path("title").asText();
        String first = nameNode.path("first").asText();
        String last  = nameNode.path("last").asText();
        return String.join(" ", title, first, last);
    }

//    Saved space for Get name via streaming model

    public String translateIsoCode(JsonNode jsonNodeSelectedPayload) {
        String isoCode = jsonNodeSelectedPayload.path("results").get(0).get("nat").asText();
        Locale locale = new Locale("", isoCode);
        return locale.getDisplayCountry(Locale.ENGLISH);
    }

}
