/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.storage;

/**
 * Contains data for registers.
 * 
 * @author Fernando Ocampo
 */
public class Document {
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
     * the height of a user.
     */
    private Short height;
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
     * name of the city.
     */
    private String cityname;
    /**
     * latitude of the user position.
     */
    private Float lat;
    /**
     * longitude of the user position.
     */
    private Float lon;
    /**
     * if user is favourite.
     */
    private Boolean favourite;

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

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Boolean isFavourite() {
        boolean result = false;
        if (favourite == null) {
            result = favourite;
        }
        return result;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Document{" + "displayname=" + displayname + ", age=" + age + ", jobtile=" + jobtile + ", height=" + height + ", mainphoto=" + mainphoto + ", compatabilityScore=" + compatabilityScore + ", contactsExchanged=" + contactsExchanged + ", religion=" + religion + ", cityname=" + cityname + ", lat=" + lat + ", lon=" + lon + '}';
    }
}
