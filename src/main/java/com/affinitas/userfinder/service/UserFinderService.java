/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.service;

import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import java.util.List;

/**
 * Defines the behavior of user search.
 * 
 * @author Fernando Ocampo
 */
public interface UserFinderService {
    
    
    /**
     * Search and return a set of users that match the given filters.
     * 
     * @param filter contains the filters to apply in the search.
     * @return set of users that match the given filters.
     * @throws SearchException if there is an error when executing the search.
     */
    List<User> search(UserFilter filter) throws SearchException;
}
