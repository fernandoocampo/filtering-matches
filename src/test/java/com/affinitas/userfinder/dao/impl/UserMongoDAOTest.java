/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.impl;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import com.affinitas.userfinder.utils.UserFinderTestUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Unit test for {@link com.affinitas.userfinder.dao.UserDAO} implementation.
 * 
 * @author Fernando.Ocampo
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMongoDAOTest {
    
    @SpyBean
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private UserDAO dao;
    
    public UserMongoDAOTest() {
    }

    /**
     * Test of findUsers method, of class UserMongoDAO. It just checks that 
     * dao logic follow an specific expected flow. It must call the 
     * mongoTemplate.find() method.
     */
    @Test
    public void testFindUsersWithHasPhotoFilter() throws Exception {
        System.out.println("testFindUsersWithHasPhotoFilter");
        // GIVEN a filter to search in mongo repository.
        UserFilter filter = new UserFilter();
        filter.setHasPhoto(true);
        // simulated value returned by query.find.
        List<User> queryresult = UserFinderTestUtils.buildUsers();
        // exptected result from mongotemplate.find
        List<User> expResult = UserFinderTestUtils.buildUsers();
        
        // if the query.find is called it will return the queryresult
        doReturn(queryresult).when(mongoTemplate).find(Mockito.any(Query.class), eq(User.class), eq("users"));
        // WHEN the service sends a filter and wants to query users in mongo.
        List<User> result = dao.findUsers(filter);
        
        verify(mongoTemplate).find(Mockito.any(Query.class), eq(User.class), eq("users"));
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findUsers method, of class UserMongoDAO. It just checks that 
     * dao logic follow an specific expected flow. It must call the 
     * mongoTemplate.geoNear() method.
     */
    @Test
    public void testFindUsersWithDistanceFilter() throws Exception {
        System.out.println("testFindUsersWithDistanceFilter");
        // GIVEN a filter to search in mongo repository.
        UserFilter filter = new UserFilter();
        int distance = 500;
        filter.setDistance(distance);
        filter.setDistanceLogic(UserFilter.DistanceLogic.GT);
        filter.setLatitude(1.89445);
        filter.setLongitude(0.7654);
        // simulated value returned by query.find.
        List<User> queryresult = UserFinderTestUtils.buildUsers();
        List<GeoResult<User>> georesults = new ArrayList<>();
        georesults.add(new GeoResult<>(queryresult.get(0),new Distance(distance)));
        georesults.add(new GeoResult<>(queryresult.get(1),new Distance(distance)));
        GeoResults<User> georesult = new GeoResults<>(georesults);
        // exptected result from mongotemplate.find
        List<User> expResult = UserFinderTestUtils.buildUsers();
        
        // if the query.find is called it will return the queryresult
        doReturn(georesult).when(mongoTemplate).geoNear(Mockito.any(NearQuery.class), eq(User.class), eq("users"));
        // WHEN the service sends a filter and wants to query users in mongo.
        List<User> result = dao.findUsers(filter);
        
        verify(mongoTemplate).geoNear(Mockito.any(NearQuery.class), eq(User.class), eq("users"));
        
        assertEquals(expResult, result);
    }
    
}
