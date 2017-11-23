/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.utils;

import com.affinitas.userfinder.model.City;
import com.affinitas.userfinder.model.SearchVO;
import com.affinitas.userfinder.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions for testing this service.
 * 
 * @author Fernando Ocampo
 */
public class UserFinderTestUtils {
    /**
     * @return a hypothetical user data.
     */
    public static User buildUser(byte age, float compatibility, short contacts, 
            String displayname, boolean favourite, short height, String id, 
            String jobtitle, String mainphoto, String religion, City city) {
        User user = new User();
        user.setAge(age);
        user.setCompatabilityScore(compatibility);
        user.setContactsExchanged(contacts);
        user.setDisplayname(displayname);
        user.setFavourite(favourite);
        user.setHeight(height);
        user.setId(id);
        user.setJobtitle(jobtitle);
        user.setMainphoto(mainphoto);
        user.setReligion(religion);
        user.setCity(city);
        
        return user;
    }
    
    /**
     * @return a list of hypothetical users.
     */
    public static List<User> buildUsers() {
        List<User> users = new ArrayList<>();
        String photo1 = "http://thecatapi.com/api/images/get?format=src2&type=gif";
        String photo2 = "http://thecatapi.com/api/images/get?format=src2&type=gif";
        City city = new City("London", (float)51.509865, (float)-0.118092);
        users.add(buildUser((byte)45, (float)23.4, (short) 3, "Frank Sinatra", true, (short)180, 
                "1", "Singer", photo1, "Catholic", city));
        users.add(buildUser((byte)45, (float)23.4, (short) 3, "Paul Anka", false, (short)181, 
                "2", "Singer", photo2, "Christian", null));
        return users;
    }
    
    public static SearchVO buildSearchData(Integer distance, Boolean favourite, Boolean hasphoto,
            Boolean incontact, Integer maxage, Integer maxcompatibilityscore, Integer maxheight,
            Integer minage, Integer mincompatibilityscore, Integer minheight) {
        SearchVO vo = new SearchVO();
        vo.setDistance(distance);
        vo.setFavourite(favourite);
        vo.setHasPhoto(hasphoto);
        vo.setInContact(incontact);
        vo.setIsFavourite(favourite);
        vo.setMaxAge(maxage);
        vo.setMaxCompabilityScore(maxcompatibilityscore);
        vo.setMaxHeight(maxheight);
        vo.setMinAge(minage);
        vo.setMinCompabilityScore(mincompatibilityscore);
        vo.setMinHeight(minheight);
        return vo;
    }
}
