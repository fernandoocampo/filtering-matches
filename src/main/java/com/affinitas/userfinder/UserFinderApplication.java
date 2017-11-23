/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder;

import com.affinitas.userfinder.controller.UserFinderRestController;
import com.affinitas.userfinder.dao.impl.UserMongoDAO;
import com.affinitas.userfinder.service.UserFinderService;
import com.affinitas.userfinder.util.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This is the class that starts the user-finder service.
 * 
 * @author Fernando.Ocampo
 */
//@Configuration
//@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackageClasses = {UserFinderRestController.class, ConfigProperties.class, UserFinderService.class, UserMongoDAO.class})
public class UserFinderApplication {    

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserFinderApplication.class, args);
    }
}
