package com.lewisBrennanLearning.yearTwoProject.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.lewisBrennanLearning.yearTwoProject.DataTransferObject.PayloadDataTransferObject;
import com.lewisBrennanLearning.yearTwoProject.Helper.HelperFunction;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

public class ParsedPayload {

    @Id
    private ObjectId id;
    private UUID uuid;
    private String fullName;
    private String gender;
    private String email;
    private String pictureUrl;
    private String dob;
    private String nationality;

    public ParsedPayload() {}

    public ParsedPayload(ObjectId id, UUID uuid, String fullName, String gender, String email, String pictureUrl, String dob, String nationality) {
        this.id = id;
        this.uuid = uuid;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.dob = dob;
        this.nationality = nationality;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

//    Method One Functions

    public ParsedPayload assignValuesJsonNode(JsonNode jsonNodeSelectedPayload) {
        ParsedPayload assignedPayload = new ParsedPayload();
        HelperFunction helperFunction = new HelperFunction();
        assignedPayload.setUuid(UUID.fromString(jsonNodeSelectedPayload.path("results").get(0).path("login").get("uuid").asText()));
        assignedPayload.setFullName(createFullNameJsonNode(jsonNodeSelectedPayload));
        assignedPayload.setGender(jsonNodeSelectedPayload.path("results").get(0).get("gender").asText());
        assignedPayload.setEmail(jsonNodeSelectedPayload.path("results").get(0).get("email").asText());
        assignedPayload.setEmail(jsonNodeSelectedPayload.path("results").get(0).get("email").asText());
        assignedPayload.setPictureUrl(jsonNodeSelectedPayload.path("results").get(0).get("picture").get("large").asText());
        assignedPayload.setDob(jsonNodeSelectedPayload.path("results").get(0).get("dob").get("date").asText().substring(0,10));
        String isoCode = jsonNodeSelectedPayload.path("results").get(0).get("nat").asText();
        assignedPayload.setNationality(helperFunction.translateIsoCode(isoCode));
        return assignedPayload;
    }

    public String createFullNameJsonNode(JsonNode jsonNodeSelectedPayload) {
        JsonNode nameNode = jsonNodeSelectedPayload.path("results").get(0).path("name");
        String title = nameNode.path("title").asText();
        String first = nameNode.path("first").asText();
        String last  = nameNode.path("last").asText();
        return String.join(" ", title, first, last);
    }

//    Method Two functions
    public ParsedPayload assignValuesDTO(PayloadDataTransferObject payloadDataTransferObject) {
        ParsedPayload assignedPayload = new ParsedPayload();
        HelperFunction helperFunction = new HelperFunction();
        assignedPayload.setUuid((payloadDataTransferObject.getUuid()));
        assignedPayload.setFullName(createFullNameDTO(payloadDataTransferObject));
        assignedPayload.setGender(payloadDataTransferObject.getGender());
        assignedPayload.setEmail(payloadDataTransferObject.getEmail());
        assignedPayload.setPictureUrl(payloadDataTransferObject.getPictureLarge());
        assignedPayload.setDob(payloadDataTransferObject.getDobDate().substring(0,10));
        String isoCode = payloadDataTransferObject.getNationality();
        assignedPayload.setNationality(helperFunction.translateIsoCode(isoCode));
        return assignedPayload;
    }

    public String createFullNameDTO(PayloadDataTransferObject payloadDataTransferObject) {
        String title = payloadDataTransferObject.getTitle();
        String first = payloadDataTransferObject.getFirstName();
        String last = payloadDataTransferObject.getLastName();
        return String.join("", title, first, last);
    }

//    Method Three Functions
//    Saved space for Get name via streaming model

}
