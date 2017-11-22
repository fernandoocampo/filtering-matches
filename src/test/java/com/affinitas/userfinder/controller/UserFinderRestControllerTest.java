/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.controller;

import com.affinitas.userfinder.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;

/**
 * Unit testing for UserFinderRestController.
 * 
 * @author Fernando Ocampo
 */
public class UserFinderRestControllerTest {
    
    public UserFinderRestControllerTest() {
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
     * Test of search method, of class UserFinderRestController.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        Boolean hasphoto = null;
        Boolean incontact = null;
        Boolean isfavourite = null;
        Byte mincompatibilityscore = null;
        Byte maxcompatibilityscore = null;
        Byte minage = null;
        Byte maxage = null;
        Short minheight = null;
        Short maxheight = null;
        Short distance = null;
        UserFinderRestController instance = new UserFinderRestController();
        ResponseEntity<List<User>> expResult = null;
        ResponseEntity<List<User>> result = instance.search(hasphoto, incontact, isfavourite, mincompatibilityscore, maxcompatibilityscore, minage, maxage, minheight, maxheight, distance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
