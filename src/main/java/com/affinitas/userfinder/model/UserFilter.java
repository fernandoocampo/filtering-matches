/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains filters which will be used for user search.
 *
 * @author Fernando Ocampo
 */
public class UserFilter {

    /**
     * Types of filters supported for this service.
     */
    public static enum FilterKey {
        HAS_PHOTO,
        IN_CONTACT,
        FAVOURITE,
        COMPABILITY_SCORE,
        AGE,
        HEIGHT,
        DISTANCE
    }

    /**
     * Contains the selected filters which are ranges.
     */
    private final Map<FilterKey, Range> rangesFilters;
    /**
     * Contains the selected filters which are booleans.
     */
    private final Map<FilterKey, Boolean> booleansFilters;
    /**
     * FilterKey to indicate if the user has a photo.
     */
    private Boolean hasPhoto;
    /**
     * FilterKey to indicate if the user is in contact.
     */
    private Boolean inContact;
    /**
     * FilterKey to indicate if the user is a favourite one.
     */
    private Boolean isFavourite;
    /**
     * FilterKey to indicate the minimum and maximum level of compability
     * required.
     */
    private Range compabilityScore;
    /**
     * FilterKey to indicate the minimum and maximun age the client are looking
     * for.
     */
    private Range age;
    /**
     * FilterKey to indicate the minimum and maximum height the client are
     * looking for.
     */
    private Range height;
    /**
     * FilterKey to indicate the distance in km where the matching users are.
     */
    private Integer distance;

    public UserFilter() {
        this.rangesFilters = new HashMap<>();
        this.booleansFilters = new HashMap<>();
        this.compabilityScore = new Range();
        this.age = new Range();
        this.height = new Range();
    }

    public Boolean getHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(Boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
        if (hasPhoto != null) {
            this.booleansFilters.put(FilterKey.HAS_PHOTO, this.hasPhoto);
        }
    }

    public Boolean getInContact() {
        return inContact;
    }

    public void setInContact(Boolean inContact) {
        this.inContact = inContact;
        if (inContact != null) {
            this.booleansFilters.put(FilterKey.IN_CONTACT, this.inContact);
        }
    }

    public Boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Boolean isFavourite) {
        this.isFavourite = isFavourite;
        if (isFavourite != null) {
            this.booleansFilters.put(FilterKey.FAVOURITE, this.isFavourite);
        }
    }

    public Range getCompabilityScore() {
        return compabilityScore;
    }

    public void setCompabilityScore(Range compabilityScore) {
        this.compabilityScore = compabilityScore;
        if (compabilityScore != null) {
            this.rangesFilters.put(FilterKey.COMPABILITY_SCORE, compabilityScore);
        }
    }

    public Range getAge() {
        return age;
    }

    public void setAge(Range age) {
        this.age = age;
        if (age != null) {
            this.rangesFilters.put(FilterKey.AGE, age);
        }
    }

    public Range getHeight() {
        return height;
    }

    public void setHeight(Range height) {
        this.height = height;
        if (height != null) {
            this.rangesFilters.put(FilterKey.HEIGHT, height);
        }
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<FilterKey, Range> getRangesFilters() {
        return rangesFilters;
    }

    public Map<FilterKey, Boolean> getBooleansFilters() {
        return booleansFilters;
    }

    public boolean thereAreFilters() {
        return !this.booleansFilters.isEmpty() && !this.rangesFilters.isEmpty();
    }
}
