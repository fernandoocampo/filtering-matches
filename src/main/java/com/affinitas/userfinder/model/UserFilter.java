/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

/**
 * Contains filters which will be used for user search.
 * 
 * @author Fernando Ocampo
 */
public class UserFilter {
    /**
     * Filter to indicate if the user has a photo.
     */
    private Boolean hasPhoto;
    /**
     * Filter to indicate if the user is in contact.
     */
    private Boolean inContact;
    /**
     * Filter to indicate if the user is a favourite one.
     */
    private Boolean isFavourite;
    /**
     * Filter to indicate the minimum level required.
     */
    private Byte minCompabilityScore;
    /**
     * Filter to indicate the maximum level of compatability required.
     */
    private Byte maxCompabilityScore;
    /**
     * Filter to indicate the minimum age the client are looking for.
     */
    private Byte minAge;
    /**
     * Filter to indicate the maximum age the client are looking for.
     */
    private Byte maxAge;
    /**
     * Filter to indicate the minimum height the client are looking for.
     */
    private Short minHeight;
    /**
     * Filter to indicate the maximum height the client are looking for.
     */
    private Short maxHeight;
    /**
     * Filter to indicate the distance in km where the matching users are.
     */
    private Short distance;

    public Boolean getHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(Boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public Boolean getInContact() {
        return inContact;
    }

    public void setInContact(Boolean inContact) {
        this.inContact = inContact;
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Byte getMinCompabilityScore() {
        return minCompabilityScore;
    }

    public void setMinCompabilityScore(Byte minCompabilityScore) {
        this.minCompabilityScore = minCompabilityScore;
    }

    public Byte getMaxCompabilityScore() {
        return maxCompabilityScore;
    }

    public void setMaxCompabilityScore(Byte maxCompabilityScore) {
        this.maxCompabilityScore = maxCompabilityScore;
    }

    public Byte getMinAge() {
        return minAge;
    }

    public void setMinAge(Byte minAge) {
        this.minAge = minAge;
    }

    public Byte getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Byte maxAge) {
        this.maxAge = maxAge;
    }

    public Short getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Short minHeight) {
        this.minHeight = minHeight;
    }

    public Short getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Short maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Short getDistance() {
        return distance;
    }

    public void setDistance(Short distance) {
        this.distance = distance;
    }
}
