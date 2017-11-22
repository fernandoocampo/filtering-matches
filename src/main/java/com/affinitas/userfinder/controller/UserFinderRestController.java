/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.controller;

import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.service.UserFinderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * It is the rest controller of UserFinder microservice. Here we define the
 * capabilities of the service.
 *
 * @author Fernando Ocampo
 */
@RestController
@RequestMapping("/userfinder")
public class UserFinderRestController {
    
    @Autowired
    UserFinderService userfinderservice;

    /**
     * Implementation of the user search capability for <code>/userfinder</code> 
     * service. It has the following behavior:
     * <p>
     * <ul>
     * <li> 1. Takes all the request parameters which are the filters for searching.
     * <li> 2. Builds an object required for the service layer.
     * <li> 3. Invokes service layer method in charge to execute the search.
     * </ul>
     * <p>
     * If not parameter is sent the capability returns an empty result.
     * 
     * @param hasphoto <code>true</code> if the user must has a photo.
     * @param incontact <code>true</code> if the user must be in contact.
     * @param isfavourite <code>true</code> if the user is a favourite.
     * @param mincompatibilityscore The minimum compability score given.
     * @param maxcompatibilityscore The maximum compability score given.
     * @param minage The minimum user age given.
     * @param maxage The maximum user age given.
     * @param minheight The minimum user height given.
     * @param maxheight The maximum user height given.
     * @param distance Range distance that user should have.
     * 
     * @return A collection of users that match the filters.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> search(@RequestParam(name = "hasphoto") Boolean hasphoto, 
            @RequestParam(name = "incontact", required = false) Boolean incontact,
            @RequestParam(name = "isfavourite", required = false) Boolean isfavourite,
            @RequestParam(name = "mincompatibilityscore", required = false) Byte mincompatibilityscore,
            @RequestParam(name = "maxcompatibilityscore", required = false) Byte maxcompatibilityscore,
            @RequestParam(name = "minage", required = false) Byte minage,
            @RequestParam(name = "maxage", required = false) Byte maxage,
            @RequestParam(name = "minheight", required = false) Short minheight,
            @RequestParam(name = "maxheight", required = false) Short maxheight,
            @RequestParam(name = "distance", required = false) Short distance) {
        List<User> result = new ArrayList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
