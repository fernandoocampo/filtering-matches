/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.controller;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.City;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.service.UserFinderService;
import com.affinitas.userfinder.util.AppProperties;
import com.affinitas.userfinder.util.ConfigProperties;
import com.affinitas.userfinder.utils.UserFinderTestUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Testing the rest controller for user finder service.
 *
 * @author Fernando Ocampo
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserFinderRestController.class, secure = false)
public class UserFinderRestControllerTest {

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

    @MockBean
    private UserFinderService service;

    public UserFinderRestControllerTest() {
    }

    /**
     * Test of rest controller for User finder functionality.
     * 
     * @throws java.lang.Exception if there is an unexpected error.
     */
    @Test
    public void testSearch() throws Exception {
        // GIVEN the following given and expected data 
        // just the has photo filter.
        Boolean hasphoto = true;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        SearchVO searchdata = new SearchVO();
        searchdata.setHasPhoto(hasphoto);
        try {
            Mockito.when(service.findUsers(Mockito.any(SearchVO.class))).thenReturn(users);
        } catch (SearchException ex) {
            throw new AssertionError("Unexpected error when execute findUsers on service.",ex);
        }
                      
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?hasphoto=true").accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        
        String expected = "[{\"id\":\"1\",\"displayname\":\"Frank Sinatra\",\"age\":45,\"height\":180,\"jobtitle\":\"Singer\",\"mainphoto\":\"http://thecatapi.com/api/images/get?format=src2&type=gif\",\"compatabilityScore\":23.4,\"contactsExchanged\":3,\"religion\":\"Catholic\",\"city\":{\"name\":\"London\",\"lat\":51.509865,\"lon\":-0.118092},\"favourite\":true},{\"id\":\"2\",\"displayname\":\"Paul Anka\",\"age\":45,\"height\":181,\"jobtitle\":\"Singer\",\"mainphoto\":\"http://thecatapi.com/api/images/get?format=src2&type=gif\",\"compatabilityScore\":23.4,\"contactsExchanged\":3,\"religion\":\"Christian\",\"city\":null,\"favourite\":false}]";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }

}
