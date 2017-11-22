/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder;

import com.affinitas.userfinder.controller.UserFinderRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the class that starts the user-finder service.
 * 
 * @author Fernando.Ocampo
 */

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = UserFinderRestController.class)
public class UserFinderApplication {    

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserFinderApplication.class, args);
    }
}
