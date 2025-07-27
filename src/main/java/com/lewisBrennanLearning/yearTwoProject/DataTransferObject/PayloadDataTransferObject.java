package com.lewisBrennanLearning.yearTwoProject.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.UUID;

public class PayloadDataTransferObject {

    private String gender;
    private String title;
    private String firstName;
    private String lastName;
    private Integer streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String coordinatesLatitude;
    private String coordinatesLongitude;
    private String timezoneOffset;
    private String timezoneDescription;
    private String email;
    private UUID uuid;
    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;
    private String dobDate;
    private Integer dobAge;
    private String registeredDate;
    private Integer registeredAge;
    private String phone;
    private String cell;
    private String idName;
    private String idValue;
    private String pictureLarge;
    private String pictureMedium;
    private String pictureThumbnail;
    @JsonProperty("nat")
    private String nationality;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCoordinatesLatitude() {
        return coordinatesLatitude;
    }

    public void setCoordinatesLatitude(String coordinatesLatitude) {
        this.coordinatesLatitude = coordinatesLatitude;
    }

    public String getCoordinatesLongitude() {
        return coordinatesLongitude;
    }

    public void setCoordinatesLongitude(String coordinatesLongitude) { this.coordinatesLongitude = coordinatesLongitude; }

    public String getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(String timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public String getTimezoneDescription() {
        return timezoneDescription;
    }

    public void setTimezoneDescription(String timezoneDescription) {
        this.timezoneDescription = timezoneDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getDobDate() {
        return dobDate;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
    }

    public Integer getDobAge() {
        return dobAge;
    }

    public void setDobAge(Integer dobAge) {
        this.dobAge = dobAge;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Integer getRegisteredAge() {
        return registeredAge;
    }

    public void setRegisteredAge(Integer registeredAge) {
        this.registeredAge = registeredAge;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public String getPictureLarge() {
        return pictureLarge;
    }

    public void setPictureLarge(String pictureLarge) {
        this.pictureLarge = pictureLarge;
    }

    public String getPictureMedium() {
        return pictureMedium;
    }

    public void setPictureMedium(String pictureMedium) {
        this.pictureMedium = pictureMedium;
    }

    public String getPictureThumbnail() {
        return pictureThumbnail;
    }

    public void setPictureThumbnail(String pictureThumbnail) {
        this.pictureThumbnail = pictureThumbnail;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

//    Logic

    @JsonProperty("name")
    private void unpackName(Map<String,String> name){
        setTitle(name.get("title"));
        setFirstName(name.get("first"));
        setLastName(name.get("last"));
    }

    @JsonProperty("location")
    @SuppressWarnings("unchecked")
    private void unpackLocation(Map<String,Object> location) {
        Map<String,Object> street = (Map<String,Object>)location.get("street");
        setStreetName((String)street.get("name"));
        setStreetNumber((Integer) street.get("number"));
        setCity((String)location.get("city"));
        setState((String)location.get("state"));
        setCountry((String)location.get("country"));
        setPostcode(location.get("postcode").toString());
        Map<String,Object> coordinates = (Map<String,Object>)location.get("coordinates");
        setCoordinatesLatitude((String)coordinates.get("latitude"));
        setCoordinatesLongitude((String)coordinates.get("longitude"));
        Map<String,Object> timezone = (Map<String,Object>)location.get("timezone");
        setTimezoneOffset((String)timezone.get("offset"));
        setTimezoneDescription((String)timezone.get("description"));
    }

    @JsonProperty("login")
    private void unpackLogin(Map<String,String> login) {
        setUuid(UUID.fromString(login.get("uuid")));
        setUsername(login.get("username"));
        setPassword(login.get("password"));
        setSalt(login.get("salt"));
        setMd5(login.get("md5"));
        setSha1(login.get("sha1"));
        setSha256(login.get("sha256"));
    }

    @JsonProperty("dob")
    private void unpackDob(Map<String,Object> dob) {
        setDobDate((String)dob.get("date"));
        setDobAge((Integer)dob.get("age"));
    }

    @JsonProperty("registered")
    private void unpackRegistered(Map<String,Object> registered) {
        setRegisteredDate((String)registered.get("date"));
        setRegisteredAge((Integer) registered.get("age"));
    }

    @JsonProperty("id")
    private void unpackId(Map<String,Object> id) {
        setIdName((String)id.get("name"));
        setIdValue((String)id.get("value"));
    }

    @JsonProperty("picture")
    private void unpackPicture(Map<String,Object> picture) {
        setPictureLarge((String)picture.get("large"));
        setPictureMedium((String)picture.get("medium"));
        setPictureThumbnail((String)picture.get("thumbnail"));
    }

}
