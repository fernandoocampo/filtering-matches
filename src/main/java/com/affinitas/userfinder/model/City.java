/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

/**
 * Contains city data.
 * 
 * @author Fernando Ocampo
 */
public class City {
    /**
     * name of the city.
     */
    private String name;
    /**
     * latitude of the user position.
     */
    private Float lat;
    /**
     * longitude of the user position.
     */
    private Float lon;

    public City() {
    }

    public City(String name, Float lat, Float lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
