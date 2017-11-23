/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.service;

import com.affinitas.userfinder.model.FilterException;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import java.util.List;

/**
 * Contains the business logic to search users that match the search data.
 * 
 * @author Fernando.Ocampo
 */
public interface UserFinderService {
    /**
     * find users that match the given search data. It is in charge to call
     * the data repository to query users with the given filters.
     * 
     * @param searchData parameters for the search.
     * @return a set of users that match the given search data.
     * @throws FilterException if the given search data is not allowed.
     * @throws SearchException if something is wrong with the search.
     */
    List<User> findUsers(SearchVO searchData) throws FilterException, SearchException;
}
