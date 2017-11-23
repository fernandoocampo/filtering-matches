/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

import java.util.List;

/**
 * Contains information of the result. The result is composed of an array of users
 * and a message is something is wrong.
 * 
 * @author Fernando.Ocampo
 */
public class Result {
    private String message;
    private List<User> userdata;

    public Result() {
    }
    
    public Result(String message, List<User> userdata) {
        this.message = message;
        this.userdata = userdata;
    }
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<User> userdata) {
        this.userdata = userdata;
    }
}
