/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao;

import com.affinitas.userfinder.model.SearchException;
import com.affinitas.userfinder.model.User;
import com.affinitas.userfinder.model.UserFilter;
import java.util.List;

/**
 * Defines the Data Access Object behavior for User data.
 *
 * @author Fernando Ocampo
 */
public interface UserDAO {

    /**
     * Returns a set of user registers that match the given parameters.
     * 
     * @param filter used to search in the database.
     * @return a set of user registers that match the given parameters.
     * @throws SearchException if something goes wrong with the communication 
     * with the database.
     */
    List<User> findUsers(UserFilter filter) throws SearchException;
}
