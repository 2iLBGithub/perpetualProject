package com.lewisBrennanLearning.yearTwoProject.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "initial_payloads")
public class InitialPayload {

    @Id
    private ObjectId id;
    private String initialPayloadJsonString;

    public InitialPayload() {}

    public InitialPayload(ObjectId id, String initialPayloadJsonString) {
        this.id = id;
        this.initialPayloadJsonString = initialPayloadJsonString;
    }

    public ObjectId getId() {return id;}

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getInitialPayloadJsonString() {
        return initialPayloadJsonString;
    }

    public void setInitialPayloadJsonString(String initialPayloadJsonString) { this.initialPayloadJsonString = initialPayloadJsonString; }
}
