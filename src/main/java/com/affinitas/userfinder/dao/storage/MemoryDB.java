/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.storage;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * It simulates a hypothetical well known robust database uses an array list 
 * to store documents data in memory.
 * 
 * @author Fernando Ocampo
 */
public class MemoryDB {
    
    /**
     * eager singleton reference.
     */
    private static final MemoryDB instance = new MemoryDB();
    
    
    public enum Filter {
        HAS_PHOTO,
        IN_CONTACT,
        FAVOURITE,
        COMPABILITY_SCORE,
        AGE,
        HEIGHT,
        DISTANCE        
    }
    
    /**
     * document collection.
     */
    private final List<Document> collection;
    
    public static MemoryDB getInstance() {
        return instance;
    }
    
    public MemoryDB() {
        collection = new ArrayList();
        initData();
    }
    
    /**
     * Initializes database data.
     */
    private void initData(){
        JSONParser parser = new JSONParser();
        try {
            
            String filepath = this.getClass().getClassLoader().getResource("storage/matches.json").getFile(); 
            
 
            Object obj = parser.parse(new FileReader(
                    filepath));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            JSONArray rowList = (JSONArray) jsonObject.get("matches");
            
            Iterator<Object> iterator = rowList.iterator();
            while (iterator.hasNext()) {
                JSONObject rowObject = (JSONObject) iterator.next();
                collection.add(buildDocument(rowObject));
            }
 
        } catch (Exception e) {
            System.out.println("Database cannot start because: " + e.getMessage());
            // if something goes wrong the application must not run.
            System.exit(0); 
        }
    }
    
    /**
     * Return a set of documents that match the given filters.
     * 
     * @param filters the given filters to search in the collection.
     * @return set of documents that match the given filters.
     */
    public List<Document> findRegisters(Map<String,Object> filters) {
        List<Document> result = new ArrayList<>();
        for (Document doc : collection) {
            if(doc.getMainphoto() != null && !doc.getMainphoto().equals("")) {
                
            }
        }
        return result;
    }
    
    public boolean doesMatch(Document doc, Map<Filter,Object> filters) {
        boolean result = false;
        for(Map.Entry<Filter,Object> entry : filters.entrySet()) {
            switch(entry.getKey()) {
                
            }
        }
        return result;
    }
    
    public Document buildDocument(JSONObject jsonobj) {
        Document doc = new Document();
        doc.setDisplayname((String)jsonobj.get("display_name"));
        doc.setAge((byte)((long)jsonobj.get("age")));
        doc.setHeight((short)((long)jsonobj.get("height_in_cm")));
        doc.setCompatabilityScore((float)((double)jsonobj.get("compatibility_score")));
        doc.setContactsExchanged((short)((long)jsonobj.get("contacts_exchanged")));
        doc.setJobtile((String)jsonobj.get("job_title"));
        doc.setMainphoto((String)jsonobj.get("main_photo"));
        doc.setReligion((String)jsonobj.get("religion"));
        doc.setFavourite((Boolean)jsonobj.get("favourite"));
        // here go city data
        JSONObject jsoncity = (JSONObject) jsonobj.get("city");
        doc.setCityname((String)jsoncity.get("name"));
        doc.setLat((float)((double)jsoncity.get("lat")));
        doc.setLon((float)((double)jsoncity.get("lon")));
        
        return doc;
    }
    
}
