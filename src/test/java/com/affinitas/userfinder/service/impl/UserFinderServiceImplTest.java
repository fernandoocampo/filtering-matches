/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.service.impl;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import com.affinitas.userfinder.service.UserFinderService;
import com.affinitas.userfinder.util.AppProperties;
import com.affinitas.userfinder.util.ConfigProperties;
import com.affinitas.userfinder.utils.UserFinderTestUtils;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test the service object.
 * 
 * @author Fernando Ocampo
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserFinderServiceImpl.class, secure = false)
public class UserFinderServiceImplTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    MongoTemplate mongoTemplate;
    
    @MockBean
    ConfigProperties globalconfig;
    
    @MockBean
    AppProperties appconfig;
    
    @MockBean
    UserDAO dao;
    
    @Autowired
    UserFinderService service;
    
    public UserFinderServiceImplTest() {
    }

    /**
     * Test of findUsers method, of class UserFinderServiceImpl.
     */
    @Test
    public void testFindUsers() throws Exception {
        // initialitates the data to simulate the dao object.
        SearchVO searchdata = UserFinderTestUtils.buildSearchData(200, true, true, true, null, null, null, null, null, null);
        List<User> users = UserFinderTestUtils.buildUsers();
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN controller consumes find users on service layer to get those 
        // objects that match the given filters
        List<User> result = service.findUsers(searchdata);
        
        // THEN we check if result is the expected.
        assertEquals(result.size(), 2);
        for(int i=0; i < result.size(); i++) {
            assertTrue(result.get(i).equals(users.get(i)));
        }
    }
    
}
