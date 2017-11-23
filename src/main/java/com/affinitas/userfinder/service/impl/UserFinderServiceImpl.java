/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.service.impl;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.Range;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import com.affinitas.userfinder.service.UserFinderService;
import com.affinitas.userfinder.util.AppProperties;
import com.affinitas.userfinder.util.ConfigProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements {@link UserFinderService} to build a special filter with booleans
 * values and range values to facilitate the job of the DAO.
 *
 * @author Fernando.Ocampo
 */
@Service
public class UserFinderServiceImpl implements UserFinderService {

    @Autowired
    UserDAO dao;
    
    @Autowired
    ConfigProperties globalconfig;
    
    @Autowired
    AppProperties appconfig;

    /**
     * Returns a set of users that match the given search data. First build the
     * filter according to the given parameter. It uses a data access object to
     * query the data repository.
     *
     * @param searchdata Given parameters to build the filter required by the
     * DAO.
     * @return a set of users that match the given search data.
     */
    @Override
    public List<User> findUsers(SearchVO searchdata) {
        List<User> result;
        UserFilter filter = buildUserFilter(searchdata);
        if (filter != null) {
            try {
                result = dao.findUsers(filter);
            } catch (SearchException ex) {
                Logger.getLogger(UserFinderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                result = new ArrayList<>();
            }
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * build a {@link UserFilter} from a given {@link SearchVO} or null if the
     * given parameter is empty.
     *
     * @param searchdata search data value given for any client.
     * @return a user filter or null if the given parameter is empty.
     */
    private UserFilter buildUserFilter(SearchVO searchdata) {
        if (searchdata == null) {
            return null; // there is nothing to query.
        }
        UserFilter filter = new UserFilter();
        Range agerange = new Range(searchdata.getMinAge(), searchdata.getMaxAge());
        System.out.println("config----------->: " + appconfig.getDefaultMaximumAge());
        agerange.setDefaultMinimum(appconfig.getDefaultMinimumAge());
        agerange.setDefaultMaximum(appconfig.getDefaultMaximumAge());
        filter.setAge(agerange );
        
        Range heightrange = new Range(searchdata.getMinHeight(), searchdata.getMaxHeight());
        heightrange.setDefaultMinimum(appconfig.getDefaultMinimumHeight());
        heightrange.setDefaultMaximum(appconfig.getDefaultMaximumHeight());
        filter.setHeight(heightrange);
        
        Range compabilityscorerange = new Range(searchdata.getMinCompabilityScore(), searchdata.getMaxCompabilityScore());
        compabilityscorerange.setDefaultMinimum(appconfig.getDefaultMinCompabilityScore());
        compabilityscorerange.setDefaultMaximum(appconfig.getDefaultMaxCompabilityScore());
        filter.setCompabilityScore(compabilityscorerange);

        filter.setDistance(searchdata.getDistance());
        filter.setHasPhoto(searchdata.getHasPhoto());
        filter.setInContact(searchdata.getInContact());
        filter.setIsFavourite(searchdata.getIsFavourite());
        return filter;
    }

}
