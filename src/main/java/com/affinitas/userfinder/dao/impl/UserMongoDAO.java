/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.impl;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.Range;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation using Mongo for {@link UserDAO} .
 *
 * @author Fernando.Ocampo
 */
@Repository
@Qualifier("UserMongoDAO")
public class UserMongoDAO implements UserDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * @see UserDAO#findUsers(com.affinitas.userfinder.model.UserFilter)
     */
    @Override
    public List<User> findUsers(UserFilter filter) throws SearchException {
        // if there is not a filter an empty array must be returned.
        if (filter == null || !filter.areThereFilters()) {
            return new ArrayList<>();
        }
        Query query = new Query();

        // build criteria using boolean filters for custom query
        buildBooleansFilters(filter, query);
        // build criteria using filters that are ranges for custom query
        buildRangesFilters(filter, query);

        // if there is localization filter we must make the query in a different way.
        List<User> result;

        try {
            if (filter.isThereDistanceFilter()) {
                result = findUsersUsingGeolocalization(filter, query);
            } else {
                result = findUsersUsingNormalQuery(query);
            }
        } catch (Exception ex) {
            // don't propagate the same exception and just print it in the log.
            Logger.getLogger(UserMongoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new SearchException("Something goes wrong with the search.");
        }

        return result;
    }

    /**
     * Executes a normal <code>Query</code> to search users.
     *
     * @param query the query that contains criteria.
     * @return a set of users that match the given filters.
     */
    private List<User> findUsersUsingNormalQuery(Query query) {
        return mongoTemplate.find(query, User.class, "users");
    }

    /**
     * If the query has distance filter, we must create a geo query using
     * geonear and to this geonear the criteria query is added.
     *
     * @param filter Data to search the users.
     * @param query the query that contains criteria different to distance.
     * @return a set of users that match the given filters.
     */
    private List<User> findUsersUsingGeolocalization(UserFilter filter, Query query) {
        List<User> users = new ArrayList<>();
        
        NearQuery nearquery = NearQuery.near(new Point(filter.getLongitude(), filter.getLatitude())).
                        spherical(true);
        
        switch(filter.getDistanceLogic()) {
            case GT:
                nearquery.maxDistance(new Distance(filter.getDistance(), Metrics.KILOMETERS));
                break;
            case LT:
            default:
                nearquery.minDistance(new Distance(filter.getDistance(), Metrics.KILOMETERS));
                break;
        }
        nearquery.query(query);

        GeoResults<User> result = mongoTemplate.geoNear(nearquery, User.class, "users");
        if (result != null) {
            for (GeoResult<User> geouser : result) {
                users.add(geouser.getContent());
            }
        }
        return users;
    }

    /**
     * Feeds the given query with filters which are booleans. In other words
     * that match an exactly value.
     *
     * @param filter The filters given for the query.
     * @param query The query to build.
     */
    private void buildBooleansFilters(UserFilter filter, Query query) {
        for (Map.Entry<UserFilter.FilterKey, Boolean> filteritem : filter.getBooleansFilters().entrySet()) {
            switch (filteritem.getKey()) {
                case FAVOURITE:
                    query.addCriteria(Criteria.where("favourite").is(filter.getIsFavourite()));
                    break;
                case HAS_PHOTO:
                    query.addCriteria(Criteria.where("main_photo").exists(filter.getHasPhoto()).ne(""));
                    break;
                case IN_CONTACT:
                    query.addCriteria(Criteria.where("contacts_exchanged").gt(1));
                    break;
            }
        }
    }

    /**
     * Feeds the given query with filters which are ranges. In other words add
     * greater or equals than and less or equals than the given filter.
     *
     * @param filter The filters given for the query.
     * @param query The query to build.
     */
    private void buildRangesFilters(UserFilter filter, Query query) {
        for (Map.Entry<UserFilter.FilterKey, Range> filteritem : filter.getRangesFilters().entrySet()) {
            switch (filteritem.getKey()) {
                case AGE:
                    buildRangeQuery(query, "age", filteritem.getValue());
                    break;
                case COMPABILITY_SCORE:
                    buildRangeQuery(query, "compatibility_score", filteritem.getValue());
                    break;
                case DISTANCE:
                    break;
                case HEIGHT:
                    buildRangeQuery(query, "height_in_cm", filteritem.getValue());
                    break;
            }
        }
    }

    /**
     * Add to the given query a criteria using gte and lte range.
     *
     * @param query The spring mongo query to add a criteria.
     * @param fieldname the name of the field to use with the criteria.
     * @param range The criteria range.
     */
    private void buildRangeQuery(Query query, String fieldname, Range range) {
        if (!range.isEmpty()) {
            query.addCriteria(Criteria.where(fieldname).gte(range.getMinimum()).lte(range.getMaximum()));
        }

    }
}
