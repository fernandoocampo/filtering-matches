/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

/**
 * Value Object that contains search parameters values.
 * 
 * @author Fernando.Ocampo
 */
public class SearchVO {
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
    private Integer minCompabilityScore;
    /**
     * Filter to indicate the maximum level of compatability required.
     */
    private Integer maxCompabilityScore;
    /**
     * Filter to indicate the minimum age the client are looking for.
     */
    private Integer minAge;
    /**
     * Filter to indicate the maximum age the client are looking for.
     */
    private Integer maxAge;
    /**
     * Filter to indicate the minimum height the client are looking for.
     */
    private Integer minHeight;
    /**
     * Filter to indicate the maximum height the client are looking for.
     */
    private Integer maxHeight;
    /**
     * Filter to indicate the distance in km where the matching users are.
     */
    private Integer distance;
    /**
     * Filter to indicate if the user is a favourite one.
     */
    private Boolean Favourite;

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

    public Integer getMinCompabilityScore() {
        return minCompabilityScore;
    }

    public void setMinCompabilityScore(Integer minCompabilityScore) {
        this.minCompabilityScore = minCompabilityScore;
    }

    public Integer getMaxCompabilityScore() {
        return maxCompabilityScore;
    }

    public void setMaxCompabilityScore(Integer maxCompabilityScore) {
        this.maxCompabilityScore = maxCompabilityScore;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Boolean getFavourite() {
        return Favourite;
    }

    public void setFavourite(Boolean Favourite) {
        this.Favourite = Favourite;
    }
}
