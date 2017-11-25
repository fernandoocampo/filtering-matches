/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.service.impl;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.Range;
import com.affinitas.userfinder.model.FilterException;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import com.affinitas.userfinder.model.UserFilter.DistanceLogic;
import com.affinitas.userfinder.service.UserFinderService;
import com.affinitas.userfinder.util.AppProperties;
import com.affinitas.userfinder.util.ConfigProperties;
import java.util.ArrayList;
import java.util.List;
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
     * @throws SearchException if there is an error on data validation or
     * something is wrong when the dao is invoked.
     * @throws FilterException if the given filters for search are invalid. e.g.
     * the ranges are not allowed.
     */
    @Override
    public List<User> findUsers(SearchVO searchdata) throws FilterException, SearchException {
        List<User> result;
        // check that the given values are allowed.
        validateFiltersRanges(searchdata);
        // check if the distance value is valid. just if the distance is given.
        validateDistanceFilter(searchdata);
        UserFilter filter = buildUserFilter(searchdata);
        if (filter != null) {
            result = dao.findUsers(filter);
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
        if (searchdata.getMinAge() != null || searchdata.getMaxAge() != null) {
            Range agerange = new Range(searchdata.getMinAge(), searchdata.getMaxAge());
            agerange.setDefaultMinimum(appconfig.getDefaultMinimumAge());
            agerange.setDefaultMaximum(appconfig.getDefaultMaximumAge());
            filter.setAge(agerange);
        }

        if (searchdata.getMinHeight() != null || searchdata.getMaxHeight() != null) {
            Range heightrange = new Range(searchdata.getMinHeight(), searchdata.getMaxHeight());
            heightrange.setDefaultMinimum(appconfig.getDefaultMinimumHeight());
            heightrange.setDefaultMaximum(appconfig.getDefaultMaximumHeight());
            filter.setHeight(heightrange);
        }

        if (searchdata.getMinCompabilityScore() != null || searchdata.getMaxCompabilityScore() != null) {
            Range compabilityscorerange = new Range(searchdata.getMinCompabilityScore(), searchdata.getMaxCompabilityScore());
            compabilityscorerange.setDefaultMinimum(appconfig.getDefaultMinCompabilityScore());
            compabilityscorerange.setDefaultMaximum(appconfig.getDefaultMaxCompabilityScore());
            filter.setCompabilityScore(compabilityscorerange);
        }

        filter.setHasPhoto(searchdata.getHasPhoto());
        filter.setInContact(searchdata.getInContact());
        filter.setIsFavourite(searchdata.getIsFavourite());

        // position filters
        filter.setDistance(searchdata.getDistance());
        filter.setLongitude(searchdata.getInquirerlongitude());
        filter.setLatitude(searchdata.getInquirerlatitude());

        if (searchdata.isDistancelowerbound() != null && searchdata.isDistancelowerbound()) {
            filter.setDistanceLogic(DistanceLogic.LT);
        } else {
            filter.setDistanceLogic(DistanceLogic.GT);
        }
        return filter;
    }

    /**
     * Validate that the filters that contains ranges don't exceed the minimum
     * and maximum values allowed.
     *
     * @param searchdata the given data for user searching.
     * @throws FilterException if some ranges exceed the allowed values.
     */
    void validateFiltersRanges(SearchVO searchdata) throws FilterException {
        if (searchdata == null) {
            return; // nothing to validate.
        }
        // check the minimum and maximum age
        if (searchdata.getMinAge() != null && searchdata.getMinAge() < appconfig.getDefaultMinimumAge()) {
            throw new FilterException("The given minimum age is not allowed, you must search at least with: "
                    + appconfig.getDefaultMinimumAge());
        }

        if (searchdata.getMaxAge() != null && searchdata.getMaxAge() > appconfig.getDefaultMaximumAge()) {
            throw new FilterException("The given maximum age is not allowed, you must search at most with: "
                    + appconfig.getDefaultMaximumAge());
        }
        // check the minumum and mmaximum height 
        if (searchdata.getMinHeight() != null && searchdata.getMinHeight() < appconfig.getDefaultMinimumHeight()) {
            throw new FilterException("The given minimum height is not allowed, you must search at least with: "
                    + appconfig.getDefaultMinimumHeight() + " cms");
        }

        if (searchdata.getMaxHeight() != null && searchdata.getMaxHeight() > appconfig.getDefaultMaximumHeight()) {
            throw new FilterException("The given maximum height is not allowed, you must search at most with: "
                    + appconfig.getDefaultMaximumHeight() + " cms");
        }

        // check the minumum and mmaximum compatibility. 
        if (searchdata.getMinCompabilityScore() != null
                && searchdata.getMinCompabilityScore() < appconfig.getDefaultMinCompabilityScore()) {
            throw new FilterException("The given minimum compatibility score is not allowed, you must search at least with: "
                    + appconfig.getDefaultMinCompabilityScore());
        }

        if (searchdata.getMaxCompabilityScore() != null
                && searchdata.getMaxCompabilityScore() > appconfig.getDefaultMaxCompabilityScore()) {
            throw new FilterException("The given maximum compatibility score is not allowed, you must search at most with: "
                    + appconfig.getDefaultMaxCompabilityScore());
        }

    }

    /**
     * Validate if the given distance in km in the parameter has a valid value.
     * At this release the a number less than 1 is considered invalid.
     *
     * @param searchdata filter data for the search.
     * @throws FilterException if the distance in km value is not valid.
     */
    void validateDistanceFilter(SearchVO searchdata) throws FilterException {
        if (searchdata != null && searchdata.getDistance() != null
                && searchdata.getDistance() < 1) {
            throw new FilterException("The given distance in km is not a valid distance value, distance must be a value greater than 0");
        }
    }

}
