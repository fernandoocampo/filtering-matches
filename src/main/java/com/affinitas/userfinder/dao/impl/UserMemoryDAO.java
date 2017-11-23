/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.impl;

import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.dao.UserDAO;
import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import java.util.List;

/**
 * Implements {@link UserDAO} using the {@link MemoryDB} database to search
 * users.
 * 
 * @author Fernando Ocampo
 */
public class UserMemoryDAO implements UserDAO {

    /**
     * @see UserDAO#findUsers(com.affinitas.userfinder.model.UserFilter) 
     */
    @Override
    public List<User> findUsers(UserFilter filter) throws SearchException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
