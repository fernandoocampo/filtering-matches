/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

/**
 * Indicates that something in the given search filters are not allowed.
 * 
 * e.g. the minimum and maximum value are not in the allowed range.
 * e.g. individual values are invalid.
 * 
 * @author Fernando.Ocampo
 */
public class FilterException extends Exception {

    public FilterException(String message) {
        super(message);
    }
    
}
