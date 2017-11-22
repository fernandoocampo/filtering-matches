/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinitas.userfinder.dao.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * It simulates a hypothetical database uses an array list to store documents 
 * data in memory.
 * 
 * @author Fernando Ocampo
 */
public class MemoryDB {
    /**
     * document collection.
     */
    private final List<Document> collection;
    
    public MemoryDB() {
        collection = new ArrayList();
    }
    
    /**
     * Return a set of documents that match the given filters.
     * 
     * @return set of documents that match the given filters.
     */
    public List<Document> findRegisters() {
        return collection;
    }
    
    public void doBulkLoad() {
        
    }
    
}
