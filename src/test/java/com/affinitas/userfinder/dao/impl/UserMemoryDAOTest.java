/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.impl;

import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit testing for UserDAO basic implementation: 
 * 
 * @author Fernando Ocampo
 */
public class UserMemoryDAOTest {
    
    public UserMemoryDAOTest() {
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
     * Test of findUsers method, of class UserMemoryDAO.
     */
    @Test
    public void testFindUsers() throws Exception {
        System.out.println("findUsers");
        UserFilter filter = null;
        UserMemoryDAO instance = new UserMemoryDAO();
        List<User> expResult = null;
        List<User> result = instance.findUsers(filter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
