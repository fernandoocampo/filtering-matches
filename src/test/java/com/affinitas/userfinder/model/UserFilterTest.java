/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.model;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luisfer
 */
public class UserFilterTest {
    
    public UserFilterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of UserFilter model functionality.
     */
    @Test
    public void testUserFilters() {
        System.out.println("testUserFilters");
        UserFilter filter = new UserFilter();
        
        // GIVEN the following data for a filter.
        boolean hasphoto = true;
        int minimumage = 23;
        int maximumage = 28;
        int minimumheight = 170;
        int maximumheight = 178;
        
        
        // WHEN the system wants to use the filter it asks for the filter map that contains
        // the picked parameters.
        filter.setAge(new Range(minimumage,maximumage));
        filter.setHasPhoto(hasphoto);
        filter.setHeight(new Range(minimumheight, maximumheight));
        
        // THEN we test that the filter is well built.
                
        assertTrue(filter.thereAreFilters());
        assertFalse(filter.getBooleansFilters().isEmpty());
        assertFalse(filter.getRangesFilters().isEmpty());
        assertTrue(filter.getAge().hasMinimumAndMaximum());
        assertTrue(filter.getRangesFilters().size() == 2);
        assertTrue(filter.getBooleansFilters().size() == 1);
    }
    
}
