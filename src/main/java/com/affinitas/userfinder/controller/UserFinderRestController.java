/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.controller;

import com.affinitas.userfinder.model.FilterException;
import com.affinitas.userfinder.model.Result;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.service.UserFinderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    UserFinderService service;

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
     * @param distanceinkm distance in km of users to search.
     * @param inquirerlongitude inquirer longitude.
     * @param inquirerlatitude inquirer latitude.
     * @param distancelowerbound true if it is a minimum distance.
     * 
     * @return A collection of users that match the filters.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Result> search(
            @RequestParam(name = "hasphoto", required = false) Boolean hasphoto, 
            @RequestParam(name = "incontact", required = false) Boolean incontact,
            @RequestParam(name = "isfavourite", required = false) Boolean isfavourite,
            @RequestParam(name = "mincompatibilityscore", required = false) Integer mincompatibilityscore,
            @RequestParam(name = "maxcompatibilityscore", required = false) Integer maxcompatibilityscore,
            @RequestParam(name = "minage", required = false) Integer minage,
            @RequestParam(name = "maxage", required = false) Integer maxage,
            @RequestParam(name = "minheight", required = false) Integer minheight,
            @RequestParam(name = "maxheight", required = false) Integer maxheight,
            @RequestParam(name = "distanceinkm", required = false) Integer distanceinkm,
            @RequestParam(name = "inquirerlongitude", required = false) Double inquirerlongitude,
            @RequestParam(name = "inquirerlatitude", required = false) Double inquirerlatitude,
            @RequestParam(name = "distancelowerbound", required = false) Boolean distancelowerbound) {
        Result response = new Result();
        List<User> userresult = new ArrayList();
        
        // build the search filter 
        SearchVO parameters = new SearchVO();
        
        parameters.setFavourite(isfavourite);
        parameters.setHasPhoto(hasphoto);
        parameters.setInContact(incontact);
        parameters.setIsFavourite(isfavourite);
        parameters.setMaxAge(maxage);
        parameters.setMaxCompabilityScore(maxcompatibilityscore);
        parameters.setMaxHeight(maxheight);
        parameters.setMinAge(minage);
        parameters.setMinCompabilityScore(mincompatibilityscore);
        parameters.setMinHeight(minheight);
        parameters.setDistance(distanceinkm);
        parameters.setInquirerlongitude(inquirerlongitude);
        parameters.setInquirerlatitude(inquirerlatitude);
        parameters.setDistancelowerbound(distancelowerbound);
        
        try {
            userresult = service.findUsers(parameters);
            response.setUserdata(userresult);
        } catch (SearchException ex) {
            response.setMessage(ex.getMessage());
            response.setUserdata(userresult);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FilterException fex) {
            response.setMessage(fex.getMessage());
            response.setUserdata(userresult);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
