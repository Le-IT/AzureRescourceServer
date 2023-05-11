package com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.domain;// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;


import java.util.Date;
import java.util.List;

/**
 * User object model
 */
@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class AzrUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String azrId;

    @ElementCollection
    private List<String> businessPhones;

    public String getAzrId() {
        return azrId;
    }

    public void setAzrId(String azrId) {
        this.azrId = azrId;
    }

    @ElementCollection
    private List<String> schichten;


    private String displayName;

    private String givenName;


    private String jobTitle;

    private String mail;

    private String mobilePhone;

    private String officeLocation;

    private String preferredLanguage;

    private String surname;

    private String userPrincipalName;

    private String presence;


    private Date birthday;

    public List<String> getBusinessPhones() {
        return businessPhones;
    }

    public void setBusinessPhones(List<String> businessPhones) {
        this.businessPhones = businessPhones;
    }


    public List<String> getSchichten() {
        return schichten;
    }

    public void setSchichten(List<String> schichten) {
        this.schichten = schichten;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

    public String getGivenName() {
        return givenName;
    }


    public String toConcatedString() {
        return "User{" +
                "presence=" + presence + '\'' +
                "businessPhones=" + businessPhones + '\'' +
                ", displayName='" + displayName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", id='" + azrId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", mail='" + mail + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", officeLocation='" + officeLocation + '\'' +
                ", preferredLanguage='" + preferredLanguage + '\'' +
                ", surname='" + surname + '\'' +
                ", userPrincipalName='" + userPrincipalName + '\'' +
                '}';
    }

    public void setup(String azrid, List<String> businessPhones, String displayName, String givenName, String jobTitle, String mail, String mobilePhone, String officeLocation, String preferredLanguage, String surname, String userPrincipalName, String presence) {
        this.azrId = azrid;
        this.businessPhones = businessPhones;
        this.displayName = displayName;
        this.givenName = givenName;
        this.jobTitle = jobTitle;
        this.mail = mail;
        this.mobilePhone = mobilePhone;
        this.officeLocation = officeLocation;
        this.preferredLanguage = preferredLanguage;
        this.surname = surname;
        this.userPrincipalName = userPrincipalName;
        this.presence = presence;
    }
}