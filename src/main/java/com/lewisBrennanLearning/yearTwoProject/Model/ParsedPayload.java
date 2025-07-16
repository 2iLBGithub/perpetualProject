package com.lewisBrennanLearning.yearTwoProject.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "parsed_payloads")
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
}
