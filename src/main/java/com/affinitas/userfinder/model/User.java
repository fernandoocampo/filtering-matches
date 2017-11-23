/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Contains user data.
 * 
 * @author Fernando Ocampo
 */
@Document(collection = "users")
public class User {
    @Id
    private String id;
    /**
     * Name to display in the portal.
     */
    @Field("display_name")
    private String displayname;
    /**
     * Age of the user.
     */
    private Byte age;
    /**
     * HEight of the user.
     */
    @Field("height_in_cm")
    private Short height;
    /**
     * job name of the user.
     */
    @Field("job_title")
    private String jobtitle;
    /**
     * Main photo of the user.
     */
    @Field("main_photo")
    private String mainphoto;
    /**
     * Calculated compatability score.
     */
    @Field("compatibility_score")
    private Float compatabilityScore;
    /**
     * Contacts that are exchanged between the user who is searching and the 
     * founded users.
     */
    @Field("contacts_exchanged")
    private Short contactsExchanged;
    /**
     * The religion of the user.
     */
    private String religion;
    /**
     * City where is the user.
     */
    private City city;
    /**
     * if user is favourite.
     */
    private Boolean favourite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getMainphoto() {
        return mainphoto;
    }

    public void setMainphoto(String mainphoto) {
        this.mainphoto = mainphoto;
    }

    public Float getCompatabilityScore() {
        return compatabilityScore;
    }

    public void setCompatabilityScore(Float compatabilityScore) {
        this.compatabilityScore = compatabilityScore;
    }

    public Short getContactsExchanged() {
        return contactsExchanged;
    }

    public void setContactsExchanged(Short contactsExchanged) {
        this.contactsExchanged = contactsExchanged;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", displayname=" + displayname + ", age=" + age + ", jobtile=" + jobtitle + ", mainphoto=" + mainphoto + ", compatabilityScore=" + compatabilityScore + ", contactsExchanged=" + contactsExchanged + ", religion=" + religion + ", city=" + city + ", favourite=" + favourite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.displayname, other.displayname)) {
            return false;
        }
        if (!Objects.equals(this.jobtitle, other.jobtitle)) {
            return false;
        }
        if (!Objects.equals(this.mainphoto, other.mainphoto)) {
            return false;
        }
        if (!Objects.equals(this.religion, other.religion)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        if (!Objects.equals(this.compatabilityScore, other.compatabilityScore)) {
            return false;
        }
        if (!Objects.equals(this.contactsExchanged, other.contactsExchanged)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.favourite, other.favourite)) {
            return false;
        }
        return true;
    }
}
