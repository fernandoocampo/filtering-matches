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
import com.affinitas.userfinder.model.UserFilter;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    public UserFinderRestControllerTest() {
    }

    /**
     * Test of rest controller for User finder functionality when a success result.
     * 
     * @throws java.lang.Exception if there is an unexpected error.
     */
    @Test
    public void testSuccessSearch() throws Exception {
        // GIVEN the following given and expected data 
        // just the has photo filter.
        Boolean hasphoto = true;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        SearchVO searchdata = new SearchVO();
        searchdata.setHasPhoto(hasphoto);
        
        // simulates response from dao object.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
                      
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?hasphoto=true").accept(
				MediaType.ALL);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse());
        
        String expected = "{\"message\":null,\"userdata\":[{\"id\":\"1\",\"displayname\":\"Frank Sinatra\",\"age\":45,\"height\":180,\"jobtitle\":\"Singer\",\"mainphoto\":\"http://thecatapi.com/api/images/get?format=src2&type=gif\",\"compatabilityScore\":23.4,\"contactsExchanged\":3,\"religion\":\"Catholic\",\"city\":{\"name\":\"London\",\"lat\":51.509865,\"lon\":-0.118092},\"favourite\":true},{\"id\":\"2\",\"displayname\":\"Paul Anka\",\"age\":45,\"height\":181,\"jobtitle\":\"Singer\",\"mainphoto\":\"http://thecatapi.com/api/images/get?format=src2&type=gif\",\"compatabilityScore\":23.4,\"contactsExchanged\":3,\"religion\":\"Christian\",\"city\":null,\"favourite\":false}]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    
    /**
     * Test if somebody query using a minimum age value not allowed.
     * 
     * @throws Exception 
     */
    @Test
    public void testInvalidMinimumAgeFilter() throws Exception {
        // GIVEN the following given and expected data 
        // just the minimum and mmaximum age.
        int minimumage = 16;
        int maximumage = 34;
        int minimumdefaultagevalue = 18;
        int maximumdefaultagevalue = 95;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        
        // simulates the minimum default age value
        Mockito.when(appconfig.getDefaultMinimumAge()).thenReturn(minimumdefaultagevalue);
        Mockito.when(appconfig.getDefaultMaximumAge()).thenReturn(maximumdefaultagevalue);
        // simulates response from the dao. however it won't never arrive.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?minage=" + minimumage + "&maxage=" + maximumage).accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
        String expected = "{\"message\":\"The given minimum age is not allowed, you must search at least with: 18\",\"userdata\":[]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    
    /**
     * Test if somebody query using a maximum age value not allowed.
     * 
     * @throws Exception 
     */
    @Test
    public void testInvalidMaximumAgeFilter() throws Exception {
        // GIVEN the following given and expected data 
        // just the minimum and mmaximum age.
        int minimumage = 26;
        int maximumage = 1005;
        int minimumdefaultagevalue = 18;
        int maximumdefaultagevalue = 95;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        
        // simulates the minimum default age value
        Mockito.when(appconfig.getDefaultMinimumAge()).thenReturn(minimumdefaultagevalue);
        Mockito.when(appconfig.getDefaultMaximumAge()).thenReturn(maximumdefaultagevalue);
        // simulates response from the dao. however it won't never arrive.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?minage=" + minimumage + "&maxage=" + maximumage).accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
        String expected = "{\"message\":\"The given maximum age is not allowed, you must search at most with: 95\",\"userdata\":[]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    
    /**
     * Test if somebody query using a minimum compatibility score value not allowed.
     * 
     * @throws Exception 
     */
    @Test
    public void testInvalidMinimumCompatibilityScoreFilter() throws Exception {
        // GIVEN the following given and expected data 
        // just the minimum and mmaximum age.
        int minimumcompatibilityscore = -1;
        int maximumcompatibilityscore = 67;
        int minimumdefaultcompatibilityscorevalue = 1;
        int maximumdefaultcompatibilityscorevalue = 99;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        
        // simulates the minimum default age value
        Mockito.when(appconfig.getDefaultMinCompabilityScore()).thenReturn(minimumdefaultcompatibilityscorevalue);
        Mockito.when(appconfig.getDefaultMaxCompabilityScore()).thenReturn(maximumdefaultcompatibilityscorevalue);
        // simulates response from the dao. however it won't never arrive.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?mincompatibilityscore=" + minimumcompatibilityscore + "&maxcompatibilityscore=" + maximumcompatibilityscore).accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
        String expected = "{\"message\":\"The given minimum compatibility score is not allowed, you must search at least with: 1\",\"userdata\":[]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    
    /**
     * Test if somebody query using a maximum compatibility score value not allowed.
     * 
     * @throws Exception 
     */
    @Test
    public void testInvalidMaximumCompatibilityScoreFilter() throws Exception {
        // GIVEN the following given and expected data 
        // just the minimum and mmaximum age.
        int minimumcompatibilityscore = 26;
        int maximumcompatibilityscore = 1005;
        int minimumdefaultcompatibilityscorevalue = 1;
        int maximumdefaultcompatibilityscorevalue = 99;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        
        // simulates the minimum default age value
        Mockito.when(appconfig.getDefaultMinCompabilityScore()).thenReturn(minimumdefaultcompatibilityscorevalue);
        Mockito.when(appconfig.getDefaultMaxCompabilityScore()).thenReturn(maximumdefaultcompatibilityscorevalue);
        // simulates response from the dao. however it won't never arrive.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?mincompatibilityscore=" + minimumcompatibilityscore + "&maxcompatibilityscore=" + maximumcompatibilityscore).accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
        String expected = "{\"message\":\"The given maximum compatibility score is not allowed, you must search at most with: 99\",\"userdata\":[]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    
    /**
     * Test if somebody query using a minimum height value not allowed.
     * 
     * @throws Exception 
     */
    @Test
    public void testInvalidMinimumHeightFilter() throws Exception {
        // GIVEN the following given and expected data 
        // just the minimum and mmaximum age.
        int minimumheight = 100;
        int maximumheight = 180;
        int minimumdefaultheightvalue = 135;
        int maximumdefaultheightvalue = 210;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        
        // simulates the minimum default age value
        Mockito.when(appconfig.getDefaultMinimumHeight()).thenReturn(minimumdefaultheightvalue);
        Mockito.when(appconfig.getDefaultMaximumHeight()).thenReturn(maximumdefaultheightvalue);
        // simulates response from the dao. however it won't never arrive.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?minheight=" + minimumheight + "&maxheight=" + maximumheight).accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
        String expected = "{\"message\":\"The given minimum height is not allowed, you must search at least with: 135 cms\",\"userdata\":[]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    
    /**
     * Test if somebody query using a maximum height value not allowed.
     * 
     * @throws Exception 
     */
    @Test
    public void testInvalidMaximumHeightFilter() throws Exception {
        // GIVEN the following given and expected data 
        // just the minimum and mmaximum age.
        int minimumheight = 160;
        int maximumheight = 340;
        int minimumdefaultheightvalue = 135;
        int maximumdefaultheightvalue = 210;
        
        List<User> users = UserFinderTestUtils.buildUsers();
        
        // simulates the minimum default age value
        Mockito.when(appconfig.getDefaultMinimumHeight()).thenReturn(minimumdefaultheightvalue);
        Mockito.when(appconfig.getDefaultMaximumHeight()).thenReturn(maximumdefaultheightvalue);
        // simulates response from the dao. however it won't never arrive.
        Mockito.when(dao.findUsers(Mockito.any(UserFilter.class))).thenReturn(users);
        
        // WHEN user request a list of users with main photo.
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userfinder?minheight=" + minimumheight + "&maxheight=" + maximumheight).accept(
				MediaType.ALL);
        
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
        String expected = "{\"message\":\"The given maximum height is not allowed, you must search at most with: 210 cms\",\"userdata\":[]}";
        
        // THEN we check that response are equals.
        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
}
