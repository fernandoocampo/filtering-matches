/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

/**
 * Contains user data.
 * 
 * @author Fernando Ocampo
 */
public class User {
    /**
     * Name to display in the portal.
     */
    private String displayname;
    /**
     * Age of the user.
     */
    private Byte age;
    /**
     * job name of the user.
     */
    private String jobtile;
    /**
     * Main photo of the user.
     */
    private String mainphoto;
    /**
     * Calculated compatability score.
     */
    private Float compatabilityScore;
    /**
     * Contacts that are exchanged between the user who is searching and the 
     * founded users.
     */
    private Short contactsExchanged;
    /**
     * The religion of the user.
     */
    private String religion;
    /**
     * City where is the user.
     */
    private City city;

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

    public String getJobtile() {
        return jobtile;
    }

    public void setJobtile(String jobtile) {
        this.jobtile = jobtile;
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
}
