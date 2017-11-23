/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Contains the properties of the business logic of the application.
 * 
 * @author Fernando.Ocampo
 */
@Configuration
@ConfigurationProperties("application.data")
@PropertySource("classpath:application.properties")
public class AppProperties {
    private Integer defaultMinCompabilityScore;
    private Integer defaultMaxCompabilityScore;
    private Integer defaultMinimumAge;
    private Integer defaultMaximumAge;
    private Integer defaultMinimumHeight;
    private Integer defaultMaximumHeight;
    private Integer defaultMinimumDistance;
    private Integer defaultMaximumDistance;

    public Integer getDefaultMinCompabilityScore() {
        return defaultMinCompabilityScore;
    }

    public void setDefaultMinCompabilityScore(Integer defaultMinCompabilityScore) {
        this.defaultMinCompabilityScore = defaultMinCompabilityScore;
    }

    public Integer getDefaultMaxCompabilityScore() {
        return defaultMaxCompabilityScore;
    }

    public void setDefaultMaxCompabilityScore(Integer defaultMaxCompabilityScore) {
        this.defaultMaxCompabilityScore = defaultMaxCompabilityScore;
    }

    public Integer getDefaultMinimumAge() {
        return defaultMinimumAge;
    }

    public void setDefaultMinimumAge(Integer defaultMinimumAge) {
        this.defaultMinimumAge = defaultMinimumAge;
    }

    public Integer getDefaultMaximumAge() {
        return defaultMaximumAge;
    }

    public void setDefaultMaximumAge(Integer defaultMaximumAge) {
        this.defaultMaximumAge = defaultMaximumAge;
    }

    public Integer getDefaultMinimumHeight() {
        return defaultMinimumHeight;
    }

    public void setDefaultMinimumHeight(Integer defaultMinimumHeight) {
        this.defaultMinimumHeight = defaultMinimumHeight;
    }

    public Integer getDefaultMaximumHeight() {
        return defaultMaximumHeight;
    }

    public void setDefaultMaximumHeight(Integer defaultMaximumHeight) {
        this.defaultMaximumHeight = defaultMaximumHeight;
    }

    public Integer getDefaultMinimumDistance() {
        return defaultMinimumDistance;
    }

    public void setDefaultMinimumDistance(Integer defaultMinimumDistance) {
        this.defaultMinimumDistance = defaultMinimumDistance;
    }

    public Integer getDefaultMaximumDistance() {
        return defaultMaximumDistance;
    }

    public void setDefaultMaximumDistance(Integer defaultMaximumDistance) {
        this.defaultMaximumDistance = defaultMaximumDistance;
    }
}
